package com.lazytravel.customer.util;

import java.util.UUID;

public class TestUUID {
    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        System.out.println("before: " + str);
        System.out.println("after: " + str.replace("-", ""));
        System.out.println(UUID.randomUUID().toString().replace("-", ""));
    }
}
