package com.lazytravel.example.dao;

import com.lazytravel.example.entity.Customer;

import java.sql.Date;
import java.util.List;

public class CustomerService {
    private final CustomerDAO dao;

    public CustomerService() {
        dao = new CustomerDAOImpl();
    }

    public Customer addCustomer(String customerName, String nickname, String sex, String phone, Date birth,
                                String address, String email, String customerPasswd, String customerStatus,
                                String idno) {

        Customer customer = new Customer();
        customer.setCustomerName(customerName);
        customer.setNickname(nickname);
        customer.setSex(sex);
        customer.setPhone(phone);
        customer.setBirth(birth);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setCustomerPasswd(customerPasswd);
        customer.setCustomerStatus(customerStatus);
        customer.setIdno(idno);
//        customer.setAvatar(avatar); // 插入圖片之後再做

        dao.add(customer);
        return customer;
    }

    public Customer updateCustomer(Integer customerId, String customerName, String nickname, String sex, String phone,
                                   Date birth, String address, String email, String customerPasswd,
                                   String customerStatus, String idno, Integer customerPoint) {

        Customer customer = new Customer();
        customer.setCustomerId(customerId);
        customer.setCustomerName(customerName);
        customer.setNickname(nickname);
        customer.setSex(sex);
        customer.setPhone(phone);
        customer.setBirth(birth);
        customer.setAddress(address);
        customer.setEmail(email);
        customer.setCustomerPasswd(customerPasswd);
        customer.setCustomerStatus(customerStatus);
        customer.setIdno(idno);
//        customer.setAvatar(avatar);
        customer.setCustomerPoint(customerPoint);

        dao.update(customer);
        return customer;
    }

    public Customer getOneCustomer(Integer customerId) {
        return dao.findByPK(customerId);
    }

    public List<Customer> getAll() {
        return dao.getAll();
    }
}
