package com.lazytravel.admin;

import java.io.Serializable;

public class Func implements Serializable {
    private Integer funcId;
    private String funcName;
    private String funcDescr;

    public Func() {
        super();
    }

    public Func(Integer funcId, String funcName, String funcDescr) {
        this.funcId = funcId;
        this.funcName = funcName;
        this.funcDescr = funcDescr;
    }

    public Integer getFuncId() {
        return funcId;
    }

    public void setFuncId(Integer funcId) {
        this.funcId = funcId;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public String getFuncDescr() {
        return funcDescr;
    }

    public void setFuncDescr(String funcDescr) {
        this.funcDescr = funcDescr;
    }

    @Override
    public String toString() {
        return "Func{" +
                "funcId=" + funcId +
                ", funcName='" + funcName + '\'' +
                ", funcDescr='" + funcDescr + '\'' +
                '}';
    }
}
