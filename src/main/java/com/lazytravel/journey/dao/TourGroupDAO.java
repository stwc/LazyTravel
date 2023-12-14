package com.lazytravel.journey.dao;

import java.util.List;
import java.util.Map;

import com.lazytravel.journey.entity.TourGroup;

public interface TourGroupDAO {
	int add(TourGroup tourGroup);
	int update(TourGroup tourGroup);
//	void delete(Integer groupId);
	
//	TourGroup findByPK(Integer groupId);
	List<TourGroup> getAll();
	List<TourGroup> getByTime(Map<String, String> map);
	
}
