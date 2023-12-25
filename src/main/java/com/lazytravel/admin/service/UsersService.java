package com.lazytravel.admin.service;

import com.lazytravel.admin.entity.Users;

import java.util.List;

public interface UsersService {
    void addUser(Users users);

    void updateUser(Users users);

    Users getUser(Integer userId);

    List<Users> getAll();

    Users login (String username, String password);
}
