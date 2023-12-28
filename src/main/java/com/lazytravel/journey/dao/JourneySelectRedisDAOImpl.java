package com.lazytravel.journey.dao;

import java.util.List;
import java.util.Set;

import com.lazytravel.util.JedisPoolUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JourneySelectRedisDAOImpl implements JourneySelectRedisDAO {

	private static JedisPool pool = JedisPoolUtil.getJedisPool();
	Jedis jedis = pool.getResource();
	
	
	@Override
	public void add(Integer journeyId, List<Integer> foodScapeIdList) {
		String key = "journey:" + journeyId + ":foodscapes";
		
		for(Integer foodScapeId : foodScapeIdList) {
			String foodScapeIdStr = String.valueOf(foodScapeId);
			jedis.sadd(key, foodScapeIdStr);
		}
		jedis.close();
	}

	@Override
	public void delete(Integer journeyId, List<Integer> foodScapeIdList) {
		String key = "journey:" + journeyId + ":foodscapes";
		
		for(Integer foodScapeId : foodScapeIdList) {
			String foodScapeIdStr = String.valueOf(foodScapeId);
			jedis.srem(key, foodScapeIdStr);
		}
		jedis.close();
	}

	@Override
	public Integer isMember(Integer journeyId, List<Integer> foodScapeIdList) {
		String key = "journey:" + journeyId + ":foodscapes";
		
		Integer count = 0;
		for(Integer foodScapeId : foodScapeIdList) {
			String foodScapeIdStr = String.valueOf(foodScapeId);
			if(jedis.sismember(key, foodScapeIdStr)) {
				count++;
			}
		}

		return count;
	}

	@Override
	public Set<String> findMembers(Integer journeyId) {
		String key = "journey:" + journeyId + ":foodscapes";
		Set<String> members = jedis.smembers(key);
		
		jedis.close();
		return members;
	}

}
