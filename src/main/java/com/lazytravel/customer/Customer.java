package com.lazytravel.customer;

import java.io.Serializable;
import java.sql.Timestamp;
import java.sql.Date;

public class Customer implements Serializable {
    private Integer customerId;
    private String customerName;
    private String nickname;
    private String sex;
    private String phone;
    private Date birth;
    private String address;
    private String email;
    private String customerPasswd;
    private String customerStatus;
    private String idno;
    private byte[] avatar;
    private Integer customerPoint;
    private Timestamp createTime;
    private Timestamp updateTime;

    public Customer() {
        super();
    }

    public Customer(Integer customerId, String customerName, String nickname, String sex, String phone, Date birth,
                    String address, String email, String customerPasswd, String customerStatus, String idno, byte[] avatar,
                    Integer customerPoint, Timestamp createTime, Timestamp updateTime) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.nickname = nickname;
        this.sex = sex;
        this.phone = phone;
        this.birth = birth;
        this.address = address;
        this.email = email;
        this.customerPasswd = customerPasswd;
        this.customerStatus = customerStatus;
        this.idno = idno;
        this.customerPoint = customerPoint;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.avatar = avatar;
    }

    public Customer(String customerName, String nickname, String sex, String phone, Date birth, String address,
                    String email, String customerPasswd, String customerStatus, String idno, Integer customerPoint) {
        this.customerName = customerName;
        this.nickname = nickname;
        this.sex = sex;
        this.phone = phone;
        this.birth = birth;
        this.address = address;
        this.email = email;
        this.customerPasswd = customerPasswd;
        this.customerStatus = customerStatus;
        this.idno = idno;
        this.customerPoint = customerPoint;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCustomerPasswd() {
        return customerPasswd;
    }

    public void setCustomerPasswd(String customerPasswd) {
        this.customerPasswd = customerPasswd;
    }

    public String getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public Integer getCustomerPoint() {
        return customerPoint;
    }

    public void setCustomerPoint(Integer customerPoint) {
        this.customerPoint = customerPoint;
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
        return "Customer{" +
                "customerId=" + customerId +
                ", customerName='" + customerName + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", phone='" + phone + '\'' +
                ", birth=" + birth +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", customerPasswd='" + customerPasswd + '\'' +
                ", customerStatus='" + customerStatus + '\'' +
                ", idno='" + idno + '\'' +
                ", customerPoint=" + customerPoint +
                '}';
    }
}
