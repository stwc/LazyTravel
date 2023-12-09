package com.lazytravel.order.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.lazytravel.journey.dao.JourneyDAOImpl;
import com.lazytravel.journey.entity.Journey;
import com.lazytravel.order.dao.OrdersHibernateDAO;
import com.lazytravel.order.dao.OrdersHibernateDAOImpl;
import com.lazytravel.order.entity.Orders;

public class OrdersService {
	private final OrdersHibernateDAO dao;

	
	public OrdersService() {
		dao = new OrdersHibernateDAOImpl();

	}
	
	public void addOrder(Orders order) {
		order.setCreateTime(new Timestamp(System.currentTimeMillis()));
        order.setUpdateTime(new Timestamp(System.currentTimeMillis()));

		dao.add(order);
		
		
		// Generate orderNo based on create time and the obtained orderId
		generateOrderNo(order);
       
        
        // Update the order with the generated orderNo
        dao.update(order);
       
		
	}
	
	public void updateOrder(Orders order) {
		
		dao.update(order);
	}
	
	

	
	public Orders getOneOrder(Integer orderId) {
		return dao.getOrdersByOrdersId(orderId);
	}
	
	public Orders getOrderByOrderNo(Integer orderNo) {
		return dao.getOrdersByOrdersNo(orderNo);
	}
	
	
	
	public List<Orders> getAll(){
		return dao.getAll();
	}
	
	private void generateOrderNo(Orders order) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
	    String datePart = dateFormat.format(new Date(order.getCreateTime().getTime()));
	    String orderIdStr = String.valueOf(order.getOrderId());

	    // 計算orderId中的數字位數
	    int orderIdDigits = orderIdStr.length();

	    // 使用數字位數格式化orderIdPart
	    String orderIdPart = String.format("%0" + orderIdDigits + "d", order.getOrderId());

	    String orderNoStr = datePart + orderIdPart;

	    if (orderNoStr != null && !orderNoStr.isEmpty() && orderNoStr.matches("\\d+")) {
	        try {
	            
	            order.setOrderNo(orderNoStr); // 設置生成的order_no到訂單對象中

	            // 使用生成的orderNo更新訂單
	            dao.update(order);

	        } catch (NumberFormatException e) {
	            // 處理轉換錯誤
	            e.printStackTrace(); // 這裡可以根據需要進行處理
	        }
	    }else {
	        // 處理 orderNoStr 不是有效數字的情況
	        System.out.println("Invalid orderNoStr: " + orderNoStr);
	    }
	}


	

	
	
}
