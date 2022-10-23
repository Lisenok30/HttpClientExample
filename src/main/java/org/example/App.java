package org.example;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Hello HttpClient!
 *
 */
public class App 
{
    private static final String MY_API_URL3 = "https://jsonplaceholder.typicode.com/posts";

    private static final String localhost = "http://192.168.50.54:3000";
    private static final String MY_API_URL = localhost+"/departments";
    private static final String MY_API_URL1 = localhost+"/users";
    private static final String MY_API_URL2 = localhost+"/users_log";
    private static final String MY_API_URL4 = localhost+"/departments?id=eq.5";
    private static final String MY_API_URL5 = localhost+"/departments?id=gte.5";
    private static final String MY_API_URL6 = localhost+"/departments?id=gte.5&id=lte.7";

//    private static final String MY_API_URL = "http://localhost:3000/departments";
//    private static final String MY_API_URL1 = "http://localhost:3000/users";
//    private static final String MY_API_URL2 = "http://localhost:3000/users_log";
//    private static final String MY_API_URL4 = "http://localhost:3000/departments?id=eq.5";
//    private static final String MY_API_URL5 = "http://localhost:3000/departments?id=gte.5";
//    private static final String MY_API_URL6 = "http://localhost:3000/departments?id=gte.5&id=lte.7";

    public static void main( String[] args ) {
        System.out.println("Hello http-client");

        JSONObject r = GetRequest.sendRequest(MY_API_URL);
        if (r != null) {
            JSONArray ja = new JSONArray(r.getString("body"));
            System.out.println("body size=" + ja.length());

            System.out.println("----------------------------------");
            System.out.println("with stream forEach:");
            System.out.println("----------------------------------");
            ja.toList().forEach(dept -> System.out.println(((Map) dept).get("name")));

            System.out.println("----------------------------------");
            System.out.println("with for loop:");
            System.out.println("----------------------------------");
            for (Object hm : ja.toList()) {
                System.out.println(((Map) hm).get("name"));
            }

            System.out.println("----------------------------------");
            System.out.println("with stream filter (id=5):");
            System.out.println("----------------------------------");
            Map d = (Map) ja.toList().stream()
                    .filter(dept -> (Integer) ((Map) dept).get("id") == 5)
                    .collect(Collectors.toList())
//                    .toList()
                    .get(0);
            System.out.println("id=" + d.get("id"));
            System.out.println("name=" + d.get("name"));

            System.out.println("----------------------------------");
            System.out.println("with stream filter (id>=5 and id<=7):");
            System.out.println("----------------------------------");
            ja.toList().stream()
                    .filter(dept -> betweenInts((Integer) ((Map) dept).get("id"), 5, 7))
                    .collect(Collectors.toList())
//                    .toList()
                    .forEach(dept -> {
                        System.out.println("id=" + ((Map) dept).get("id"));
                        System.out.println("name=" + ((Map) dept).get("name"));
                    });

            System.out.println("----------------------------------");
            System.out.println("with for loop (id>=5 and id<=7):");
            System.out.println("----------------------------------");
            for (Object hm : ja.toList()) {
                if (betweenInts((Integer) ((Map) hm).get("id"), 5, 7))
                    System.out.println(((Map) hm).get("name"));
            }

            System.out.println("----------------------------------");
            System.out.println("with stream filter (id in set of [1,3,5,7]):");
            System.out.println("----------------------------------");
            ja.toList().stream()
                    .filter(dept -> inSetOfInts((Integer) ((Map) dept).get("id"), new int[]{1, 3, 5, 7}))
                    .collect(Collectors.toList())
//                    .toList()
                    .forEach(dept -> {
                        System.out.println("id=" + ((Map) dept).get("id"));
                        System.out.println("name=" + ((Map) dept).get("name"));
                    });

            System.out.println("----------------------------------");
            System.out.println("with for loop (id in set of [1,3,5,7]):");
            System.out.println("----------------------------------");
            for (Object hm : ja.toList()) {
                if (inSetOfInts((Integer) ((Map) hm).get("id"), new int[]{1, 3, 5, 7}))
                    System.out.println(((Map) hm).get("name"));
            }
        }

        JSONObject r1 = GetRequest.sendRequest(MY_API_URL1);
        if (r1 != null) {
            JSONArray ja1 = new JSONArray(r1.getString("body"));
            System.out.println("body size=" + ja1.length());
        }

        JSONObject r2 = GetRequest.sendRequest(MY_API_URL2);
        if (r2 != null) {
            JSONArray ja2 = new JSONArray(r2.getString("body"));
            System.out.println("body size=" + ja2.length());
        }

        JSONObject r3 = GetRequest.sendRequest(MY_API_URL3);
        if (r3 != null) {
            JSONArray ja3 = new JSONArray(r3.getString("body"));
            System.out.println("body size=" + ja3.length());
        }

        JSONObject r4 = GetRequest.sendRequest(MY_API_URL4);
        if (r4 != null) {
            JSONArray ja4 = new JSONArray(r4.getString("body"));
            System.out.println("body size=" + ja4.length());
        }

        JSONObject r5 = GetRequest.sendRequest(MY_API_URL5);
        if (r5 != null) {
            JSONArray ja5 = new JSONArray(r5.getString("body"));
            System.out.println("body size=" + ja5.length());
        }

        JSONObject r6 = GetRequest.sendRequest(MY_API_URL6);
        if (r6 != null) {
            JSONArray ja6 = new JSONArray(r6.getString("body"));
            System.out.println("body size=" + ja6.length());
        }
    }

    private static boolean betweenInts(Integer k, int i, int j) {
        if (k == null) return false;
        System.out.println(""+k+" between "+i+" and "+j+" result="+(k >= i && k <= j));
        return (k >= i && k <= j);
    }

    @SuppressWarnings("ReassignedVariable")
    private static boolean inSetOfInts(Integer k, int[] ints) {
        if (k == null) return false;
        boolean inSet = false;
        for (int i: ints) {
            if (k == i) {
                inSet = true;
                System.out.println(""+k+" equals "+i+" result="+true);
                break;
            }
        }
        String s = Arrays.stream(ints).mapToObj(String::valueOf).collect(Collectors.joining(","));
        System.out.println(""+k+" in ["+s+"] final result="+inSet);
        return inSet;
    }

}
