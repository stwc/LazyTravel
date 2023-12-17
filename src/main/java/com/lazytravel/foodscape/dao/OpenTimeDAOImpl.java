package com.lazytravel.foodscape.dao;

import java.util.List;

import org.hibernate.Session;

import com.lazytravel.foodscape.entity.FoodScape;
import com.lazytravel.foodscape.entity.OpenTime;
import com.lazytravel.util.HibernateUtil;

public class OpenTimeDAOImpl implements OpenTimeDAO{
	
	private OpenTimeDAO dao;
	
	public OpenTimeDAOImpl() {
		dao = new OpenTimeDAOImpl();
	}

	@Override
	public int insert(OpenTime opentime) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(opentime);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
		
	}

	@Override
	public int update(OpenTime opentime) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(opentime);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
		
	}

	@Override
	public OpenTime getByPK(Integer openTimeId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			OpenTime opentime = session.get(OpenTime.class, openTimeId);
			session.getTransaction().commit();
			return opentime;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null ;
	}

	@Override
	public List<OpenTime> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<OpenTime> list = session.createQuery("from OpenTime", OpenTime.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<OpenTime> getAll(int currentPage) {

		return dao.getAll(currentPage);
	}



}
