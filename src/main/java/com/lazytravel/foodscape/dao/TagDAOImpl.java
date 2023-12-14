package com.lazytravel.foodscape.dao;

import java.util.List;

import org.hibernate.Session;

import com.lazytravel.foodscape.entity.FoodScape;
import com.lazytravel.foodscape.entity.Tag;
import com.lazytravel.util.HibernateUtil;

public class TagDAOImpl implements TagDAO{
	
	private TagDAO dao;
	
	public TagDAOImpl() {
		dao = new TagDAOImpl();
	}

	@Override
	public int insert(Tag tag) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(tag);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
	}

	@Override
	public int update(Tag tag) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(tag);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
		
	}

	@Override
	public Tag getByPK(Integer tagId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Tag tag = session.get(Tag.class, tagId);
			session.getTransaction().commit();
			return tag;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null ;
	}

	@Override
	public List<Tag> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<Tag> list = session.createQuery("from Tag", Tag.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<Tag> getAll(int currentPage) {

		return dao.getAll(currentPage);
	}


	

}
