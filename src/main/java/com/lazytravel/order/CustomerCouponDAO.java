package com.lazytravel.order;

import java.util.List;

public interface CustomerCouponDAO {
	
	void add(CustomerCoupon customerCoupon);
	void update(CustomerCoupon customerCoupon);
	void delete(String customerId);
	List<CustomerCoupon>getAll();

}
