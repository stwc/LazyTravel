package com.lazytravel.blog;

import java.io.Serializable;

public class BlogLike implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer bloglikeid;
	private Integer customerid;
	private Integer createtime;
	private Integer blogid;
	private String status;
	
	public BlogLike() {
		super();
	}

	public BlogLike(Integer bloglikeid, Integer customerid, Integer createtime, Integer blogid, String status) {
		super();
		this.bloglikeid = bloglikeid;
		this.customerid = customerid;
		this.createtime = createtime;
		this.blogid = blogid;
		this.status = status;
	}

	public Integer getBloglikeid() {
		return bloglikeid;
	}

	public void setBloglikeid(Integer bloglikeid) {
		this.bloglikeid = bloglikeid;
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

	public Integer getBlogid() {
		return blogid;
	}

	public void setBlogid(Integer blogid) {
		this.blogid = blogid;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BlogLike [bloglikeid=" + bloglikeid + ", customerid=" + customerid + ", createtime=" + createtime
				+ ", blogid=" + blogid + ", status=" + status + "]";
	}
	

}
