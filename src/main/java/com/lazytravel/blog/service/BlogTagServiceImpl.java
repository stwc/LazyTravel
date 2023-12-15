package com.lazytravel.blog.service;

import java.util.List;

import com.lazytravel.blog.dao.BlogTagDAO;
import com.lazytravel.blog.dao.BlogTagDAOImpl;
import com.lazytravel.blog.entity.BlogTag;
import com.lazytravel.blog.entity.BlogTag.CompositeDetail;

public class BlogTagServiceImpl  implements BlogTagService{

		private BlogTagDAO dao;
		
		public BlogTagServiceImpl() {
			dao =new BlogTagDAOImpl();
		}

		@Override
		public BlogTag addBlogTag(CompositeDetail compositeDetail) {
			Integer id = dao.add(compositeDetail.getBlogId(), compositeDetail.getTagId());
		    BlogTag blogTag = dao.getByPK(compositeDetail.getBlogId(), compositeDetail.getTagId());
		    return blogTag;
		}

		@Override
		public Integer updateBlogTag(CompositeDetail compositeDetail) {
			return dao.update(compositeDetail.getBlogId(), compositeDetail.getTagId());
		}



		@Override
		public Integer deleteBlogTag(CompositeDetail compositeDetail) {
			 return dao.delete(compositeDetail.getBlogId(), compositeDetail.getTagId());
		}

		@Override
		public BlogTag getBlogTagByBlogTagId(CompositeDetail compositeDetail) {
		return dao.getByPK(compositeDetail.getBlogId(), compositeDetail.getTagId());
		}
		
		@Override
		public List<BlogTag> getAllBlogTags() {
			return dao.getAll();
		}
}
