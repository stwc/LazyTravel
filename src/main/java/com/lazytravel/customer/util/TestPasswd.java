package com.lazytravel.customer.util;

import com.lazytravel.customer.entity.Customer;
import com.lazytravel.customer.service.CustomerService;
import com.lazytravel.customer.service.CustomerServiceImpl;
import com.password4j.Hash;
import com.password4j.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/TestPasswd")
public class TestPasswd extends HttpServlet {
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
        List<Customer> customerList = customerService.getAll();
        for (int i = 0; i < 6; ++i) {
            Customer customer = customerList.get(i);
            String customerPasswd = customer.getCustomerPasswd();
            Hash hash = Password.hash(customerPasswd).withBcrypt();
            String hashedPw = hash.getResult();
            customer.setCustomerPasswd(hashedPw);
            customerService.updateCustomer(customer);
        }

        res.setContentType("text/html; charset=Big5");
        PrintWriter out = res.getWriter();
        out.println("重置測試會員密碼成功！");
        String loginPath = req.getContextPath() + "/customer/login.jsp";
        out.println("<a href='" + loginPath + "'>返回登入頁面</a>");
    }
}