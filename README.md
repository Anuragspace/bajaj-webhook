# Bajaj Webhook Application

A Spring Boot application that generates webhooks, solves SQL problems, and submits solutions via API calls.

## Overview


![Maven Daemon setup screenshot 1](https://github.com/user-attachments/assets/f0af42b3-ad5c-4a77-b48f-bba2346cceea)
![Maven Daemon setup screenshot 2](https://github.com/user-attachments/assets/b869bc77-737e-4a08-9dc9-df05081685e8)



This application implements the following workflow:
1. On startup, sends a POST request to generate a webhook
2. Receives webhook URL and access token
3. Solves SQL Question 2 (for even registration numbers)
4. Submits the SQL solution to the webhook URL using JWT token

## SQL Problem Solved

**Question 2**: Calculate the number of employees who are younger than each employee, grouped by their respective departments.

**Solution Query**:
```sql
SELECT 
    e1.EMP_ID, 
    e1.FIRST_NAME, 
    e1.LAST_NAME, 
    d.DEPARTMENT_NAME, 
    COUNT(e2.EMP_ID) AS YOUNGER_EMPLOYEES_COUNT 
FROM EMPLOYEE e1 
JOIN DEPARTMENT d ON e1.DEPARTMENT = d.DEPARTMENT_ID 
LEFT JOIN EMPLOYEE e2 ON e1.DEPARTMENT = e2.DEPARTMENT AND e2.DOB > e1.DOB 
GROUP BY e1.EMP_ID, e1.FIRST_NAME, e1.LAST_NAME, d.DEPARTMENT_NAME 
ORDER BY e1.EMP_ID DESC
```

## Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── bajaj/
│   │           └── webhook/
│   │               ├── BajajWebhookApplication.java    # Main application class
│   │               ├── config/
│   │               │   └── WebhookConfig.java          # RestTemplate configuration
│   │               ├── dto/                            # Data Transfer Objects
│   │               │   ├── SolutionSubmissionRequest.java
│   │               │   ├── WebhookGenerationRequest.java
│   │               │   └── WebhookGenerationResponse.java
│   │               ├── runner/
│   │               │   └── WebhookRunner.java          # Startup execution runner
│   │               └── service/
│   │                   ├── SqlProblemSolver.java       # SQL solution logic
│   │                   └── WebhookService.java         # Webhook API interactions
│   └── resources/
│       └── application.properties                      # Application configuration
```

## Requirements

- Java 11 or higher
- Maven 3.6+ or use the included Maven wrapper

## Configuration

Before running, update the user details in `WebhookRunner.java`:

```java
String name = "Your Name";
String regNo = "YOUR_REG_NO"; // Use even number for Question 2
String email = "your.email@example.com";
```

## Building and Running

### Option 1: Using Maven (if installed)
```bash
mvn clean compile
mvn spring-boot:run
```

### Option 2: Using Maven Wrapper
```bash
./mvnw clean compile
./mvnw spring-boot:run
```

### Option 3: Creating JAR file
```bash
mvn clean package
java -jar target/bajaj-webhook-app-1.0.0.jar
```

### Option 4: Using the provided run.bat (Windows)
```bash
run.bat
```

## API Endpoints Used

1. **Webhook Generation**: 
   - POST `https://bfhldevapigw.healthrx.co.in/hiring/generateWebhook/JAVA`

2. **Solution Submission**: 
   - POST `https://bfhldevapigw.healthrx.co.in/hiring/testWebhook/JAVA`

## Dependencies

- Spring Boot 3.2.0
- Spring Boot Starter Web
- Jackson for JSON processing

## Features

- Automatic workflow execution on application startup
- RESTful API integration using RestTemplate
- JSON serialization/deserialization
- JWT token handling
- Comprehensive logging
- Error handling and exception management

## Output

The application will log:
- Webhook generation status
- SQL solution generated
- Submission status and response

## Submission Files

- Source code in this repository
- JAR file: `target/bajaj-webhook-app-1.0.0.jar`
- This README with instructions

## JAR Download Link

Raw downloadable GitHub link to JAR:
`https://github.com/Anuragspace/bajaj-webhook/raw/main/target/bajaj-webhook-app-1.0.0.jar`
