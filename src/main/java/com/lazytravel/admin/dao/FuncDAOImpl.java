package com.lazytravel.admin.dao;

import com.lazytravel.admin.entity.Func;
import com.lazytravel.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class FuncDAOImpl implements FuncDAO {
    private final SessionFactory factory;

    public FuncDAOImpl() {
        factory = HibernateUtil.getSessionFactory();
    }

    private Session getSession() {
        return factory.getCurrentSession();
    }

    @Override
    public void add(Func func) {
        Session session = getSession();
        try {
            session.beginTransaction();

            session.save(func);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void update(Func func) {
        Session session = getSession();
        try {
            session.beginTransaction();

            session.update(func);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public void delete(Integer funcId) {
        Session session = getSession();
        try {
            session.beginTransaction();

            Func func = session.get(Func.class, funcId);
            if (func != null)
                session.delete(func);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public Func findByPK(Integer funcId) {
        Session session = getSession();
        Func func = null;
        try {
            session.beginTransaction();

            func = session.get(Func.class, funcId);

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
        return func;
    }

    @Override
    public List<Func> getAll() {
        Session session = getSession();
        List<Func> list = null;
        try {
            session.beginTransaction();

            list = session.createQuery("from Func", Func.class).list();

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

        Func func = null;
        FuncDAO dao = new FuncDAOImpl();

        // 新增單筆資料
//        func = new Func();
//        func.setFuncName("部落格");
//        func.setFuncDescr("管理部落格");
//        dao.add(func);
//        System.out.println("[SQL] 新增單筆資料成功\n");

        // 查詢單筆
//        func = dao.findByPK(1);
//        System.out.println(func);
//        System.out.println("[SQL] 查詢單筆成功\n");

        // 查詢全部筆數
//        List<Func> funcList = dao.getAll();
//        for (Func func1 : funcList) {
//            System.out.println(func1);
//        }
//        System.out.println("[SQL] 查詢全部筆數成功\n");

        // 更新單筆
//        func = dao.findByPK(3);
//        func.setFuncDescr("管理部落格文章、留言");
//        dao.update(func);
//        System.out.println("[SQL] 更新單筆成功\n");

        // 刪除單筆
//        dao.delete(2);
//        System.out.println("[SQL] 刪除單筆成功\n");
    }
    */
}
