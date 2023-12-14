package com.lazytravel.customer.controller;

import com.google.gson.Gson;
import com.lazytravel.customer.service.CustomerService;
import com.lazytravel.customer.service.CustomerServiceImpl;
import com.lazytravel.customer.entity.Customer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = {"/customer/customer.do", "/admin/customer.do"})
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
            case "insert":
                // 註冊，來自register.html的請求
                forwardPath = insert(req, res);
                break;
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
            default:
                forwardPath = "/example/select_page.jsp";
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

        String customerName = req.getParameter("customer_name");
        if (customerName == null || customerName.trim().isEmpty())
            errorMsgs.add("請輸入會員姓名");

        String nickname = req.getParameter("nickname");
        if (nickname == null || nickname.trim().isEmpty())
            errorMsgs.add("請輸入暱稱");

        String email = req.getParameter("email");
        String emailRegex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if (email == null || email.trim().isEmpty()) {
            errorMsgs.add("Email: 請勿空白");
        } else if (!email.trim().matches(emailRegex)) {
            errorMsgs.add("請輸入正確的Email格式");
        }

        // 目前沒對密碼做加密處理
        String customerPasswd = req.getParameter("customer_passwd");
        if (customerPasswd == null || customerPasswd.trim().isEmpty())
            errorMsgs.add("請輸入密碼");

        Date birth = null;
        try {
            birth = Date.valueOf(req.getParameter("birth").trim());
        } catch (IllegalArgumentException e) {
            birth = new Date(System.currentTimeMillis());
            errorMsgs.add("請輸入日期!");
        }

        // 簡易驗證身份證字號，不嚴謹
        String idno = req.getParameter("idno");
        String idnoRegex = "^[A-Z][12][0-9]{8}$";
        if (idno == null || idno.trim().isEmpty()) {
            errorMsgs.add("身份證: 請勿空白");
        } else if (!idno.trim().matches(idnoRegex)) {
            errorMsgs.add("請輸入正確的身份證格式");
        }

        String phone = req.getParameter("phone");
        String phoneRegex = "^09[0-9]{8}$";
        if (phone == null || phone.trim().isEmpty()) {
            errorMsgs.add("手機: 請勿空白");
        } else if (!phone.trim().matches(phoneRegex)) {
            errorMsgs.add("請輸入正確的手機格式");
        }

        String address = req.getParameter("address");
        if (address == null || address.trim().isEmpty())
            errorMsgs.add("請輸入地址");

        String str = req.getParameter("customer_point");
        if (str == null || str.trim().isEmpty())
            errorMsgs.add("請輸入會員金");
        Integer customerPoint = null;
        try {
            customerPoint = Integer.valueOf(str);
        } catch (Exception e) {
            errorMsgs.add("會員金請輸入數字");
        }


        // 這三個欄位不用做錯誤處理
        Integer customerId = Integer.valueOf(req.getParameter("customer_id"));
        String sex = req.getParameter("sex");
        String customerStatus = req.getParameter("customer_status");

        // 假如輸入格式錯誤的，備份選原使用者輸入過的資料
        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setCustomerName(customerName);
        customer.setNickname(nickname);
        customer.setSex(sex);
        customer.setPhone(phone);
        customer.setBirth(birth);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setCustomerPasswd(customerPasswd);
        customer.setCustomerStatus(customerStatus);
        customer.setIdno(idno);
        customer.setCustomerPoint(customerPoint);

        if (!errorMsgs.isEmpty()) {
            req.setAttribute("customer", customer);
            return "/example/update_emp_input.jsp";
        }

        // 修改資料
        customerService.updateCustomer(customer);
        req.setAttribute("customer", customerService.getOneCustomer(customerId));

        return "/example/listOneEmp.jsp";
    }

    private String insert(HttpServletRequest req, HttpServletResponse res) {
        // 錯誤處理
        List<String> errorMsgs = new ArrayList<>();
        req.setAttribute("errorMsgs", errorMsgs);

        String email = req.getParameter("email");
        String customerPasswd = req.getParameter("customer_passwd");
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
        customer.setCustomerPasswd(customerPasswd);
        customer.setCustomerName(customerName);
        customer.setNickname(nickname);
        customer.setSex(sex);
        customer.setPhone(phone);
        customer.setIdno(idno);
        customer.setBirth(birth);
        customer.setAddress(address);
        customer.setCustomerStatus("1"); // 先暫時讓註冊會員狀態起始值為1

        if (!errorMsgs.isEmpty()) {
            req.setAttribute("customer", customer);
            return "/customer/register.html";
        }

        // 新增資料
        customerService.addCustomer(customer);

        return "/customer/register-success.jsp";
    }
}