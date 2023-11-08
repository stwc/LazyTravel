package com.lazytravel.blog;

import java.io.Serializable;

public class BlogMsg implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer blogmsgid;
	private Integer content;
	private Integer blogid;
	private Integer customerid;
	private Integer createtime;

	public BlogMsg() {
		super();
	}

	public BlogMsg(Integer blogmsgid, Integer content, Integer blogid, Integer customerid, Integer createtime) {
		super();
		this.blogmsgid = blogmsgid;
		this.content = content;
		this.blogid = blogid;
		this.customerid = customerid;
		this.createtime = createtime;
	}

	public Integer getBlogmsgid() {
		return blogmsgid;
	}

	public void setBlogmsgid(Integer blogmsgid) {
		this.blogmsgid = blogmsgid;
	}

	public Integer getContent() {
		return content;
	}

	public void setContent(Integer content) {
		this.content = content;
	}

	public Integer getBlogid() {
		return blogid;
	}

	public void setBlogid(Integer blogid) {
		this.blogid = blogid;
	}

	public Integer getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}

	public Integer getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Integer createtime) {
		this.createtime = createtime;
	}

	public String toString() {
		return "Blogmsg [blogmsgid=" + blogmsgid + ", content=" + content + ", blogid=" + blogid + ", customerid="
				+ customerid + ", createtime=" + createtime + "]";
	}
	
}
