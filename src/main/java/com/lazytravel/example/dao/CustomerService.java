package com.lazytravel.example.dao;

import com.lazytravel.example.entity.Customer;

import java.sql.Date;
import java.util.List;

public class CustomerService {
    private final CustomerDAO dao;

    public CustomerService() {
        dao = new CustomerDAOImpl();
    }

    public void addCustomer(Customer customer) {
        dao.add(customer);
    }

    public void updateCustomer(Customer customer) {
        dao.update(customer);
    }

    public Customer getOneCustomer(Integer customerId) {
        return dao.findByPK(customerId);
    }

    public List<Customer> getAll() {
        return dao.getAll();
    }
}
