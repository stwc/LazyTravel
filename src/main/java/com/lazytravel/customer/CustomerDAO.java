package com.lazytravel.customer;

import java.util.List;

public interface CustomerDAO {
    void add(Customer customer);

    void update(Customer customer);

    void delete(Integer customerId);

    Customer getCustomerByCustomerId(Integer customerId);

    List<Customer> getAll();
}
