package com.lazytravel.journey.dao;

import java.util.List;

import org.hibernate.Session;

import com.lazytravel.journey.entity.Journey;
import com.lazytravel.util.HibernateUtil;


public class JourneyDAOImpl implements JourneyDAO {

	@Override
	public int add(Journey journey) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(journey);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		}
	}

	@Override
	public int update(Journey journey) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(journey);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		}
	}

	@Override
	public Journey findByPK(Integer journeyId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Journey journey = null;
		try {
			session.beginTransaction();
			journey = session.get(Journey.class, journeyId);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return journey;
	}

	@Override
	public List<Journey> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<Journey> list = null;
		try {
			session.beginTransaction();
			 list = session.createQuery("from Journey", Journey.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return list;
	}
	


// --------------------------------- JDBC -----------------------------------
//	private static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/LazyTravelDB");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	private static final String JOURNEY_INSERT_STMT = "INSERT INTO journey(journey_name, price, content, days, journey_status) VALUES (?, ?, ?, ?, ?)";
//	private static final String JOURNEY_UPDATE_STMT = "UPDATE journey SET journey_name = ?, price = ?, content = ?, days = ?, journey_status = ? WHERE journey_id = ?";
//	private static final String JOURNEY_DELETE_STMT = "DELETE FROM journey WHERE journey_id = ?";
//	private static final String JOURNEY_FIND_BY_PK = "SELECT * FROM journey WHERE journey_id = ?";
//	private static final String JOURNEY_GET_ALL = "SELECT * FROM journey";
//
//	
//	@Override
//	public void add(Journey journey) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(JOURNEY_INSERT_STMT);
//			
//			pstmt.setString(1, journey.getJourneyName());
//			pstmt.setInt(2, journey.getPrice());
//			pstmt.setString(3, journey.getContent());
//			pstmt.setInt(4, journey.getDays());
//			pstmt.setString(5, journey.getJourneyStatus());
//			
//			pstmt.executeUpdate();
//		
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			closeResources(con, pstmt, null);
//		}
//	}
//
//	
//	@Override
//	public void update(Journey journey) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(JOURNEY_UPDATE_STMT);
//			
//			pstmt.setString(1, journey.getJourneyName());
//			pstmt.setInt(2, journey.getPrice());
//			pstmt.setString(3, journey.getContent());
//			pstmt.setInt(4, journey.getDays());
//			pstmt.setString(5, journey.getJourneyStatus());
//			pstmt.setInt(6, journey.getJourneyId());
//			
//			pstmt.executeUpdate();
//			
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			closeResources(con, pstmt, null);
//		}
//	}
//
//	
//	@Override
//	public void delete(Integer journeyId) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(JOURNEY_DELETE_STMT);
//			pstmt.setInt(1, journeyId);
//			pstmt.executeUpdate();
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			closeResources(con, pstmt, null);
//		}
//	}
//
//	
//	@Override
//	public Journey findByPK(Integer journeyId) {
//		Journey journey = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(JOURNEY_FIND_BY_PK);
//			pstmt.setInt(1, journeyId);
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				journey = new Journey();
//				journey.setJourneyId(rs.getInt("JOURNEY_ID"));
//				journey.setJourneyName(rs.getString("JOURNEY_NAME"));
//				journey.setPrice(rs.getInt("PRICE"));
//				journey.setCreateTime(rs.getTimestamp("CREATE_TIME"));
//				journey.setContent(rs.getString("CONTENT"));
//				journey.setAvgScore(rs.getDouble("AVG_SCORE"));
//				journey.setScoreCount(rs.getInt("SCORE_COUNT"));
//				journey.setDays(rs.getInt("DAYS"));
//				journey.setBuyCount(rs.getInt("BUY_COUNT"));
//				journey.setJourneyStatus(rs.getString("JOURNEY_STATUS"));				
//			}	
//			
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			closeResources(con, pstmt, rs);
//		}
//		return journey;
//	}
//
//	
//	@Override
//	public List<Journey> getAll() {
//		List<Journey> journeyList = new ArrayList<Journey>();
//		Journey journey = null;
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(JOURNEY_GET_ALL);
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()){
//				journey = new Journey();
//				journey.setJourneyId(rs.getInt("JOURNEY_ID"));
//				journey.setJourneyName(rs.getString("JOURNEY_NAME"));
//				journey.setPrice(rs.getInt("PRICE"));
//				journey.setCreateTime(rs.getTimestamp("CREATE_TIME"));
//				journey.setContent(rs.getString("CONTENT"));
//				journey.setAvgScore(rs.getDouble("AVG_SCORE"));
//				journey.setScoreCount(rs.getInt("SCORE_COUNT"));
//				journey.setDays(rs.getInt("DAYS"));
//				journey.setBuyCount(rs.getInt("BUY_COUNT"));
//				journey.setJourneyStatus(rs.getString("JOURNEY_STATUS"));
//				journeyList.add(journey);
//			}
//			
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			closeResources(con, pstmt, rs);
//		}
//		return journeyList;
//	}
//		
//	
//	private void closeResources(Connection con, PreparedStatement pstmt, ResultSet rs) {
//		if(rs != null) {
//			try {
//				rs.close();
//			} catch (SQLException se) {
//				se.printStackTrace(System.err);
//			}
//		}
//		if(pstmt != null) {
//			try {
//				pstmt.close();
//			} catch (SQLException se) {
//				se.printStackTrace(System.err);
//			}
//		}
//		if(con != null) {
//			try {
//				con.close();
//			} catch (Exception e) {
//				e.printStackTrace(System.err);
//			}
//		}
//	}
	
	

// ------------------------------ 用 main 方法測試 --------------------------------	
//	public static void main(String[] args) {
//		JourneyDAO journeyDAO = new JourneyDAOImpl();
//		
//		// 新增單筆
//		Journey journey1 = new Journey();
//		journey1.setJourneyName("慢活一日遊");
//		journey1.setPrice(3333);
//		journey1.setContent("吃飽睡，睡飽吃");
//		journey1.setDays(1);
//		journey1.setJourneyStatus("1");
//		journeyDAO.add(journey1);
//		System.out.println("[SQL] 新增單筆資料成功");
//		
//		// 修改單筆
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		session.beginTransaction();
//		Journey journey2 = session.get(Journey.class, 23002);
//		session.getTransaction().commit();
////		Journey journey2 = new Journey();
//		journey2.setJourneyId(23002);
//		journey2.setJourneyName("慢活一日遊");
//		journey2.setPrice(3344);
//		journey2.setContent("吃飽睡，睡飽吃");
//		journey2.setDays(1);
//		journey2.setJourneyStatus("1");
//		journeyDAO.update(journey2);
//		System.out.println("[SQL] 修改單筆資料成功");
//
////		// 刪除單筆
////		journeyDAO.delete(23003);
////		System.out.println("[SQL] 刪除單筆資料成功");
//		
//		// 查詢單筆
//		Journey journey3 = journeyDAO.findByPK(23001);
//		System.out.println(journey3);
//		System.out.println("[SQL] 查詢單筆資料成功");
//		
//		// 查詢全部筆數
//		List<Journey> journeyList = journeyDAO.getAll();
//		for(Journey j : journeyList) {
//			System.out.println(j);
//		}
//		System.out.println("[SQL] 查詢全部資料成功");		
//	}

}
