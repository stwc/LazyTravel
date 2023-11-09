package com.lazytravel.admin;

import java.util.List;

public interface FuncDAO {
    void add(Func func);

    void update(Func func);

    void delete(Integer funcId);

    Func getFuncByFuncId(Integer funcId);

    List<Func> getAll();
}
