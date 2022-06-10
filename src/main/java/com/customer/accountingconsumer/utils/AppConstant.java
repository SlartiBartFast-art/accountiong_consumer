package com.customer.accountingconsumer.utils;

public class AppConstant {

    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DEFAULT_SORT_BY = "id";
    public static final String DEFAULT_SORT_DIRECTION = "asc";

    public static final String API = "http://localhost:8080/sock/";
    public static final String API_ALL = "http://localhost:8080/sock/socksAll";
    public static final String API_PAGINATION =
            "http://localhost:8080/sock/?pageSize={pageSize}&pageNo={pageNo}&sortBy={sortBy}&sortDir={sortDir}";
    public static final String API_FIND =
            "http://localhost:8080/sock/socks?coloring={coloring}&operator={operator}&cottonPart={cottonPart}";
    public static final String API_ID = "http://localhost:8080/sock/{id}";
}
