//package com.lazytravel.blog.service;
//
//import java.util.List;
//
//import org.hibernate.service.spi.ServiceException;
//
//import com.lazytravel.blog.dao.BlogClDAOImpl;
//import com.lazytravel.blog.dao.BlogDAO;
//import com.lazytravel.blog.entity.Blog;
//
//public class BlogServiceImpl implements BlogService {
//
//	private BlogDAO dao;
//
//	public BlogServiceImpl() {
//		dao =  new BlogDAOImpl();
//	}
//
//	@Override
//	public int createBlog(Blog blog) {
//		// 這裡可以加入任何你需要的業務邏輯或驗證
//		// 例如，檢查blog標題是否為空，是否滿足其他條件
//
//		// 調用DAO進行blog創建
//		return dao.insert(blog);
//	}
//
//	@Override
//	public int updateBlog(Blog blog) {
//		// 這裡可以加入任何你需要的業務邏輯或驗證
//
//		// 調用DAO進行blog更新
//		return dao.update(blog);
//	}
//
//	@Override
//	public Blog getBlogById(Integer blogId) {
//		// 可以加入相應的業務邏輯
//
//		// 調用DAO獲取blog
//		return dao.getByPK(blogId);
//	}
//
//	@Override
//	public List<Blog> getAllBlogs() {
//		// 可以加入相應的業務邏輯
//
//		// 調用DAO獲取所有blog
//		return dao.getAll();
//	}
//
//}
