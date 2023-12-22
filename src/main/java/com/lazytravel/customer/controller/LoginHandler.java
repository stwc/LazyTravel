package com.lazytravel.customer.controller;

import com.lazytravel.customer.entity.Customer;
import com.lazytravel.customer.service.CustomerService;
import com.lazytravel.customer.service.CustomerServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

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

        final String email = req.getParameter("email");
        final String passwd = req.getParameter("customer_passwd");
        Customer customer = customerService.login(email, passwd);
        if (customer != null) {
            // 登入成功
            HttpSession session = req.getSession();
            session.setAttribute("customer", customer);

            // 確認session id
            System.out.println("session id: " + session.getId());

            // 重導回首頁
            String indexPath = req.getContextPath() + "/index.jsp";
            res.sendRedirect(indexPath);
        } else {
            // 登入失敗
            req.setAttribute("loginFailed", true);

            // 重導回登入頁面
            res.setContentType("text/html; charset=UTF-8");
            String loginPath = "/customer/login.jsp";
            RequestDispatcher dispatcher = req.getRequestDispatcher(loginPath);
            dispatcher.forward(req, res);
            return;
        }
    }
}