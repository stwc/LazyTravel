package com.lazytravel.order.service;

import java.util.List;

import com.lazytravel.order.dao.CouponHibernateDAO;
import com.lazytravel.order.dao.CouponHibernateDAOImpl;
import com.lazytravel.order.entity.Coupon;

public class CouponService {
	private final CouponHibernateDAO dao ;
	
	public CouponService() {
		dao = new CouponHibernateDAOImpl();
	}
	
	public void addCoupon(Coupon coupon) {
		dao.add(coupon);
	}
	
	public void updateCoupon(Coupon coupon) {
		dao.update(coupon);
	}
	
	public Coupon getOneCoupon(Integer couponId) {
		return dao.getCouponByCouponId(couponId);
	}
	
	public List<Coupon> getAll(){
		return dao.getAll();
	}
	
	public Coupon getCouponByCouponNo(String couponNo) {
		return dao.getCouponByCouponNo(couponNo);
	}
	
	public Boolean checkCouponStock(String couponNo) {
		Coupon coupon = dao.getCouponByCouponNo(couponNo);
		Integer couponstock = coupon.getStock();
		if(couponstock > 0) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
}
