package com.lazytravel.blog;

import java.io.Serializable;

public class BlogTag implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer blodid;
	private Integer tagid;
	
	public BlogTag() {
		super();
	}

	public BlogTag(Integer blodid, Integer tagid) {
		super();
		this.blodid = blodid;
		this.tagid = tagid;
	}

	public Integer getBlodid() {
		return blodid;
	}

	public void setBlodid(Integer blodid) {
		this.blodid = blodid;
	}

	public Integer getTagid() {
		return tagid;
	}

	public void setTagid(Integer tagid) {
		this.tagid = tagid;
	}
	public String toString() {
		return "BlogTag [blodid=" + blodid + ", tagid=" + tagid + "]";
	}

}
