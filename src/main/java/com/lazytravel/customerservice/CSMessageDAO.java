package com.lazytravel.customerservice;

import java.util.List;

public interface CSMessageDAO {
    void add(CSMessage csMessage);

    void update(CSMessage csMessage);

    void delete(Integer messageId);

    CSMessage findByPK(Integer messageId);

    List<CSMessage> getAll();
}
