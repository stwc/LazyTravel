package com.lazytravel.blog;

import java.io.Serializable;

public class BlogImg implements Serializable{
	private static final long serialVersionUID = 1L;
	private Integer blogimgid;
	private Integer blogid;
	private Integer createtime;
	private Integer img;
	
	public BlogImg() {
		super();
	}

	public BlogImg(Integer blogimgid, Integer blogid, Integer createtime, Integer img) {
		super();
		this.blogimgid = blogimgid;
		this.blogid = blogid;
		this.createtime = createtime;
		this.img = img;
	}

	public Integer getBlogimgid() {
		return blogimgid;
	}

	public void setBlogimgid(Integer blogimgid) {
		this.blogimgid = blogimgid;
	}

	public Integer getBlogid() {
		return blogid;
	}

	public void setBlogid(Integer blogid) {
		this.blogid = blogid;
	}

	public Integer getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Integer createtime) {
		this.createtime = createtime;
	}

	public Integer getImg() {
		return img;
	}

	public void setImg(Integer img) {
		this.img = img;
	}
	public String toString() {
		return "BlogImg [blogimgid=" + blogimgid + ", blogid=" + blogid + ", createtime=" + createtime + ", img=" + img
				+ "]";
	}
	
}
