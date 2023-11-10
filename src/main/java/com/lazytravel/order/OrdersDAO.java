package com.lazytravel.order;

import java.util.List;


public interface OrdersDAO {

	void add(Orders orders);
	void update(Orders orders);
	void delete(Integer orderId);  //刪除需要條件
	Orders getOrdersByOrdersId(Integer orderId);
	List<Orders> getAll(); 
	
}
