package com.lazytravel.customerservice.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lazytravel.customerservice.entity.CSImg;
import com.lazytravel.customerservice.entity.CSMail;
import com.lazytravel.util.HibernateUtil;


public  class CSImgDAOImpl implements CSImgDAO{
	private SessionFactory factory;
	
	public  CSImgDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public  Integer add(CSImg csImg) {
		Transaction transaction = null;
        Integer imgId = null;
		try {
			Session session = getSession();
            transaction = session.beginTransaction();
            imgId = (Integer) session.save(csImg);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return imgId;
    }

	@Override
	public Integer update(CSImg csImg) {
		Transaction transaction = null;
		try {
			 Session session = getSession();
	            transaction = session.beginTransaction();
	            session.update(csImg);
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
	public CSImg getByPK(Integer imgId) {
		Transaction transaction = null;
		CSImg csImg = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            csImg = session.get(CSImg.class, imgId);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return csImg;
	}

	@Override
	public List<CSImg> getAll() {
		Transaction transaction = null;
        List<CSImg> csImgs = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            csImgs = session.createQuery("from CSImg", CSImg.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return csImgs;

    }
	}