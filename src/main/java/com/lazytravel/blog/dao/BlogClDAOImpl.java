package com.lazytravel.blog.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.lazytravel.blog.entity.Blog;
import com.lazytravel.blog.entity.BlogCl;
import com.lazytravel.customer.entity.Customer;
import com.lazytravel.util.HibernateUtil;

public class BlogClDAOImpl implements BlogClDAO {

	private SessionFactory factory;

	public BlogClDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public Integer add(BlogCl blogCl) {
		Transaction transaction = null;
		Integer blogClId = null;
		try {
			Session session = getSession();
			transaction = session.beginTransaction();
			blogClId = (Integer) session.save(blogCl);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace(); //
		}
		return blogClId;
	}

	@Override
	public Integer update(BlogCl blogCl) {
		Transaction transaction = null;
		try {
			Session session = getSession();
			transaction = session.beginTransaction();
			session.update(blogCl);
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
	public BlogCl getByPK(Integer blogClId) {
		Transaction transaction = null;
		BlogCl blogCl = null;
		try {
			Session session = getSession();
			transaction = session.beginTransaction();
			blogCl = session.get(BlogCl.class, blogClId);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace(); //
		}
		return blogCl;
	}

	@Override
	public List<BlogCl> getAll() {
		Transaction transaction = null;
		List<BlogCl> blogCls = null;
		try {
			Session session = getSession();
			transaction = session.beginTransaction();
			blogCls = session.createQuery("from BlogCl", BlogCl.class).getResultList();
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace(); //
		}
		return blogCls;
	}

	@Override
	public  String isBlogCl(Integer customerId,Integer blogId) {
		 Transaction transaction = null;
		 Session session = getSession();
		   
		    	try {
		            transaction = session.beginTransaction();
		            // 使用 HQL 查詢是否存在對應的 BlogCl 記錄
//		            String hql = "SELECT blogCl.blogClStatus FROM BlogCl blogCl WHERE blogCl.customer.customerId = :customerId AND blogCl.blog.blogId = :blogId";
//		            Query<String> query = session.createQuery(hql, String.class);
//		            query.setParameter("customerId", customerId);
//		            query.setParameter("blogId", blogId);
//		            String blogClStatus = query.uniqueResult();
		            
		            Character tmp = (Character) session.createNativeQuery("select BLOG_CL_STATUS from Blog_Cl where customer_Id = :customerId and blog_Id = :blogId")
		            		.setParameter("customerId", customerId)
		            		.setParameter("blogId", blogId)
		            		.uniqueResult();
		            
		            String blogClStatus = String.valueOf(tmp);
		            		
		            transaction.commit(); // 提交事務
		            
		            if(blogClStatus ==null){
		            	return null;
		            }else {
		            	return blogClStatus;
		            }
		    } catch (Exception e) {
		        if (transaction != null) {
		            transaction.rollback(); // 如果發生異常，回滾事務
		        }
		        e.printStackTrace();
		        return null;
		    } finally {
		    	session.close();
		    }
				
		}

	@Override
	public void addFavoriteCl(Integer customerId,Integer blogId) {
		Transaction transaction = null;
		 Session session = getSession();
		    try {
		    	transaction = session.beginTransaction();
		    	BlogCl blogCl =new BlogCl();
		    	blogCl.setCustomer(session.load(Customer.class, customerId));
		    	blogCl.setBlog(session.load(Blog.class, blogId));
		    	blogCl.setBlogClStatus("1");
		    	session.save(blogCl);
		    	transaction.commit();
		    }catch (Exception e) {
				e.printStackTrace();
			}
		    finally {
		    	session.close();
		    }
	
	}

	@Override
	public void unFavoriteCl(Integer customerId,Integer blogId) {
		Transaction transaction = null;
		 Session session = getSession();
		    try {
		    	transaction = session.beginTransaction();
		    	String hql = "FROM BlogCl WHERE customer.customerId = :customerId AND blog.blogId = :blogId";
		        Query query = session.createQuery(hql);
		        query.setParameter("customerId", customerId);
		        query.setParameter("blogId", blogId);
		        BlogCl blogCl = (BlogCl) query.uniqueResult();

		        blogCl.setBlogClStatus("0");  // 0 表示取消收藏
	            session.update(blogCl);
		        
		    	transaction.commit();
		    }catch (Exception e) {
				e.printStackTrace();
			}
		    finally {
		    	session.close();
		    }
	
	}

	@Override
	public void updateFavoriteCl(Integer customerId, Integer blogId) {
		Transaction transaction = null;
		 Session session = getSession();
		    try {
		    	transaction = session.beginTransaction();
		    	String hql = "FROM BlogCl WHERE customer.customerId = :customerId AND blog.blogId = :blogId";
		        Query query = session.createQuery(hql);
		        query.setParameter("customerId", customerId);
		        query.setParameter("blogId", blogId);
		        BlogCl blogCl = (BlogCl) query.uniqueResult();

		        blogCl.setBlogClStatus("1");  // 0 表示取消收藏
	            session.merge(blogCl);
		        
	            transaction.commit();
		    }catch (Exception e) {
				e.printStackTrace();
			}
		    finally {
		    	session.close();
		    }
	
	}
		
	}
