package com.lazytravel.foodscape.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.lazytravel.foodscape.entity.OpenTime;
import com.lazytravel.util.HibernateUtil;

public class OpenTimeDAOImpl implements OpenTimeDAO{
	
	private OpenTimeDAO dao;
	private SessionFactory factory;
	
	
	public OpenTimeDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void add(OpenTime opentime) {
		Session session = getSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(opentime);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}

	@Override
	public void update(OpenTime opentime) {
		Session session = getSession();
		try {
			session.beginTransaction();
			session.update(opentime);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
	}

	@Override
	public OpenTime getByPK(Integer openTimeId) {
		Session session = getSession();
		OpenTime opentime = null;
		try {
			session.beginTransaction();
			opentime = getSession().get(OpenTime.class, openTimeId);
			session.getTransaction().commit();
			return opentime;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return opentime;
	}

	@Override
	public List<OpenTime> getAll() {
		Session session = getSession();
		List<OpenTime> list = null;
		try {
			session.beginTransaction();
			list = session.createQuery("from OpenTime", OpenTime.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return list;
	}

	@Override
	public List<OpenTime> getAll(int currentPage) {

		return dao.getAll(currentPage);
	}



}
