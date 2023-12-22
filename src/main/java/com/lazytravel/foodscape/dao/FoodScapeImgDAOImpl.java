package com.lazytravel.foodscape.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.lazytravel.foodscape.entity.FoodScape;
import com.lazytravel.foodscape.entity.FoodScapeImg;
import com.lazytravel.util.HibernateUtil;
import com.lazytravel.foodscape.dao.FoodScapeImgDAO;

public class FoodScapeImgDAOImpl implements FoodScapeImgDAO{
	
	private FoodScapeImgDAO dao;
	private SessionFactory factory;
	
	public FoodScapeImgDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void add(FoodScapeImg foodscapeimg) {
		Session session = getSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(foodscapeimg);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

		
	}

	@Override
	public void update(FoodScapeImg foodscapeimg) {
		Session session = getSession();
		try {
			session.beginTransaction();
			session.update(foodscapeimg);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}


	}

	@Override
	public FoodScapeImg getByPK(Integer imgId, Integer foodScapeId) {
		Session session = getSession();
		FoodScapeImg foodscapeimg = null;
		try {
			session.beginTransaction();
			foodscapeimg = getSession().get(FoodScapeImg.class, imgId);
			session.getTransaction().commit();
			return foodscapeimg;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return foodscapeimg ;
	}

	@Override
	public List<FoodScapeImg> getAll() {
		Session session = getSession();
		List<FoodScapeImg> list = null;
		try {
			session.beginTransaction();
			list = session.createQuery("from FoodScapeImg", FoodScapeImg.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return list;
	}

	@Override
	public List<FoodScapeImg> getAll(int currentPage) {
		return dao.getAll(currentPage);
	}


}