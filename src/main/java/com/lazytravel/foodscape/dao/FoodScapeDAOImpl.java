package com.lazytravel.foodscape.dao;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.lazytravel.foodscape.entity.FoodScape;
import com.lazytravel.util.HibernateUtil;


public class FoodScapeDAOImpl implements FoodScapeDAO{
	
	private FoodScapeDAO dao;
	private SessionFactory factory;
	
	public FoodScapeDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	private Session getSession() {
		return factory.getCurrentSession();
	}


	@Override
	public void add(FoodScape foodscape) {
		Session session = getSession();
		try {
			session.beginTransaction();
//			foodscape.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
			foodscape.setUpdateTime(new java.sql.Timestamp(System.currentTimeMillis()));
			Integer id = (Integer) session.save(foodscape);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		
	}

	@Override
	public void update(FoodScape foodscape) {
		
		Session session = getSession();
		try {
			session.beginTransaction();
//			foodscape.setCreateTime(existingTag.getCreateTime()); // Set the existing create time
			foodscape.setUpdateTime(new java.sql.Timestamp(System.currentTimeMillis()));
			session.update(foodscape);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		
		
	}

	@Override
	public FoodScape getByPK(Integer foodScapeId) {
		Session session = getSession();
		FoodScape foodscape = null;
		try {
			session.beginTransaction();
			foodscape = getSession().get(FoodScape.class, foodScapeId);
			session.getTransaction().commit();
			return foodscape;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return foodscape ;
	}

	@Override
	public List<FoodScape> getAll() {
		Session session = getSession();
		List<FoodScape> list = null;
		try {
			session.beginTransaction();
			list = session.createQuery("from FoodScape", FoodScape.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return list;
	}

	@Override
	public List<FoodScape> getAll(int currentPage) {

		return dao.getAll(currentPage);
	}


}
