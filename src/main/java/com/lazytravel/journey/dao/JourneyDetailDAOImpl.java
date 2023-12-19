package com.lazytravel.journey.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.lazytravel.journey.entity.JourneyDetail;
import com.lazytravel.util.HibernateUtil;

public class JourneyDetailDAOImpl implements JourneyDetailDAO {

	@Override
	public int add(JourneyDetail journeyDetail) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.save(journeyDetail);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();;
			return -1;
		}
	}

	@Override
	public int update(JourneyDetail journeyDetail) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(journeyDetail);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		}
	}

//	@Override
//	public void delete(CompositeJourneyAndFoodscape compositeJourneyAndFoodscape) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		try {
//			session.beginTransaction();
//			
//			if(session.get(CompositeJourneyAndFoodscape.class, compositeJourneyAndFoodscape.getJourneyId()) != null) {
//				if(session.get(CompositeJourneyAndFoodscape.class, compositeJourneyAndFoodscape.getFoodScapeId()) != null) {
//					session.delete(compositeJourneyAndFoodscape);
//				}
//			}
//
//			session.getTransaction().commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//	}
	
	@Override
	public void delete(Integer journeyId, Integer foodscapeId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			
			JourneyDetail journeyDetail = session.get(JourneyDetail.class, new JourneyDetail.CompositeJourneyAndFoodscape(journeyId, foodscapeId));
			if(journeyDetail != null) {
				session.delete(journeyDetail);;
			}
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
	}

//	@Override
//	public JourneyDetail findByPk(Integer journeyId, Integer foodscapeId) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		JourneyDetail journeyDetail = null;
//		try {
//			session.beginTransaction();
//			journeyDetail = session.createQuery("from JourneyDetail where journeyId = :journeyId AND foodScapeId = :foodScapeId", JourneyDetail.class).setParameter("journeyId", journeyId).setParameter("foodScapeId", foodscapeId).uniqueResult();
//			session.getTransaction().commit();
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//		}
//		return journeyDetail;
//	}
	
	@Override
	public List<JourneyDetail> findByJourneyId(Integer journeyId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<JourneyDetail> list = null;
		try {
			session.beginTransaction();
			list = session.createQuery("from JourneyDetail where journeyId = :journeyId", JourneyDetail.class).setParameter("journeyId", journeyId).list();
			session.getTransaction().commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return list;
	}

	@Override
	public List<JourneyDetail> addList(List<JourneyDetail> journeyDetail, Integer journeyId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<JourneyDetail> journeyDetailList = new ArrayList<JourneyDetail>();
		try {
			session.beginTransaction();		
			
			for(JourneyDetail object : journeyDetail) {
				object.setJourneyId(journeyId);
				
				// session.merge()
			    JourneyDetail mergedDetail = (JourneyDetail) session.merge(object);
			    journeyDetailList.add(mergedDetail);
				
//	            // 使用session.persist()
//	            JourneyDetail persistentDetail = (JourneyDetail) object;
//	            session.persist(persistentDetail);
//	            journeyDetailList.add(persistentDetail);
				
//			    // 使用session.save()
//				journeyDetailList.add((JourneyDetail) session.save(object));
			}
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return journeyDetailList;
	}
	
	
	
//// ------------------------------ 用 main 方法測試 --------------------------------	
//	public static void main(String[] args) {
//		JourneyDetailDAO journeyDetailDAO = new JourneyDetailDAOImpl();
//		
//		// 新增
//		
//		
//		
//		
//		// 修改
//		
//		
//		
//		// 刪除
//		
//		
//		
//		
//		// 查詢一筆
//		
//		
//		
//	}
	
}
