package org.example;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Metod {
    String url = "http://91.241.64.178:7081/api/users";
    RestTemplate restTemplate = new RestTemplate();
    String cookieSession;

    public void getAllUsers() {
        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<List<User>> actualExample = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        List<User> users = actualExample.getBody();

        cookieSession = actualExample.getHeaders().getFirst(actualExample.getHeaders().SET_COOKIE);
        System.out.println("First request was completed");
    }

    public void saveRequest() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", cookieSession);

        User newUser = new User(3L,"James","Brown", (byte) 12);
        HttpEntity requestEntity = new HttpEntity(newUser, requestHeaders);
        ResponseEntity response = restTemplate.exchange(url, HttpMethod.POST,requestEntity, String.class);
        System.out.println(response.getBody());
    }

    public void editRequest() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", cookieSession);

        User newUser = new User(3L,"Thomas","Shelby", (byte) 12);
        HttpEntity requestEntity = new HttpEntity(newUser, requestHeaders);
        ResponseEntity response = restTemplate.exchange(url, HttpMethod.PUT,requestEntity, String.class);
        System.out.println(response.getBody());
    }

    public void deleteRequest() {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Cookie", cookieSession);
        HttpEntity requestEntity = new HttpEntity(requestHeaders);

        ResponseEntity response = restTemplate.exchange(url + "/3", HttpMethod.DELETE,requestEntity, String.class);
        System.out.println(response.getBody());
    }
}