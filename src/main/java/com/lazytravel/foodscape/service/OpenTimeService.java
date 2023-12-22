package com.lazytravel.foodscape.service;

import java.util.List;

import com.lazytravel.foodscape.entity.OpenTime;

public interface OpenTimeService {
	void addOpenTime(OpenTime opentime);
	
	void updateOpenTime(OpenTime opentime);
	
	//void deleteOpenTime(Integer openTimeId);
	
	OpenTime getOpenTimeByOpenTimeId(Integer openTimeId);
	
	List<OpenTime> getAllOpenTimes();

}
