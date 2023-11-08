package com.lazytravel.admin;

import java.io.Serializable;

public class Func implements Serializable {
    private Integer funcId;
    private String funcName;
    private String description;

    public Func() {
        super();
    }

    public Func(Integer funcId, String funcName, String description) {
        this.funcId = funcId;
        this.funcName = funcName;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Func{" +
                "funcId=" + funcId +
                ", funcName='" + funcName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
