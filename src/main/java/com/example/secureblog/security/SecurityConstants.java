package com.example.secureblog.security;

public class SecurityConstants {
    public static final String SECRET_KEY = "secureblog_secret";
    public static final long EXPIRATION_TIME = 86400000; // 24 hours
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
}
