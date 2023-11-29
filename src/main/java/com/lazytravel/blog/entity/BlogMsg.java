package com.lazytravel.blog;

import java.io.Serializable;
import java.sql.Timestamp;

public class BlogMsg implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer blogMsgId;
	private String content;
	private Integer blogId;
	private Integer customerId;
	private Timestamp createTime;

	public BlogMsg() {
		super();
	}

	public BlogMsg(Integer blogMsgId, String content, Integer blogId, Integer customerId, Timestamp createTime) {
		super();
		this.blogMsgId = blogMsgId;
		this.content = content;
		this.blogId = blogId;
		this.customerId = customerId;
		this.createTime = createTime;
	}

	public Integer getBlogMsgId() {
		return blogMsgId;
	}

	public void setBlogMsgId(Integer blogMsgId) {
		this.blogMsgId = blogMsgId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getBlogId() {
		return blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String toString() {
		return "BlogMsg [blogMsgId=" + blogMsgId + ", content=" + content + ", blogId=" + blogId + ", customerId="
				+ customerId + ", createTime=" + createTime + "]";
	}
	
}