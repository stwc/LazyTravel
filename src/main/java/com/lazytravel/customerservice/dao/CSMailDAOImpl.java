package com.lazytravel.customerservice.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.lazytravel.blog.entity.Blog;
import com.lazytravel.customerservice.entity.CSMail;
import com.lazytravel.journey.entity.JourneyDetail;
import com.lazytravel.util.DateUtil;
import com.lazytravel.util.HibernateUtil;

public class CSMailDAOImpl implements CSMailDAO {
	private SessionFactory factory;

	public CSMailDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer add(CSMail csMail) {
		Transaction transaction = null;
		Integer mailId = null;
		try {
			Session session = getSession();
			transaction = session.beginTransaction();
			mailId = (Integer) session.save(csMail);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace(); //
		}
		return mailId;
	}

	@Override
	public Integer update(CSMail csMail) {
		Transaction transaction = null;
		try {
			Session session = getSession();
			transaction = session.beginTransaction();
			session.update(csMail);
			transaction.commit();
			return 1;
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace(); //
			return -1;
		}
	}

	@Override
	public CSMail getByPK(Integer mailId) {
		Transaction transaction = null;
		CSMail csmail = null;
		try {
			Session session = getSession();
			transaction = session.beginTransaction();
			csmail = session.get(CSMail.class, mailId);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace(); //
		}
		return csmail;
	}

	@Override
	public List<CSMail> getAll() {
		Transaction transaction = null;
		List<CSMail> csmails = null;
		try {
			Session session = getSession();
			transaction = session.beginTransaction();
			csmails = session.createQuery("from CSMail", CSMail.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace(); //
		}
		return csmails;

	}

	@Override
	public CSMail findByCustomerId(Integer customerId) {
		Transaction transaction = null;
		CSMail csmail = new CSMail();
		try {
			Session session = getSession();
			transaction = session.beginTransaction();

			Query<CSMail> query = session.createQuery(" FROM CSMail mail WHERE mail.customer.customerId = :customerId");
			query.setParameter("customerId", customerId);

			csmail = (CSMail) query.uniqueResult();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace(); //
		}
		return csmail;
	}


	@Override
	public List<CSMail> getCSMailByCustomerId(Integer customerId) {
		List<CSMail> list = new ArrayList<>();
		Session session = getSession();
		try {
			session.beginTransaction();
			list = session.createQuery("FROM CSMail WHERE customer.customerId = :customerId", CSMail.class)
					.setParameter("customerId", customerId).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}
}
