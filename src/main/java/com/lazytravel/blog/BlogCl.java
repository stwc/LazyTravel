package com.lazytravel.blog;

import java.io.Serializable;

public class BlogCl implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer blogclid;
	private Integer customerid;
	private Integer liketime;
	private Integer blogid;
	private String customerstatus;
	
	public BlogCl() {
		super();
	}

	public BlogCl(Integer blogclid, Integer customerid, Integer liketime, Integer blogid, String customerstatus) {
		super();
		this.blogclid = blogclid;
		this.customerid = customerid;
		this.liketime = liketime;
		this.blogid = blogid;
		this.customerstatus = customerstatus;
	}

	public Integer getBlogclid() {
		return blogclid;
	}

	public void setBlogclid(Integer blogclid) {
		this.blogclid = blogclid;
	}

	public Integer getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}

	public Integer getLiketime() {
		return liketime;
	}

	public void setLiketime(Integer liketime) {
		this.liketime = liketime;
	}

	public Integer getBlogid() {
		return blogid;
	}

	public void setBlogid(Integer blogid) {
		this.blogid = blogid;
	}

	public String getCustomerstatus() {
		return customerstatus;
	}
	public void setCustomerstatus(String customerstatus) {
		this.customerstatus = customerstatus;
	}
	
}
