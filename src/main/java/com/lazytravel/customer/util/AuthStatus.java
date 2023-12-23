package com.lazytravel.customer.util;

public enum AuthStatus {
    FAILED("驗證有誤，請重新輸入或重發驗證信。"), SUCCESS("驗證成功!"), EXPIRED("驗證碼已逾時，請重發驗證信。");

    private final String message;

    AuthStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
