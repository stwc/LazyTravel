//package com.lazytravel.customerservice.entity;
//
//import java.io.Serializable;
//import java.sql.Timestamp;
//
//import javax.persistence.CascadeType;
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.Lob;
//import javax.persistence.ManyToOne;
//import javax.persistence.Table;
//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;
//import javax.persistence.Transient;
//
//@Entity
//@Table(name = "CS_MESSAGE")
//public class CSMessage{
//	
//	
//	public CSMessage() {}
//	
//	public CSMessage(Integer messageId, String content, Timestamp createTime, String messageFrom) {
//		super();
//		this.messageId = messageId;
//		this.content = content;
//		this.createTime = createTime;
//		this.messageFrom = messageFrom;
//	}
//	@Id 
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name ="MESSAGE_ID")
//    private Integer messageId;
//    
//	@ManyToOne(fetch = FetchType.EAGER,cascade=CascadeType.ALL)
//	@JoinColumn(name="MAIL_ID",referencedColumnName = "MAIL_ID")
//	private CSMail csMail;
//	
//	
//	@Lob
//	@Column(name="CONTENT")
//	private String content;
//    
//	@Column(name="CREATE_TIME")
//	private Timestamp createTime;
//    
//	@Column(name="MESSAGE_FROM",columnDefinition = "CHAR")
//	private String messageFrom;
//
//	
//	
//	@Transient
//	private String createTimeFormate;
//	
//	@Transient
//	private Integer customerId;
//	
//	
//
//    public Integer getCustomerId() {
//		return customerId;
//	}
//
//	public void setCustomerId(Integer customerId) {
//		this.customerId = customerId;
//	}
//
//	public Integer getMessageId() {
//        return messageId;
//    }
//    
//	public CSMail getCsMail() {
//		return csMail;
//	}
//	public void setCsMail(CSMail csMail) {
//		this.csMail = csMail;
//	}
//	public String getContent() {
//		return content;
//	}
//	public void setContent(String content) {
//		this.content = content;
//	}
//	public Timestamp getCreateTime() {
//		return createTime;
//	}
//	public void setCreateTime(Timestamp createTime) {
//		this.createTime = createTime;
//	}
//	public String getMessageFrom() {
//		return messageFrom;
//	}
//	public void setMessageFrom(String messageFrom) {
//		this.messageFrom = messageFrom;
//	}
//	public void setMessageId(Integer messageId) {
//		this.messageId = messageId;
//	}
//	@Override
//	public String toString() {
//		return "CSMessage [messageId=" + messageId + ", csMail=" + csMail + ", content=" + content + ", createTime="
//				+ createTime + ", messageFrom=" + messageFrom + "]";
//	}
//
//	public String getCreateTimeFormate() {
//		return createTimeFormate;
//	}
//
//	public void setCreateTimeFormate(String createTimeFormate) {
//		this.createTimeFormate = createTimeFormate;
//	}
//	
//	
//	
//	
//	}
