package com.lazytravel.order.dao.JDBC;

import java.util.List;

import com.lazytravel.order.entity.Coupon;

public interface CouponDAO {

	void add(Coupon coupon);

	void update(Coupon coupon);

	Coupon getCouponByCouponId(Integer couponId);

	List<Coupon> getAll();

}
