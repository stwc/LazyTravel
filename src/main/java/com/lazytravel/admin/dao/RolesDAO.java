package com.lazytravel.admin.dao;

import com.lazytravel.admin.entity.Roles;

import java.util.List;

public interface RolesDAO {
    void add(Roles roles);

    void update(Roles roles);

    void delete(Integer roleId);

    Roles getRoleByRoleId(Integer roleId);

    List<Roles> getAll();
}
