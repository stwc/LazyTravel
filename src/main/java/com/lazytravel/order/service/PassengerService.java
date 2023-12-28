package com.lazytravel.order.service;

import java.util.List;

import com.lazytravel.journey.dao.JourneyDAO;
import com.lazytravel.journey.dao.JourneyDAOImpl;
import com.lazytravel.journey.entity.TourGroup;
import com.lazytravel.order.dao.PassengerHibernateDAO;
import com.lazytravel.order.dao.PassengerHibernateDAOImpl;
import com.lazytravel.order.entity.Passenger;

public class PassengerService {
	private final PassengerHibernateDAO dao;
	private final JourneyDAO journeydao;
	
	public PassengerService() {
		dao = new PassengerHibernateDAOImpl();
		journeydao= new JourneyDAOImpl();
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
