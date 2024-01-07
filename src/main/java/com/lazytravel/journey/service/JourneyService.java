package com.lazytravel.journey.service;

import java.util.List;

import com.lazytravel.journey.entity.Journey;
import com.lazytravel.journey.entity.JourneyDetail;

public interface JourneyService {
	Integer addJourneyAndDetail(Journey journey, List<JourneyDetail> list);
	void updateJourney(Journey journey);
	Journey getOneJourney(Integer journeyId);
	List<Journey> getAll();
	
	List<Journey> getAllByStatusIsMarketed();
}
