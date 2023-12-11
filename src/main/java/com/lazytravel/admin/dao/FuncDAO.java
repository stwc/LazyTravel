package com.lazytravel.admin.dao;

import com.lazytravel.admin.entity.Func;

import java.util.List;

public interface FuncDAO {
    void add(Func func);

    void update(Func func);

    void delete(Integer funcId);

    Func findByPK(Integer funcId);

    List<Func> getAll();
}
