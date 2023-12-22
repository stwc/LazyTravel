package com.lazytravel.order.dao.JDBC;

import java.util.List;

import com.lazytravel.order.entity.CustomerCoupon;

public interface CustomerCouponDAO {
	
	void add(CustomerCoupon customerCoupon);
	void update(CustomerCoupon customerCoupon);
	void delete(String customerId);
	List<CustomerCoupon>getAll();

}
