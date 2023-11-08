package com.lazytravel.admin;

import java.util.List;

public interface UserDAO {
    void add(User user);

    void update(User user);

    void delete(Integer userId);

    User getUserByUserId(Integer userId);

    List<User> getAll();
}
