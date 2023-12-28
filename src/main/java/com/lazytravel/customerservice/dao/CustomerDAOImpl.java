package com.lazytravel.customerservice.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.lazytravel.example.dao.CustomerDAO;
import com.lazytravel.example.entity.Customer;

public class CustomerDAOImpl implements CustomerDAO {
    private static DataSource ds = null;

    static {
        try {
            Context ctx = new InitialContext();
            ds = (DataSource) ctx.lookup("java:comp/env/jdbc/LazyTravelDB");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    private static final String INSERT_STMT =
            "insert into customer (customer_name, nickname, sex, phone, birth, address, email, customer_passwd, " +
                    "customer_status, idno) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String FIND_BY_PK =
            "select * from customer where CUSTOMER_ID = ?";
    private static final String GET_ALL =
            "select * from customer";
    private static final String UPDATE_STMT =
            "update customer set CUSTOMER_NAME = ?, NICKNAME = ?, SEX = ?, PHONE = ?, BIRTH = ?, ADDRESS =? , " +
                    "EMAIL = ?, CUSTOMER_PASSWD = ?, CUSTOMER_STATUS = ?, IDNO = ?, CUSTOMER_POINT = ? " +
                    "where CUSTOMER_ID = ?";

    @Override
    public void add(Customer customer) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(INSERT_STMT);

            pstmt.setString(1, customer.getCustomerName());
            pstmt.setString(2, customer.getNickname());
            pstmt.setString(3, customer.getSex());
            pstmt.setString(4, customer.getPhone());
            pstmt.setDate(5, customer.getBirth());
            pstmt.setString(6, customer.getAddress());
            pstmt.setString(7, customer.getEmail());
            pstmt.setString(8, customer.getCustomerPasswd());
            pstmt.setString(9, customer.getCustomerStatus());
            pstmt.setString(10, customer.getIdno());

            pstmt.executeUpdate();

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    @Override
    public void update(Customer customer) {
        Connection con = null;
        PreparedStatement pstmt = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(UPDATE_STMT);

            pstmt.setString(1, customer.getCustomerName());
            pstmt.setString(2, customer.getNickname());
            pstmt.setString(3, customer.getSex());
            pstmt.setString(4, customer.getPhone());
            pstmt.setDate(5, customer.getBirth());
            pstmt.setString(6, customer.getAddress());
            pstmt.setString(7, customer.getEmail());
            pstmt.setString(8, customer.getCustomerPasswd());
            pstmt.setString(9, customer.getCustomerStatus());
            pstmt.setString(10, customer.getIdno());
            pstmt.setInt(11, customer.getCustomerPoint());
            pstmt.setInt(12, customer.getCustomerId());

            pstmt.executeUpdate();

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
    }

    @Override
    public Customer findByPK(Integer customerId) {
        Customer customer = null;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            con = ds.getConnection();
            pstmt = con.prepareStatement(FIND_BY_PK);

            pstmt.setInt(1, customerId);

            rs = pstmt.executeQuery();

            while (rs.next()) {
                customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setNickname(rs.getString("nickname"));
                customer.setSex(rs.getString("sex"));
                customer.setPhone(rs.getString("phone"));
                customer.setBirth(rs.getDate("birth"));
                customer.setAddress(rs.getString("address"));
                customer.setEmail(rs.getString("email"));
                customer.setCustomerPasswd(rs.getString("customer_passwd"));
                customer.setCustomerStatus(rs.getString("customer_status"));
                customer.setIdno(rs.getString("idno"));
                customer.setCustomerPoint(rs.getInt("customer_point"));
                customer.setCreateTime(rs.getTimestamp("create_time"));
                customer.setUpdateTime(rs.getTimestamp("update_time"));
            }

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> list = new ArrayList<Customer>();
        Customer customer = null;

        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {

            con = ds.getConnection();
            pstmt = con.prepareStatement(GET_ALL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                customer = new Customer();
                customer.setCustomerId(rs.getInt("customer_id"));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setNickname(rs.getString("nickname"));
                customer.setSex(rs.getString("sex"));
                customer.setPhone(rs.getString("phone"));
                customer.setBirth(rs.getDate("birth"));
                customer.setAddress(rs.getString("address"));
                customer.setEmail(rs.getString("email"));
                customer.setCustomerPasswd(rs.getString("customer_passwd"));
                customer.setCustomerStatus(rs.getString("customer_status"));
                customer.setIdno(rs.getString("idno"));
                customer.setCustomerPoint(rs.getInt("customer_point"));
                customer.setCreateTime(rs.getTimestamp("create_time"));
                customer.setUpdateTime(rs.getTimestamp("update_time"));
                list.add(customer);
            }

        } catch (SQLException se) {
            throw new RuntimeException("A database error occured. " + se.getMessage());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException se) {
                    se.printStackTrace(System.err);
                }
            }
            if (con != null) {
                try {
                    con.close();
                } catch (Exception e) {
                    e.printStackTrace(System.err);
                }
            }
        }
        return list;
    }

    /*
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("輸入要查詢的使用者編號: ");
//        int userId = sc.nextInt();
//        sc.close();

        Customer customer = null;
        CustomerDAO dao = new CustomerDAOImpl();

        // 新增單筆資料
//        users = new Users("tibame", "123456", 1);
//        dao.add(users);
//        System.out.println("[SQL] 新增單筆資料成功\n");

        // 查詢單筆
        customer = dao.findByPK(1);
        System.out.println(customer);
        System.out.println("[SQL] 查詢單筆成功\n");

        // 查詢全部筆數
        List<Customer> customerList = dao.getAll();
        for (Customer item : customerList) {
            System.out.println(item);
        }
        System.out.println("[SQL] 查詢全部筆數成功\n");

        // 更新單筆
//        users = new Users("tibame", "94879487", 1);
//        dao.update(users);
//        System.out.println("[SQL] 更新單筆成功\n");
    }
    */
}
