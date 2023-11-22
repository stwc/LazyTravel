package com.lazytravel.admin.entity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", updatable = false)
    private Integer roleId;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "role_descr")
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
