package com.lazytravel.customerservice.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.lazytravel.customer.entity.Customer;
import com.lazytravel.customerservice.entity.CSMail;
import com.lazytravel.customerservice.service.CSMailService;
import com.lazytravel.customerservice.service.CSMailServiceImpl;

@WebServlet(value = "/customerService/CSMail.do")
public class CSMailServlet extends HttpServlet {

	private CSMailService csMailService;

	@Override
	public void init() throws ServletException {
		csMailService = new CSMailServiceImpl();
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
		case "getOne_For_Display":
			// // 來自select_page.jsp的請求
			forwardPath = getOneDisplay(req, res);
			break;
		case "getOne_For_Update":
			// 來自listAllEmp.jsp的請求
			forwardPath = getOneUpdate(req, res);
			break;
		case "update":
			// 來自update_emp_input.jsp的請求
			forwardPath = update(req, res);
			break;
		case "toBackContent":
			// 來自addEmp.jsp的請求
			forwardPath = toBackContent(req, res);
			break;			
		case "backinsert":
			// 來自addEmp.jsp的請求
			forwardPath = backinsert(req, res);
			break;
		case "frontinsert":
			// 來自addEmp.jsp的請求
			forwardPath = frontinsert(req, res);
			break;
		case "toFrontContent":
			// 來自addEmp.jsp的請求
			forwardPath = toFrontContent(req, res);
			break;
		default:
			forwardPath = "/customerService/select_CSMail_page.jsp";
		}

		res.setContentType("text/html; charset=UTF-8");
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
		dispatcher.forward(req, res);
	}


	// 查詢
	private String getOneDisplay(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);
		// ----------------------------------------------------//
		String str = req.getParameter("mailId");
		if (str == null || str.trim().isEmpty())
			errorMsgs.add("請輸入信件編號");
		if (!errorMsgs.isEmpty())
			return "/customerService/select_CSMail_page.jsp";
		// ------------------1.-------------------------//
		Integer mailId = null;
		try {
			mailId = Integer.valueOf(str);
		} catch (Exception e) {
			errorMsgs.add("信件ID格式不正確");
		}
		if (!errorMsgs.isEmpty())
			return "/customerService/select_CSMail_page.jsp";
		// ------------------2.-------------------------//
		csMailService = new CSMailServiceImpl();
		CSMail csMail = csMailService.getCSMailByCSMailId(mailId);
		if (csMail == null)
			errorMsgs.add("查無資料");
		if (!errorMsgs.isEmpty())
			return "/customerService/select_CSMail_page.jsp";
		// ------------------3.-------------------------//
		req.setAttribute("csMail", csMail);
		return "/customerService/listOneCSMail.jsp";
	}

	// 2.修改
	private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
		Integer mailId = Integer.valueOf(req.getParameter("mailId"));
		CSMail csMail = csMailService.getCSMailByCSMailId(mailId);

		req.setAttribute("csMail", csMail);
		return "/customerService/update_CSMail_input.jsp";
	}

	private String update(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);
		// -------------------1.---------------------------------//
		Integer mailId = Integer.valueOf(req.getParameter("mailId"));
		Integer customerId = Integer.valueOf(req.getParameter("customer_id"));
		String title = String.valueOf(req.getParameter("title"));
		String csmailStatus = (String.valueOf(req.getParameter("csMail_status"))).trim();

		Timestamp lastMsgTime = null;
		try {
			lastMsgTime = java.sql.Timestamp.valueOf(req.getParameter("lastMsgTime").trim());
		} catch (Exception e) {
			lastMsgTime = new java.sql.Timestamp(System.currentTimeMillis());
			errorMsgs.add("最後訊息時間");
		}

		Timestamp createTime = null;
		try {
			createTime = java.sql.Timestamp.valueOf(req.getParameter("createTime").trim());
		} catch (Exception e) {
			createTime = new java.sql.Timestamp(System.currentTimeMillis());
			errorMsgs.add("建立時間");
		}
		// -------------------------------------------------------------------//
		CSMail csMail = new CSMail();
		csMail.setMailId(mailId);
		csMail.setCreateTime(createTime);
		
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		csMail.setCustomer(customer);

		if (!errorMsgs.isEmpty()) {
			req.setAttribute("csMail", csMail);
			return "/customerService/update_CSMail_input.jsp";
		}
		// -------------------2.---------------------------------//
		// 修改資料
		csMailService.updateCSMail(csMail);

		req.setAttribute("csMail", csMailService.getCSMailByCSMailId(mailId));

		return "/customerService/listOneCSMail.jsp";
	}

	
	private String toBackContent(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		
		Integer mailId = Integer.valueOf(req.getParameter("mail_Id").trim());
		req.setAttribute("mailId", mailId);
		
		return "/customerService/backContent.jsp";
	}

	
//3.新增
	private String backinsert(HttpServletRequest req, HttpServletResponse res) {
//		// 錯誤處理
//		List<String> errorMsgs = new ArrayList<>();
//		req.setAttribute("errorMsgs", errorMsgs);
		
		// -------------------1.---------------------------------//
		HttpSession session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		
		String mailContent = req.getParameter("mailContent").trim();
		
		Integer mailId = Integer.valueOf(req.getParameter("mail_Id").trim());
		
		CSMail cSMail = csMailService.getCSMailByCSMailId(mailId);
		
		cSMail.setAnswer(mailContent);
		cSMail.setCsMailStatus("1");
		
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		cSMail.setRECEIVED_TIME(timestamp);;
		// -------------------2.---------------------------------//
// 假如輸入格式錯誤的，備份選原使用者輸入過的資料
//		if (!errorMsgs.isEmpty()) {
//			req.setAttribute("csMail", csMail);
//			return "/customerService/addCSMail.jsp";
//		}
		// -------------------3.---------------------------------//
		// 修改資料
		csMailService.updateCSMail(cSMail);
		return "/customerService/backContenMail.jsp";
	}
	
	
	
	private String toFrontContent(HttpServletRequest req, HttpServletResponse res) {
		HttpSession session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		String  customerName = customer.getCustomerName();
		
		
		req.setAttribute("customerName", customerName);
		
		return "/customerService/frontContent.jsp";
	}	
	
	private String frontinsert(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理
//		List<String> errorMsgs = new ArrayList<>();
//		req.setAttribute("errorMsgs", errorMsgs);
		// -------------------1.---------------------------------//
		HttpSession session = req.getSession();
		Customer customer = (Customer) session.getAttribute("customer");
		
		Timestamp righNow = java.sql.Timestamp.from(Instant.now());
		String mailquestions = String.valueOf(req.getParameter("mailquestions"));
		
		CSMail cSMail = new CSMail();
		cSMail.setCustomer(customer);
		cSMail.setQuestions(mailquestions);
		cSMail.setCreateTime(righNow);
		cSMail.setCsMailStatus("0");
		

		// -------------------2.---------------------------------//
		// 假如輸入格式錯誤的，備份選原使用者輸入過的資料
//		if (!errorMsgs.isEmpty()) {
//			req.setAttribute("csMail", csMail);
//			return "/customerService/addCSMail.jsp";
//		}
		// -------------------3.---------------------------------//
		// 新增資料
		csMailService.addCSMail(cSMail);

		return "/customerService/frontContentMail.jsp";
	}
}
