package com.lazytravel.admin.dao;

import com.lazytravel.admin.entity.Roles;
import com.lazytravel.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class RolesDAOImpl implements RolesDAO {
    private final SessionFactory factory;

    public RolesDAOImpl() {
        factory = HibernateUtil.getSessionFactory();
    }

    private Session getSession() {
        return factory.getCurrentSession();
    }

    @Override
    public void add(Roles roles) {
        Session session = getSession();
        try {
            session.beginTransaction();

            session.save(roles);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void update(Roles roles) {
        Session session = getSession();
        try {
            session.beginTransaction();

            session.update(roles);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Integer roleId) {
        Session session = getSession();
        try {
            session.beginTransaction();

            Roles roles = session.get(Roles.class, roleId);
            if (roles != null)
                session.delete(roles);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public Roles findByPK(Integer roleId) {
        Session session = getSession();
        Roles roles = null;
        try {
            session.beginTransaction();

            roles = session.get(Roles.class, roleId);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return roles;
    }

    @Override
    public List<Roles> getAll() {
        Session session = getSession();
        List<Roles> list = null;
        try {
            session.beginTransaction();

            list = session.createQuery("from Roles", Roles.class).list();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return list;
    }

    /*
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("輸入要查詢的使用者編號: ");
//        int userId = sc.nextInt();
//        sc.close();

        Roles roles = null;
        RolesDAO dao = new RolesDAOImpl();

        // 新增單筆資料
//        roles = new Roles();
//        roles.setRoleName("Blog管理員");
//        roles.setRoleDescr("管理部落格文章");
//        dao.add(roles);
//        System.out.println("[SQL] 新增單筆資料成功\n");

        // 查詢單筆
//        roles = dao.findByPK(1);
//        System.out.println(roles);
//        System.out.println("[SQL] 查詢單筆成功\n");

        // 查詢全部筆數
//        List<Roles> rolesList = dao.getAll();
//        for (Roles role : rolesList) {
//            System.out.println(role);
//        }
//        System.out.println("[SQL] 查詢全部筆數成功\n");

        // 更新單筆
        roles = dao.findByPK(3);
        roles.setRoleDescr("管理部落格文章、留言等資訊");
        dao.update(roles);
        System.out.println("[SQL] 更新單筆成功\n");
    }
    */
}
