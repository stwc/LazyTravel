package com.lazytravel.customerservice.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.lazytravel.customer.entity.Customer;


@Entity
@Table(name = "CS_MAIL")
public class CSMail {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="MAIL_ID")
    private Integer mailId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="CUSTOMER_ID",referencedColumnName = "CUSTOMER_ID")
    private Customer customer;
	
	@Column(name="TITLE",columnDefinition = "VARCHAR")
    private String title;
	
	@Column(name="CREATE_TIME")
    private Timestamp createTime;
	
	@Column(name="LAST_MSG_TIME")
    private Timestamp lastMsgTime;
	
	@Column(name="CS_MAIL_STATUS",columnDefinition = "CHAR")
    private String csMailStatus;

    public CSMail() {
    }

    public Integer getMailId() {
        return mailId;
    }


	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Timestamp getLastMsgTime() {
		return lastMsgTime;
	}

	public void setLastMsgTime(Timestamp lastMsgTime) {
		this.lastMsgTime = lastMsgTime;
	}

	public String getCsMailStatus() {
		return csMailStatus;
	}

	public void setCsMailStatus(String csMailStatus) {
		this.csMailStatus = csMailStatus;
	}

	public void setMailId(Integer mailId) {
		this.mailId = mailId;
	}

	@Override
	public String toString() {
		return "CSMail [mailId=" + mailId + ", customer=" + customer + ", title=" + title + ", createTime=" + createTime
				+ ", lastMsgTime=" + lastMsgTime + ", csMailStatus=" + csMailStatus + "]";
	}


  }