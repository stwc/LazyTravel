package com.lazytravel.blog.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lazytravel.blog.entity.BlogCl;
import com.lazytravel.util.HibernateUtil;

public class BlogClDAOImpl implements BlogClDAO {

	private SessionFactory factory;

	public BlogClDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	public int insert(BlogCl entity) {
		return (Integer) getSession().save(entity);
	}

	@Override
	public int delete(Integer blogClId) {
		BlogCl blogCl = getSession().get(BlogCl.class, blogClId);
		if (blogCl != null) {
			getSession().delete(blogCl);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}

	}

	@Override
	public BlogCl getByPK(Integer cutomerId) {
		return getSession().get(BlogCl.class,cutomerId );
	}
}