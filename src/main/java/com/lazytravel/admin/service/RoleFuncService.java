package com.lazytravel.admin.service;

import com.lazytravel.admin.entity.RoleFunc;

import java.util.List;

public interface RoleFuncService {
    void addRoleFunc(RoleFunc roleFunc);

    RoleFunc findByPK(Integer roleId, Integer funcId);

    List<RoleFunc> findByRoleId(Integer roleId);

    List<RoleFunc> findByFuncId(Integer funcId);

    List<RoleFunc> getAll();

    void deleteByPK(Integer roleId, Integer funcId);

    void deleteByRoleId(Integer roleId);

    void deleteByFuncId(Integer funcId);
}
