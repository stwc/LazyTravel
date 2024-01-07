package com.lazytravel.journey.service;

import java.util.List;
import java.util.Map;

import com.lazytravel.journey.dao.ShoppingCartDAO;
import com.lazytravel.journey.dao.ShoppingCartDAOImpl;
import com.lazytravel.journey.entity.ShoppingCart;
//import com.lazytravel.journey.entity.TourGroup;

public class ShoppingCartServiceImpl implements ShoppingCartService{

	private ShoppingCartDAO shoppingCartDAO;
//	private TourGroupDAO tourGroupDAO;
	
	public ShoppingCartServiceImpl(){
		shoppingCartDAO = new ShoppingCartDAOImpl();
//		tourGroupDAO = new TourGroupDAOImpl();
	}
	
	@Override
	public void addCart(ShoppingCart shoppingCart) {	
		shoppingCartDAO.add(shoppingCart);
		
//		// 寫到Servlet
//		Integer groupId = Integer.valueOf(shoppingCart.getGroupId());
//		TourGroup tourGroup = tourGroupDAO.findByPK(groupId);
//		return tourGroup;
	}

	@Override
	public Integer updateQuantityFromCart(ShoppingCart shoppingCart) {
		return shoppingCartDAO.update(shoppingCart);
	}

	@Override
	public void deleteCart(String customerId, String groupId) {
		shoppingCartDAO.delete(customerId, groupId);
	}

	@Override
	public ShoppingCart getOneByCustomerIdAndGroupId(String customerId, String groupId) {
		return shoppingCartDAO.findByCustomerIdAndGroupId(customerId, groupId);
	}

	@Override
	public Map<String, String> getAllByCustomerIdReturnMap(String customerId) {
		return shoppingCartDAO.findByCustomerIdReturnMap(customerId);
	}
	
	@Override
	public List<ShoppingCart> getAllByCustomerIdReturnList(String customerId) {
		return shoppingCartDAO.findByCustomerIdReturnList(customerId);
	}

}
