package com.lazytravel.order.service;

import java.sql.Timestamp;
import java.util.List;

import com.lazytravel.order.dao.OrdersHibernateDAO;
import com.lazytravel.order.dao.OrdersHibernateDAOImpl;
import com.lazytravel.order.entity.Orders;

public class OrdersService {
	private final OrdersHibernateDAO dao;
	
	public OrdersService() {
		dao = new OrdersHibernateDAOImpl();
	}
	
	public void addOrder(Orders order) {
		order.setCreateTime(new Timestamp(System.currentTimeMillis()));
        order.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        
		dao.add(order);
	}
	
	public void updateOrder(Orders order) {
		dao.update(order);
	}
	
	

	
	public Orders getOneOrder(Integer orderId) {
		return dao.getOrdersByOrdersId(orderId);
	}
	
	public Orders getOrderByOrderNo(Integer orderNo) {
		return dao.getOrdersByOrdersNo(orderNo);
	}
	
	
	
	public List<Orders> getAll(){
		return dao.getAll();
	}
	

	
	
}
