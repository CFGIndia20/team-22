package com.markups.umeed;

public class LogInRequest {
    Integer contact;
    String password;

    public LogInRequest(Integer contact, String password) {
        this.contact = contact;
        this.password = password;
    }
}
