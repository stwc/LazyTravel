package com.lazytravel.admin;

import java.io.Serializable;
import java.sql.Timestamp;

public class User implements Serializable {
    private Integer userId;
    private String username;
    private String password;
    private Integer roleId;
    private String status;
    private Timestamp createTime;
    private Timestamp updateTime;

    public User() {
        super();
    }

    public User(Integer userId, String username, String password, Integer roleId, String status, Timestamp createTime, Timestamp updateTime) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.status = status;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public User(String username, String password, Integer roleId, String status) {
        this.username = username;
        this.password = password;
        this.roleId = roleId;
        this.status = status;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
