package com.emazon.stockservice.domain.constants;

public class AuthConstants {

    private AuthConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static final String ROLE_AUX_WAREHOUSE = "AUX_WAREHOUSE";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
}
