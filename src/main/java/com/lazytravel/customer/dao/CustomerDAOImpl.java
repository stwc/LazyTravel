package com.lazytravel.customer.dao;

import com.lazytravel.customer.entity.Customer;
import com.lazytravel.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Date;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    // SessionFactory 為 thread-safe，可宣告為屬性讓請求執行緒們共用
    private final SessionFactory factory;

    public CustomerDAOImpl() {
        factory = HibernateUtil.getSessionFactory();
    }

    // Session 為 not thread-safe，所以此方法在各個增刪改查方法裡呼叫
    // 以避免請求執行緒共用了同個 Session
    private Session getSession() {
        return factory.getCurrentSession();
    }

    @Override
    public void add(Customer customer) {
        Session session = getSession();
        try {
            session.beginTransaction();

            session.save(customer);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

//        getSession().save(customer);
    }

    @Override
    public void update(Customer customer) {
        Session session = getSession();
        try {
            session.beginTransaction();

            session.update(customer);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

//        getSession().update(customer);
    }

    @Override
    public Customer findByPK(Integer customerId) {
        Session session = getSession();
        Customer customer = null;
        try {
            session.beginTransaction();

            customer = session.get(Customer.class, customerId);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return customer;

//        return getSession().get(Customer.class, customerId);
    }

    @Override
    public List<Customer> getAll() {
        Session session = getSession();
        List<Customer> list = null;
        try {
            session.beginTransaction();

            list = session.createQuery("from Customer", Customer.class).list();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return list;

//        return getSession().createQuery("from Customer", Customer.class).list();
    }

    @Override
    public Customer findByEmail(String email) {
        Session session = getSession();
        Customer customer = null;
        try {
            session.beginTransaction();

            customer = session.createQuery("from Customer where email = :email", Customer.class)
                    .setParameter("email", email)
                    .uniqueResult();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return customer;
    }

    /*
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("輸入要查詢的使用者編號: ");
//        int userId = sc.nextInt();
//        sc.close();

        CustomerDAO dao = new CustomerDAOImpl();
        Customer customer = null;

        // 新增單筆資料
//        customer = new Customer();
//        customer.setCustomerName("林文浩");
//        customer.setNickname("文文");
//        customer.setSex("0");
//        customer.setPhone("0911145566");
//        customer.setBirth(Date.valueOf("2003-11-20"));
//        customer.setAddress("桃園市桃園區春日路618-1號2樓");
//        customer.setEmail("meng20031120@gmail.com");
//        customer.setCustomerPasswd("123456");
//        customer.setCustomerStatus("0");
//        customer.setIdno("H133556789");
//        dao.add(customer);
//        System.out.println("[SQL] 新增單筆資料成功\n");

        // 查詢單筆
        customer = dao.findByPK(11005);
        System.out.println(customer);
        System.out.println("[SQL] 查詢單筆成功\n");

        // 查詢全部筆數
//        List<Customer> customerList = dao.getAll();
//        for (Customer item : customerList) {
//            System.out.println(item);
//        }
//        System.out.println("[SQL] 查詢全部筆數成功\n");

        // 更新單筆(測試更新狀態)
//        customer = dao.findByPK(11005);
//        customer.setSex("1");
//        dao.update(customer);
//        System.out.println("[SQL] 更新單筆成功\n");
    }
    */
}
