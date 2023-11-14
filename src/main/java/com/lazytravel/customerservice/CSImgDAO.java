package com.lazytravel.customerservice;

import java.util.List;

public interface CSImgDAO {
    void add(CSImg csImg);

    void update(CSImg csImg);

    void delete(Integer imgId);

    CSImg findByPK(Integer imgId);

    List<CSImg> getAll();

}