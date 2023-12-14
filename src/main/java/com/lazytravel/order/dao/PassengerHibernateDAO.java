package com.lazytravel.order.dao;

import java.util.List;

import com.lazytravel.order.entity.Passenger;

public interface PassengerHibernateDAO {

	void add(Passenger passenger);
	void update(Passenger passenger);
	Passenger getByPk(Integer passengerId);
	List<Passenger> getAll();
}
