package com.lazytravel.journey.dao;

import java.util.List;
import java.util.Set;

public interface JourneySelectRedisDAO {
	void add(Integer journeyId, List<Integer> foodScapeIdList);
	void delete(Integer journeyId, List<Integer> foodScapeIdList);
	
	Integer isMember(Integer journeyId, List<Integer> foodScapeIdList);

	Set<String> findMembers(Integer journeyId);

}
