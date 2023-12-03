package com.lazytravel.order.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.lazytravel.order.entity.Orders;
import com.lazytravel.util.HibernateUtil;


public class OrdersHibernateDAOImpl implements OrdersHibernateDAO {


	
	private SessionFactory factory;
	
	public OrdersHibernateDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	
	@Override
	public void add(Orders orders) {
		getSession().save(orders);
	}
	
	
	@Override
	public void update(Orders orders) {
		getSession().update(orders);
	}


	@Override
	public Orders getOrdersByOrdersId(Integer orderId) {
		return getSession().get(Orders.class, orderId);
	}


	@Override
	public Orders getOrdersByOrdersNo(Integer orderNo) {
		return getSession().get(Orders.class, orderNo);
	}


	@Override
	public List<Orders> getAll() {
		return getSession().createQuery("from Orders",Orders.class).list();
	}
	
//	@Override
//	public int add(Orders orders) {
//
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			Integer id = (Integer) session.save(orders);
//			session.getTransaction().commit();
//			return id;
//				
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//
//		return -1;
//	}
//
//	@Override
//	public int update(Orders orders) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			session.update(orders);
//			session.getTransaction().commit();
//			return 1;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		return -1;
//	}
//
//	@Override
//	public Orders getOrdersByOrdersId(Integer orderId) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			Orders orders = session.get(Orders.class, orderId);
//			session.getTransaction().commit();
//			return orders;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		return null;
//	}
//	
//	@Override
//	public Orders getOrdersByOrdersNo(Integer orderNo) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			Orders orders = session.get(Orders.class, orderNo);
//			session.getTransaction().commit();
//			return orders;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		return null;
//	}
//
//	@Override
//	public List<Orders> getAll() {
//
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			List<Orders> list = session.createQuery("from Orders", Orders.class).list();
//			session.getTransaction().commit();
//			return list;
//		} catch (Exception e) {
//
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		return null;
//
//	}

}
