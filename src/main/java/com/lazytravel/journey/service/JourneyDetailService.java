package com.lazytravel.journey.service;

import java.util.List;

import com.lazytravel.foodscape.entity.FoodScape;
import com.lazytravel.journey.entity.JourneyDetail;

public interface JourneyDetailService {
	void add(JourneyDetail journeyDetail);
	void update(JourneyDetail journeyDetail);
	void delete(Integer journeyId, Integer foodscapeId);
	
//	JourneyDetail getOneJourneyDetail(Integer journeyId, Integer foodscapeId);
	List<JourneyDetail> getByJourneyId(Integer journeyId);
	
	List<JourneyDetail> addList(List<JourneyDetail> journeyDetail, Integer journeyId);
	
	List<FoodScape> findFoodscapeNameAndAddress(Integer journeyId);
	List<FoodScape> findFoodscapeLngAndLat(Integer journeyId, Integer nthDay);
}
