package com.lazytravel.admin.controller;

import com.google.gson.Gson;
import com.lazytravel.admin.dto.LoginDTO;
import com.lazytravel.admin.entity.Users;
import com.lazytravel.admin.service.UsersService;
import com.lazytravel.admin.service.UsersServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "AdminLoginHandler", value = "/adminLogin.do")
public class AdminLoginHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // 登出
        req.getSession().removeAttribute("users");

        res.setContentType("text/html; charset=UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher("/admin-login.jsp");
        dispatcher.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");

        Gson gson = new Gson();
        HttpSession session = req.getSession();

        BufferedReader reader = req.getReader();
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
//        System.out.println("request: " + jsonBuilder.toString());

        LoginDTO loginDTO = gson.fromJson(jsonBuilder.toString(), LoginDTO.class);

        String jsonString = null;
        UsersService usersService = new UsersServiceImpl();
        Users users = usersService.login(loginDTO.getUsername(), loginDTO.getPassword());
        if (users != null) {
            if (!users.getUserStatus().equals("0")) {
                // 登入
                jsonString = gson.toJson("OK");
                session.setAttribute("users", users);
            } else {
                // 停用
                jsonString = gson.toJson("BAN");
            }
        } else {
            // 帳號或密碼錯誤
            jsonString = gson.toJson("FAIL");
        }

        res.getWriter().write(jsonString);
        return;
    }
}