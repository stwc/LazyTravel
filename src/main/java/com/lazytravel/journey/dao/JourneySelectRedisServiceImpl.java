package com.lazytravel.journey.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lazytravel.journey.entity.Journey;

public class JourneySelectRedisServiceImpl implements JourneySelectRedisService {

	private JourneySelectRedisDAO dao;
	private JourneyDAO journeyDAO;

	public JourneySelectRedisServiceImpl() {
		dao = new JourneySelectRedisDAOImpl();
		journeyDAO = new JourneyDAOImpl();
	}

	@Override
	public void addJourneySelect(Integer journeyId, List<Integer> foodScapeIdList) {
		dao.add(journeyId, foodScapeIdList);
	}

	@Override
	public void deleteJourneySelect(Integer journeyId, List<Integer> foodScapeIdList) {
		dao.delete(journeyId, foodScapeIdList);
	}

	@Override
	public Integer isMember(Integer journeyId, List<Integer> foodScapeIdList) {
		return dao.isMember(journeyId, foodScapeIdList);
	}

	@Override
	public Set<String> getAllMembersByjourneyId(Integer journeyId) {
		return dao.findMembers(journeyId);
	}

	@Override
	public List<Map.Entry<Journey, Integer>> getEntryList(List<Integer> foodScapeIdList) {
		// 將已上架的行程，存成一集合
		List<Journey> journeyMarketedList = new ArrayList<>();
		List<Journey> journeyList = journeyDAO.getAll();

		for (Journey journey : journeyList) {
			String status = journey.getJourneyStatus();
			if (status.equals("1")) {
				Journey journeyMarketed = new Journey(journey.getJourneyId(), journey.getJourneyName(),
						journey.getPrice(), journey.getDays(), journey.getBuyCount());
				journeyMarketedList.add(journeyMarketed);
			}
		}

		// 根據已上架的行程，計算每個行程有幾個user選擇的景點數，存成一Map(景點數=0不存入)
		// key為Journey物件、value為行程包含的景點數
		Map<Journey, Integer> map = new HashMap<>();

		for (Journey journeyMarketed : journeyMarketedList) {
			Integer journeyId = journeyMarketed.getJourneyId();
			Integer count = dao.isMember(journeyId, foodScapeIdList);
			if(count > 0) {
				map.put(journeyMarketed, count);
			}
		}

		// 根據count做降冪排序:
		// 將 map.entrySet() 作為的參數，以便將Set轉換為List<Map.Entry<Journey, Integer>>，來進行排序
		List<Map.Entry<Journey, Integer>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Journey, Integer>>() {
			public int compare(Map.Entry<Journey, Integer> o1, Map.Entry<Journey, Integer> o2) {
				return o2.getValue().compareTo(o1.getValue());
			}
		});

		for (Map.Entry<Journey, Integer> entry : list) {
			Journey journey = entry.getKey();
			System.out.println("JourneyId: " + journey.getJourneyId() + ", Name: " + journey.getJourneyName()
					+ ", Price: " + journey.getPrice() + ", Count: " + entry.getValue());
		}
		
		return list;
	}

}
