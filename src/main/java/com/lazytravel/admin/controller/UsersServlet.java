package com.lazytravel.admin.controller;

import com.google.gson.Gson;
import com.lazytravel.admin.dto.UsersDTO;
import com.lazytravel.admin.entity.Roles;
import com.lazytravel.admin.entity.Users;
import com.lazytravel.admin.service.RoleService;
import com.lazytravel.admin.service.RoleServiceImpl;
import com.lazytravel.admin.service.UsersService;
import com.lazytravel.admin.service.UsersServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "UsersServlet", value = "/admin/user.do")
public class UsersServlet extends HttpServlet {
    private UsersService usersService;

    @Override
    public void init() throws ServletException {
        usersService = new UsersServiceImpl();
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
                String jsonStr = getAll();
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

    private String getAll() {
        List<Users> usersList = usersService.getAll();
        List<UsersDTO> usersDTOList = new ArrayList<>();

        RoleService roleService = new RoleServiceImpl();

        for (var user : usersList) {
            UsersDTO usersDTO = new UsersDTO();
            usersDTO.setUserId(user.getUserId());
            usersDTO.setUsername(user.getUsername());

            Roles roles = roleService.getRole(user.getRoleId());
            usersDTO.setRoleName(roles.getRoleName());

            usersDTO.setUserStatus(user.getUserStatus().equals("0") ? "停用" : "啟用");
            usersDTO.setCreateTime(user.getCreateTime());
            usersDTO.setUpdateTime(user.getUpdateTime());
            usersDTOList.add(usersDTO);
        }

        return new Gson().toJson(usersDTOList);
    }
}