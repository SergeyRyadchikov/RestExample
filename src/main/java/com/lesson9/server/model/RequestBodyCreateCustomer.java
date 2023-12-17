package com.lesson9.server.model;

import java.util.HashMap;

public class RequestBodyCreateCustomer implements RequestBody{

    private HashMap<String, Object> getDataForRequestBody = new HashMap<>();

    private final String name;

    private final String email;

    private final String phone;

    public RequestBodyCreateCustomer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public void fillingMap() {
        getDataForRequestBody.put("name", name);
        getDataForRequestBody.put("email", email);
        getDataForRequestBody.put("phone", phone);

    }

    @Override
    public HashMap<String, Object> getDataForRequestBody() {
        fillingMap();
        return getDataForRequestBody;
    }
}
