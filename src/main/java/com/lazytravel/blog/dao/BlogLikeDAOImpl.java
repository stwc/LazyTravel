package com.lazytravel.blog.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lazytravel.blog.entity.Blog;
import com.lazytravel.blog.entity.BlogLike;
import com.lazytravel.util.HibernateUtil;

public class BlogLikeDAOImpl implements BlogLikeDAO {
	private SessionFactory factory;

	public BlogLikeDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession(){
		return factory.getCurrentSession();
	}

	@Override
	public Integer add(BlogLike blogLike) {
		Transaction transaction = null;
        Integer blogLikeId = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            blogLikeId = (Integer) session.save(blogLike);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return blogLikeId;
	}

	@Override
	public Integer update(BlogLike blogLike) {
		Transaction transaction = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            session.update(blogLike);
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
	public BlogLike getByPK(Integer blogLikeId) {
		Transaction transaction = null;
        BlogLike blogLike = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            blogLike = session.get(BlogLike.class, blogLikeId);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return blogLike;
	}

	@Override
	public List<BlogLike> getAll() {
		Transaction transaction = null;
        List<BlogLike> blogLikes = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            blogLikes = session.createQuery("from BlogLike", BlogLike.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return blogLikes;
    }

	
}
