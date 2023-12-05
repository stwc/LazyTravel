package com.lazytravel.blog.service;

import java.util.List;

import com.lazytravel.blog.entity.BlogMsg;

public interface BlogMsgService {
	
void addBlogMsg(BlogMsg blogmsg) ;
	
    // 更新留言的業務邏輯
    void updateBlogMsg(BlogMsg blogmsg);

    // 刪除留言的業務邏輯
    void deleteBlogLike(Integer blogmsg);

    // 獲取留言的業務邏輯
    BlogMsg getByPK(Integer blogMsgId);

    // 獲取所有留言的業務邏輯
    List<BlogMsg> getAllBlogMsgs();

}
