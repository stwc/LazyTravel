package com.lazytravel.order.dao.JDBC;

import java.util.List;

import com.lazytravel.order.entity.Passenger;

public interface PassengerDAO {
	
	void add(Passenger passenger);
	void update(Passenger passenger);
	void delete(Integer passengerId);
	Passenger getPassengerByPassengerId(Integer passengerId);
	List<Passenger>getAll();
	
}
