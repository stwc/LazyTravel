package com.lazytravel.foodscape.dao;

import java.util.List;
import com.lazytravel.foodscape.entity.OpenTime;


public interface OpenTimeDAO {
	
	void add(OpenTime opentime);
	
	void update(OpenTime opentime);
	
	//int delete(Integer openTimeId);
	
	OpenTime getByPK(Integer openTimeId);
	
	List<OpenTime> getAll();
	
	List<OpenTime> getAll(int currentPage);
	
	
}
