package com.lazytravel.customer.service;

import com.lazytravel.customer.dao.CustomerDAO;
import com.lazytravel.customer.dao.CustomerDAOImpl;
import com.lazytravel.customer.entity.Customer;

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
    public Integer login(String email, String passwd) {
        Customer customer = dao.findByEmail(email);
        if (customer != null && email.equals(customer.getEmail()) && passwd.equals(customer.getCustomerPasswd()))
            return customer.getCustomerId();
        else
            return -1;
    }

    @Override
    public Boolean isEmailExists(String email) {
        return (dao.findByEmail(email) != null);
    }
}
