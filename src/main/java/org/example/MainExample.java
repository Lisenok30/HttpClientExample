package org.example;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.*;
import java.net.http.*;

public class MainExample {
    private static final String localhost = "http://192.168.50.54:3000";
    private static final String MY_API_URL = localhost+"/departments";
    private static final String MY_API_URL3 = "https://jsonplaceholder.typicode.com/posts";

    public static void main(String[] args) throws InterruptedException, IOException, ParseException {
        System.out.println("Hello! It is me.");
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(MY_API_URL))
                .build();
        HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
        int status = response.statusCode();
        String body = ((String) response.body()).trim();
        JSONParser jp = new JSONParser();
        Object obj = jp.parse(body);
        JSONArray array = (JSONArray)obj;
        int len = array.size();
        System.out.println(len);
        JSONObject jo0 = (JSONObject)array.get(0);
        JSONObject jo1 = (JSONObject)array.get(1);
        JSONObject jo2 = (JSONObject)array.get(2);
//        System.out.println(jo0.get("title"));
//        System.out.println(jo1.get("title"));
//        System.out.println(jo2.get("title"));
        System.out.println(jo0.get("id"));
        System.out.println(jo1.get("id"));
        System.out.println(jo2.get("id"));
        System.out.println(jo0.get("name"));
        System.out.println(jo1.get("name"));
        System.out.println(jo2.get("name"));
        return;
    }
}
