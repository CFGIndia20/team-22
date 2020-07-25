package com.markups.umeed;

public class AddUserRequest {
    String name,password,contact,location;
    Integer manager_ID;

    public AddUserRequest(String name, String password, String contact, String location, Integer manager_ID) {
        this.name = name;
        this.password = password;
        this.contact = contact;
        this.location = location;
        this.manager_ID = manager_ID;
    }
}
