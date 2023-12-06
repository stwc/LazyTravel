package com.lazytravel.journey.dao;

import java.util.List;

import com.lazytravel.journey.entity.Journey;

public interface JourneyService {
	void addJourney(Journey journey);
	void updateJourney(Journey journey);
	Journey getOneJourney(Integer journeyId);
	List<Journey> getAll();

}
