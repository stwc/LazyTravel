package com.lazytravel.order.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import com.lazytravel.order.entity.Coupon;
import com.lazytravel.order.entity.Orders;
import com.lazytravel.util.HibernateUtil;
import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

public class OrdersHibernateDAOImpl implements OrdersHibernateDAO {

	private SessionFactory factory;

	public OrdersHibernateDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}

	private Session getSession() {
		return factory.getCurrentSession();
	}

	@Override
	public void add(Orders orders) {
		Session session = getSession();
		try {
			session.beginTransaction();
			orders.setCreateTime(new java.sql.Timestamp(System.currentTimeMillis()));
			orders.setUpdateTime(new java.sql.Timestamp(System.currentTimeMillis()));
			session.save(orders);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}

//	    getSession().save(orders);
	}

	@Override
	public void update(Orders orders) {
		Session session = getSession();
		try {
			session.beginTransaction();
			Orders existingOrder = getSession().get(Orders.class, orders.getOrderId());
			orders.setCreateTime(existingOrder.getCreateTime()); // Set the existing create time
			orders.setUpdateTime(new java.sql.Timestamp(System.currentTimeMillis()));
			session.merge(orders);
			session.getTransaction().commit();

		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
//	    getSession().merge(orders);

	}

	@Override
	public Orders getOrdersByOrdersId(Integer orderId) {
		Session session = getSession();
		Orders orders = null;
		try {
			session.beginTransaction();
			orders = getSession().get(Orders.class, orderId);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return orders;
//		return getSession().get(Order.class, orderId);

	}

	@Override
	public Orders getOrdersByOrdersNo(Integer orderNo) {
		Session session = getSession();
		Orders orders = null;
		try {
			session.beginTransaction();
			Query<Orders> query = session.createQuery("FROM Orders WHERE order_no = :orderNo" , Orders.class);
			query.setParameter("orderNo", orderNo);
			orders = query.getSingleResult();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return orders;
		
//		return getSession().get(Order.class, orderNo);
	}

	@Override
	public List<Orders> getAll() {
		Session session = getSession();
		List<Orders> list = null;
		try {
			session.beginTransaction();
			list  = session.createQuery("from Orders", Orders.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return list;
//		return getSession().createQuery("from orders", Orders.class).list();
    }
	
	
	@Override
	public String getJourneyNameByOrderId(Integer orderId) {
		Session session = getSession();
		String orderName = null;
		String statement = "SELECT journey.journey_name FROM orders JOIN tour_group ON orders.group_id = tour_group.group_id "
						 + "JOIN journey ON journey.journey_id = tour_group.journey_id where orders.order_id = :orderId";
		try {
			session.beginTransaction();
			orderName = (String)session.createNativeQuery(statement)
								.setParameter("orderId",orderId)
								.uniqueResult();
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return orderName;
	}
	

	@Override
	public List<Orders> getOrderByCustomerId(Integer customerId) {
		Session session = getSession();
		List<Orders> orderslist = null;
		try {
			session.beginTransaction();
			Query<Orders> query = session.createQuery("FROM Orders WHERE customer_Id = :customerId" , Orders.class);
			query.setParameter("customerId", customerId);
			orderslist = query.list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return orderslist;
	}

	public void cancelOrder(Integer orderId) {
	    Session session = getSession();
	    try {
	        session.beginTransaction();
	        Orders existingOrder = session.get(Orders.class, orderId);
	        existingOrder.setOrderStatus("2");
	        existingOrder.setUpdateTime(new java.sql.Timestamp(System.currentTimeMillis()));
	        session.update(existingOrder);
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	        session.getTransaction().rollback();
	    } finally {
	        session.close();
	    }
	}
	
	
	
	
	
//	public static void main(String[] args) {
//		OrdersHibernateDAO dao = new OrdersHibernateDAOImpl();
//		List<Orders> orders = null;
//		Orders orders1 = null;
//		
//		orders = dao.getOrderByCustomerId(11001);
//		orders1 = dao.getOrdersByOrdersNo(2024110801);
//		System.out.println(orders);
//		System.out.println(orders1);
//		
//		dao.cancelOrder(31001);
//		System.out.println(orders1);
//		
//	}
	

}
