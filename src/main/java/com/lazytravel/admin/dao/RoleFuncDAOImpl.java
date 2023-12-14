package com.lazytravel.admin.dao;

import com.lazytravel.admin.entity.Func;
import com.lazytravel.admin.entity.RoleFunc;
import com.lazytravel.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class RoleFuncDAOImpl implements RoleFuncDAO {
    private final SessionFactory factory;

    public RoleFuncDAOImpl() {
        factory = HibernateUtil.getSessionFactory();
    }

    private Session getSession() {
        return factory.getCurrentSession();
    }

    @Override
    public void add(RoleFunc roleFunc) {
        Session session = getSession();
        try {
            session.beginTransaction();

            session.save(roleFunc);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

//    @Override
//    public void update(RoleFunc roleFunc) {
//        Session session = getSession();
//        try {
//            session.beginTransaction();
//
//            session.update(roleFunc);
//
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            session.getTransaction().rollback();
//        }
//    }

    @Override
    public RoleFunc findByPK(Integer roleId, Integer funcId) {
        Session session = getSession();
        RoleFunc roleFunc = null;
        try {
            session.beginTransaction();

            roleFunc = session.createQuery("from RoleFunc where roleId = :roleId and funcId = :funcId", RoleFunc.class)
                    .setParameter("roleId", roleId)
                    .setParameter("funcId", funcId)
                    .uniqueResult();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return roleFunc;
    }

    @Override
    public List<RoleFunc> findByRoleId(Integer roleId) {
        Session session = getSession();
        List<RoleFunc> list = null;
        try {
            session.beginTransaction();

            list = session.createQuery("from RoleFunc where roleId = :roleId", RoleFunc.class)
                    .setParameter("roleId", roleId)
                    .list();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return list;
    }

    @Override
    public List<RoleFunc> findByFuncId(Integer funcId) {
        Session session = getSession();
        List<RoleFunc> list = null;
        try {
            session.beginTransaction();

            list = session.createQuery("from RoleFunc where funcId = :funcId", RoleFunc.class)
                    .setParameter("funcId", funcId)
                    .list();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return list;
    }

    @Override
    public List<RoleFunc> getAll() {
        Session session = getSession();
        List<RoleFunc> list = null;
        try {
            session.beginTransaction();

            list = session.createQuery("from RoleFunc ", RoleFunc.class).list();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }

        return list;
    }

    @Override
    public void deleteByPK(Integer roleId, Integer funcId) {
        Session session = getSession();
        try {
            session.beginTransaction();

            RoleFunc roleFunc = session.createQuery("from RoleFunc where roleId = :roleId and funcId = :funcId", RoleFunc.class)
                    .setParameter("roleId", roleId)
                    .setParameter("funcId", funcId)
                    .uniqueResult();
            if (roleFunc != null)
                session.delete(roleFunc);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void deleteByRoleId(Integer roleId) {
        Session session = getSession();
        try {
            session.beginTransaction();

            List<RoleFunc> list = session.createQuery("from RoleFunc where roleId = :roleId", RoleFunc.class)
                    .setParameter("roleId", roleId)
                    .list();
            for (RoleFunc roleFunc : list)
                session.delete(roleFunc);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void deleteByFuncId(Integer funcId) {
        Session session = getSession();
        try {
            session.beginTransaction();

            List<RoleFunc> list = session.createQuery("from RoleFunc where funcId = :funcId", RoleFunc.class)
                    .setParameter("funcId", funcId)
                    .list();
            for (RoleFunc roleFunc : list)
                session.delete(roleFunc);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    /*
    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        System.out.print("輸入要查詢的使用者編號: ");
//        int userId = sc.nextInt();
//        sc.close();

        RoleFunc roleFunc = null;
        RoleFuncDAO dao = new RoleFuncDAOImpl();

        // 新增單筆資料
//        func = new Func();
//        func.setFuncName("部落格");
//        func.setFuncDescr("管理部落格");
//        dao.add(func);
//        System.out.println("[SQL] 新增單筆資料成功\n");

        // 查詢單筆 by PK
//        roleFunc = dao.findByPK(1, 2);
//        System.out.println(roleFunc);
//        System.out.println("[SQL] 查詢單筆成功\n");

        // 查詢 by func_id
//        List<RoleFunc> roleFuncList = dao.findByFuncId(2);
//        for (RoleFunc roleFuncs : roleFuncList) {
//            System.out.println(roleFuncs);
//        }
//        System.out.println("[SQL] 查詢 by func_id 成功\n");

        // 查詢 by role_id
//        List<RoleFunc> roleFuncList = dao.findByRoleId(1);
//        for (RoleFunc roleFuncs : roleFuncList) {
//            System.out.println(roleFuncs);
//        }
//        System.out.println("[SQL] 查詢 by role_id 成功\n");

        // 查詢全部筆數
//        List<RoleFunc> roleFuncList = dao.getAll();
//        for (RoleFunc roleFuncs : roleFuncList) {
//            System.out.println(roleFuncs);
//        }
//        System.out.println("[SQL] 查詢全部筆數成功\n");

        // 更新單筆
//        func = dao.findByPK(3);
//        func.setFuncDescr("管理部落格文章、留言");
//        dao.update(func);
//        System.out.println("[SQL] 更新單筆成功\n");

        // 刪除單筆 by PK
        dao.deleteByPK(1, 2);
        System.out.println("[SQL] 刪除單筆成功\n");

        // 刪除 by role_id
//        dao.deleteByRoleId(1);
//        System.out.println("[SQL] 刪除 by role_id 成功\n");

        // 刪除 by func_id
//        dao.deleteByFuncId(2);
//        System.out.println("[SQL] 刪除 by func_id 成功\n");
    }
    */
}
