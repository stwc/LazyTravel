package com.lazytravel.admin.service;

import com.lazytravel.admin.entity.Func;

import java.util.List;

public interface FuncService {
    void addFunc(Func func);

    void updateFunc(Func func);

    Func getFunc(Integer funcId);

    void deleteFunc(Integer funcId);

    List<Func> getAll();
}
