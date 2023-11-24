package com.lazytravel.journey.dao;

import java.util.List;

import com.lazytravel.journey.entity.Journey;

public interface JourneyDAO {
	void add(Journey journey);
	void update(Journey journey);
	void delete(Integer journeyId);
	
	Journey findByPK(Integer journeyId);
	List<Journey> getAll();
}
