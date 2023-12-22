package com.lazytravel.foodscape.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;


import com.lazytravel.foodscape.entity.Tag;
import com.lazytravel.util.HibernateUtil;

public class TagDAOImpl implements TagDAO{
	
	private TagDAO dao;
	private SessionFactory factory;
	
	
	public TagDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void add(Tag tag) {
		Session session = getSession();
		try {
			session.beginTransaction();
//			tag.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
			tag.setUpdateTime(new java.sql.Timestamp(System.currentTimeMillis()));
			Integer id = (Integer) session.save(tag);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

	}

	@Override
	public void update(Tag tag) {
		Session session = getSession();
		try {
			session.beginTransaction();
//			tag.setCreateTime(existingTag.getCreateTime()); // Set the existing create time
			tag.setUpdateTime(new java.sql.Timestamp(System.currentTimeMillis()));
			session.update(tag);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		
	}

	@Override
	public Tag getByPK(Integer tagId) {
		Session session = getSession();
		Tag tag = null;
		try {
			session.beginTransaction();
			tag = getSession().get(Tag.class, tagId);
			session.getTransaction().commit();
			return tag;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return tag ;
	}

	@Override
	public List<Tag> getAll() {
		Session session = getSession();
		List<Tag> list = null;
		try {
			session.beginTransaction();
			list = session.createQuery("from Tag", Tag.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return list;
	}

	@Override
	public List<Tag> getAll(int currentPage) {

		return dao.getAll(currentPage);
	}


	

}
