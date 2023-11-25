package com.lazytravel.admin.dao;

import com.lazytravel.admin.entity.Users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAOImpl implements UsersDAO {

    @Override
    public void add(Users users) {

    }

    @Override
    public void update(Users users) {

    }

    @Override
    public Users getUserByUserId(Integer userId) {
        Users users = null;

        return users;
    }

    @Override
    public List<Users> getAll() {
        List<Users> usersList = new ArrayList<>();

        return usersList;
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("輸入要查詢的使用者編號: ");
//        int userId = sc.nextInt();
//        sc.close();

        Users users = null;
        UsersDAO dao = new UsersDAOImpl();

        // 新增單筆資料
//        users = new Users("tibame", "123456", 1);
//        dao.add(users);
//        System.out.println("[SQL] 新增單筆資料成功\n");

        // 查詢單筆
        users = dao.getUserByUserId(1);
        System.out.println(users);
        System.out.println("[SQL] 查詢單筆成功\n");

        // 查詢全部筆數
        List<Users> usersList = dao.getAll();
        for (Users user: usersList) {
            System.out.println(user);
        }
        System.out.println("[SQL] 查詢全部筆數成功\n");

        // 更新單筆
        users = new Users("tibame", "94879487", 1);
        dao.update(users);
        System.out.println("[SQL] 更新單筆成功\n");
    }
}