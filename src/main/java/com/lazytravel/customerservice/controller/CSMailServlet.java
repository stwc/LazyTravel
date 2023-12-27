package com.lazytravel.customerservice.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		case "insert":
			// 來自addEmp.jsp的請求
			forwardPath = insert(req, res);
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
		csMail.setTitle(title);
		csMail.setCsMailStatus(csmailStatus);
		csMail.setCreateTime(createTime);
		csMail.setLastMsgTime(lastMsgTime);
		
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

//3.新增
	private String insert(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);
		// -------------------1.---------------------------------//
		Integer customerId = Integer.valueOf(req.getParameter("customer_id"));
		String title = String.valueOf(req.getParameter("title"));
		String csmailStatus = String.valueOf(req.getParameter("csmail_status"));

		Timestamp lastMsgTime = null;
		try {
			lastMsgTime = java.sql.Timestamp.valueOf(req.getParameter("upDateTime").trim());
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

		// 假如輸入格式錯誤的，備份選原使用者輸入過的資料
		CSMail csMail = new CSMail();
		csMail.setTitle(title);
		csMail.setCreateTime(createTime);
		csMail.setLastMsgTime(lastMsgTime);
		csMail.setCsMailStatus(csmailStatus);

		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		csMail.setCustomer(customer);

		// -------------------2.---------------------------------//
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("csMail", csMail);
			return "/customerService/addCSMail.jsp";
		}
		// -------------------3.---------------------------------//
		// 新增資料
		csMailService.addCSMail(csMail);

		return "/customerService/listAllCSMail.jsp";
	}
}
