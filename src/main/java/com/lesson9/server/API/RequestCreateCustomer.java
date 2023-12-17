package com.lesson9.server.API;

import com.lesson9.server.model.RequestBody;
import com.lesson9.server.service.ConvertToJSON;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.nio.charset.StandardCharsets.UTF_8;

public class RequestCreateCustomer implements Request {

    private String protocol = "http://";
    private String domain = "127.0.0.1:8080";
    private String endPoint = "/clients";

    private String url = protocol + domain + endPoint;


    @Override
    public String sendRequest(RequestBody requestBody) throws URISyntaxException, IOException, InterruptedException {

        String JSON = ConvertToJSON.convert(requestBody.getDataForRequestBody());

        URI uri = new URI(url);
        System.out.println("POST /clients " + uri);

        HttpClient HttpClient = java.net.http.HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(uri)
                .POST(HttpRequest.BodyPublishers.ofString(JSON))
                .header("Content-type", "application/json")
                .build();


        var response = HttpClient
                .send(request, responseInfo -> HttpResponse
                        .BodySubscribers
                        .mapping(HttpResponse
                                .BodySubscribers
                                .ofString(UTF_8), String::getBytes));

        if (response.statusCode() == 201) {
            return "Created";
        } else {
            return ("fail: " + response.statusCode() + "\n" + "message: " + new String(response.body()));
        }
    }


    @Override
    public String sendRequest() throws URISyntaxException, IOException, InterruptedException {
        return null;
    }
}
