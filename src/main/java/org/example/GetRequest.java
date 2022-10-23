package org.example;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class GetRequest {

    @SuppressWarnings("rawtypes")
    public static JSONObject sendRequest(String apiUrl) {
        System.out.println( "apiUrl: "+apiUrl);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(apiUrl))
                .build();

        HttpResponse response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
        JSONObject json = new JSONObject();
        if (response != null) {
//            json.put("response", response);   // also contains 'body' so we do not need duplication
            int status = response.statusCode();
            System.out.println("status=" + status);
            String body = (String)response.body();
            System.out.println("body=" + body);
            json.put("apiUrl", apiUrl);
            json.put("status", status);
            json.put("body", body);
            System.out.println("empty=" + json.isEmpty());
            return json;
        }
        return null;
    }

}
