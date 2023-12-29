package com.lazytravel.order.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.lazytravel.order.dao.CustomResponseData;
import com.lazytravel.order.entity.Coupon;
import com.lazytravel.order.entity.CustomerCoupon;
import com.lazytravel.order.service.CouponService;
import com.lazytravel.order.service.CustomerCouponService;

@WebServlet(name = "CustomerCouponServlet", urlPatterns = { "/customorCenter/customercoupon.do" , "/order/customercoupon.do" })
public class CustomerCoupnServlet extends HttpServlet {

	private CouponService couponservice;
	private CustomerCouponService customercouponservice;

	@Override
	public void init() throws ServletException {
		couponservice = new CouponService();
		customercouponservice = new CustomerCouponService();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		String forwardPath = "";

		switch (action) {

		case "addcustomercoupon":
			addcustomercoupon(req, res);
			return;
			
		case "getcustomercouponByCutomerId" :
			getcustomercouponByCutomerId(req , res);
			return;

		default:
			forwardPath = "XXX";

		}

		res.setContentType("text/html charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}
	


	private void getcustomercouponByCutomerId(HttpServletRequest req, HttpServletResponse res) {
		try {
			Integer customerId = Integer.parseInt(req.getParameter("customerId"));
			List<CustomerCoupon> customercoupon = customercouponservice.getCustomerCoupon(customerId);
			List<CustomResponseData> responseDataList = new ArrayList<>();
			for(CustomerCoupon customerCoupon : customercoupon) {
				int couponId = customerCoupon.getCouponId();
				Coupon coupon = couponservice.getOneCoupon(couponId);
				
				if (coupon != null) {
					CustomResponseData responseData = new CustomResponseData();
					responseData.setCouponId(coupon.getCouponID());
					responseData.setCouponName(coupon.getCouponName());
	                responseData.setSerialNo(coupon.getSerialNo());
	                responseData.setStartTime(coupon.getStartTime());
	                responseData.setEndTime(coupon.getEndTime());
	                responseData.setThreshold(coupon.getThreshold());
	                responseData.setDiscount(coupon.getDiscount());
	                responseData.setCouponStatus(customerCoupon.getCouponStatus());

	                responseDataList.add(responseData);
	                
				}

			}
			
			
			
			Gson gson = new Gson();
	        String jsonResponse = gson.toJson(responseDataList);
	     // 設置響應類型為 JSON
	        res.setContentType("application/json; charset=UTF-8");	
	        res.getWriter().write(jsonResponse);
		} catch (Exception e) {
			try {
	            res.setContentType("application/json; charset=UTF-8");
	            res.getWriter().write("{\"error\": \"Error processing request\"}");
	        } catch (IOException ioException) {
	            ioException.printStackTrace();
	        }
		}
		
	}

	private void addcustomercoupon(HttpServletRequest req, HttpServletResponse res) {
	    String couponNo = req.getParameter("couponNo").trim();
	    Integer customerId = Integer.parseInt(req.getParameter("customerId"));
	    CouponService couponService = new CouponService();
	    CustomerCouponService customerCouponService = new CustomerCouponService();
	    Coupon coupon = couponService.getCouponByCouponNo(couponNo);

       

	    if (coupon != null) {
	        Integer stock = coupon.getStock();
	        if (stock > 0) {
	        	res.setContentType("application/json; charset=UTF-8");
	        	Integer addstatus = customerCouponService.addCustomerCoupon(customerId, coupon.getCouponID());
	            if (addstatus == 1) {
	            	try {
						res.getWriter().write("{\"message\": \"新增成功 !!\"}");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else {
					try {
						res.getWriter().write("{\"message\": \"你已經有這張囉 !!\"}");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
	            
	        } else {
	        	res.setContentType("application/json; charset=UTF-8");
	            try {
					res.getWriter().write("{\"message\": \"優惠券庫存不足 !!\"}");
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
	    } else {
	    	res.setContentType("application/json; charset=UTF-8");
            try {
				res.getWriter().write("{\"message\": \"優惠券不存在!!\"}");
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    
	    

	   
	}

}
