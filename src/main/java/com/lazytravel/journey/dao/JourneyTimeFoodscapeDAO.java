package com.lazytravel.journey.dao;

import java.util.List;

import com.lazytravel.journey.entity.JourneyTimeFoodscape;

public interface JourneyTimeFoodscapeDAO {
	void add(JourneyTimeFoodscape journeyTimeFoodscape);
	void update(JourneyTimeFoodscape journeyTimeFoodscape);	
	void delete(Integer journeyId, Integer foodscapeId);
	
	JourneyTimeFoodscapeDAO findByPk(Integer journeyId, Integer foodscapeId);
	List<JourneyTimeFoodscape> getAll();
}
