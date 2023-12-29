package com.lazytravel.admin.dao;

import com.lazytravel.admin.entity.Users;

import java.util.List;

public interface UsersDAO {
    void add(Users users);

    void update(Users users);

    Users findByPK(Integer userId);

    List<Users> getAll();

    Users findByUsername(String username);
}
