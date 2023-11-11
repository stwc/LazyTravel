package com.lazytravel.customerservice;

import java.io.Serializable;
import java.sql.Timestamp;

public class CSMail implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer mailId;
	private Integer customeId;
	private String  title;
	private Timestamp createTime;
	private Timestamp lastMsgTime;
	private String  csMailStatus;
	
	public CSMailail() {
		super();
	}

	public CSMail(Integer mailId, Integer customeId, String title, Timestamp createTime, Timestamp lastMsgTime,
			String csMailStatus) {
		super();
		this.mailId = mailId;
		this.customeId = customeId;
		this.title = title;
		this.createTime = createTime;
		this.lastMsgTime = lastMsgTime;
		this.csMailStatus = csMailStatus;
	}

	public Integer getMailId() {
		return mailId;
	}

	public void setMailId(Integer mailId) {
		this.mailId = mailId;
	}

	public Integer getCustomeId() {
		return customeId;
	}

	public void setCustomeId(Integer customeId) {
		this.customeId = customeId;
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

	public String toString() {
		return "Csmail [mailId=" + mailId + ", customeId=" + customeId + ", title=" + title + ", createTime="
				+ createTime + ", lastMsgTime=" + lastMsgTime + ", csMailStatus=" + csMailStatus + "]";
	}
	
}