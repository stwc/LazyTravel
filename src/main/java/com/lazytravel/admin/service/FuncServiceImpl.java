package com.lazytravel.admin.service;

import com.lazytravel.admin.dao.FuncDAO;
import com.lazytravel.admin.dao.FuncDAOImpl;
import com.lazytravel.admin.entity.Func;

import java.util.List;

public class FuncServiceImpl implements FuncService {
    private final FuncDAO dao;

    public FuncServiceImpl() {
        dao = new FuncDAOImpl();
    }

    @Override
    public void addFunc(Func func) {
        dao.add(func);
    }

    @Override
    public void updateFunc(Func func) {
        dao.update(func);
    }

    @Override
    public Func getFunc(Integer funcId) {
        return dao.findByPK(funcId);
    }

    @Override
    public void deleteFunc(Integer funcId) {
        dao.delete(funcId);
    }

    @Override
    public List<Func> getAll() {
        return dao.getAll();
    }
}
