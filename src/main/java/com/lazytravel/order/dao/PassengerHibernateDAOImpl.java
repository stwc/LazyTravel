package com.lazytravel.order.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.lazytravel.order.entity.Passenger;
import com.lazytravel.util.HibernateUtil;

public class PassengerHibernateDAOImpl implements PassengerHibernateDAO{

	private SessionFactory factory;
	
	public PassengerHibernateDAOImpl() {
		factory = HibernateUtil.getSessionFactory();
	}
	
	private Session getSession() {
		return factory.getCurrentSession();
	}
	
	@Override
	public void add(Passenger passenger) {
		Session session = getSession();
		try {
			session.beginTransaction();
			session.save(passenger);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
//		getSession().update(passenger);		
	}
	
	@Override
	public Passenger getByPk(Integer passengerId) {
		Session session = getSession();
		Passenger passenger = null;
		try {
			session.beginTransaction();
			passenger = session.get(Passenger.class, passengerId);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return passenger;
//		return getSession.get(Passenger.class, passengerId);
	}
	
	
	@Override
    public List<Passenger> getByOrderId(Integer orderId) {
        Session session = getSession();
        List<Passenger> passengers = null;
        try {
            session.beginTransaction();
            passengers = session.createQuery("FROM Passenger WHERE orderId = :orderId", Passenger.class)
                    .setParameter("orderId", orderId)
                    .getResultList();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return passengers;
    }
	
	@Override
	public void update(Passenger passenger) {
		Session session = getSession();
		try {
			session.beginTransaction();
			session.update(passenger);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			
		}
		
//		getSession().update(passenger);
	}
	
	@Override
	public List<Passenger> getAll(){
		Session session = getSession();
		List<Passenger> list = null;
		try {
			session.beginTransaction();
			list = session.createNamedQuery("from Passenger", Passenger.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		
		return list;
//		return getSession().createQuery("from Passenger", Passenger.class).list();
	}
	
	
	public static void main(String args[]) {

//	    PassengerHibernateDAO dao = new PassengerHibernateDAOImpl();
//		Passenger passenger = null;
//		List<Passenger> paslist = null;
//
//		passenger = new Passenger();
//		passenger.setOrderId(31001);
//		passenger.setIdno("f123456789");
//		passenger.setPassengerName("金城武");
//		passenger.setPhone("0912345678");
//		passenger.setBirth(Date.valueOf("1990-01-01"));
//		dao.add(passenger);
//		System.out.println("新增成功");
		
		
		//查詢單筆
//		passenger = dao.getByPk(1);
//		System.out.println(passenger);
		
//		更新單筆
//		passenger = dao.getByPk(1);
//		passenger.setPassengerName("豬二哥");
//		dao.update(passenger);
//		System.out.println("更新成功");
//		
//		//用orderId查單筆
//		paslist = dao.getByOrderId(31001);
//		System.out.println(paslist);
	
	

	}

}
