package com.lazytravel.order.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;

import com.lazytravel.order.entity.CustomerCoupon;
import com.lazytravel.order.entity.CustomerCoupon.CompositeDetail;
import com.lazytravel.util.HibernateUtil;

public class CustomerCouponHibernateDAOImpl implements CustomerCouponHibernateDAO {

	private SessionFactory factory;
	
	public CustomerCouponHibernateDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	public Session getSession() {
		return factory.getCurrentSession();
	}
	
	
	@Override
	public void add(CustomerCoupon customercoupon) {
		Session session = getSession();
		try {
			session.beginTransaction();
			session.save(customercoupon);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
//		getSession().save(customercoupon);
	}
	
	@Override
	public List<CustomerCoupon> getByCustomerId(Integer customerId) {
	    Session session = getSession();
	    List<CustomerCoupon> customerCoupons = new ArrayList<>();
	    try {
	        session.beginTransaction();
	        Query<CustomerCoupon> query = session.createQuery("from CustomerCoupon where customerId = :customerId", CustomerCoupon.class);
	        query.setParameter("customerId", customerId);
	        customerCoupons = query.getResultList();
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
	    return customerCoupons;
	}
	
	
	
	@Override
	public List<CustomerCoupon> getByCouponId(Integer couponId) {
	    Session session = getSession();
	    List<CustomerCoupon> customerCoupons = new ArrayList<>();
	    try {
	        session.beginTransaction();
	        Query<CustomerCoupon> query = session.createQuery("from CustomerCoupon where couponId = :couponId", CustomerCoupon.class);
	        query.setParameter("couponId", couponId);
	        customerCoupons = query.getResultList();
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    }
	    return customerCoupons;
	}
	
	
	@Override
	public void update(CustomerCoupon customercoupon) {
		Session session = getSession();
		try {
			session.beginTransaction();
			session.update(customercoupon);
			session.getTransaction().commit();;
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

	}
	
	
	
	
	
	@Override
	public CustomerCoupon getByCompositeKey(CompositeDetail compositeKey) {
		Session session = getSession();
		CustomerCoupon customercoupon = null;
		try {
			session.beginTransaction();
			customercoupon = getSession().get(CustomerCoupon.class, compositeKey);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return customercoupon;
//		return getSession().get(CustomerCoupon.class, customerId)
	}
	
	
	
	@Override
	public List<CustomerCoupon> getAll(){
		Session session = getSession();
		List<CustomerCoupon> list = null;
		try {
			session.beginTransaction();
			list = session.createQuery("from CustomerCoupon", CustomerCoupon.class).list();
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();	
		}
		
		return list;
//		return getSession().createQuery("from customercoupon", CustomerCoupon.class).list();
		
	}
	
	public static void main(String args[]) {
		CustomerCouponHibernateDAO dao = new CustomerCouponHibernateDAOImpl();
		CustomerCoupon customercoupon = null;
		List<CustomerCoupon> customercouponlist = null;
		
//		customercoupon = new CustomerCoupon();
//		customercoupon.setCustomerId(11001);
//		customercoupon.setCouponId(32002);
//		customercoupon.setCouponStatus("0");
//		customercoupon.setCreateTime(new Timestamp(System.currentTimeMillis()));
//		customercoupon.setUpdateTime(new Timestamp(System.currentTimeMillis()));
//		dao.add(customercoupon);
//		System.out.println("新增成功");
		
//		複合主鍵查詢單筆
//		CompositeDetail compositeKey = new CompositeDetail(11001, 32002);
//		customercoupon = dao.getByCompositeKey(compositeKey);
//		System.out.println(customercoupon);
//		System.out.println("查詢成功");
		
//		customerId查詢
//		customercouponlist = dao.getByCustomerId(11001);
//		System.out.println(customercouponlist);
		
		
//		couponId查詢
//		customercouponlist = dao.getByCouponId(32001);
//		System.out.println(customercouponlist);
		
//		更新
//		CompositeDetail compositeKey = new CompositeDetail(11001, 32002);
//		customercoupon = dao.getByCompositeKey(compositeKey);
//		customercoupon.setCouponStatus("1");
//		dao.update(customercoupon);
//		System.out.println("更新成功");
		
		
//		查全部筆數
//		List<CustomerCoupon> list = dao.getAll();
//		for(CustomerCoupon customercouponall : list) {
//			System.out.println(customercouponall);
//		}
//		System.out.println("[SQL] 查詢全部筆數成功\n");
	}

	
	

	
	
	
}
