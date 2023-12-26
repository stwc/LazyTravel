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
import com.lazytravel.order.entity.Passenger;
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
			
		case "insertPassengers":
		    insertPassengers(req, res);
		    break;
		
			
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
