package com.lazytravel.blog;

import java.io.Serializable;

public class BlogTag implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Integer blodId;
	private Integer tagId;
	
	public BlogTag() {
		super();
	}

	public BlogTag(Integer blodId, Integer tagId) {
		super();
		this.blodId = blodId;
		this.tagId = tagId;
	}

	public Integer getBlodId() {
		return blodId;
	}

	public void setBlodId(Integer blodId) {
		this.blodId = blodId;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	public String toString() {
		return "BlogTag [blodId=" + blodId + ", tagId=" + tagId + "]";
	}
	
}
