package com.lazytravel.admin.dao;

import com.lazytravel.admin.entity.RoleFunc;

import java.util.List;

public interface RoleFuncDAO {
    void add(RoleFunc roleFunc);

    void update(RoleFunc roleFunc);

    void delete(Integer roleId, Integer funcId);

    RoleFunc findByPK(Integer roleId, Integer funcId);

    List<RoleFunc> getAll();
}
