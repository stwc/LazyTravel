//package com.lazytravel.customerservice.dao;
//
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.TypedQuery;
//
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
//import org.hibernate.query.Query;
//
//import com.lazytravel.customerservice.entity.CSMail;
//import com.lazytravel.customerservice.entity.CSMessage;
//import com.lazytravel.util.DateUtil;
//import com.lazytravel.util.HibernateUtil;
//
//public class CSMessageDaoImpl implements CSMessageDAO {
//	private SessionFactory factory;
//
//	public CSMessageDaoImpl() {
//		factory = HibernateUtil.getSessionFactory();
//	}
//
//	private Session getSession() {
//		return factory.getCurrentSession();
//	}
//
//	@Override
//	public Integer add(CSMessage csMessage) {
//		Transaction transaction = null;
//		Integer messageId = null;
//		try {
//			Session session = getSession();
//			transaction = session.beginTransaction();
//			messageId = (Integer) session.save(csMessage);
//			transaction.commit();
//		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
//			e.printStackTrace(); //
//		}
//		return messageId;
//	}
//
//	@Override
//	public Integer update(CSMessage csMessage) {
//		Transaction transaction = null;
//		try {
//			Session session = getSession();
//			transaction = session.beginTransaction();
//			session.update(csMessage);
//			transaction.commit();
//			return 1;
//		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
//			e.printStackTrace(); //
//			return -1;
//		}
//	}
//
//	public CSMessage getByPK(Integer csMessageId) {
//		Transaction transaction = null;
//		CSMessage csMessage = null;
//		try {
//			Session session = getSession();
//			transaction = session.beginTransaction();
//			csMessage = session.get(CSMessage.class, csMessageId);
//			transaction.commit();
//		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
//			e.printStackTrace(); //
//		}
//		return csMessage;
//	}
//
//	@Override
//	public List<CSMessage> getAll() {
//		Transaction transaction = null;
//		List<CSMessage> csMessages = null;
//		try {
//			Session session = getSession();
//			transaction = session.beginTransaction();
//			csMessages = session.createQuery("from CSMessage", CSMessage.class).getResultList();
//			transaction.commit();
//		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
//			e.printStackTrace(); //
//		}
//		return csMessages;
//
//	}
//
//	@Override
//	public List<CSMessage> findByCustomerId(Integer customerId) {
//		Transaction transaction = null;
//		List<CSMessage> csMessagesList = new ArrayList<CSMessage>();
//		try {
//			Session session = getSession();
//			transaction = session.beginTransaction();
//
//			String hql = "SELECT msg.messageId ,msg.content ,msg.createTime ,msg.messageFrom  "
//					+ " FROM CSMail mail INNER JOIN mail.csMessageList msg"
//					+ " WHERE mail.customer.customerId = :customerId";
//
//			Query<Object[]> csMessageQuery = session.createQuery(hql, Object[].class);
//			csMessageQuery.setParameter("customerId", customerId);
//
//			List<Object[]> objectList = csMessageQuery.getResultList();
//
//			for (Object[] row : objectList) {
//				CSMessage csMessage = new CSMessage();
//				csMessage.setMessageId((Integer) row[0]);
//				csMessage.setContent((String) row[1]);
//				csMessage.setCreateTime((Timestamp) row[2]);
//				csMessage.setMessageFrom((String) row[3]);
//
//				Timestamp createTime = (Timestamp) row[2];
//				String createTimeFormate = DateUtil.formate(createTime);
//
//				csMessage.setCreateTimeFormate(createTimeFormate);
//				csMessagesList.add(csMessage);
//			}
//
//			transaction.commit();
//		} catch (Exception e) {
//			if (transaction != null) {
//				transaction.rollback();
//			}
//			e.printStackTrace(); //
//		}
//		return csMessagesList;
//
//	}
//
//	@Override
//	public List<CSMessage> getCSMessageByMailId(Integer mailId) {
//		List<CSMessage> list = new ArrayList<>();
//		Session session = getSession();
//		try {
//			session.beginTransaction();
//			list = session.createQuery("FROM CSMessage WHERE csMail.mailId = :mailId", CSMessage.class)
//					.setParameter("mailId", mailId)
//					.getResultList();
//
//			session.getTransaction().commit();
//		} catch (Exception e) {
//			session.getTransaction().rollback();
//			e.printStackTrace();
//		}
//		return list;
//	}
//}
