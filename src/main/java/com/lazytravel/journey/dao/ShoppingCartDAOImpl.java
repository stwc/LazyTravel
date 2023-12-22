package com.lazytravel.journey.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lazytravel.journey.entity.ShoppingCart;
import com.lazytravel.util.JedisPoolUtil;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class ShoppingCartDAOImpl implements ShoppingCartDAO{

	private static JedisPool pool = JedisPoolUtil.getJedisPool();
	Jedis jedis = pool.getResource();
	
	@Override
	public void add(ShoppingCart shoppingCart) {		
		jedis.hset("cart:" + shoppingCart.getCustomerId(), shoppingCart.getGroupId(), String.valueOf(shoppingCart.getQuantity()));	
	}

	@Override
	public Integer update(ShoppingCart shoppingCart) {
		if(jedis.exists("cart:" + shoppingCart.getCustomerId())) {
			jedis.hset("cart:" + shoppingCart.getCustomerId(), shoppingCart.getGroupId(), String.valueOf(shoppingCart.getQuantity()));
		}
		return shoppingCart.getQuantity();
	}

	@Override
	public void delete(String customerId, String groupId) {
		jedis.hdel("cart:" + customerId, groupId);
	}

	@Override
	public ShoppingCart findByCustomerIdAndGroupId(String customerId, String groupId) {
	    String quantity = jedis.hget("cart:" + customerId, groupId);
	    if (quantity != null) {
	        ShoppingCart shoppingCart = new ShoppingCart();
	        shoppingCart.setCustomerId(customerId);
	        shoppingCart.setGroupId(groupId);
	        shoppingCart.setQuantity(Integer.parseInt(quantity));
	        return shoppingCart;
	    } else {
	        return null;
	    }
	}

	@Override
	public Map<String, String> findByCustomerIdReturnMap(String customerId) {
		Map<String, String> cartData = new HashMap<>();
		cartData = jedis.hgetAll("cart:" + customerId);
		return cartData;
	}
	
	@Override
	public List<ShoppingCart> findByCustomerIdReturnList(String customerId) {
		Map<String, String> cartData = jedis.hgetAll("cart:" + customerId);
		
		List<ShoppingCart> shoppingCartList = new ArrayList<>();
		
		for(String groupId : cartData.keySet()) {
	        ShoppingCart shoppingCart = new ShoppingCart();
	        shoppingCart.setCustomerId(customerId);
	        shoppingCart.setGroupId(groupId);
	        shoppingCart.setQuantity(Integer.valueOf(cartData.get(groupId)));
	        shoppingCartList.add(shoppingCart);
		}
		
		return shoppingCartList;
	}

}
