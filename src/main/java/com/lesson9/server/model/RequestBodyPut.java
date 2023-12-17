package com.lesson9.server.model;

import java.util.HashMap;

public class RequestBodyPut implements RequestBody {

    private HashMap<String, Object> getDataForRequestBody = new HashMap<>();

    private final int id;

    private final String name;

    private final String email;

    private final String phone;

    public RequestBodyPut(int id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }


    public void fillingMap() {
        getDataForRequestBody.put("id", id);
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
