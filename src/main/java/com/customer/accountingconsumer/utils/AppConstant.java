package com.customer.accountingconsumer.utils;

/**
 * API = "http://localhost:8080/sock/"; //get, post, patch
 * API_ALL = "http://localhost:8080/sock/socksAll"; //SockResponse
 * API_FIND = "http://localhost:8080/sock/socks"; //get-requestParam
 * API_ID = "http://localhost:8080/sock/{id}";
 */
public class AppConstant {

    public static final String DEFAULT_PAGE_NUMBER = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";
    public static final String DEFAULT_SORT_BY = "id";
    public static final String DEFAULT_SORT_DIRECTION = "asc";

    public static final String API = "http://localhost:8080/sock/";
    public static final String API_ALL = "http://localhost:8080/sock/socksAll";
    public static final String API_FIND = "http://localhost:8080/sock/socks";
    public static final String API_ID = "http://localhost:8080/sock/{id}";

}
