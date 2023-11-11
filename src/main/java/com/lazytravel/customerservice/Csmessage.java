package com.lazytravel.customerservice;

import java.io.Serializable;
import java.sql.Timestamp;

public class CSMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer messageId;
	private Integer mailId;
	private String  content;
	private Timestamp createTime;
	private String messageFrom;
	
	public CSMessage() {
		super();
		
	}

	public CSMessage(Integer messageid, Integer mailid, String content, Timestamp createtime, String from) {
		super();
		this.messageId = messageId;
		this.mailId = mailId;
		this.content = content;
		this.createTime = createTime;
		this.messageFrom = from;
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

	public String getmessageFrom() {
		return messageFrom;
	}

	public void setmessageFrom(String from) {
		this.messageFrom = from;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
