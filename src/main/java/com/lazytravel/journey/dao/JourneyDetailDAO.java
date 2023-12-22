package com.lazytravel.journey.dao;

import java.util.List;

import com.lazytravel.journey.entity.JourneyDetail;

public interface JourneyDetailDAO {
	int add(JourneyDetail journeyDetail);
	int update(JourneyDetail journeyDetail);
	
//	void delete(JourneyDetail.CompositeJourneyAndFoodscape compositeJourneyAndFoodscape);
	void delete(Integer journeyId, Integer foodscapeId);
	
//	JourneyDetail findByPk(Integer journeyId, Integer foodscapeId);
	List<JourneyDetail> findByJourneyId(Integer journeyId);
	
	List<JourneyDetail> addList(List<JourneyDetail> journeyDetail, Integer journeyId);

}
