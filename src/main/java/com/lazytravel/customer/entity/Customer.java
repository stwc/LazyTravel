package com.lazytravel.customer.entity;

import javax.persistence.*;

import com.google.gson.annotations.Expose;
import com.lazytravel.blog.entity.BlogCl;

import java.sql.Timestamp;
import java.util.Set;
import java.sql.Date;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", updatable = false)
    private Integer customerId;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "nickname")
    private String nickname;
    @Column(name = "sex", columnDefinition = "char")
    private String sex;
    @Column(name = "phone", columnDefinition = "char")
    private String phone;
    @Column(name = "birth")
    private Date birth;
    @Column(name = "address")
    private String address;
    @Column(name = "email")
    private String email;
    @Column(name = "customer_passwd")
    private String customerPasswd;
    @Column(name = "customer_status", columnDefinition = "char")
    private String customerStatus;
    @Column(name = "idno", columnDefinition = "char")
    private String idno;
    @Column(name = "avatar", columnDefinition = "longblob")
    private byte[] avatar;
    @Column(name = "customer_point", insertable = false)
    private Integer customerPoint;
    @Column(name = "create_time", insertable = false, updatable = false)
    private Timestamp createTime;
    @Column(name = "update_time", insertable = false, updatable = false)
    private Timestamp updateTime;

//    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
//    @Expose
//    private Set<BlogCl> blogCls;
    
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
        this.avatar = avatar;
        this.customerPoint = customerPoint;
        this.createTime = createTime;
        this.updateTime = updateTime;
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

//    public Set<BlogCl> getBlogCls() {
//		return blogCls;
//	}

//	public void setBlogCls(Set<BlogCl> blogCls) {
//		this.blogCls = blogCls;
//	}

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
                ", customerStatus='" + customerStatus + '\'' +
                ", idno='" + idno + '\'' +
                ", customerPoint=" + customerPoint +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
