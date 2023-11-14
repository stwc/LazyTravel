package com.lazytravel.admin;

import java.io.Serializable;
import java.sql.Timestamp;

public class RoleFunc implements Serializable {
    private Integer roleId;
    private Integer funcId;
    private Timestamp createTime;

    public RoleFunc() {
        super();
    }

    public RoleFunc(Integer roleId, Integer funcId, Timestamp createTime) {
        this.roleId = roleId;
        this.funcId = funcId;
        this.createTime = createTime;
    }

    public RoleFunc(Integer roleId, Integer funcId) {
        this.roleId = roleId;
        this.funcId = funcId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getFuncId() {
        return funcId;
    }

    public void setFuncId(Integer funcId) {
        this.funcId = funcId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "RoleFunc{" +
                "roleId=" + roleId +
                ", funcId=" + funcId +
                ", createTime=" + createTime +
                '}';
    }
}
