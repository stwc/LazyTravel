package com.lazytravel.customer.controller;

import com.google.gson.Gson;
import com.lazytravel.customer.service.CustomerService;
import com.lazytravel.customer.service.CustomerServiceImpl;
import com.lazytravel.customer.entity.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = {"/customer/customer.do", "/customerCenter/customer.do", "/admin/customer.do"})
@MultipartConfig(fileSizeThreshold = 0 * 1024 * 1024, maxFileSize = 1 * 1024 * 1024, maxRequestSize = 10 * 1024 * 1024)
public class CustomerServlet extends HttpServlet {
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

        String action = req.getParameter("action");
        String forwardPath = "";
        switch (action) {
            case "getall":
                String jsonStr = new Gson().toJson(customerService.getAll());
                res.setContentType("application/json; charset=UTF-8");
                res.getWriter().write(jsonStr);
                return;
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
            case "resetpw":
                forwardPath = resetPw(req, res);
                break;
            case "logout":
                forwardPath = logout(req, res);
                break;
            default:
                forwardPath = "/index.jsp";
        }

        res.setContentType("text/html; charset=UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
        dispatcher.forward(req, res);
    }

    private String getOneDisplay(HttpServletRequest req, HttpServletResponse res) {
        // 錯誤處理
        List<String> errorMsgs = new ArrayList<>();
        req.setAttribute("errorMsgs", errorMsgs);

        String str = req.getParameter("customer_id");
        if (str == null || str.trim().isEmpty())
            errorMsgs.add("請輸入員工編號");
        if (!errorMsgs.isEmpty())
            return "/example/select_page.jsp";

        Integer customerId = null;
        try {
            customerId = Integer.valueOf(str);
        } catch (Exception e) {
            errorMsgs.add("會員ID格式不正確");
        }
        if (!errorMsgs.isEmpty())
            return "/example/select_page.jsp";

        Customer customer = customerService.getOneCustomer(customerId);
        if (customer == null)
            errorMsgs.add("查無資料");
        if (!errorMsgs.isEmpty())
            return "/example/select_page.jsp";

        req.setAttribute("customer", customer);
        return "/example/listOneEmp.jsp";
    }

    private String getOneUpdate(HttpServletRequest req, HttpServletResponse res) {
        Integer customerId = Integer.valueOf(req.getParameter("customer_id"));

        Customer customer = customerService.getOneCustomer(customerId);

        req.setAttribute("customer", customer);
        return "/example/update_emp_input.jsp";
    }

    private String update(HttpServletRequest req, HttpServletResponse res) {
        // 錯誤處理
        List<String> errorMsgs = new ArrayList<>();
        req.setAttribute("errorMsgs", errorMsgs);

        String tmpStr = req.getParameter("customer_id");
        Integer customerId = Integer.valueOf(tmpStr);
        Customer customer = customerService.getOneCustomer(customerId);

        // 抓前端傳來的參數
        String email = req.getParameter("email");
        if (!customer.getEmail().equals(email) && customerService.isEmailExists(email)) {
            req.setAttribute("updateFailed", true);
            errorMsgs.add("此Email信箱已有人使用");
        }

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
        customer.setEmail(email);
        customer.setCustomerName(customerName);
        customer.setNickname(nickname);
        customer.setSex(sex);
        customer.setPhone(phone);
        customer.setIdno(idno);
        customer.setBirth(birth);
        customer.setAddress(address);

        // 輸入資料錯誤，請重新輸入
        if (!errorMsgs.isEmpty()) {
//            req.setAttribute("customer", customer);
            return "/customerCenter/customer-modify.jsp";
        }

        // 更新成功
        customerService.updateCustomer(customer);
        req.getSession().setAttribute("customer", customer);
        return "/customerCenter/customer-center.jsp";
    }

    private String resetPw(HttpServletRequest req, HttpServletResponse res) {
        String email = ((Customer) req.getSession().getAttribute("customer")).getEmail();
        String oldPasswd = req.getParameter("customer_old_passwd");
        String newPasswd = req.getParameter("customer_passwd");

        boolean isResetPwSuccess = customerService.resetPassword(email, oldPasswd, newPasswd);
        if (!isResetPwSuccess) {
            req.setAttribute("isPwWrong", true);
            return "/customerCenter/customer-resetpw.jsp";
        } else {
            return "/customerCenter/customer-center.jsp";
        }
    }

    private String logout(HttpServletRequest req, HttpServletResponse res) {
        HttpSession session = req.getSession();
        session.removeAttribute("customer");
        return "/customer/login.jsp";
    }
}