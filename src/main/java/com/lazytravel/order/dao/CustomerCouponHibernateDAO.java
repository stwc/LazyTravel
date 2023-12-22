package com.lazytravel.order.dao;

import java.util.List;

import com.lazytravel.order.entity.CustomerCoupon;
import com.lazytravel.order.entity.CustomerCoupon.CompositeDetail;


public interface CustomerCouponHibernateDAO {
	
	CustomerCoupon addCustomerCoupon(int customerId, int couponId);
	CustomerCoupon getByCustomerIdAndCouponId(int customerId, int couponId);
	void update(CustomerCoupon customercoupon);
	List<CustomerCoupon> getByCustomerId(Integer customerId);
	List<CustomerCoupon> getByCouponId(Integer couponId);
	CustomerCoupon getByCompositeKey(CompositeDetail compositeKey);
	
	
	List<CustomerCoupon> getAll();
}