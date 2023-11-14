package com.lazytravel.admin;

import java.util.List;

public interface UsersDAO {
    void add(Users users);

    void update(Users users);

    void delete(Integer userId);

    Users getUserByUserId(Integer userId);

    List<Users> getAll();
}
