package com.lazytravel.admin;

import java.io.Serializable;

public class Roles implements Serializable {
    private Integer roleId;
    private String roleName;
    private String roleDescr;

    public Roles() {
        super();
    }

    public Roles(Integer roleId, String roleName, String roleDescr) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.roleDescr = roleDescr;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDescr() {
        return roleDescr;
    }

    public void setRoleDescr(String roleDescr) {
        this.roleDescr = roleDescr;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", roleDescr='" + roleDescr + '\'' +
                '}';
    }
}
