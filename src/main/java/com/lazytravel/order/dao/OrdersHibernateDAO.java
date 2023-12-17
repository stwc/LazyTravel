package com.lazytravel.order.dao;

import java.util.List;

import com.lazytravel.order.entity.Orders;


public interface OrdersHibernateDAO {

	void add(Orders orders);
	void update(Orders orders);
	Orders getOrdersByOrdersId(Integer orderId);
	Orders getOrdersByOrdersNo(Integer orderNo);
	String getJourneyNameByOrderId(Integer orderId);

	List<Orders> getAll(); 
	
}
