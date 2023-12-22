package com.lazytravel.customer.controller;

import com.lazytravel.customer.entity.Customer;
import com.lazytravel.customer.service.CustomerService;
import com.lazytravel.customer.service.CustomerServiceImpl;
import com.lazytravel.customer.util.CustomerStatus;
import com.password4j.Hash;
import com.password4j.Password;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "RegisterHandler", value = "/customer/register.do")
public class RegisterHandler extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = new CustomerServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String forwardPath = "";
        forwardPath = insert(req, res);

        res.setContentType("text/html; charset=UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
        dispatcher.forward(req, res);
    }

    private String insert(HttpServletRequest req, HttpServletResponse res) {
        // 錯誤處理
        List<String> errorMsgs = new ArrayList<>();
        req.setAttribute("errorMsgs", errorMsgs);

        // 抓前端送來的參數
        String email = req.getParameter("email");
        if (customerService.isEmailExists(email)) {
            req.setAttribute("insertFailed", true);
            errorMsgs.add("此Email信箱已註冊過");
        }

        String customerPasswd = req.getParameter("customer_passwd");
        Hash hash = Password.hash(customerPasswd).withBcrypt();
        String hashedPw = hash.getResult();

        String customerName = req.getParameter("customer_name");
        String nickname = req.getParameter("nickname");
        String sex = req.getParameter("sex");
        String phone = req.getParameter("phone");
        String idno = req.getParameter("idno");

        Date birth;
        try {
            birth = Date.valueOf(req.getParameter("birth").trim());
        } catch (IllegalArgumentException e) {
            birth = new Date(System.currentTimeMillis());
            errorMsgs.add("請輸入日期!");
        }

        String address = req.getParameter("address");

        // 假如輸入格式錯誤的，備份還原使用者輸入過的資料
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setCustomerPasswd(hashedPw);
        customer.setCustomerName(customerName);
        customer.setNickname(nickname);
        customer.setSex(sex);
        customer.setPhone(phone);
        customer.setIdno(idno);
        customer.setBirth(birth);
        customer.setAddress(address);
        customer.setCustomerStatus(CustomerStatus.ACTIVE.getValue()); // 先暫時讓註冊會員狀態起始值為1

        // 輸入資料錯誤，請重新輸入
        if (!errorMsgs.isEmpty()) {
            req.setAttribute("customer", customer);
            return "/customer/register.jsp";
        }

        // 註冊成功
        customerService.addCustomer(customer);
        return "/customer/register-success.jsp";
    }
}