package com.lazytravel.journey.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.Session;

import com.lazytravel.journey.entity.TourGroup;
import com.lazytravel.util.HibernateUtil;

public class TourGroupDAOImpl implements TourGroupDAO {

	@Override
	public int add(TourGroup tourGroup) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Integer id = (Integer) session.save(tourGroup);
			session.getTransaction().commit();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();;
			return -1;
		}
	}

	@Override
	public int update(TourGroup tourGroup) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			session.update(tourGroup);
			session.getTransaction().commit();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		}
	}
	
	@Override
	public TourGroup findByPK(Integer groupId) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		TourGroup tourGroup = null;
		try {
			session.beginTransaction();
			tourGroup = session.get(TourGroup.class, groupId);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return tourGroup;
	}

	

	@Override
	public List<TourGroup> getAll() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<TourGroup> list = null;
		try {
			session.beginTransaction();
			list = session.createQuery("from TourGroup", TourGroup.class).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return list;
	}

	@Override
	public List<TourGroup> getByTime(Map<String, String> map) {
		if(map.size() == 0) {
			return getAll();
		}
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<TourGroup> list = null;
		try {
			session.beginTransaction();
			
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<TourGroup> critiria = builder.createQuery(TourGroup.class);
			Root<TourGroup> root = critiria.from(TourGroup.class);
			
			List<Predicate> predicates = new ArrayList<>();
			
			if (map.containsKey("startTimeSearch") && map.containsKey("endTimeSearch")) {
			    CriteriaBuilder builder1 = session.getCriteriaBuilder();
			    Predicate startTimePredicate = builder1.greaterThanOrEqualTo(root.get("startTime"), Date.valueOf(map.get("startTimeSearch")));
			    Predicate endTimePredicate = builder1.lessThanOrEqualTo(root.get("endTime"), Date.valueOf(map.get("endTimeSearch")));
			    Predicate timeBetweenPredicate = builder1.and(startTimePredicate, endTimePredicate);
			    predicates.add(timeBetweenPredicate);
			}

			
			for (Map.Entry<String, String> row : map.entrySet()) {
				if("startTimeSearch".equals(row.getKey())) {
					if(!map.containsKey("endTimeSearch")) {
						predicates.add(builder.greaterThanOrEqualTo(root.get("startTime"), Date.valueOf(row.getValue())));
					}
				}
				
				if("endTimeSearch".equals(row.getKey())){
					if(!map.containsKey("startTimeSearch")) {
						predicates.add(builder.lessThanOrEqualTo(root.get("endTime"), Date.valueOf(row.getValue())));
					}
				}
			}
			
			critiria.where(builder.and(predicates.toArray(new Predicate[predicates.size()])));
			critiria.orderBy(builder.asc(root.get("groupId")));
			list = session.createQuery(critiria).getResultList();
			
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return list;
	}
	
	@Override
	public List<TourGroup> findByFK(Integer journeyId){
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<TourGroup> list = null;
		try {
			session.beginTransaction();
			list = session.createQuery("from TourGroup where journey.journeyId = :journeyId order by startTime, endTime", TourGroup.class).setParameter("journeyId", journeyId).list();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return list;
	};

	
	
// --------------------------------- JDBC -----------------------------------	
//	public static DataSource ds = null;
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/LazyTravelDB");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static final String TOURGROUP_INSERT_STMT = "INSERT INTO tour_group(journey_id, start_time, end_time, price, signup_num, min_required, max_required, signup_date, due_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
//	private static final String TOURGROUP_UPDATE_STMT = "UPDATE tour_group SET journey_id = ?, start_time = ?, end_time = ?, price = ?, signup_num = ?, min_required = ?, max_required = ?, signup_date = ?, due_date = ?, create_time = ?, update_time = ? WHERE group_id = ?";
//	private static final String TOURGROUP_DELETE_STMT = "DELETE FROM tour_group WHERE group_id = ?";
//	private static final String TOURGROUP_FIND_BY_PK = "SELECT * FROM tour_group WHERE group_id = ?";
//	private static final String TOURGROUP_GET_ALL = "SELECT * FROM tour_group";
//
//	
//	@Override
//	public void add(TourGroup tourGroup) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(TOURGROUP_INSERT_STMT);
//			
//			pstmt.setInt(1, tourGroup.getJourneyId());
//			pstmt.setDate(2, tourGroup.getStartTime());
//			pstmt.setDate(3, tourGroup.getEndTime());
//			pstmt.setInt(4, tourGroup.getPrice());
//			pstmt.setInt(5, tourGroup.getSignupNum());
//			pstmt.setInt(6, tourGroup.getMinRequired());
//			pstmt.setInt(7, tourGroup.getMaxRequired());
//			pstmt.setTimestamp(8, tourGroup.getSignupDate());
//			pstmt.setTimestamp(9, tourGroup.getDueDate());
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
//	public void update(TourGroup tourGroup) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(TOURGROUP_UPDATE_STMT);
//			
//			pstmt.setInt(1, tourGroup.getJourneyId());
//			pstmt.setDate(2, tourGroup.getStartTime());
//			pstmt.setDate(3, tourGroup.getEndTime());
//			pstmt.setInt(4, tourGroup.getPrice());
//			pstmt.setInt(5, tourGroup.getSignupNum());
//			pstmt.setInt(6, tourGroup.getMinRequired());
//			pstmt.setInt(7, tourGroup.getMaxRequired());
//			pstmt.setTimestamp(8, tourGroup.getSignupDate());
//			pstmt.setTimestamp(9, tourGroup.getDueDate());
//			pstmt.setTimestamp(10, tourGroup.getCreateTime());
//			pstmt.setTimestamp(11, tourGroup.getUpdateTime());
//			pstmt.setInt(12, tourGroup.getGroupId());
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
//	public void delete(Integer groupId) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(TOURGROUP_DELETE_STMT);
//			pstmt.setInt(1, groupId);
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
//	public TourGroup findByPK(Integer groupId) {
//		TourGroup tourGroup = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(TOURGROUP_FIND_BY_PK);
//			pstmt.setInt(1, groupId);
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()) {
//				tourGroup = new TourGroup();
//				tourGroup.setGroupId(rs.getInt("GROUP_ID"));
//				tourGroup.setJourneyId(rs.getInt("JOURNEY_ID"));
//				tourGroup.setStartTime(rs.getDate("START_TIME"));
//				tourGroup.setEndTime(rs.getDate("END_TIME"));
//				tourGroup.setPrice(rs.getInt("PRICE"));
//				tourGroup.setSignupNum(rs.getInt("SIGNUP_NUM"));
//				tourGroup.setMinRequired(rs.getInt("MIN_REQUIRED"));
//				tourGroup.setMaxRequired(rs.getInt("MAX_REQUIRED"));
//				tourGroup.setSignupDate(rs.getTimestamp("SIGNUP_DATE"));
//				tourGroup.setDueDate(rs.getTimestamp("DUE_DATE"));
//				tourGroup.setCreateTime(rs.getTimestamp("CREATE_TIME"));
//				tourGroup.setUpdateTime(rs.getTimestamp("update_time"));
//			}	
//			
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			closeResources(con, pstmt, rs);
//		}
//		return tourGroup;
//	}
//
//	
//	@Override
//	public List<TourGroup> getAll() {
//		List<TourGroup> tourGroupList = new ArrayList<TourGroup>();
//		TourGroup tourGroup = null;
//		
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(TOURGROUP_GET_ALL);
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()){
//				tourGroup = new TourGroup();
//				tourGroup.setGroupId(rs.getInt("GROUP_ID"));
//				tourGroup.setJourneyId(rs.getInt("JOURNEY_ID"));
//				tourGroup.setStartTime(rs.getDate("START_TIME"));
//				tourGroup.setEndTime(rs.getDate("END_TIME"));
//				tourGroup.setPrice(rs.getInt("PRICE"));
//				tourGroup.setSignupNum(rs.getInt("SIGNUP_NUM"));
//				tourGroup.setMinRequired(rs.getInt("MIN_REQUIRED"));
//				tourGroup.setMaxRequired(rs.getInt("MAX_REQUIRED"));
//				tourGroup.setSignupDate(rs.getTimestamp("SIGNUP_DATE"));
//				tourGroup.setDueDate(rs.getTimestamp("DUE_DATE"));
//				tourGroup.setCreateTime(rs.getTimestamp("CREATE_TIME"));
//				tourGroup.setUpdateTime(rs.getTimestamp("update_time"));
//				tourGroupList.add(tourGroup);
//			}
//			
//		} catch (SQLException se) {
//			se.printStackTrace();
//		} finally {
//			closeResources(con, pstmt, rs);
//		}
//		return tourGroupList;
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
//		TourGroupDAO tourGroupDAO = new TourGroupDAOImpl();
		
//		// 新增單筆
//		TourGroup tourGroup1 = new TourGroup();
//		
////		tourGroup1.setJourneyId(23003);
//		Journey journey1= new Journey();
//		journey1.setJourneyId(23001);
//		tourGroup1.setJourney(journey1);
//		
//		tourGroup1.setStartTime(java.sql.Date.valueOf("2023-11-20"));
//		tourGroup1.setEndTime(java.sql.Date.valueOf("2023-12-20"));
//		tourGroup1.setPrice(3333);
//		tourGroup1.setSignupNum(5);
//		tourGroup1.setMinRequired(50);
//		tourGroup1.setMaxRequired(100);
//		tourGroup1.setSignupDate(java.sql.Timestamp.valueOf("2023-11-21 06:00:00"));
//		tourGroup1.setDueDate(java.sql.Timestamp.valueOf("2023-11-21 06:00:00"));
//		tourGroupDAO.add(tourGroup1);
//		System.out.println("[SQL] 新增單筆資料成功");

//		// 修改單筆	
//		TourGroup tourGroup2 = new TourGroup();
//		tourGroup2.setGroupId(24006);
//		
////		tourGroup2.setJourneyId(23001);
//		Journey journey2= new Journey();
//		journey2.setJourneyId(23002);
//		tourGroup2.setJourney(journey2);
//		
//		tourGroup2.setStartTime(java.sql.Date.valueOf("2023-11-20"));
//		tourGroup2.setEndTime(java.sql.Date.valueOf("2023-12-20"));
//		tourGroup2.setPrice(6666);
//		tourGroup2.setSignupNum(5);
//		tourGroup2.setMinRequired(50);
//		tourGroup2.setMaxRequired(100);
//		tourGroup2.setSignupDate(java.sql.Timestamp.valueOf("2023-11-21 06:00:00"));
//		tourGroup2.setDueDate(java.sql.Timestamp.valueOf("2023-11-21 06:00:00"));
//		tourGroup2.setCreateTime(java.sql.Timestamp.valueOf("2023-11-21 01:00:00"));
//		tourGroup2.setUpdateTime(java.sql.Timestamp.valueOf("2023-11-21 02:00:00"));		
//		tourGroupDAO.update(tourGroup2);
//		System.out.println("[SQL] 修改單筆資料成功");

////		// 刪除單筆
////		tourGroupDAO.delete(24002);
////		System.out.println("[SQL] 刪除單筆資料成功");

////		// 查詢單筆
////		TourGroup tourGroup3 = tourGroupDAO.findByPK(24004);
////		System.out.println(tourGroup3);
////		System.out.println("[SQL] 查詢單筆資料成功");
	
//		// 查詢全部筆數
//		List<TourGroup> list = tourGroupDAO.getAll();
//		for(TourGroup l : list) {
//			System.out.println(l);
//		}
//		System.out.println("[SQL] 查詢全部資料成功");		

//		// 查詢符合行程時間的筆數
//		Map<String, String> map = new TreeMap<String, String>();
//		map.put("startTimeSearch", "2023-09-29");
//		map.put("endTimeSearch", "2023-11-20");
//
//		List<TourGroup> list2 = tourGroupDAO.getByTime(map);
//		for(TourGroup l : list2) {
//			System.out.println(l);
//		}
//		System.out.println("[SQL] 查詢資料成功");		
//	}

}
