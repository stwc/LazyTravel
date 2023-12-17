package com.lazytravel.foodscape.dao;
import java.util.List;

import org.hibernate.Session;

import com.lazytravel.foodscape.entity.FoodScape;
import com.lazytravel.util.HibernateUtil;


public class FoodScapeDAOImpl implements FoodScapeDAO{
	private FoodScapeDAO dao;
	
	public FoodScapeDAOImpl() {
		dao = new FoodScapeDAOImpl();
	}

	@Override
	public int insert(FoodScape foodscape) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(foodscape);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
		
	}

	@Override
	public int update(FoodScape foodscape) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(foodscape);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
		
		
	}

	@Override
	public FoodScape getByPK(Integer foodScapeId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			FoodScape foodscape = session.get(FoodScape.class, foodScapeId);
			session.getTransaction().commit();
			return foodscape;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null ;
	}

	@Override
	public List<FoodScape> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<FoodScape> list = session.createQuery("from FoodScape", FoodScape.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<FoodScape> getAll(int currentPage) {

		return dao.getAll(currentPage);
	}




}
