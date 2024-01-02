package com.lazytravel.admin.controller;

import com.google.gson.Gson;
import com.lazytravel.admin.dto.UsersDTO;
import com.lazytravel.admin.entity.Roles;
import com.lazytravel.admin.entity.Users;
import com.lazytravel.admin.service.RoleService;
import com.lazytravel.admin.service.RoleServiceImpl;
import com.lazytravel.admin.service.UsersService;
import com.lazytravel.admin.service.UsersServiceImpl;
import com.password4j.Hash;
import com.password4j.Password;

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
                res.setContentType("application/json; charset=UTF-8");
                res.getWriter().write(getAll());
                return;
            case "getone":
                res.setContentType("application/json; charset=UTF-8");
                res.getWriter().write(getOne(req));
                return;
            case "create":
                forwardPath = createUser(req, res);
                break;
            default:
                forwardPath = "/admin/index.jsp";
        }


        res.setContentType("text/html; charset=UTF-8");
        RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPath);
        dispatcher.forward(req, res);
    }

    private String createUser(HttpServletRequest req, HttpServletResponse res) {
        String username = req.getParameter("username").trim();
        String userPasswd = req.getParameter("user_passwd");
        Integer roleId = Integer.valueOf(req.getParameter("role"));
        String userStatus = req.getParameter("status");

        if (usersService.getUserByUsername(username) == null) {
            Users users = new Users();
            users.setUsername(username);

            String hashedPw = Password.hash(userPasswd).withBcrypt().getResult();
            users.setUserPasswd(hashedPw);

            users.setRoleId(roleId);
            users.setUserStatus(userStatus);

            usersService.addUser(users);
            return "/admin/user.jsp";
        } else {
            req.setAttribute("createFailed", true);
            return "/admin/user-add.jsp";
        }
    }

    private String getAll() {
        List<Users> usersList = usersService.getAll();
        List<UsersDTO> usersDTOList = new ArrayList<>();

        RoleService roleService = new RoleServiceImpl();

        for (var user : usersList) {
            UsersDTO usersDTO = new UsersDTO();
            usersDTO.setUserId(user.getUserId());
            usersDTO.setUsername(user.getUsername());

            if (user.getRoleId() != null) {
                Roles roles = roleService.getRole(user.getRoleId());
                usersDTO.setRoleName(roles.getRoleName());
            } else {
                usersDTO.setRoleName("");
            }

            usersDTO.setUserStatus(user.getUserStatus().equals("0") ? "停用" : "啟用");
            usersDTO.setCreateTime(user.getCreateTime());
            usersDTO.setUpdateTime(user.getUpdateTime());
            usersDTOList.add(usersDTO);
        }

        return new Gson().toJson(usersDTOList);
    }

    private String getOne(HttpServletRequest req) {
        int userId = Integer.parseInt(req.getParameter("id"));
        Users user = usersService.getUser(userId);

        UsersDTO usersDTO = new UsersDTO();
        usersDTO.setUserId(user.getUserId());
        usersDTO.setUsername(user.getUsername());
        usersDTO.setRoleName(String.valueOf(user.getRoleId()));
        usersDTO.setUserStatus(user.getUserStatus());
        usersDTO.setCreateTime(user.getCreateTime());
        usersDTO.setUpdateTime(user.getUpdateTime());

        return new Gson().toJson(usersDTO);
    }
}