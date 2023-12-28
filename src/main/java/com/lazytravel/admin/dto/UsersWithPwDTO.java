package com.lazytravel.admin.dto;

import java.sql.Timestamp;

public class UsersWithPwDTO {
    private Integer userId;
    private String username;

    private String password;

    private String roleName;
    private String userStatus;

    public UsersWithPwDTO() {

    }

    public UsersWithPwDTO(Integer userId, String username, String password, String roleName, String userStatus) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.roleName = roleName;
        this.userStatus = userStatus;
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

}
