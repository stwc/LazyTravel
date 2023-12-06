package com.lazytravel.blog.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.lazytravel.blog.entity.Blog;
import com.lazytravel.util.HibernateUtil;

public class BlogDAOImpl implements BlogDAO{
	private SessionFactory factory;
	
	public BlogDAOImpl() {
		factory =HibernateUtil.getSessionFactory();
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public int insert(Blog entity) {
		return(Integer) getSession().save(entity);
	}

	@Override
	public int update(Blog entity) {
		try {
			getSession().update(entity);
			return 1; 
		}catch(Exception e) {
			return -1;
		}
	}

	@Override
	public Blog getByPK(Integer blogId) {
		return getSession().get(Blog.class, blogId);
	}

	@Override
	public List<Blog> getAll() {
		return getSession().createQuery("from blog",Blog.class).list();
	}

}
