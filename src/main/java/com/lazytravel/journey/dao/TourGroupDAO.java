package com.lazytravel.journey.dao;

import java.util.List;

import com.lazytravel.journey.entity.TourGroup;

public interface TourGroupDAO {
	void add(TourGroup tourGroup);
	void update(TourGroup tourGroup);
	void delete(Integer groupId);
	
	TourGroup findByPK(Integer groupId);
	List<TourGroup> getAll();
}
