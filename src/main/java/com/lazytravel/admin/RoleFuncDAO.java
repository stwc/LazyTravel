package com.lazytravel.admin;

import java.util.List;

public interface RoleFuncDAO {
    void add(RoleFunc roleFunc);

    void update(RoleFunc roleFunc);

    void delete(Integer roleId, Integer funcId);

    RoleFunc getRoleFunc(Integer roleId, Integer funcId);

    List<RoleFunc> getAll();
}
