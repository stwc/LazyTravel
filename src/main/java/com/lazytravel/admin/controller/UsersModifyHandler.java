package com.lazytravel.admin.controller;

import com.google.gson.Gson;
import com.lazytravel.admin.dto.UsersWithPwDTO;
import com.lazytravel.admin.entity.Users;
import com.lazytravel.admin.service.UsersService;
import com.lazytravel.admin.service.UsersServiceImpl;
import com.password4j.Hash;
import com.password4j.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(name = "UsersModifyHandler", value = "/UsersModifyHandler")
public class UsersModifyHandler extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        doPost(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        res.setContentType("text/html; charset=UTF-8");

        Gson gson = new Gson();
        BufferedReader reader = req.getReader();
        StringBuilder jsonBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuilder.append(line);
        }
        System.out.println("request: " + jsonBuilder);

        UsersWithPwDTO usersWithPwDTO = gson.fromJson(jsonBuilder.toString(), UsersWithPwDTO.class);

        String jsonString = null;
        UsersService usersService = new UsersServiceImpl();
        Users users = usersService.getUser(usersWithPwDTO.getUserId());
//        String newUsername= usersWithPwDTO.getUsername();
//        boolean usernameNotExist = usersService.getUserByUsername(newUsername) == null;
        if (users != null) {
//            users.setUsername(usersWithPwDTO.getUsername());

            String password = usersWithPwDTO.getPassword();
            if (!password.isEmpty()) {
                Hash hash = Password.hash(password).withBcrypt();
                String hashedPw = hash.getResult();
                users.setUserPasswd(hashedPw);
            }

            users.setRoleId(Integer.valueOf(usersWithPwDTO.getRoleName()));
            users.setUserStatus(usersWithPwDTO.getUserStatus());

            usersService.updateUser(users);

            jsonString = gson.toJson("OK");
        } else {
            jsonString = gson.toJson("FAIL");
        }

        res.getWriter().write(jsonString);
    }
}