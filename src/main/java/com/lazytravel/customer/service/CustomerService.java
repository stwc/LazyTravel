package com.lazytravel.customer.service;

import com.lazytravel.customer.entity.Customer;
import com.lazytravel.customer.util.AuthStatus;

import java.util.List;

public interface CustomerService {

    void addCustomer(Customer customer);

    void updateCustomer(Customer customer);

    Customer getOneCustomer(Integer customerId);

    List<Customer> getAll();

    Customer login(String email, String passwd);

    Boolean isEmailExists(String email);

    Boolean resetPassword(String email, String oldPassword, String newPassword);

    void sendRegisterMail(Customer customer, String path);

    AuthStatus isAuthSuccess(String type, Integer customerId, String authCode);
}
