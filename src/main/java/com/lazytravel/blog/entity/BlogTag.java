package com.lazytravel.blog.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;


@Entity
@Table(name = "blog_tag")
@IdClass(BlogTag.class)
public class BlogTag {

	@Id
	@Column(name = "BLOG_ID")
	private Integer blogId;

	@Id
	@Column(name = "TAG_ID")
	private Integer tagId;

	public CompositeDetail getCompositeKey() {
		return new CompositeDetail(blogId, tagId);
	}

	public void setCompositeKey(CompositeDetail key) {
		this.blogId = key.getBlogId();
		this.tagId = key.getTagId();

	}

	public Integer getBlogId() {
		return blogId;
	}

	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}

	public Integer getTagId() {
		return tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	static class CompositeDetail implements Serializable {
		private static final long serialVersionUID = 1L;

		private Integer blogId;
		private Integer tagId;

		public CompositeDetail() {
			super();
		}

		public CompositeDetail(Integer blogId, Integer tagId) {
			super();
			this.blogId = blogId;
			this.tagId = tagId;
		}

		public Integer getBlogId() {
			return blogId;
		}

		public void setBlogId(Integer blogId) {
			this.blogId = blogId;
		}

		public Integer getTagId() {
			return tagId;
		}

		public void setTagid(Integer tagId) {
			this.tagId = tagId;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((blogId == null) ? 0 : blogId.hashCode());
			result = prime * result + ((tagId == null) ? 0 : tagId.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj != null && getClass() == obj.getClass()) {
				CompositeDetail compositeKey = (CompositeDetail) obj;
				if (blogId.equals(compositeKey.blogId) && tagId.equals(compositeKey.tagId)) {
					return true;
				}
			}
			return true;
		}
	}
}