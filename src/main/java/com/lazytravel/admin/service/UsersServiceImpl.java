package com.lazytravel.admin.service;

import com.lazytravel.admin.dao.UsersDAO;
import com.lazytravel.admin.dao.UsersDAOImpl;
import com.lazytravel.admin.entity.Users;

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
}
