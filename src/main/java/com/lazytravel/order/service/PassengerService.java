package com.lazytravel.order.service;

import java.util.List;

import com.lazytravel.order.dao.PassengerHibernateDAO;
import com.lazytravel.order.dao.PassengerHibernateDAOImpl;
import com.lazytravel.order.entity.Passenger;

public class PassengerService {
	private final PassengerHibernateDAO dao;
	
	public PassengerService() {
		dao = new PassengerHibernateDAOImpl();
	}
	
	public void addPassenger(Passenger passenger) {
		dao.add(passenger);
	}
	
	public void updatePassenger(Passenger passenger) {
		dao.update(passenger);
	}
	
	 public List<Passenger> getPassengersByOrderId(Integer orderId) {
	        return dao.getByOrderId(orderId);
	}
}
