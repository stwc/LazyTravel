package com.lazytravel.order.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
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
		Session session = getSession();
		try {
			session.beginTransaction();
			orders.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
			orders.setUpdateTime(new java.sql.Timestamp(System.currentTimeMillis()));
			session.save(orders);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

//	    getSession().save(orders);
	}

	@Override
	public void update(Orders orders) {
		Session session = getSession();
		try {
			session.beginTransaction();
			Orders existingOrder = getSession().get(Orders.class, orders.getOrderId());
			orders.setCreateTime(existingOrder.getCreateTime()); // Set the existing create time
			orders.setUpdateTime(new java.sql.Timestamp(System.currentTimeMillis()));
			session.merge(orders);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
//	    getSession().merge(orders);

	}

	@Override
	public Orders getOrdersByOrdersId(Integer orderId) {
		Session session = getSession();
		Orders orders = null;
		try {
			session.beginTransaction();
			orders = getSession().get(Orders.class, orderId);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return orders;
//		return getSession().get(Order.class, orderId);

	}

	@Override
	public Orders getOrdersByOrdersNo(Integer orderNo) {
		Session session = getSession();
		Orders orders = null;
		try {
			session.beginTransaction();
			orders = getSession().get(Orders.class, orderNo);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return orders;
		
//		return getSession().get(Order.class, orderNo);
	}

	@Override
	public List<Orders> getAll() {
		Session session = getSession();
		List<Orders> list = null;
		try {
			session.beginTransaction();
			list  = session.createQuery("from Orders", Orders.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return list;
//		return getSession().createQuery("from orders", Orders.class).list();
    }
	

}
