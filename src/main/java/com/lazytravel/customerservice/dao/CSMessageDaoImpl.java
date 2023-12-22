package com.lazytravel.customerservice.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lazytravel.customerservice.entity.CSMessage;
import com.lazytravel.util.HibernateUtil;

public  class CSMessageDaoImpl implements CSMessageDAO{
private SessionFactory factory;
	
	public CSMessageDaoImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
	@Override
	public Integer add(CSMessage csMessage) {
		Transaction transaction = null;
        Integer messageId = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            messageId = (Integer) session.save(csMessage);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return messageId;
    }

	@Override
	public  Integer update(CSMessage csMessage) {
		Transaction transaction = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            session.update(csMessage);
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

	public CSMessage getByPK(Integer csMessageId) {
		Transaction transaction = null;
		CSMessage csMessage = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            csMessage = session.get(CSMessage.class, csMessageId);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return csMessage;
	}

	@Override
	public List<CSMessage> getAll() {
		Transaction transaction = null;
        List<CSMessage> csMessages = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            csMessages = session.createQuery("from CSMessage", CSMessage.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return csMessages;

    }


	}