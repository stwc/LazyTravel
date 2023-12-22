package com.lazytravel.customer.service;

import com.lazytravel.customer.dao.CustomerDAO;
import com.lazytravel.customer.dao.CustomerDAOImpl;
import com.lazytravel.customer.entity.Customer;
import com.password4j.Hash;
import com.password4j.Password;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private final CustomerDAO dao;

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
    public Boolean isEmailExists(String email) {
        return (dao.findByEmail(email) != null);
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
}
