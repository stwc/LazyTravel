package com.lazytravel.admin;

import java.util.List;

public interface RoleDAO {
    void add(Role role);

    void update(Role role);

    void delete(Integer roleId);

    Role getRoleByRoleId(Integer roleId);

    List<Role> getAll();
}
