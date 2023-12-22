package com.lazytravel.customerservice.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CS_MESSAGE")
public class CSMessage{
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="MESSAGE_ID")
    private Integer messageId;
    
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="MAIL_ID",referencedColumnName = "MAIL_ID")
	private CSMail csMail;
	
	
	
	@Column(name="CONTENT",columnDefinition = "LONGTEXT")
	private String content;
    
	@Column(name="CREATE_TIME")
	private Timestamp createTime;
    
	@Column(name="MESSAGE_FROM",columnDefinition = "CHAR")
	private String messageFrom;

    public CSMessage() {
    }
    public Integer getMessageId() {
        return messageId;
    }
    
	public CSMail getCsMail() {
		return csMail;
	}
	public void setCsMail(CSMail csMail) {
		this.csMail = csMail;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getMessageFrom() {
		return messageFrom;
	}
	public void setMessageFrom(String messageFrom) {
		this.messageFrom = messageFrom;
	}
	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}
	@Override
	public String toString() {
		return "CSMessage [messageId=" + messageId + ", csMail=" + csMail + ", content=" + content + ", createTime="
				+ createTime + ", messageFrom=" + messageFrom + "]";
	}
	
	}
