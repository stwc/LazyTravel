package com.lazytravel.foodscape.dao;

import java.util.List;
import com.lazytravel.foodscape.entity.OpenTime;


public interface OpenTimeDAO {
	
	int insert(OpenTime opentime);
	
	int update(OpenTime opentime);
	
	//int delete(Integer openTimeId);
	
	OpenTime getByPK(Integer openTimeId);
	
	List<OpenTime> getAll();
	
	List<OpenTime> getAll(int currentPage);
	
	
}
