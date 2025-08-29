package com.bajaj.webhook.runner;

import com.bajaj.webhook.service.WebhookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class WebhookRunner implements CommandLineRunner {
    
    @Autowired
    private WebhookService webhookService;
    
    @Override
    public void run(String... args) throws Exception {
        // User details - replace with your actual details
        String name = "Anurag Adarsh";  // Replace with your actual name
        String regNo = "22BML0024"; // Replace with your actual registration number (even for Question 2)
        String email = "anuraglife2020@gmail.com"; // Replace with your actual email
        
        System.out.println("===========================================");
        System.out.println("BAJAJ WEBHOOK APPLICATION STARTING...");
        System.out.println("===========================================");
        
        // Execute the complete workflow
        webhookService.executeWorkflow(name, regNo, email);
    }
}