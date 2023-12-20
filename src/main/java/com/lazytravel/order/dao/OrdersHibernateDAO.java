package com.lazytravel.order.dao;

import java.util.List;

import com.lazytravel.order.entity.Orders;


public interface OrdersHibernateDAO {

	void add(Orders orders);
	void update(Orders orders);
	void cancelOrder(Integer orderId);
	Orders getOrdersByOrdersId(Integer orderId);
	Orders getOrdersByOrdersNo(Integer orderNo);
	List<Orders> getOrderByCustomerId(Integer customerId);
	String getJourneyNameByOrderId(Integer orderId);
	
	List<Orders> getAll(); 
	
}
