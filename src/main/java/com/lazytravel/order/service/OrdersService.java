package com.lazytravel.order.service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.lazytravel.journey.dao.JourneyDAO;
import com.lazytravel.journey.dao.JourneyDAOImpl;
import com.lazytravel.journey.dao.TourGroupDAO;
import com.lazytravel.journey.dao.TourGroupDAOImpl;
import com.lazytravel.journey.entity.Journey;
import com.lazytravel.journey.entity.TourGroup;
import com.lazytravel.order.dao.OrdersHibernateDAO;
import com.lazytravel.order.dao.OrdersHibernateDAOImpl;
import com.lazytravel.order.entity.Orders;

public class OrdersService {
	private final OrdersHibernateDAO orderdao;
	private final TourGroupDAO tourgroupdao;
	private final JourneyDAO journeydao;

	
	public OrdersService() {
		orderdao = new OrdersHibernateDAOImpl();
		tourgroupdao = new TourGroupDAOImpl();
		journeydao = new JourneyDAOImpl();

	}
	
	public void addOrder(Orders order) {
		order.setCreateTime(new Timestamp(System.currentTimeMillis()));
        order.setUpdateTime(new Timestamp(System.currentTimeMillis()));

        orderdao.add(order);
        
        //新增訂單及增加JOURNEY表格BuyCount+1
        // 获取订单所属的Group
//         TourGroup tougroup = tourgroupdao.getGroupById(order.getGroupId());

        // 更新Group的JourneyID
//         int journeyId = tougroup.getJourneyId();

        // 获取Journey
//         Journey journey = journeydao.getJourneyById(journeyId);

        //自動產生orderNo
		generateOrderNo(order);
		orderdao.update(order);
       
		
	}
	
	public void updateOrder(Orders order) {
		
		orderdao.update(order);
	}
	
	public void cancelOrder(Integer orderId) {
		orderdao.cancelOrder(orderId);
	}
	
	

	
	public Orders getOneOrder(Integer orderId) {
		return orderdao.getOrdersByOrdersId(orderId);
	}
	
	public Orders getOrderByOrderNo(Integer orderNo) {
		return orderdao.getOrdersByOrdersNo(orderNo);
	}
	
	public List<Orders> getOrderByCustomerId(Integer customerId) {
		return orderdao.getOrderByCustomerId(customerId);
	}

	
	
	
	public List<Orders> getAll(){
		return orderdao.getAll();
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
	            orderdao.update(order);

	        } catch (NumberFormatException e) {
	            // 處理轉換錯誤
	            e.printStackTrace(); // 這裡可以根據需要進行處理
	        }
	    }else {
	        // 處理 orderNoStr 不是有效數字的情況
	        System.out.println("Invalid orderNoStr: " + orderNoStr);
	    }
	}
	
	public String getJourneyNameByOrderId(Integer orderId) {
		return orderdao.getJourneyNameByOrderId(orderId);
	}
	
	



	

	
	
}
