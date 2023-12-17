package com.lazytravel.order.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.lazytravel.order.entity.Passenger;
import com.lazytravel.order.service.CouponService;
import com.lazytravel.order.service.PassengerService;

@WebServlet(name = "PassengerServlet" , urlPatterns = {"/order/passenger.do" , "/admin/passenger.do"})
public class PassengerServlet extends HttpServlet{
	
	
	private PassengerService passengerService;
	
	@Override
	public void init() throws ServletException{
		passengerService = new PassengerService();
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
		
			
//		case  "update" :
//			forwardPath = update(req , res);
//			break;
//
//		case "insert":
//			forwardPath = insert(req, res);
//			break;
			

		default:
			forwardPath = "XXX";
		}
		
		res.setContentType("text/html charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
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
