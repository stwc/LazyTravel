package com.lazytravel.admin.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", updatable = false)
    private Integer userId;
    @Column(name = "username")
    private String username;
    @Column(name = "user_passwd")
    private String userPasswd;
    @Column(name = "role_id")
    private Integer roleId;
    @Column(name = "user_status", columnDefinition = "char")
    private String userStatus;
    @Column(name = "create_time", insertable = false, updatable = false)
    private Timestamp createTime;
    @Column(name = "update_time", insertable = false, updatable = false)
    private Timestamp updateTime;

    public Users() {
        super();
    }

    public Users(Integer userId, String username, String userPasswd, Integer roleId, String userStatus,
                 Timestamp createTime, Timestamp updateTime) {
        this.userId = userId;
        this.username = username;
        this.userPasswd = userPasswd;
        this.roleId = roleId;
        this.userStatus = userStatus;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Users(String username, String userPasswd, Integer roleId) {
        this.username = username;
        this.userPasswd = userPasswd;
        this.roleId = roleId;
    }

    public Users(String username, String userPasswd, Integer roleId, String userStatus) {
        this.username = username;
        this.userPasswd = userPasswd;
        this.roleId = roleId;
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

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
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

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", userPasswd='" + userPasswd + '\'' +
                ", roleId=" + roleId +
                ", userStatus='" + userStatus + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
