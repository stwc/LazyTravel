package com.lazytravel.blog.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lazytravel.blog.entity.BlogImg;
import com.lazytravel.blog.entity.BlogMsg;
import com.lazytravel.util.HibernateUtil;


public class BlogImgDAOImpl implements BlogImgDAO {
	private SessionFactory factory;

	public BlogImgDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
    @Override
    public Integer add(BlogImg blogImg) {
    	Transaction transaction = null;
        Integer blogImgId = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            blogImgId = (Integer) session.save(blogImg);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return blogImgId;
    }

    @Override
    public Integer update(BlogImg blogImg) {
    	Transaction transaction = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            session.update(blogImg);
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
    public Integer delete(Integer blogImgId) {
    	Transaction transaction = null;
		BlogImg blogImg =null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            blogImg = session.get(BlogImg.class, blogImgId);
            session.delete(blogImg);
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
    public BlogImg getByPK(Integer blogImgId) {
    	Transaction transaction = null;
		BlogImg blogImg = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            blogImg = session.get(BlogImg.class, blogImgId);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return blogImg;
	}

    @Override
    public List<BlogImg> getAll() {
    	Transaction transaction = null;
        List<BlogImg> blogImgs = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            blogImgs = session.createQuery("from BlogImg", BlogImg.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return blogImgs;
	}
	
}