package com.lazytravel.order.service;

import java.util.List;

import com.lazytravel.order.dao.CouponHibernateDAO;
import com.lazytravel.order.dao.CouponHibernateDAOImpl;
import com.lazytravel.order.dao.CustomerCouponHibernateDAO;
import com.lazytravel.order.dao.CustomerCouponHibernateDAOImpl;
import com.lazytravel.order.entity.Coupon;
import com.lazytravel.order.entity.CustomerCoupon;

public class CustomerCouponService {

	private final CustomerCouponHibernateDAO ccdao;
	private final CouponHibernateDAO cdao;

	public CustomerCouponService() {
		ccdao = new CustomerCouponHibernateDAOImpl();
		cdao = new CouponHibernateDAOImpl();
	}

	public Integer addCustomerCoupon(int customerId, int couponId) {
		CustomerCoupon existingCoupon = ccdao.getByCustomerIdAndCouponId(customerId, couponId);

		if (existingCoupon != null) {
			// 如果已领取过相同的优惠券，不再添加
			return  0;
		}

		Coupon coupon = cdao.getCouponByCouponId(couponId);

		if (coupon != null && "1".equals(coupon.getCouponStatus()) && coupon.getStock() > 0) {

			ccdao.addCustomerCoupon(customerId, couponId);

			// 减少优惠券库存
			int currentStock = coupon.getStock();
			coupon.setStock(currentStock - 1);
			cdao.update(coupon); // 更新优惠券库存
			
		} 
		return  1;
	}

	public void updateCustomerCoupon() {

	}

	public List<CustomerCoupon> getCustomerCoupon(Integer customerId) {
		return ccdao.getByCustomerId(customerId);
	}

	public static void main(String args[]) {
		CustomerCouponHibernateDAO dao = new CustomerCouponHibernateDAOImpl();
		CustomerCoupon customercoupon = null;

//		dao.addCustomerCoupon(11001, 32002);
	}
}
