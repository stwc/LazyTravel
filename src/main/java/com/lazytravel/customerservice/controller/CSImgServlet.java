//package com.lazytravel.customerservice.controller;
//
//import java.io.IOException;
//import java.sql.Date;
//import java.sql.Timestamp;
//import java.util.ArrayList;
//import java.util.Base64;
//import java.util.List;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.lazytravel.customer.entity.Customer;
//import com.lazytravel.customerservice.entity.CSImg;
//import com.lazytravel.customerservice.entity.CSMessage;
//import com.lazytravel.customerservice.service.CSImgService;
//import com.lazytravel.customerservice.service.CSImgServiceImpl;
//import com.lazytravel.customerservice.service.CSMessageService;
//import com.lazytravel.customerservice.service.CSMessageServiceImpl;
//
//@WebServlet(value = "/customerService/CSImg.do")
//public class CSImgServlet extends HttpServlet {
//
//	private CSImgService csImgService;
//
//	@Override
//	public void init() throws ServletException {
//		csImgService = new CSImgServiceImpl();
//	}
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		doPost(req, res);
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//
//		String action = req.getParameter("action");
//		String forwardPath = "";
//		switch (action) {
//		case "getOne_For_Display":
//			// // 來自select_page.jsp的請求
//			forwardPath = getOneDisplay(req, res);
//			break;
//		case "getOne_For_Update":
//			// 來自listAllEmp.jsp的請求
//			forwardPath = getOneUpdate(req, res);
//			break;
//		case "update":
//			// 來自update_emp_input.jsp的請求
//			forwardPath = update(req, res);
//			break;
//		case "insert":
//			// 來自addEmp.jsp的請求
//			forwardPath = insert(req, res);
//			break;
//		default:
//			forwardPath = "/customerService/select_CSImg_page.jsp";
//		}
//
//		res.setContentType("text/html; charset=UTF-8");
//		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
//		dispatcher.forward(req, res);
//	}
//
//	// 查詢
//	private String getOneDisplay(HttpServletRequest req, HttpServletResponse res) {
//		// 錯誤處理
//		List<String> errorMsgs = new ArrayList<>();
//		req.setAttribute("errorMsgs", errorMsgs);
//		// ----------------------------------------------------//
//		String str = req.getParameter("ImgId");
//		if (str == null || str.trim().isEmpty())
//			errorMsgs.add("請輸入圖片編號");
//		if (!errorMsgs.isEmpty())
//			return "/customerService/select_CSImg_page.jsp";
//		// ------------------1.-------------------------//
//		Integer ImgId = null;
//		try {
//			ImgId = Integer.valueOf(str);
//		} catch (Exception e) {
//			errorMsgs.add("圖片ID格式不正確");
//		}
//		if (!errorMsgs.isEmpty())
//			return "/customerService/select_CSImg_page.jsp";
//		// ------------------2.-------------------------//
//		csImgService = new CSImgServiceImpl();
//		CSImg csImg = csImgService.getCSImgByCSImgId(ImgId);
//		if (csImg == null)
//			errorMsgs.add("查無資料");
//		if (!errorMsgs.isEmpty())
//			return "/customerService/select_CSImg_page.jsp";
//		// ------------------3.-------------------------//
//		req.setAttribute("csImg", csImg);
//		return "/customerService/listOneCSImg.jsp";
//	}
//
//	// 2.修改
//	private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
//		Integer ImgId = Integer.valueOf(req.getParameter("ImgId"));
//		CSImg csImg = csImgService.getCSImgByCSImgId(ImgId);
//
//		req.setAttribute("csImg", csImg);
//		return "/customerService/update_CSImg_input.jsp";
//	}
//
//	private String update(HttpServletRequest req, HttpServletResponse res) {
//		// 錯誤處理
//		List<String> errorMsgs = new ArrayList<>();
//		req.setAttribute("errorMsgs", errorMsgs);
//		// -------------------1.---------------------------------//
//		Integer ImgId = Integer.valueOf(req.getParameter("Imgid"));
//		Integer csmessageId = Integer.valueOf(req.getParameter("MessageId"));
//		// ↓↓↓↓↓↓↓↓↓↓↓圖片處理//
//		String base64Img = req.getParameter("img");
//		byte[] decodedBytes = Base64.getDecoder().decode(base64Img);
//		// 圖片處理//
//		Timestamp createTime = null;
//		try {
//			createTime = java.sql.Timestamp.valueOf(req.getParameter("createTime").trim());
//		} catch (Exception e) {
//			createTime = new java.sql.Timestamp(System.currentTimeMillis());
//			errorMsgs.add("上傳時間");
//		}
//		// -----------------------------//
//		CSImg csImg = new CSImg();
//		csImg.setImgId(ImgId);
//		csImg.setImg(decodedBytes);
//		csImg.setCreateTime(createTime);
//
//		CSMessage csMessage = new CSMessage();
//		csMessage.setMessageId(csmessageId);
//		csImg.setCsMessage(csMessage);
//
//		if (!errorMsgs.isEmpty()) {
//			req.setAttribute("csImg", csImg);
//			return "/customerService/update_CSImg_input.jsp";
//		}
//		// -------------------2.---------------------------------//
//		// 修改資料
//		csImgService.updateCSImg(csImg);
//
//		req.setAttribute("csImg", csImgService.getCSImgByCSImgId(ImgId));
//
//		return "/customerService/listOneCSImg.jsp";
//	}
//
////3.新增
//	private String insert(HttpServletRequest req, HttpServletResponse res) {
//		// 錯誤處理
//		List<String> errorMsgs = new ArrayList<>();
//		req.setAttribute("errorMsgs", errorMsgs);
//		// -------------------1.---------------------------------//
//		Integer csmessageId = Integer.valueOf(req.getParameter("MessageId"));
//		// ↓↓↓↓↓↓↓↓↓↓↓圖片處理//
//		String base64Img = req.getParameter("img");
//		byte[] decodedBytes = Base64.getDecoder().decode(base64Img);
//		// 圖片處理//
//		Timestamp createTime = null;
//		try {
//			createTime = java.sql.Timestamp.valueOf(req.getParameter("createTime").trim());
//		} catch (Exception e) {
//			createTime = new java.sql.Timestamp(System.currentTimeMillis());
//			errorMsgs.add("上傳時間");
//		}
//		// -----------------------------//
//		CSImg csImg = new CSImg();
//		csImg.setImg(decodedBytes);
//		csImg.setCreateTime(createTime);
//
//		CSMessage csMessage = new CSMessage();
//		csMessage.setMessageId(csmessageId);
//		csImg.setCsMessage(csMessage);
//
//		if (!errorMsgs.isEmpty()) {
//			req.setAttribute("csImg", csImg);
//			return "/customerService/update_CSImg_input.jsp";
//		}
//
//		// -------------------2.---------------------------------//
//		if (!errorMsgs.isEmpty()) {
//			req.setAttribute("csImg", csImg);
//			return "/customerService/addCSImg.jsp";
//		}
//		// -------------------3.---------------------------------//
//		// 新增資料
//		csImgService.addCSImg(csImg);
//
//		return "/customerService/listAlICSImg.jsp";
//	}
//}