package com.lesson9.server.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;

public class ConvertToJSON {
    public static String convert(HashMap<String, Object> requestBody) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestBody);
            return json;
        }catch (JsonProcessingException e){
            return e.getMessage();
        }
    }
}
