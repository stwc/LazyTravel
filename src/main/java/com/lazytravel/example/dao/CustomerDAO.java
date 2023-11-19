package com.lazytravel.example.dao;

import java.util.List;

import com.lazytravel.example.entity.Customer;

public interface CustomerDAO {
    void add(Customer customer);

    void update(Customer customer);

    Customer findByPK(Integer customerId);

    List<Customer> getAll();
}
