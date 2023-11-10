package com.lazytravel.order;

import java.util.List;

public interface PassengerDAO {
	
	void add(Passenger passenger);
	void update(Passenger passenger);
	void delete(Integer passengerId);
	Passenger getPassengerByPassengerId(Integer passengerId);
	List<Passenger>getAll();
	
}
