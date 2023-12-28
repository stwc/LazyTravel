package com.lazytravel.admin.dto;

import java.sql.Timestamp;

public class UsersDTO {
    private Integer userId;
    private String username;
    private String roleName;
    private String userStatus;
    private Timestamp createTime;
    private Timestamp updateTime;

    public UsersDTO() {

    }

    public UsersDTO(Integer userId, String username, String roleName, String userStatus, Timestamp createTime, Timestamp updateTime) {
        this.userId = userId;
        this.username = username;
        this.roleName = roleName;
        this.userStatus = userStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
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
}
