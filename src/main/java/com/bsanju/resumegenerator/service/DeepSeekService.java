package com.bsanju.resumegenerator.service;

import com.bsanju.resumegenerator.dto.DeepSeekRequest;
import com.bsanju.resumegenerator.dto.DeepSeekResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DeepSeekService {

    private final RestTemplate restTemplate;

    @Value("${deepseek.api.url}")
    private String apiUrl;

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    public DeepSeekService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String generateDescription(String prompt) {
        DeepSeekRequest request = new DeepSeekRequest();
        request.setPrompt(prompt);
        request.setModel("deepseek-chat");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);
        try {
            ResponseEntity<DeepSeekResponse> response = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.POST,
                    new HttpEntity<>(request, headers),
                    DeepSeekResponse.class
            );
            if (response.getStatusCode() == HttpStatus.OK &&
                    response.getBody() != null &&
                    !response.getBody().getChoices().isEmpty()) {
                return response.getBody().getChoices().get(0).getText();
            }
            return "Error: No response from AI.";
        } catch (Exception e) {
            return "Error calling DeepSeek API: " + e.getMessage();
        }
    }
}