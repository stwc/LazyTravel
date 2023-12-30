package com.lazytravel.customerservice.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
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
	

	@Column(name="CREATE_TIME")
    private Timestamp createTime;
	
	@Column(name="CS_MAIL_STATUS",columnDefinition = "CHAR")
    private String csMailStatus;
	
	@Column(name="questions",columnDefinition = "LONGTEXT")
	private String questions;
	
	@Column(name="answer",columnDefinition = "LONGTEXT")
	private String answer;
	
	@Column(name="RECEIVED_TIME")
    private Timestamp RECEIVED_TIME;
    
	public CSMail() {
	}

	public CSMail(Integer mailId, Customer customer, Timestamp createTime, String csMailStatus, String questions,
			String answer, Timestamp rECEIVED_TIME) {
		super();
		this.mailId = mailId;
		this.customer = customer;
		this.createTime = createTime;
		this.csMailStatus = csMailStatus;
		this.questions = questions;
		this.answer = answer;
		RECEIVED_TIME = rECEIVED_TIME;
	}

	public Integer getMailId() {
		return mailId;
	}

	public void setMailId(Integer mailId) {
		this.mailId = mailId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getCsMailStatus() {
		return csMailStatus;
	}

	public void setCsMailStatus(String csMailStatus) {
		this.csMailStatus = csMailStatus;
	}

	public String getQuestions() {
		return questions;
	}

	public void setQuestions(String questions) {
		this.questions = questions;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Timestamp getRECEIVED_TIME() {
		return RECEIVED_TIME;
	}

	public void setRECEIVED_TIME(Timestamp rECEIVED_TIME) {
		RECEIVED_TIME = rECEIVED_TIME;
	}

	@Override
	public String toString() {
		return "CSMail [mailId=" + mailId + ", customer=" + customer + ", createTime=" + createTime + ", csMailStatus="
				+ csMailStatus + ", questions=" + questions + ", answer=" + answer + ", RECEIVED_TIME=" + RECEIVED_TIME
				+ "]";
	}

 }