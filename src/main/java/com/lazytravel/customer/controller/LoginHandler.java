package com.lazytravel.customer.controller;

import com.lazytravel.customer.entity.Customer;
import com.lazytravel.customer.service.CustomerService;
import com.lazytravel.customer.service.CustomerServiceImpl;
import com.lazytravel.customer.util.CustomerStatus;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.UUID;

@WebServlet(name = "LoginHandler", value = "/customer/login.do")
public class LoginHandler extends HttpServlet {
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
        res.setContentType("text/html; charset=UTF-8");

        String indexPath = "/index.jsp";
        String loginPath = "/customer/login.jsp";
        String authPath = "/customer/register-auth.jsp";

        final String email = req.getParameter("email");
        final String passwd = req.getParameter("customer_passwd");
        Customer customer = customerService.login(email, passwd);
        if (customer != null) {
            if (customer.getCustomerStatus().equals(CustomerStatus.BANNED.getValue())) {
                // 帳號被停權
                req.setAttribute("isBanned", true);
                // 重導回登入頁面
                RequestDispatcher dispatcher = req.getRequestDispatcher(loginPath);
                dispatcher.forward(req, res);
                return;
            } else if (customer.getCustomerStatus().equals(CustomerStatus.ACTIVE.getValue())) {
                // 登入成功
                HttpSession session = req.getSession();
                session.setAttribute("customer", customer);

                // 產生Unique Identifier，做下次自動登入用
                String token = UUID.randomUUID().toString().replace("-", "");
                Cookie cookie = new Cookie("AUTH_TOKEN", token); // 存進cookie
                cookie.setMaxAge(7 * 24 * 60 * 60); // 一星期內有效
                res.addCookie(cookie);
                // token也存進redis
                customerService.setAutoLogin(customer.getCustomerId(), token);

                // 重導回首頁
//                RequestDispatcher dispatcher = req.getRequestDispatcher(indexPath);
                // 重導回先前的頁面
                String location = (String) session.getAttribute("location");
                System.out.println("original location: " + location);
                RequestDispatcher dispatcher = req.getRequestDispatcher(location);

                dispatcher.forward(req, res);
            } else if (customer.getCustomerStatus().equals(CustomerStatus.NOT_AUTH.getValue())) {
                // 暫存會員資料
                HttpSession session = req.getSession();
                session.setAttribute("tmpCustomer", customer);
                req.setAttribute("notAuth", true);
                // 帳號尚未驗證，重導至驗證頁面
                RequestDispatcher dispatcher = req.getRequestDispatcher(authPath);
                dispatcher.forward(req, res);
            }
        } else {
            // 登入失敗
            req.setAttribute("loginFailed", true);

            // 重導回登入頁面
            RequestDispatcher dispatcher = req.getRequestDispatcher(loginPath);
            dispatcher.forward(req, res);
            return;
        }
    }
}