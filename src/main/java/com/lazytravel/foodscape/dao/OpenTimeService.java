package com.lazytravel.foodscape.dao;

import java.util.List;

import com.lazytravel.foodscape.entity.OpenTime;

public interface OpenTimeService {
	OpenTime insertOpenTime(OpenTime opentime);
	
	OpenTime updateOpenTime(OpenTime opentime);
	
	void deleteOpenTime(Integer openTimeId);
	
	OpenTime getOpenTimeByOpenTimeId(Integer openTimeId);
	
	List<OpenTime> getAllOpenTimes(int currentPage);

}
