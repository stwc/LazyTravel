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
	        session.beginTransaction();
	
	        session.save(coupon);
	
	        session.getTransaction().commit();
//			getSession().save(coupon);
		}
		
		@Override 
		public void delete(Coupon coupon) {
			
			getSession().delete(coupon);
		}
		
		@Override
		public Coupon getCouponByCouponId(Integer couponId) {
			return getSession().get(Coupon.class, couponId);
		}
		
		@Override
		public List<Coupon> getAll(){
			return getSession().createQuery("from Coupon",Coupon.class).list();
			
		}
		

		public static void main(String args[]) {
			
			
			CouponHibernateDAO dao = new CouponHibernateDAOImpl();
			Coupon coupon = null;
			
			
			coupon = new Coupon();
			coupon.setSerialNo("2222");
			coupon.setCouponName("重陽節優惠");
			coupon.setDiscount(222);
			coupon.setThreshold(2000);
			coupon.setStartTime(Timestamp.valueOf("2003-11-20 00:00:00"));
			coupon.setEndTime(Timestamp.valueOf("2003-11-21 00:00:00"));
			coupon.setStock(100);
			coupon.setTotal(100);
			dao.add(coupon);
			System.out.println("新增成功");
			
		}
		
		
}
