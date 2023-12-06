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
		orders.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
		orders.setUpdateTime(new java.sql.Timestamp(System.currentTimeMillis()));
	
	    getSession().save(orders);
	}
	
	
	@Override
	public void update(Orders orders) {
		Orders existingOrder = getSession().get(Orders.class, orders.getOrderId());
	    orders.setCreateTime(existingOrder.getCreateTime()); // Set the existing create time
		orders.setUpdateTime(new java.sql.Timestamp(System.currentTimeMillis()));
		 getSession().merge(orders);
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
	

	    
}
