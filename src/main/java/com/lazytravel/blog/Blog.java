package com.lazytravel.blog;

import java.io.Serializable;

public class Blog implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer blogId;
	private String name;
	private Integer customerid;
	private Integer date;
	private Integer content;
	private Integer updatetime;
	private Integer createtime;
	private Integer likesum;
	private Integer viewsum;
	private Integer clsum;
	private Integer img;
	private String status;

	public Blog() {
		super();
	}

	public Blog(Integer blogId, String name, Integer customerid, Integer date, Integer content, Integer updatetime,
			Integer createtime, Integer likesum, Integer viewsum, Integer clsum, Integer img, String status) {
		super();
		this.blogId = blogId;
		this.name = name;
		this.customerid = customerid;
		this.date = date;
		this.content = content;
		this.updatetime = updatetime;
		this.createtime = createtime;
		this.likesum = likesum;
		this.viewsum = viewsum;
		this.clsum = clsum;
		this.img = img;
		this.status = status;
	}

	public Integer getBlogId() {
		return blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCustomerid() {
		return customerid;
	}

	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}

	public Integer getDate() {
		return date;
	}

	public void setDate(Integer date) {
		this.date = date;
	}

	public Integer getContent() {
		return content;
	}

	public void setContent(Integer content) {
		this.content = content;
	}

	public Integer getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Integer updatetime) {
		this.updatetime = updatetime;
	}

	public Integer getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Integer createtime) {
		this.createtime = createtime;
	}

	public Integer getLikesum() {
		return likesum;
	}

	public void setLikesum(Integer likesum) {
		this.likesum = likesum;
	}

	public Integer getViewsum() {
		return viewsum;
	}

	public void setViewsum(Integer viewsum) {
		this.viewsum = viewsum;
	}

	public Integer getClsum() {
		return clsum;
	}

	public void setClsum(Integer clsum) {
		this.clsum = clsum;
	}

	public Integer getImg() {
		return img;
	}

	public void setImg(Integer img) {
		this.img = img;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String toString() {
		return "Blog [blogId=" + blogId + ", name=" + name + ", customerid=" + customerid + ", date=" + date
				+ ", content=" + content + ", updatetime=" + updatetime + ", createtime=" + createtime + ", likesum="
				+ likesum + ", viewsum=" + viewsum + ", clsum=" + clsum + ", img=" + img + ", status=" + status + "]";
	}
	
}
