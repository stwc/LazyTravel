package com.lazytravel.order.dao;

import java.util.List;

import com.lazytravel.order.entity.Coupon;

public interface CouponHibernateDAO {
	
	void add(Coupon coupon);
	void update(Coupon coupon);
	Coupon getCouponByCouponId(Integer couponId);
	Coupon getCouponByCouponNo(String couponNo);
	List<Coupon> getAll();


}
