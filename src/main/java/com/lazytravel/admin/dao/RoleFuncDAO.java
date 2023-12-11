package com.lazytravel.admin.dao;

import com.lazytravel.admin.entity.RoleFunc;

import java.util.List;

public interface RoleFuncDAO {
    void add(RoleFunc roleFunc);

//    void update(RoleFunc roleFunc);

    RoleFunc findByPK(Integer roleId, Integer funcId);

    List<RoleFunc> findByRoleId(Integer roleId);

    List<RoleFunc> findByFuncId(Integer funcId);

    List<RoleFunc> getAll();

    void deleteByPK(Integer roleId, Integer funcId);

    void deleteByRoleId(Integer roleId);

    void deleteByFuncId(Integer funcId);
}
