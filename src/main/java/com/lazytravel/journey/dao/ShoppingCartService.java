package com.lazytravel.journey.dao;

import java.util.List;
import java.util.Map;

import com.lazytravel.journey.entity.ShoppingCart;

public interface ShoppingCartService {
	void addCart(ShoppingCart shoppingCart);
	Integer updateQuantityFromCart(ShoppingCart shoppingCart);
	void deleteCart(String customerId, String groupId);
	
	ShoppingCart getOneByCustomerIdAndGroupId(String customerId, String groupId);
	Map<String, String> getOneByCustomerIdReturnMap(String customerId);
	List<ShoppingCart> getOneByCustomerIdReturnList(String customerId);
}
