package com.lazytravel.order.dao;

import java.util.List;

import com.lazytravel.order.entity.Coupon;

public interface CouponDAO {

	void add(Coupon coupon);

	void update(Coupon coupon);

	void delete(Integer couponId);

	Coupon getCouponByCouponId(Integer couponId);

	List<Coupon> getAll();

}
