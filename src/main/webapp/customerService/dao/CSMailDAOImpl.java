package com.lazytravel.customerservice.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.lazytravel.blog.entity.Blog;
import com.lazytravel.customerservice.entity.CSCustomerVO;
import com.lazytravel.customerservice.entity.CSMail;
import com.lazytravel.customerservice.entity.CSMessage;
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
	public List<CSCustomerVO> getCSCustomerList() {
		Transaction transaction = null;
		List<CSCustomerVO> csCustomerList = new ArrayList<CSCustomerVO>();
		try {
			Session session = getSession();
			transaction = session.beginTransaction();
//			String sql = "SELECT csMail.MAIL_ID mailId,csMail.CUSTOMER_ID customerId, "
//					+ " (select CONTENT from cs_message where MAIL_ID = csMail.MAIL_ID order by CREATE_TIME desc limit 1) content, "
//					+ " (select CREATE_TIME from cs_message where MAIL_ID = csMail.MAIL_ID order by CREATE_TIME desc limit 1) createTime, "
//					+ " (select CUSTOMER_NAME from customer where CUSTOMER_ID = csMail.CUSTOMER_ID order by CREATE_TIME desc limit 1) customerName "
//					+ " from cs_mail csMail ";

			StringBuilder sb = new StringBuilder();
			sb.append("select csmsg.CONTENT, csmsg.CREATE_TIME, c.CUSTOMER_NAME, csmsg.MESSAGE_FROM,  csmsg.MAIL_ID ");
			sb.append("from lazytravel.cs_message csmsg ");
			sb.append("join cs_mail csmail on csmsg.MAIL_ID = csmail.MAIL_ID ");
			sb.append("join customer c on c.CUSTOMER_ID = csmail.CUSTOMER_ID ");
//			sb.append("where csmsg.MAIL_ID = '51001'");
			String sql = sb.toString();

			Query<Object[]> query = session.createNativeQuery(sql);
			List<Object[]> objectList = query.getResultList();

			for (Object[] row : objectList) {
				CSCustomerVO csCustomerVO = new CSCustomerVO();
				int idx = 0;
				csCustomerVO.setContent((String) row[0] == null ? "" : (String) row[0]);

				Timestamp createTime = (Timestamp) row[++idx];
				String createTimeFormate = DateUtil.formate(createTime);
				csCustomerVO.setCreateTime(createTimeFormate);

				csCustomerVO.setCustomerName((String) row[++idx]);
				
				Integer i = Character.getNumericValue((char) row[++idx]);
				csCustomerVO.setMessageFrom(i == 1);
				
				csCustomerVO.setMailId(String.valueOf(row[++idx]));

				csCustomerList.add(csCustomerVO);
			}

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace(); //
		}
		return csCustomerList;

	}

	@Override
	public List<CSMail> getCSMailByCustomerId(Integer customerId) {
		List<CSMail> list = new ArrayList<>();
		Session session = getSession();
		try {
			session.beginTransaction();
			list = session.createQuery("FROM CSMail WHERE customer.customerId = :customerId", CSMail.class)
					.setParameter("customerId", customerId).getResultList();
			session.getTransaction().commit();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return list;
	}
}
