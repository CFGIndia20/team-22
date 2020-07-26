package com.markups.umeed.models;

public class AddUserRequest {
    String name,password;
    Integer contact,center_id,manager_id;

    public AddUserRequest(String name, String password, Integer contact, Integer center_id, Integer manager_id) {
        this.name = name;
        this.password = password;
        this.contact = contact;
        this.center_id = center_id;
        this.manager_id = manager_id;
    }
}
