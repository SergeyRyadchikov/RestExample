package com.lesson9.server.model;

import java.util.HashMap;

public class RequestBodySearchAndDelete implements RequestBody{

    private HashMap<String, Object> getDataForRequestBody = new HashMap<>();

    private final int id;

    public RequestBodySearchAndDelete(int id) {
        this.id = id;
    }


    public void fillingMap() {
        getDataForRequestBody.put("id", id);
    }


    @Override
    public HashMap<String, Object> getDataForRequestBody() {
        fillingMap();
        return getDataForRequestBody;
    }
}
