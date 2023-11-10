package com.lazytravel.customerservice;

import java.io.Serializable;
import java.sql.Timestamp;

public class Csmessage implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer messageId;
	private Integer mailId;
	private String  content;
	private Timestamp createTime;
	private String from;
	
	public Csmessage() {
		super();
		
	}

	public Csmessage(Integer messageid, Integer mailid, String content, Timestamp createtime, String from) {
		super();
		this.messageId = messageId;
		this.mailId = mailId;
		this.content = content;
		this.createTime = createTime;
		this.from = from;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}

	public Integer getMailId() {
		return mailId;
	}

	public void setMailId(Integer mailId) {
		this.mailId = mailId;
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

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}