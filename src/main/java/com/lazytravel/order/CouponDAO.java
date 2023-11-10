package com.lazytravel.order;

import java.util.List;

public interface CouponDAO {
	
	void add(Coupon coupon);
	void update(Coupon coupon);
	void delete(Integer couponId);
	Coupon getCouponByCouponId(Integer couponId);
	List<Coupon> getAll();
	

}
