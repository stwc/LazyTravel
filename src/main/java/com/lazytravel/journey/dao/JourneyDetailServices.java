package com.lazytravel.journey.dao;

import com.lazytravel.journey.entity.JourneyDetail;

public interface JourneyDetailServices {
	void add(JourneyDetail journeyDetail);
	void update(JourneyDetail journeyDetail);
	void delete(Integer journeyId, Integer foodscapeId);
	
	JourneyDetail getOneJourneyDetail(Integer journeyId, Integer foodscapeId);

}
