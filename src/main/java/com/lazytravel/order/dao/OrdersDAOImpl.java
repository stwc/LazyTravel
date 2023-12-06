//package com.lazytravel.order.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//import com.lazytravel.order.entity.Orders;
//
//public class OrdersDAOImpl implements OrdersDAO {
//	private static DataSource ds = null;
//
//	static {
//		try {
//			Context ctx = new InitialContext();
//			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/LazyTravelDB");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
//
//	private static final String INSERT_STMT = "insert into orders (order_no, customer_id, group_id, tourist, customer_point, coupon_id, total_amt, order_status) "
//	        + "values (?, ?, ?, ?, ?, ?, ?, ?)";
//
//
//	private static final String FIND_BY_PK = "select * from orders where ORDER_ID = ?";
//	
//	private static final String FIND_BY_ORDERNO = "select * from orders where ORDER_NO = ?";
//
//	private static final String GET_ALL = "select * from orders";
//
//	private static final String UPDATE_STMT = "update orders set order_no = ?, customer_id = ?, group_id = ?,  tourist = ?,customer_point = ?, coupon_id = ?, total_amt = ?, order_status = ?  where ORDER_ID = ?  ";
//
//	@Override
//	public void add(Orders orders) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(INSERT_STMT);
//
//			pstmt.setInt(1, orders.getOrderNo());
//			pstmt.setInt(2, orders.getCustomerId());
//			pstmt.setInt(3, orders.getGroupId());
//			pstmt.setInt(4, orders.getTourist());
//			pstmt.setInt(5, orders.getCustomerPoint());
//			pstmt.setInt(6, orders.getCouponId());
//			pstmt.setInt(7, orders.getTotalAmt());
//			pstmt.setString(8, orders.getOrderStatus());
//		
//
//			
//	
//
//			pstmt.executeUpdate();
//
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured" + se.getMessage());
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//
//	}
//	
//	
//	@Override
//    public void update(Orders orders) {
//        Connection con = null;
//        PreparedStatement pstmt = null;
//
//        try {
//
//            con = ds.getConnection();
//            pstmt = con.prepareStatement(UPDATE_STMT);
//
//        	pstmt.setInt(1, orders.getOrderNo());
//			pstmt.setInt(2, orders.getCustomerId());
//			pstmt.setInt(3, orders.getGroupId());
//			pstmt.setInt(4, orders.getTourist());
//			pstmt.setInt(5, orders.getCustomerPoint());
//			pstmt.setInt(6, orders.getCouponId());
//			pstmt.setInt(7, orders.getTotalAmt());
//			pstmt.setString(8, orders.getOrderStatus());
//			pstmt.setInt(9, orders.getOrderId());
//
//          
//            pstmt.executeUpdate();
//
//        } catch (SQLException se) {
//            throw new RuntimeException("A database error occured. " + se.getMessage());
//        } finally {
//            if (pstmt != null) {
//                try {
//                    pstmt.close();
//                } catch (SQLException se) {
//                    se.printStackTrace(System.err);
//                }
//            }
//            if (con != null) {
//                try {
//                    con.close();
//                } catch (Exception e) {
//                    e.printStackTrace(System.err);
//                }
//            }
//        }
//    }
//	
//	
//	@Override
//	public Orders getOrdersByOrdersId(Integer orderId) {
//		Orders order = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(FIND_BY_PK);
//			pstmt.setInt(1, orderId);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				order = new Orders();
//				order.setOrderId(rs.getInt("order_id"));
//				order.setCustomerId(rs.getInt("customer_id"));
//				order.setGroupId(rs.getInt("group_id"));
//				order.setCreateTime(rs.getTimestamp("create_time"));
//				order.setPaidTime(rs.getTimestamp("paid_time"));
//				order.setTotalAmt(rs.getInt("total_amt"));
//				order.setOrderStatus(rs.getString("order_status"));
//				order.setCustomerPoint(rs.getInt("customer_point"));
//				order.setCouponId(rs.getInt("coupon_id"));
//				order.setTourist(rs.getInt("tourist"));
//				order.setContent(rs.getString("content"));
//				order.setContentTime(rs.getTimestamp("content_time"));
//				order.setScore(rs.getDouble("score"));
//				order.setOrderNo(rs.getInt("order_no"));
//				
//
//			}
//
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//
//		}
//		return order;
//	}
//	
//	@Override
//	public Orders getOrdersByOrdersNo(Integer orderNo) {
//		Orders order = null;
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(FIND_BY_ORDERNO);
//			pstmt.setInt(1, orderNo);
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				order = new Orders();
//				order.setOrderId(rs.getInt("order_id"));
//				order.setCustomerId(rs.getInt("customer_id"));
//				order.setGroupId(rs.getInt("group_id"));
//				order.setCreateTime(rs.getTimestamp("create_time"));
//				order.setPaidTime(rs.getTimestamp("paid_time"));
//				order.setTotalAmt(rs.getInt("total_amt"));
//				order.setOrderStatus(rs.getString("order_status"));
//				order.setCustomerPoint(rs.getInt("customer_point"));
//				order.setCouponId(rs.getInt("coupon_id"));
//				order.setTourist(rs.getInt("tourist"));
//				order.setContent(rs.getString("content"));
//				order.setContentTime(rs.getTimestamp("content_time"));
//				order.setScore(rs.getDouble("score"));
//				order.setOrderNo(rs.getInt("order_no"));
//				
//			}
//
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//
//		} finally {
//			if (rs != null) {
//				try {
//					rs.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//
//		}
//		return order;
//	}
//	
//	
//	
//	
//
//	
//
//	@Override
//	public List<Orders> getAll() {
//		List<Orders> list = new  ArrayList<Orders>();
//		Orders order = null;
//		
//		Connection con = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        
//        
//        
//        try {
//			con = ds.getConnection();
//			pstmt = con.prepareStatement(GET_ALL);
//	        rs = pstmt.executeQuery();
//	      while (rs.next()) {
//	    	  order = new Orders();
//				order.setOrderId(rs.getInt("order_id"));
//				order.setCustomerId(rs.getInt("customer_id"));
//				order.setGroupId(rs.getInt("group_id"));
//				order.setCreateTime(rs.getTimestamp("create_time"));
//				order.setPaidTime(rs.getTimestamp("paid_time"));
//				order.setTotalAmt(rs.getInt("total_amt"));
//				order.setOrderStatus(rs.getString("order_status"));
//				order.setCustomerPoint(rs.getInt("customer_point"));
//				order.setCouponId(rs.getInt("coupon_id"));
//				order.setTourist(rs.getInt("tourist"));
//				order.setContent(rs.getString("content"));
//				order.setContentTime(rs.getTimestamp("content_time"));
//				order.setScore(rs.getDouble("score"));
//				order.setOrderNo(rs.getInt("order_no"));
//				
//				list.add(order);
//	      }
//	        
//			
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. " + se.getMessage());
//		}finally {
//            if (rs != null) {
//                try {
//                    rs.close();
//                } catch (SQLException se) {
//                    se.printStackTrace(System.err);
//                }
//            }
//            if (pstmt != null) {
//                try {
//                    pstmt.close();
//                } catch (SQLException se) {
//                    se.printStackTrace(System.err);
//                }
//            }
//            if (con != null) {
//                try {
//                    con.close();
//                } catch (Exception e) {
//                    e.printStackTrace(System.err);
//                }
//            }
//        }
//        
//        
//		return list;
//	}
//}
