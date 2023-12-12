package com.lazytravel.foodscape.dao;

import java.util.List;

import org.hibernate.Session;

import com.lazytravel.foodscape.entity.FoodScape;
import com.lazytravel.foodscape.entity.FoodScapeImg;
import com.lazytravel.util.HibernateUtil;
import com.lazytravel.foodscape.dao.FoodScapeImgDAO;

public class FoodScapeImgDAOImpl implements FoodScapeImgDAO{
	
	private FoodScapeImgDAO dao;
	
	public FoodScapeImgDAOImpl() {
		dao = new FoodScapeImgDAOImpl();
	}

	@Override
	public int insert(FoodScapeImg foodscapeimg) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(foodscapeimg);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;
		
	}

	@Override
	public int update(FoodScapeImg foodscapeimg) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(foodscapeimg);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return -1;

	}

	@Override
	public FoodScapeImg getByPK(Integer imgId, Integer foodScapeId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			FoodScapeImg foodscapeimg = session.get(FoodScapeImg.class, imgId);
			session.getTransaction().commit();
			return foodscapeimg;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null ;
	}

	@Override
	public List<FoodScapeImg> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			List<FoodScapeImg> list = session.createQuery("from FoodScapeImg", FoodScapeImg.class).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public List<FoodScapeImg> getAll(int currentPage) {
		return dao.getAll(currentPage);
	}


}