package com.lazytravel.admin.service;

import com.lazytravel.admin.dao.RoleFuncDAO;
import com.lazytravel.admin.dao.RoleFuncDAOImpl;
import com.lazytravel.admin.entity.RoleFunc;

import java.util.List;

public class RoleFuncServiceImpl implements RoleFuncService {
    private final RoleFuncDAO dao;

    public RoleFuncServiceImpl() {
        dao = new RoleFuncDAOImpl();
    }

    @Override
    public void addRoleFunc(RoleFunc roleFunc) {
        dao.add(roleFunc);
    }

    @Override
    public RoleFunc findByPK(Integer roleId, Integer funcId) {
        return dao.findByPK(roleId, funcId);
    }

    @Override
    public List<RoleFunc> findByRoleId(Integer roleId) {
        return dao.findByRoleId(roleId);
    }

    @Override
    public List<RoleFunc> findByFuncId(Integer funcId) {
        return dao.findByFuncId(funcId);
    }

    @Override
    public List<RoleFunc> getAll() {
        return dao.getAll();
    }

    @Override
    public void deleteByPK(Integer roleId, Integer funcId) {
        dao.deleteByPK(roleId, funcId);
    }

    @Override
    public void deleteByRoleId(Integer roleId) {
        dao.deleteByRoleId(roleId);
    }

    @Override
    public void deleteByFuncId(Integer funcId) {
        dao.deleteByFuncId(funcId);
    }
}
