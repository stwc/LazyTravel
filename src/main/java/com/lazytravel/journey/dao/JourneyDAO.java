package com.lazytravel.journey.dao;

import java.util.List;

import com.lazytravel.journey.entity.Journey;

public interface JourneyDAO {
	int add(Journey journey);
	int update(Journey journey);
//	int delete(Integer journeyId);
	
	Journey findByPK(Integer journeyId);
	List<Journey> getAll();

}
