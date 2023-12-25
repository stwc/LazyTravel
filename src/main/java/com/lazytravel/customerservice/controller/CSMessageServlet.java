package com.lazytravel.customerservice.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lazytravel.customer.entity.Customer;
import com.lazytravel.customerservice.entity.CSMail;
import com.lazytravel.customerservice.entity.CSMessage;
import com.lazytravel.customerservice.service.CSMailService;
import com.lazytravel.customerservice.service.CSMailServiceImpl;
import com.lazytravel.customerservice.service.CSMessageService;
import com.lazytravel.customerservice.service.CSMessageServiceImpl;

@WebServlet(value = "/customerService/CSMessage.do")
public class CSMessageServlet extends HttpServlet {

	private CSMessageService csMessageService;

	@Override
	public void init() throws ServletException {
		csMessageService = new CSMessageServiceImpl();
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
			forwardPath = "/customerService/select_CSMessage_page.jsp";
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
		String str = req.getParameter("messageId");
		if (str == null || str.trim().isEmpty())
			errorMsgs.add("請輸入訊息編號");
		if (!errorMsgs.isEmpty())
			return "/customerService/select_CSMessage_page.jsp";
		// ------------------1.-------------------------//
		Integer messageId = null;
		try {
			messageId = Integer.valueOf(str);
		} catch (Exception e) {
			errorMsgs.add("訊息ID格式不正確");
		}
		if (!errorMsgs.isEmpty())
			return "/customerService/select_CSMessage_page.jsp";
		// ------------------2.-------------------------//
		csMessageService = new CSMessageServiceImpl();
		CSMessage csMessage = csMessageService.getCSMessageByCSMessageId(messageId);
		if (csMessage == null)
			errorMsgs.add("查無資料");
		if (!errorMsgs.isEmpty())
			return "/customerService/select_CSMessage_page.jsp";
		// ------------------3.-------------------------//
		req.setAttribute("csMessage", csMessage);
		return "/customerService/listOneCSMessage.jsp";
	}

	// 2.修改
	private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
		Integer messageId = Integer.valueOf(req.getParameter("messageId"));
		CSMessage csMessage = csMessageService.getCSMessageByCSMessageId(messageId);

		req.setAttribute("csMessage", csMessage);
		return "/customerService/update_CSMessage_input.jsp";
	}

	private String update(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);
		// -------------------1.---------------------------------//
		Integer messageId = Integer.valueOf(req.getParameter("messageId"));
		Integer mailId = Integer.valueOf(req.getParameter("mailId"));
		String content = String.valueOf(req.getParameter("content"));
		String messagefrom = String.valueOf(req.getParameter("messageFrom"));

		Timestamp createTime = null;
		try {
			createTime = java.sql.Timestamp.valueOf(req.getParameter("createTime").trim());
		} catch (Exception e) {
			createTime = new java.sql.Timestamp(System.currentTimeMillis());
			errorMsgs.add("發送時間");
		}
		// -----------------------------//
		CSMessage csMessage = new CSMessage();
		csMessage.setMessageId(messageId);
		csMessage.setContent(content);
		csMessage.setMessageFrom(messagefrom);
		csMessage.setCreateTime(createTime);

		CSMail csMail = new CSMail();
		csMail.setMailId(mailId);
		csMessage.setCsMail(csMail);

		if (!errorMsgs.isEmpty()) {
			req.setAttribute("csMessage", csMessage);
			return "/customerService/update_CSMessage_input.jsp";
		}
		// -------------------2.---------------------------------//
		// 修改資料
		csMessageService.updateCSMessage(csMessage);

		req.setAttribute("csMessage", csMessageService.getCSMessageByCSMessageId(messageId));

		return "/customerService/listOneCSMessage.jsp";
	}

//3.新增
	private String insert(HttpServletRequest req, HttpServletResponse res) {
		// 錯誤處理
		List<String> errorMsgs = new ArrayList<>();
		req.setAttribute("errorMsgs", errorMsgs);
		// -------------------1.---------------------------------//
		Integer mailId = Integer.valueOf(req.getParameter("mail_Id"));
		String content = String.valueOf(req.getParameter("content"));
		String messagefrom = String.valueOf(req.getParameter("message_from"));

		Timestamp createTime = null;
		try {
			createTime = java.sql.Timestamp.valueOf(req.getParameter("createTime").trim());
		} catch (Exception e) {
			createTime = new java.sql.Timestamp(System.currentTimeMillis());
			errorMsgs.add("發送時間");
		}

		// 假如輸入格式錯誤的，備份選原使用者輸入過的資料
		CSMessage csMessage = new CSMessage();
		csMessage.setContent(content);
		csMessage.setMessageFrom(messagefrom);
		csMessage.setCreateTime(createTime);

		CSMail csMail = new CSMail();
		csMail.setMailId(mailId);
		csMessage.setCsMail(csMail);

		// -------------------2.---------------------------------//
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("csMessage", csMessage);
			return "/customerService/addCSMessage.jsp";
		}
		// -------------------3.---------------------------------//
		// 新增資料
		csMessageService.addCSMessage(csMessage);

		return "/customerService/listAllCSMessage.jsp";
	}
}