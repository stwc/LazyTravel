package com.lazytravel.journey.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lazytravel.journey.entity.Journey;

public interface JourneySelectRedisService {
	void addJourneySelect(Integer journeyId, List<Integer> foodScapeIdList);
	void deleteJourneySelect(Integer journeyId, List<Integer> foodScapeIdList);
	
	Integer isMember(Integer journeyId, List<Integer> foodScapeIdList);

	Set<String> getAllMembersByjourneyId(Integer journeyId);

	List<Map.Entry<Journey, Integer>> getEntryList(List<Integer> foodScapeIdList);
}
