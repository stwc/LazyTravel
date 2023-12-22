package com.lazytravel.journey.dao;

import java.util.List;
import java.util.Map;

import com.lazytravel.journey.entity.ShoppingCart;

public interface ShoppingCartDAO {
	void add(ShoppingCart shoppingCart);
	Integer update(ShoppingCart shoppingCart);
	void delete(String customerId, String groupId);
	
	ShoppingCart findByCustomerIdAndGroupId(String customerId, String groupId);
	
	Map<String, String> findByCustomerIdReturnMap(String customerId);
	
	List<ShoppingCart> findByCustomerIdReturnList(String customerId);

}

