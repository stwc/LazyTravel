package com.lazytravel.blog.service;

import java.util.List;

import com.lazytravel.blog.entity.BlogTag;

public interface BlogTagService {
	
void addBlogTag(BlogTag tagId) ;
	
    // 更新標籤的業務邏輯
    void updateBlogTag(BlogTag tagId);

    // 刪除標籤的業務邏輯
    void deleteBlogTag(Integer tagId);

    // 獲取標籤的業務邏輯
    BlogTag getByPK(Integer blogId);

    // 獲取所有標籤的業務邏輯
    List<BlogTag> getAllBlogs();

}
