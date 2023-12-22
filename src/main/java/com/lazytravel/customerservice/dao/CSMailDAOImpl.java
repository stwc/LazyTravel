package com.lazytravel.customerservice.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;


import com.lazytravel.customerservice.entity.CSMail;
import com.lazytravel.util.HibernateUtil;



public  class CSMailDAOImpl implements CSMailDAO{
private SessionFactory factory;
	
	public  CSMailDAOImpl() {
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


	}
