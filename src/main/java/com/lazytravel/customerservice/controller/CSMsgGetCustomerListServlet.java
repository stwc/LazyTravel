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
import com.lazytravel.customerservice.service.CSMailService;
import com.lazytravel.customerservice.service.CSMailServiceImpl;

@WebServlet(name = "CSMsgGetCustomerListServlet", value = "/customerService/getCustomerList")
public class CSMsgGetCustomerListServlet extends HttpServlet {

	private CSMailService csMailService;
	private Gson gson = new Gson();

	@Override
	public void init() throws ServletException {
		csMailService = new CSMailServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	}

}