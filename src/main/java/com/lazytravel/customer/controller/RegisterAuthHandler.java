package com.lazytravel.customer.controller;

import com.lazytravel.customer.entity.Customer;
import com.lazytravel.customer.service.CustomerService;
import com.lazytravel.customer.service.CustomerServiceImpl;
import com.lazytravel.customer.util.AuthCodeUtil;
import com.lazytravel.customer.util.AuthStatus;
import com.lazytravel.customer.util.CustomerStatus;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegisterAuthHandler", value = "/customer/register-auth.do")
public class RegisterAuthHandler extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String forwardPath = "";
        String action = req.getParameter("action");
        if ("resend".equals(action)) {
            // 點「再次發送驗證信」button會進來此段落

            // 登入時若帳號信箱未驗證會重導至register-auth.jsp，按再次發送驗證信後，參數抓session中暫存的tmpCustomer
            Customer customer = (Customer) req.getSession().getAttribute("tmpCustomer");
            req.getSession().removeAttribute("tmpCustomer");

            if (customer == null) {
                // 若錯誤訊息出現驗證碼過期的話，參數抓輸入的網址列
                Integer customerId = Integer.valueOf(req.getParameter("customer_id"));
                customer = customerService.getOneCustomer(customerId);
            }

            customerService.sendRegisterMail(customer, AuthCodeUtil.getPath(req, "/customer/register-auth.do"));
            req.setAttribute("hideResend", true);
            forwardPath = "/customer/register-auth.jsp";
        } else {
            // 輸入網址或點擊連結進來的
            Integer customerId = Integer.valueOf(req.getParameter("id"));
            String authCode = req.getParameter("code");
            AuthStatus authStatus = customerService.isAuthSuccess(customerId, authCode);
            switch (authStatus) {
                case EXPIRED -> {
                    req.setAttribute("isExpired", true);
                    forwardPath = "/customer/register-auth.jsp";
                }
                case SUCCESS -> {
                    Customer customer = customerService.getOneCustomer(customerId);
                    customer.setCustomerStatus(CustomerStatus.ACTIVE.getValue());
                    customerService.updateCustomer(customer);
                    forwardPath = "/customer/register-success.jsp";
                }
                case FAILED -> {
                    req.setAttribute("isFailed", true);
                    forwardPath = "/customer/register-auth.jsp";
                }
            }
        }

        res.setContentType("text/html; charset=UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
        dispatcher.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doGet(req, res);
    }

}