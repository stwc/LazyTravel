package com.lazytravel.order.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.lazytravel.order.entity.Orders;
import com.lazytravel.order.entity.Passenger;
import com.lazytravel.order.service.OrdersService;
import com.lazytravel.order.service.PassengerService;

@WebServlet(name = "PassengerServlet" , urlPatterns = {"/order/passenger.do" , "/admin/passenger.do"})
public class PassengerServlet extends HttpServlet{
	
	
	private PassengerService passengerService;
	private OrdersService orderSvc;
	
	@Override
	public void init() throws ServletException{
		passengerService = new PassengerService();
		orderSvc = new OrdersService();
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
		
		case "getPasDetails" :
			getPasDetails(req , res);
			return;
			
		case "insertPassengers":
		    insertPassengers(req, res);
		    break;
		    
		case "passsengerDetail_modify" :
			forwardPath = passsengerDetail_modify(req, res);
			break;
			
		
			
		case  "update" :
			update(req , res);
			return;

			

		default:
			forwardPath = "XXX";
		}
		
		res.setContentType("text/html charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}
	
	private void update(HttpServletRequest req, HttpServletResponse res) {
			String[] passengers =req.getParameterValues("passengerId");
		    String[] passengerNames = req.getParameterValues("passengerName");
		    String[] idNumbers = req.getParameterValues("idno");
		    String[] birthDates = req.getParameterValues("birth");
		    String[] phoneNumbers = req.getParameterValues("phone");
		    Integer orderId = Integer.parseInt(req.getParameter("orderId"));
		    Orders order= orderSvc.getOneOrder(orderId);
		    String orderIdstr = String.valueOf(orderId);
		    String orderNo = order.getOrderNo();
		    String orderStatus = order.getOrderStatus();
		    String tourist = String.valueOf(order.getTourist());
		    
		
		    
			for(int i = 0; i < passengers.length; i++) {
				Passenger passenger = passengerService.getByPk(Integer.parseInt(passengers[i]));
				passenger.setPassengerName(passengerNames[i]);
				passenger.setIdno(idNumbers[i]);
				passenger.setPhone(phoneNumbers[i]);
				try {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // 日期格式
					java.util.Date utilDate = sdf.parse(birthDates[i]);
					Date date = new Date(utilDate.getTime());
					passenger.setBirth(date);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				passengerService.updatePassenger(passenger);
			}
			
			String redirectUrl = "http://localhost:8081/LazyTravel/admin/passengerDetails.jsp";
			redirectUrl += "?order_id=" + orderIdstr
	                + "&order_no=" + orderNo
	                + "&order_status=" + orderStatus
	                + "&tourist=" + tourist;
			 try {
			        res.sendRedirect(redirectUrl);
			    } catch (IOException e) {
			        e.printStackTrace();
			    }

	}

	private String passsengerDetail_modify(HttpServletRequest req, HttpServletResponse res) {
		Integer orderId = Integer.valueOf(req.getParameter("orderId").trim());
		System.out.println(orderId);
		Orders order = orderSvc.getOneOrder(orderId);
		List<Passenger> passengers = passengerService.getPassengersByOrderId(orderId);
		
		req.setAttribute("order", order);
		req.setAttribute("passengers", passengers);
		
		return "/admin/passengerEditor.jsp";
	}

	private void insertPassengers(HttpServletRequest req, HttpServletResponse res) {
		
			Integer orderId = Integer.parseInt(req.getParameter("orderId"));
;
			String jsondata = req.getParameter("passengers");
			System.out.println(jsondata);
			JSONArray jsonArray = new JSONArray(jsondata);
			
			for(int i = 0 ; i < jsonArray.length(); i++) {
				 JSONObject jsonObject = jsonArray.getJSONObject(i);
				
			        String name = jsonObject.getString("name");
			        String idno = jsonObject.getString("idno");
			        String birth = jsonObject.getString("birth");
			        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		            java.util.Date sdfsBirth;
					try {
						sdfsBirth = sdf.parse(birth);
						Date trasBirth =  new Date(sdfsBirth.getTime());
						String phone = jsonObject.getString("phone");
						
						
						Passenger passenger = new Passenger();
						passenger.setOrderId(orderId);
						passenger.setPassengerName(name);
						passenger.setIdno(idno);
						passenger.setBirth(trasBirth);
						passenger.setPhone(phone);

						passengerService.addPassenger(passenger);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

			}

			



	}

	private void getPasDetails(HttpServletRequest req, HttpServletResponse res) {
		
	    try {
	    	Integer orderId = Integer.parseInt(req.getParameter("orderId"));
			List<Passenger> passengers = passengerService.getPassengersByOrderId(orderId);
			Gson gson = new Gson();
			String jsonResponse = gson.toJson(passengers);
			res.setContentType("application/json; charset=UTF-8");
			System.out.println(jsonResponse);
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
	
	
	
}
