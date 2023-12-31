package com.lazytravel.customer.controller;

import com.google.gson.Gson;
import com.lazytravel.customer.service.CustomerService;
import com.lazytravel.customer.service.CustomerServiceImpl;
import com.lazytravel.customer.entity.Customer;
import com.lazytravel.customer.util.Utils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.InputStream;
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
            case "update":
                // 來自customer-modify.jsp的請求
                forwardPath = update(req, res);
                break;
            case "resetpw":
                // 來自customer-resetpw.jsp的請求
                forwardPath = resetPw(req, res);
                break;
            case "logout":
                // 來自header.jsp的請求
                forwardPath = logout(req, res);
                break;
            case "getOneModify":
                forwardPath = getOneModify(req, res);
                break;
            case "changeStatus":
                forwardPath = changeStatus(req, res);
                break;
            case "sendForgotPwMail":
                forwardPath = sendForgotPwMail(req, res);
                break;
            case "forgotpw":
                forwardPath = forgotPw(req, res);
                break;
            default:
                forwardPath = "/index.jsp";
        }

        res.setContentType("text/html; charset=UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
        dispatcher.forward(req, res);
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
        if (!customer.getEmail().equals(email) && customerService.emailExists(email) != null) {
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

        byte[] avatar = null;
        try (InputStream in = req.getPart("avatar").getInputStream()) {
            if (in.available() != 0)
                avatar = in.readAllBytes();
            else
                avatar = customerService.getOneCustomer(customerId).getAvatar();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 假如輸入格式錯誤的，備份還原使用者輸入過的資料
        customer.setEmail(email);
        customer.setCustomerName(customerName);
        customer.setNickname(nickname);
        customer.setSex(sex);
        customer.setPhone(phone);
        customer.setIdno(idno);
        customer.setBirth(birth);
        customer.setAddress(address);
        customer.setAvatar(avatar);

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
        // session移除會員資料
        session.removeAttribute("customer");
        // redis移除登入token
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("AUTH_TOKEN")) {
                    String token = cookie.getValue();
                    customerService.removeAuthToken(token);
                    break;
                }
            }
        }
        // cookie移除登入token
        res.addCookie(new Cookie("AUTH_TOKEN", ""));

        System.out.println("[會員] 會員登出");
        return "/login.jsp";
    }

    private String getOneModify(HttpServletRequest req, HttpServletResponse res) {
        Integer customerId = Integer.valueOf(req.getParameter("customer_id"));

        Customer customer = customerService.getOneCustomer(customerId);

        req.setAttribute("customer", customer);
        return "/admin/customer-modify.jsp";
    }

    private String changeStatus(HttpServletRequest req, HttpServletResponse res) {
        Integer customerId = Integer.valueOf(req.getParameter("customer_id"));
        String status = req.getParameter("customer_status");
        Customer customer = customerService.getOneCustomer(customerId);
        customer.setCustomerStatus(status);
        customerService.updateCustomer(customer);
        return "/admin/customer.jsp";
    }

    private String sendForgotPwMail(HttpServletRequest req, HttpServletResponse res) {
        String email = req.getParameter("email");
        Customer customer = customerService.emailExists(email);
        if (customer == null) {
            req.setAttribute("emailNotExists", true);
            return "/customer/forgotpw.jsp";
        } else {
            customerService.sendForgotPwMail(customer, Utils.getPath(req, "/customer/resetpw.jsp"));
            req.setAttribute("hideResetPw", true);
            return "/customer/resetpw.jsp";
        }
    }

    private String forgotPw(HttpServletRequest req, HttpServletResponse res) {
        String newPasswd = req.getParameter("customer_passwd");
        Integer customerId = Integer.valueOf(req.getParameter("customerId"));
        String authCode = req.getParameter("authCode");

        boolean isResetPwSuccess = customerService.forgotPassword(customerId, authCode, newPasswd);
        if (!isResetPwSuccess) {
            req.setAttribute("resetPwFailed", true);
            req.setAttribute("hideResetPw", true);
            return "/customer/resetpw.jsp";
        } else {
            return "/login.jsp";
        }
    }
}