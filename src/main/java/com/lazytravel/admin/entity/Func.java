package com.lazytravel.admin.entity;

import javax.persistence.*;

@Entity
@Table(name = "func")
public class Func {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "func_id", updatable = false)
    private Integer funcId;
    @Column(name = "func_name")
    private String funcName;
    @Column(name = "func_descr")
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
