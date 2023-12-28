package com.lazytravel.customerservice.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.lazytravel.customerservice.entity.CSCustomerVO;
import com.lazytravel.customerservice.entity.CSMessage;
import com.lazytravel.customerservice.service.CSMailService;
import com.lazytravel.customerservice.service.CSMailServiceImpl;
import com.lazytravel.customerservice.service.CSMessageService;
import com.lazytravel.customerservice.service.CSMessageServiceImpl;

@WebServlet(name = "CSMsgGetCustomerListServlet", value = "/customerService/getCustomerList")
public class CSMsgGetCustomerListServlet extends HttpServlet {

	private CSMailService csMailService;
	private Gson gson = new Gson();

	@Override
	public void init() throws ServletException {
		csMailService = new CSMailServiceImpl();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String mailId = req.getParameter("mailId");
		List<CSCustomerVO> CSCustomerList = csMailService.getCSCustomerList().stream()
				.filter(a -> a.getMailId().equals(mailId)).toList();

		String csMessageListString = gson.toJson(CSCustomerList);

		ServletOutputStream out = res.getOutputStream();
		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		out.write(csMessageListString.getBytes(StandardCharsets.UTF_8));
		out.flush();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}

}