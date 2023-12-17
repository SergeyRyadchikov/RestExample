package com.lesson9.server.API;

import com.lesson9.server.model.RequestBody;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static java.nio.charset.StandardCharsets.UTF_8;

public class RequestSearch implements Request{

    private int id;
    private String protocol = "http://";
    private String domain = "127.0.0.1:8080";
    private String endPoint;

    private String url;


    public RequestSearch(int id) {
        this.id = id;
        this.endPoint = String.format("/clients/%s", id);
        this.url = protocol + domain + endPoint;
    }

    @Override
    public String sendRequest() throws URISyntaxException, IOException, InterruptedException {

        URI uri = new URI(url);

        System.out.println("GET /clients/{id} " + uri);

        HttpClient HttpClient = java.net.http.HttpClient.newHttpClient();

        var request = HttpRequest.newBuilder(uri).GET().header("Content-type", "application/json").build();

        var response = HttpClient.send(request, responseInfo -> HttpResponse.BodySubscribers.mapping(HttpResponse
                .BodySubscribers.ofString(UTF_8), String::getBytes));

        if (response.statusCode() == 200){

            String result = new String(response.body());

            System.out.println(result);

            return result;

        } else {

            return ("fail: " + response.statusCode() + "\n" + "message: " + new String(response.body()));

        }
    }

    @Override
    public String sendRequest(RequestBody requestBody) throws URISyntaxException, IOException, InterruptedException {
        return null;
    }
}
