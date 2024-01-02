package com.lazytravel.blog.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.lazytravel.blog.entity.Blog;
import com.lazytravel.blog.entity.BlogCl;
import com.lazytravel.blog.entity.BlogLike;
import com.lazytravel.customer.entity.Customer;
import com.lazytravel.util.HibernateUtil;

public class BlogLikeDAOImpl implements BlogLikeDAO {
	private SessionFactory factory;

	public BlogLikeDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession(){
		return factory.getCurrentSession();
	}

	@Override
	public Integer add(BlogLike blogLike) {
		Transaction transaction = null;
        Integer blogLikeId = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            blogLikeId = (Integer) session.save(blogLike);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return blogLikeId;
	}

	@Override
	public Integer update(BlogLike blogLike) {
		Transaction transaction = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            session.update(blogLike);
            transaction.commit();
            return 1;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); //
            return -1;
        }
	}

	@Override
	public BlogLike getByPK(Integer blogLikeId) {
		Transaction transaction = null;
        BlogLike blogLike = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            blogLike = session.get(BlogLike.class, blogLikeId);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return blogLike;
	}

	@Override
	public List<BlogLike> getAll() {
		Transaction transaction = null;
        List<BlogLike> blogLikes = null;
        try {
            Session session = getSession();
            transaction = session.beginTransaction();
            blogLikes = session.createQuery("from BlogLike", BlogLike.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace(); // 
        }
        return blogLikes;
    }

	@Override
	public String isBlogLike(Integer customerId, Integer blogId) {
		Transaction transaction = null;
		 Session session = getSession();
		   
		    	try {
		            transaction = session.beginTransaction();
		            // 使用 HQL 查詢是否存在對應的 BlogCl 記錄
		            String hql = "SELECT blogLike.blogLikeStatus FROM BlogLike blogLike WHERE blogLike.customer.customerId = :customerId AND blogLike.blog.blogId = :blogId";
		            Query<String> query = session.createQuery(hql, String.class);
		            query.setParameter("customerId", customerId);
		            query.setParameter("blogId", blogId);
		            String blogLikeStatus = query.uniqueResult();
		            
//		            Character tmp = (Character) session.createNativeQuery("select BLOG_CL_STATUS from Blog_Cl where customer_Id = :customerId and blog_Id = :blogId")
//		            		.setParameter("customerId", customerId)
//		            		.setParameter("blogId", blogId)
//		            		.uniqueResult();
//		            
//		            String blogClStatus = String.valueOf(tmp);
		            System.out.println(blogLikeStatus);
		            transaction.commit(); // 提交事務
		            System.out.println("BlogLikeStatus: " + blogLikeStatus);
		            if(blogLikeStatus == null){
		            	return "novalue";
		            	
		            }else {
		            	System.out.println(blogLikeStatus);
		            	return  blogLikeStatus;
		            }
		            
		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback(); // 如果發生異常，回滾事務
		        }
		        e.printStackTrace();
		        return "0";
		    } finally {
		    	session.close();
		    	
		    }
				
		}

	@Override
	public String addLike(Integer customerId, Integer blogId) {
		Transaction transaction = null;
		 Session session = getSession();
		    try {
		    	transaction = session.beginTransaction();
		    	BlogLike blogLike =new BlogLike();
		    	blogLike.setCustomer(session.load(Customer.class, customerId));
		    	blogLike.setBlog(session.load(Blog.class, blogId));
		    	blogLike.setBlogLikeStatus("1");
		    	session.save(blogLike);
		    	transaction.commit();
		    }catch (Exception e) {
				e.printStackTrace();
			}
		    finally {
		    	session.close();
		    }
	return "1";
	}

	@Override
	public String unLike(Integer customerId, Integer blogId) {
		Transaction transaction = null;
		 Session session = getSession();
		    try {
		    	transaction = session.beginTransaction();
		    	String hql = "FROM BlogLike WHERE customer.customerId = :customerId AND blog.blogId = :blogId";
		        Query query = session.createQuery(hql);
		        query.setParameter("customerId", customerId);
		        query.setParameter("blogId", blogId);
		        BlogLike blogLike = (BlogLike) query.uniqueResult();

		        blogLike.setBlogLikeStatus("0");  // 0 表示取消收藏
	            session.update(blogLike);
		        
		    	transaction.commit();
		    }catch (Exception e) {
				e.printStackTrace();
			}
		    finally {
		    	session.close();
		    }
	return "0";
	}

	@Override
	public String onLike(Integer customerId, Integer blogId) {
		Transaction transaction = null;
		 Session session = getSession();
		    try {
		    	transaction = session.beginTransaction();
		    	String hql = "FROM BlogLike WHERE customer.customerId = :customerId AND blog.blogId = :blogId";
		        Query query = session.createQuery(hql);
		        query.setParameter("customerId", customerId);
		        query.setParameter("blogId", blogId);
		        BlogLike blogLike = (BlogLike) query.uniqueResult();

		        blogLike.setBlogLikeStatus("1");  // 0 表示取消收藏
	            session.update(blogLike);
		        
	            transaction.commit();
		    }catch (Exception e) {
				e.printStackTrace();
			}
		    finally {
		    	session.close();
		    }
	return "1";
	}

	
}
