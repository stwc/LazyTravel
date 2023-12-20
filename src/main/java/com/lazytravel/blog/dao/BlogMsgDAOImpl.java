package com.lazytravel.blog.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lazytravel.blog.entity.Blog;
import com.lazytravel.blog.entity.BlogMsg;
import com.lazytravel.util.HibernateUtil;

public class BlogMsgDAOImpl implements BlogMsgDAO {
	
	private SessionFactory factory;

	
	 public BlogMsgDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer add(BlogMsg blogMsg) {
		Transaction transaction = null;
        Integer blogMsgId = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            blogMsgId = (Integer) session.save(blogMsg);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return blogMsgId;
	}

	@Override
	public Integer update(BlogMsg blogmsg) {
		Transaction transaction = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            session.update(blogmsg);
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
	public Integer delete(Integer blogmsgId) {
		Transaction transaction = null;
		BlogMsg blogmsg =null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            blogmsg = session.get(BlogMsg.class, blogmsgId);
            session.delete(blogmsg);
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
	public BlogMsg getByPK(Integer blogmsgId) {
		Transaction transaction = null;
		BlogMsg blogmsg = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            blogmsg = session.get(BlogMsg.class, blogmsgId);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return blogmsg;
	}

	@Override
	public List<BlogMsg> gatAll() {
		Transaction transaction = null;
        List<BlogMsg> blogmsgs = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            blogmsgs = session.createQuery("from BlogMsg", BlogMsg.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return blogmsgs;
	}
	
}
