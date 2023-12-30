package com.lazytravel.journey.dao;

import java.util.List;
import java.util.Map;

import com.lazytravel.journey.entity.TourGroup;

public interface TourGroupService {
	void add(TourGroup tourGroup);
	void update(TourGroup tourGroup);
	void updateList(List<TourGroup> tourGroupList);
	
	TourGroup getOneTourGroup(Integer groupId);
	List<TourGroup> getAll();
	List<TourGroup> getByTime(Map<String, String> map);
	
	List<TourGroup> getByJourneyId(Integer journeyId);
	List<TourGroup> getMarketedByJourneyId(Integer journeyId);
}
