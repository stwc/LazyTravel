package com.lazytravel.journey.controller;

import com.lazytravel.util.JedisPoolUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class TestRedis {
	private static JedisPool pool = JedisPoolUtil.getJedisPool();
	
	public static void main(String[] args) {
		Jedis jedis = pool.getResource();
		
//		System.out.println("購物車 Hash");
//		jedis.hset("cart:11001", "24001", "1");
//		jedis.hset("cart:11001", "24002", "2");
//		jedis.hset("cart:11001", "24003", "5");	
//		jedis.hset("cart:11002", "24002", "2");
//		jedis.hset("cart:11002", "24003", "3");
		
//		System.out.println("行程篩選 Set");
//		jedis.sadd("journey:23002:foodscapes", "22001","22002","22003","22004","22005","22006","22007","22008","22015","22022");
//		jedis.sadd("journey:23003:foodscapes", "22003","22004","22005","22010","22012","22019","22020","22021","22022","22023");
//		jedis.sadd("journey:23004:foodscapes", "22001","22004","22008","22011","22012","22013","22015");
//		jedis.sadd("journey:23005:foodscapes", "22024","22026","22028");
//		jedis.sadd("journey:23006:foodscapes", "22024","22025","22026","22027","22028","22029","22030");
//		
//		
//		jedis.srem("journey:23002:foodscapes", "22001","22002","22003","22004","22005","22006","22007","22008","22015","22022");
		
	}
}
