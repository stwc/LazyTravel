package com.lazytravel.journey;

public interface JourneyTimeFoodscapeDAO {
	void add(JourneyTimeFoodscape journeyTimeFoodscape);
	void update(JourneyTimeFoodscape journeyTimeFoodscape);	
	void delete(int journeyId, int foodscapeId);
}
