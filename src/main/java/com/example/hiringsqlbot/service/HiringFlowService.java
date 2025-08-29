package com.example.hiringsqlbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@Service
public class HiringFlowService {

    @Autowired
    private RestTemplate restTemplate;

    private final String registerUrl = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
    private final String submitUrl = "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";

    public void runFlow() throws Exception {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("name", "John Doe");
        requestBody.put("regNo", "22bce1100");
        requestBody.put("email", "john@example.com");

        ResponseEntity<Map> response = restTemplate.postForEntity(registerUrl, requestBody, Map.class);

        String webhookUrl = (String) response.getBody().get("webhook");
        String accessToken = (String) response.getBody().get("accessToken");

        String sqlFile = regNoEndsEven("22BCE1100") ?
                "sql/question_even.sql" : "sql/question_odd.sql";

        String finalQuery = FileCopyUtils.copyToString(
                new InputStreamReader(new ClassPathResource(sqlFile).getInputStream(), StandardCharsets.UTF_8)
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        Map<String, String> payload = new HashMap<>();
        payload.put("finalQuery", finalQuery);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(payload, headers);
        ResponseEntity<String> submitResponse = restTemplate.postForEntity(submitUrl, entity, String.class);

        System.out.println("Response from submission: " + submitResponse.getBody());
    }

    private boolean regNoEndsEven(String regNo) {
        char lastChar = regNo.charAt(regNo.length() - 1);
        return (lastChar - '0') % 2 == 0;
    }
}
