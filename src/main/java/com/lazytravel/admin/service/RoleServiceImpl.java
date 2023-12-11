package com.lazytravel.admin.service;

import com.lazytravel.admin.dao.RolesDAO;
import com.lazytravel.admin.dao.RolesDAOImpl;
import com.lazytravel.admin.entity.Roles;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    private final RolesDAO dao;

    public RoleServiceImpl() {
        dao = new RolesDAOImpl();
    }

    @Override
    public void addRole(Roles roles) {
        dao.add(roles);
    }

    @Override
    public void updateRole(Roles roles) {
        dao.update(roles);
    }

    @Override
    public Roles getRole(Integer roleId) {
        return dao.findByPK(roleId);
    }

    @Override
    public void deleteRole(Integer roleId) {
        dao.delete(roleId);
    }

    @Override
    public List<Roles> getAll() {
        return dao.getAll();
    }
}
