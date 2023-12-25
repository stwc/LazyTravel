package com.lazytravel.admin.dao;

import com.lazytravel.admin.entity.Users;
import com.lazytravel.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class UsersDAOImpl implements UsersDAO {
    private final SessionFactory factory;

    public UsersDAOImpl() {
        factory = HibernateUtil.getSessionFactory();
    }

    private Session getSession() {
        return factory.getCurrentSession();
    }

    @Override
    public void add(Users users) {
        Session session = getSession();
        try {
            session.beginTransaction();

            session.save(users);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void update(Users users) {
        Session session = getSession();
        try {
            session.beginTransaction();

            session.update(users);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public Users findByPK(Integer userId) {
        Session session = getSession();
        Users users = null;
        try {
            session.beginTransaction();

            users = session.get(Users.class, userId);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return users;
    }

    @Override
    public List<Users> getAll() {
        Session session = getSession();
        List<Users> list = null;
        try {
            session.beginTransaction();

            list = session.createQuery("from Users", Users.class).list();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return list;
    }

    @Override
    public Users findByUsername(String username) {
        Session session = getSession();
        Users users = null;
        try {
            session.beginTransaction();

            users = session.createQuery("from Users where username = :username", Users.class)
                    .setParameter("username", username)
                    .uniqueResult();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return users;
    }


    /*
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("輸入要查詢的使用者編號: ");
//        int userId = sc.nextInt();
//        sc.close();

        Users users = null;
        UsersDAO dao = new UsersDAOImpl();

        // 新增單筆資料
//        users = new Users("tibame", "123456", 1);
//        users.setUserStatus("1");
//        dao.add(users);
//        System.out.println("[SQL] 新增單筆資料成功\n");

        // 查詢單筆
//        users = dao.findByPK(12001);
//        System.out.println(users);
//        System.out.println("[SQL] 查詢單筆成功\n");

        // 查詢全部筆數
//        List<Users> usersList = dao.getAll();
//        for (Users user : usersList) {
//            System.out.println(user);
//        }
//        System.out.println("[SQL] 查詢全部筆數成功\n");

        // 更新單筆
        users = dao.findByPK(12003);
        users.setUserPasswd("089487");
        dao.update(users);
        System.out.println("[SQL] 更新單筆成功\n");
    }
    */
}
