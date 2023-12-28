package com.lazytravel.admin.controller;

import com.google.gson.Gson;
import com.lazytravel.admin.service.RoleService;
import com.lazytravel.admin.service.RoleServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RoleServlet", value = "/admin/role.do")
public class RoleServlet extends HttpServlet {
    private RoleService roleService;

    @Override
    public void init() throws ServletException {
        roleService = new RoleServiceImpl();
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
                String jsonStr = new Gson().toJson(roleService.getAll());
                res.setContentType("application/json; charset=UTF-8");
                res.getWriter().write(jsonStr);
                return;
            default:
                forwardPath = "/admin/index.jsp";
        }

        res.setContentType("text/html; charset=UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
        dispatcher.forward(req, res);
    }
}