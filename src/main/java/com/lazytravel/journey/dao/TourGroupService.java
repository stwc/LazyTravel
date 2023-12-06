package com.lazytravel.journey.dao;

import java.util.List;
import java.util.Map;

import com.lazytravel.journey.entity.TourGroup;

public interface TourGroupService {
	void add(TourGroup tourGroup);
	void update(TourGroup tourGroup);
	List<TourGroup> getAll();
	List<TourGroup> getByTime(Map<String, String> map);
	
}
