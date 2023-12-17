package com.lazytravel.blog.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lazytravel.blog.entity.Blog;
import com.lazytravel.util.HibernateUtil;

public class BlogDAOImpl implements BlogDAO {
	private SessionFactory factory;

	public BlogDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer add(Blog blog) {
		Transaction transaction = null;
        Integer blogId = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            blogId = (Integer) session.save(blog);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return blogId;
    }

	@Override
	public Integer update(Blog blog) {
		Transaction transaction = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            session.update(blog);
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
	public Blog getByPK(Integer blogId) {
		Transaction transaction = null;
        Blog blog = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            blog = session.get(Blog.class, blogId);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return blog;
	}

	@Override
	public List<Blog> getAll() {
		Transaction transaction = null;
        List<Blog> blogs = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            blogs = session.createQuery("from Blog", Blog.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return blogs;
    }

}
