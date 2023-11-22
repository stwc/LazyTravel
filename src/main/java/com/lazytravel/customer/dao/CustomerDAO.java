package com.lazytravel.customer.dao;

import com.lazytravel.customer.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    void add(Customer customer);

    void update(Customer customer);

    Customer findByPK(Integer customerId);

    List<Customer> getAll();
}
