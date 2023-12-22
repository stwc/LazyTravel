package com.lazytravel.blog.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.lazytravel.blog.entity.BlogMsg;
import com.lazytravel.blog.entity.BlogTag;
import com.lazytravel.blog.entity.BlogTag.CompositeDetail;
import com.lazytravel.util.HibernateUtil;

public class BlogTagDAOImpl implements BlogTagDAO{
	
private SessionFactory factory;
	
	public BlogTagDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer add(Integer blogId, Integer tagId) {
		Transaction transaction = null;
        Integer newblogTag =null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            
            BlogTag blogTag = new BlogTag();
            BlogTag.CompositeDetail compositeKey = new BlogTag.CompositeDetail(blogId, tagId);
            blogTag.setCompositeKey(compositeKey);
            
           session.save(blogTag);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return newblogTag;
	}

		@Override
		public Integer update(Integer blogId, Integer tagId) {
		    Transaction transaction = null;
		    try {
		        Session session = getSession();
		        transaction = session.beginTransaction();

		        // 使用複合主鍵檢索對應的 BlogTag 物件
		        BlogTag.CompositeDetail compositeKey = new BlogTag.CompositeDetail(blogId, tagId);
		        BlogTag blogTag = session.get(BlogTag.class, compositeKey);
		        System.out.println(blogTag);
		        System.out.println(compositeKey);

		        if (blogTag != null) {
		            // 對現有的物件進行修改
		            session.update(compositeKey);
		            transaction.commit();
		            return 1; 	
		        } else {
		            transaction.commit();
		            return 0; 
		        }
		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback();
		        }
		        e.printStackTrace();
		        return -1; // 表示發生異常
		    }
		}
	@Override
	public Integer delete(Integer blogId, Integer tagId) {
	    Transaction transaction = null;
	    try {
	        Session session = getSession();
	        transaction = session.beginTransaction();

	        // 使用複合主鍵建立實例
	        BlogTag.CompositeDetail compositeKey = new BlogTag.CompositeDetail(blogId, tagId);

	        // 使用複合主鍵檢索對應的 BlogTag 物件
	        BlogTag blogTag = session.get(BlogTag.class, compositeKey);

	        // 如果找到對應的 BlogTag 物件，進行刪除
	        if (blogTag != null) {
	            session.delete(blogTag);
	            transaction.commit();
	            return 1; // 表示成功刪除
	        } else {
	            transaction.commit();
	            return 0; // 表示未找到對應的 BlogTag 物件
	        }

	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace(); 
	        return -1; // 表示刪除過程中發生錯誤
	    }
	}

	@Override
	public BlogTag getByPK(Integer blogId, Integer tagId) {
	    Transaction transaction = null;
	    BlogTag blogTag = null;
	    try {
	        Session session = getSession();
	        transaction = session.beginTransaction();

	        // 使用複合主鍵建立實例
	        BlogTag.CompositeDetail compositeKey = new BlogTag.CompositeDetail(blogId, tagId);

	        // 使用複合主鍵檢索對應的 BlogTag 物件
	        blogTag = session.get(BlogTag.class, compositeKey);

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    }
	    return blogTag;
	}

	@Override
	public List<BlogTag> getAll() {
		Transaction transaction = null;
        List<BlogTag> blogTags = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            blogTags = session.createQuery("from BlogTag", BlogTag.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return blogTags;
	}
}
