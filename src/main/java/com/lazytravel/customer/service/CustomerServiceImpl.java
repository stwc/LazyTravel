package com.lazytravel.customer.service;

import com.lazytravel.customer.dao.CustomerDAO;
import com.lazytravel.customer.dao.CustomerDAOImpl;
import com.lazytravel.customer.entity.Customer;
import com.lazytravel.customer.util.Utils;
import com.lazytravel.customer.util.AuthStatus;
import com.lazytravel.util.JedisPoolUtil;
import com.password4j.Hash;
import com.password4j.Password;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerDAO dao;
    private static final JedisPool pool = JedisPoolUtil.getJedisPool();

    public CustomerServiceImpl() {
        dao = new CustomerDAOImpl();
    }

    @Override
    public void addCustomer(Customer customer) {
        dao.add(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        dao.update(customer);
    }

    @Override
    public Customer getOneCustomer(Integer customerId) {
        return dao.findByPK(customerId);
    }

    @Override
    public List<Customer> getAll() {
        return dao.getAll();
    }

    @Override
    public Customer login(String email, String passwd) {
        Customer customer = dao.findByEmail(email);
        if (customer == null)
            return null;

        try {
            boolean isEmailMatched = email.equals(customer.getEmail());
            boolean isPasswdMatched = Password.check(passwd, customer.getCustomerPasswd()).withBcrypt();
            if (isEmailMatched && isPasswdMatched)
                return customer;
            else
                return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Customer emailExists(String email) {
        return dao.findByEmail(email);
    }

    @Override
    public Boolean resetPassword(String email, String oldPassword, String newPassword) {
        Customer customer = login(email, oldPassword);
        if (customer == null)
            return false;

        Hash hash = Password.hash(newPassword).withBcrypt();
        String hashedPw = hash.getResult();
        customer.setCustomerPasswd(hashedPw);
        dao.update(customer);
        return true;
    }

    @Override
    public void sendRegisterMail(Customer customer, String path) {
        String customerId = String.valueOf(customer.getCustomerId());
        String authCode = Utils.generateAuthCode();
        System.out.println("Auth code is: " + authCode);

        Jedis jedis = pool.getResource();
        String key = "RegisterAuth:" + customerId;
        jedis.set(key, authCode);
        jedis.expire(key, 30);
        jedis.close();

        String to = customer.getEmail();
        String subject = "趕懶遊 電子郵件驗證";
        String url = path + "?id=" + customerId + "&" + "code=" + authCode;
        String messageText = "Hello " + customer.getCustomerName() + ",\n請點選連結驗證電子郵件地址:\n" + url + "\n";

        MailService mailService = new MailService();
        mailService.sendMail(to, subject, messageText);
    }

    @Override
    public AuthStatus isAuthSuccess(Integer customerId, String authCode) {
        Jedis jedis = pool.getResource();
        String key = "RegisterAuth:" + customerId;
//        System.out.println("tempAuth key: " + key);
        String tempAuth = jedis.get(key);
//        System.out.println("tempAuth: " + tempAuth);
        jedis.close();
        if (tempAuth == null) {
            return AuthStatus.EXPIRED;
        } else if (authCode.equals(tempAuth)) {
            return AuthStatus.SUCCESS;
        } else {
            return AuthStatus.FAILED;
        }
    }

    @Override
    public void sendForgotPwMail(Customer customer, String path) {
        String customerId = String.valueOf(customer.getCustomerId());
        String authCode = Utils.generateAuthCode();
        System.out.println("Auth code is: " + authCode);

        Jedis jedis = pool.getResource();
        String key = "ForgotPwAuth:" + customerId;
        jedis.set(key, authCode);
        jedis.expire(key, 60 * 60);
        jedis.close();

        String to = customer.getEmail();
        String subject = "趕懶遊 重置密碼";
        String url = path + "?id=" + customerId + "&" + "code=" + authCode;
        String messageText = "Hello " + customer.getCustomerName() + ",\n請點選連結重置密碼:\n" + url + "\n";

        MailService mailService = new MailService();
        mailService.sendMail(to, subject, messageText);
    }

    @Override
    public Boolean forgotPassword(Integer customerId, String authCode, String newPassword) {
        Jedis jedis = pool.getResource();
        String key = "ForgotPwAuth:" + customerId;
        String tempAuth = jedis.get(key);
        jedis.close();
        if (!authCode.equals(tempAuth)) {
            return false;
        } else {
            Customer customer = dao.findByPK(customerId);
            Hash hash = Password.hash(newPassword).withBcrypt();
            String hashedPw = hash.getResult();
            customer.setCustomerPasswd(hashedPw);
            dao.update(customer);
            return true;
        }
    }
}
