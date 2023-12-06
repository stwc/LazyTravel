package com.lazytravel.blog.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.lazytravel.blog.entity.BlogImg;
import com.lazytravel.util.HibernateUtil;


public class BlogImgDAOImpl implements BlogImgDAO {
	private SessionFactory factory;

	public BlogImgDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	private Session getSession() {
		return factory.getCurrentSession();
	}
    @Override
    public int insert(BlogImg entity) {
        return (Integer) getSession().save(entity);
    }

    @Override
    public int update(BlogImg entity) {
    	try {
			getSession().update(entity);
			return 1; 
		}catch(Exception e) {
			return -1;
		}
    }

    @Override
    public int delete(Integer blogImgId) {
    	BlogImg blogImg = getSession().get(BlogImg.class, blogImgId);
		if (blogImgId != null) {
			getSession().delete(blogImgId);
			// 回傳給 service，1代表刪除成功
			return 1;
		} else {
			// 回傳給 service，-1代表刪除失敗
			return -1;
		}
    }

    @Override
    public BlogImg getByPK(Integer blogImgId) {
            return getSession().get(BlogImg.class, blogImgId);
    }

    @Override
    public List<BlogImg> getAll() {
        // 實際應用中需要根據實際情況從資料庫中獲取所有紀錄
        return null;
    }
}