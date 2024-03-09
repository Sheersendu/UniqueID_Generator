package com.UniqueIDGenerator.controllers;

import com.UniqueIDGenerator.UniqueIdGeneratorApplication;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserControllerTest {
    private static ConfigurableApplicationContext appContext;
    private static String baseUrl;

    @BeforeAll
    public static void setUp() {
        // Start your Spring Boot application
        appContext = SpringApplication.run(UniqueIdGeneratorApplication.class);
        // Get the base URL of your application
        baseUrl = "http://localhost:8080/api";
    }

    @AfterAll
    public static void tearDown() {
        // Stop the Spring Boot application
        appContext.close();
    }

    @Test
    public void createUserTest() throws JSONException {
        // Set up RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Set up request headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create request body
        String requestBody = "{\"userName\":\"Sheersendu\", \"emailId\":\"yes@email.com\", \"phoneNumber\":\"1234567890\"}";

        // Make the HTTP request
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        String response = restTemplate.postForObject(baseUrl + "/user/register", entity, String.class);
        JSONObject responseJsonObject = new JSONObject(response);

        // Validate the response
        assertNotNull(response);
        assertEquals("Sheersendu", responseJsonObject.getString("username"));
        assertEquals("yes@email.com", responseJsonObject.getString("emailId"));
        assertEquals("1234567890", responseJsonObject.getString("phoneNumber"));
    }
}
