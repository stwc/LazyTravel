package com.lazytravel.admin.service;

import com.lazytravel.admin.dao.UsersDAO;
import com.lazytravel.admin.dao.UsersDAOImpl;
import com.lazytravel.admin.entity.Users;
import com.password4j.Password;

import java.util.List;

public class UsersServiceImpl implements UsersService {
    private final UsersDAO dao;

    public UsersServiceImpl() {
        dao = new UsersDAOImpl();
    }

    @Override
    public void addUser(Users users) {
        dao.add(users);
    }

    @Override
    public void updateUser(Users users) {
        dao.update(users);
    }

    @Override
    public Users getUser(Integer userId) {
        return dao.findByPK(userId);
    }

    @Override
    public List<Users> getAll() {
        return dao.getAll();
    }

    @Override
    public Users login(String username, String password) {
        Users users = dao.findByUsername(username);
        if (users == null)
            return null;

        try {
            boolean isUsernameMatched = username.equals(users.getUsername());
            boolean isPasswdMatched = Password.check(password, users.getUserPasswd()).withBcrypt();
            if (isUsernameMatched && isPasswdMatched)
                return users;
            else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
