package com.lazytravel.admin.entity;

import java.io.Serializable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "role_func")
@IdClass(RoleFunc.CompositeDetail.class)
public class RoleFunc {
    @Id
    @Column(name = "role_id")
    private Integer roleId;
    @Id
    @Column(name = "func_id")
    private Integer funcId;
    @Column(name = "create_time")
    private Timestamp createTime;

    public CompositeDetail getCompositeKey() {
        return new CompositeDetail(roleId, funcId);
    }

    public void setCompositeKey(CompositeDetail key) {
        this.roleId = key.getRoleId();
        this.funcId = key.getFuncId();
    }

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

    static class CompositeDetail implements Serializable {
        private Integer roleId;
        private Integer funcId;

        public CompositeDetail() {
            super();
        }

        public CompositeDetail(Integer roleId, Integer funcId) {
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            CompositeDetail that = (CompositeDetail) o;

            if (!Objects.equals(roleId, that.roleId)) return false;
            return Objects.equals(funcId, that.funcId);
        }

        @Override
        public int hashCode() {
            int result = roleId != null ? roleId.hashCode() : 0;
            result = 31 * result + (funcId != null ? funcId.hashCode() : 0);
            return result;
        }
    }
}
