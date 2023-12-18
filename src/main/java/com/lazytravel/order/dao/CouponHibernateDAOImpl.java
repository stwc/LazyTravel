package com.lazytravel.order.dao;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.lazytravel.order.entity.Coupon;
import com.lazytravel.util.HibernateUtil;

public class CouponHibernateDAOImpl implements CouponHibernateDAO {

	private SessionFactory factory;

	public CouponHibernateDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void add(Coupon coupon) {
		Session session = getSession();
		try {
			session.beginTransaction();

			session.save(coupon);

			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		// getSession().save(coupon);
	}

	@Override
	public void update(Coupon coupon) {
		Session session = getSession();
		try {
			session.beginTransaction();
			session.update(coupon);
			session.getTransaction().commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
//		getSession().update(coupon);
	}

	@Override
	public Coupon getCouponByCouponId(Integer couponId) {
		Session session = getSession();
		Coupon coupon = null;
		try {
			session.beginTransaction();
			coupon = session.get(Coupon.class, couponId);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return coupon;
		
//		return getSession().get(Coupon.class, couponId);
	}

	@Override
	public List<Coupon> getAll() {
		Session session = getSession();
		List<Coupon> list = null;
		try {
			session.beginTransaction();
			
			list = session.createQuery("from Coupon", Coupon.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return list;
//		return getSession().createQuery("from Coupon", Coupon.class).list();

	}

	public static void main(String args[]) {

		CouponHibernateDAO dao = new CouponHibernateDAOImpl();
		Coupon coupon = null;

//		coupon = new Coupon();
//		coupon.setSerialNo("2222");
//		coupon.setCouponName("重陽節優惠");
//		coupon.setDiscount(222);
//		coupon.setThreshold(2000);
//		coupon.setStartTime(Timestamp.valueOf("2003-11-20 00:00:00"));
//		coupon.setEndTime(Timestamp.valueOf("2003-11-21 00:00:00"));
//		coupon.setStock(100);
//		coupon.setTotal(100);
//		dao.add(coupon);
//		System.out.println("新增成功");
		
		
		//查詢單筆
//		coupon = dao.getCouponByCouponId(32001);
//		System.out.println(coupon);
		
//		更新單筆
//		coupon = dao.getCouponByCouponId(32001);
//		coupon.setCouponStatus("0");
//		dao.update(coupon);
//		System.out.println("更新成功");
		
//		查詢全部
		List<Coupon> couponList = dao.getAll();
		for(Coupon item : couponList) {
			System.out.println(item);
		}

	}

}
