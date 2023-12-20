package com.lazytravel.blog.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lazytravel.blog.entity.BlogCl;
import com.lazytravel.blog.entity.BlogMsg;
import com.lazytravel.util.HibernateUtil;

public class BlogClDAOImpl implements BlogClDAO {

	private SessionFactory factory;

	public BlogClDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer add(BlogCl blogCl) {
		Transaction transaction = null;
        Integer blogClId = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            blogClId = (Integer) session.save(blogCl);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return blogClId;
	}

	@Override
	public Integer update(BlogCl blogCl) {
		Transaction transaction = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            session.update(blogCl);
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
	public BlogCl getByPK(Integer blogClId) {
		Transaction transaction = null;
		BlogCl blogCl =null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            blogCl = session.get(BlogCl.class, blogClId);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); //
        }
        return blogCl;
	}

	@Override
	public List<BlogCl> getAll() {
		Transaction transaction = null;
        List<BlogCl> blogCls = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            blogCls = session.createQuery("from BlogCl", BlogCl.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return blogCls;
	}
}