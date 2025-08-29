package com.bajaj.webhook.service;

import com.bajaj.webhook.dto.SolutionSubmissionRequest;
import com.bajaj.webhook.dto.WebhookGenerationRequest;
import com.bajaj.webhook.dto.WebhookGenerationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {
    
    private static final String WEBHOOK_GENERATION_URL = "https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA";
    private static final String WEBHOOK_SUBMISSION_URL = "https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA";
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private SqlProblemSolver sqlProblemSolver;
    
    /**
     * Generate webhook by sending POST request with user details
     */
    public WebhookGenerationResponse generateWebhook(String name, String regNo, String email) {
        try {
            // Create request body
            WebhookGenerationRequest request = new WebhookGenerationRequest(name, regNo, email);
            
            // Set headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            // Create HTTP entity
            HttpEntity<WebhookGenerationRequest> entity = new HttpEntity<>(request, headers);
            
            // Send POST request
            ResponseEntity<WebhookGenerationResponse> response = restTemplate.exchange(
                WEBHOOK_GENERATION_URL,
                HttpMethod.POST,
                entity,
                WebhookGenerationResponse.class
            );
            
            System.out.println("Webhook generation successful!");
            System.out.println("Webhook URL: " + response.getBody().getWebhook());
            System.out.println("Access Token received");
            
            return response.getBody();
            
        } catch (Exception e) {
            System.err.println("Error generating webhook: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to generate webhook", e);
        }
    }
    
    /**
     * Submit solution to the webhook URL using JWT token
     */
    public void submitSolution(String webhookUrl, String accessToken, String regNo) {
        try {
            // Get SQL solution based on registration number
            String sqlSolution = sqlProblemSolver.getSolutionForRegNo(regNo);
            
            System.out.println("Generated SQL Solution:");
            System.out.println(sqlSolution);
            
            // Create request body
            SolutionSubmissionRequest request = new SolutionSubmissionRequest(sqlSolution);
            
            // Set headers with JWT token
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.set("Authorization", accessToken);
            
            // Create HTTP entity
            HttpEntity<SolutionSubmissionRequest> entity = new HttpEntity<>(request, headers);
            
            // Send POST request to webhook URL
            ResponseEntity<String> response = restTemplate.exchange(
                WEBHOOK_SUBMISSION_URL,
                HttpMethod.POST,
                entity,
                String.class
            );
            
            System.out.println("Solution submitted successfully!");
            System.out.println("Response status: " + response.getStatusCode());
            System.out.println("Response body: " + response.getBody());
            
        } catch (Exception e) {
            System.err.println("Error submitting solution: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Failed to submit solution", e);
        }
    }
    
    /**
     * Complete workflow: Generate webhook and submit solution
     */
    public void executeWorkflow(String name, String regNo, String email) {
        try {
            System.out.println("Starting Bajaj Webhook Application workflow...");
            System.out.println("User: " + name + ", RegNo: " + regNo + ", Email: " + email);
            
            // Step 1: Generate webhook
            WebhookGenerationResponse webhookResponse = generateWebhook(name, regNo, email);
            
            // Step 2: Submit solution
            submitSolution(webhookResponse.getWebhook(), webhookResponse.getAccessToken(), regNo);
            
            System.out.println("Workflow completed successfully!");
            
        } catch (Exception e) {
            System.err.println("Workflow failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}