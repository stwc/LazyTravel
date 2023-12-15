package com.lazytravel.journey.dao;

import java.util.List;

import com.lazytravel.journey.entity.JourneyDetail;

public interface JourneyDetailService {
	void add(JourneyDetail journeyDetail);
	void update(JourneyDetail journeyDetail);
	void delete(Integer journeyId, Integer foodscapeId);
	
//	JourneyDetail getOneJourneyDetail(Integer journeyId, Integer foodscapeId);
	List<JourneyDetail> getByJourneyId(Integer journeyId);
	
	List<JourneyDetail> addList(List<JourneyDetail> journeyDetail);

}
