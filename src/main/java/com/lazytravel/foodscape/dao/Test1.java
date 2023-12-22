package com.lazytravel.foodscape.dao;

import java.util.Set;

import org.hibernate.Session;

import com.lazytravel.foodscape.entity.FoodScape;
import com.lazytravel.foodscape.entity.Tag;
import com.lazytravel.util.HibernateUtil;


public class Test1 {
	public static void main(String[] args) throws Exception {
//		FoodScapeDAO dao = new FoodScapeDAOImpl();
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
try {
			
			session.beginTransaction();
			
			FoodScape foodscape = session.get(FoodScape.class, 22002);
			Set<Tag> tags = foodscape.getTags();
			for (Tag a : tags) {
				System.out.println(a.getName());
			}
			
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			HibernateUtil.shutdown();
//		FoodScape foodscape1 = new FoodScape();
//		foodscape1.setFoodScapeName("中正紀念堂");
//		foodscape1.setPhone("27312312");
//		foodscape1.setAddress("忠孝東路五段237號");
//		foodscape1.setCity("台北市");
//		foodscape1.setLng(123.57823);
//		foodscape1.setLat(25.12312);
//		foodscape1.setIntro("環境很好");
//		foodscape1.setFoodScapeStatus("4");
//		foodscape1.setCategory("景點");
//		dao.insert(foodscape1);
//		System.out.println("增加成功");
		
//		FoodScape foodscape2 = new FoodScape();
//		foodscape2.setFoodScapeId(22001);
//		foodscape2.setFoodScapeName("信義誠品");
//		foodscape2.setPhone("27113241");
//		foodscape2.setAddress("信義路五段111號");
//		foodscape2.setCity("新北");
//		foodscape2.setLng(0);
//		foodscape2.setLat(23.41231);
//		foodscape2.setIntro("這裡人很多");
//		foodscape2.setFoodScapeStatus("4");
//		foodscape2.setCategory("景點");
//		dao.update(foodscape2);
	
		
//		FoodScape foodscape3 = dao.getByPK(22001);
//		System.out.println(foodscape3);
		
//		List<FoodScape> list = dao.getAll();
//		for (FoodScape foodscape: list) {
//			System.out.println(foodscape);
//		}
}

	}
}




