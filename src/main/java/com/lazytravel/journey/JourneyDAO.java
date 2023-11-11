package com.lazytravel.journey;

public interface JourneyDAO {
	void add(Journey journey);
	void update(Journey journey);
	void delete(int journeyId);
}
