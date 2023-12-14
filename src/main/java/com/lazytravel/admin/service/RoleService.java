package com.lazytravel.admin.service;

import com.lazytravel.admin.entity.Roles;

import java.util.List;

public interface RoleService {
    void addRole(Roles roles);

    void updateRole(Roles roles);

    Roles getRole(Integer roleId);

    void deleteRole(Integer roleId);

    List<Roles> getAll();
}
