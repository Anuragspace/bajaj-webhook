# Bajaj Webhook Application - Submission Summary

## Project Details
- **Repository**: https://github.com/Anuragspace/bajaj-webhook
- **Application**: Spring Boot webhook application for SQL problem solving
- **Question Solved**: SQL Question 2 (for even registration numbers)

## Key Files Delivered

### 1. Source Code
Complete Spring Boot application with:
- Main Application: `BajajWebhookApplication.java`
- Service Layer: `WebhookService.java`, `SqlProblemSolver.java`
- DTOs: Request/Response objects for API communication
- Configuration: Spring Boot configuration and RestTemplate setup

### 2. Build Files
- `pom.xml` - Maven configuration with all dependencies
- `build.gradle` - Alternative Gradle build configuration
- `.gitignore` - Proper git ignore rules

### 3. Executable JAR
- **File**: `target/bajaj-webhook-app-1.0.0.jar`
- **Size**: ~16MB (includes all dependencies)
- **Download Link**: https://github.com/Anuragspace/bajaj-webhook/raw/main/target/bajaj-webhook-app-1.0.0.jar

## SQL Solution Implemented

**Problem**: Calculate the number of employees who are younger than each employee, grouped by their respective departments.

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

## Application Workflow

1. **On Startup**: Application automatically triggers webhook generation
2. **Webhook Generation**: Sends POST request to generate webhook with user details
3. **SQL Processing**: Determines question based on registration number (even = Question 2)
4. **Solution Submission**: Submits SQL solution to webhook URL using JWT token
5. **Logging**: Comprehensive logging of all steps and responses

## Technical Stack

- **Framework**: Spring Boot 3.2.0
- **Java Version**: 17+ (compatible with Java 24)
- **Build Tool**: Maven 3.9+ (with Maven Daemon support)
- **Dependencies**: 
  - Spring Boot Starter Web
  - Jackson for JSON processing
  - RestTemplate for HTTP calls

## How to Run

### Option 1: Run JAR directly
```bash
java -jar target/bajaj-webhook-app-1.0.0.jar
```

### Option 2: Build and run with Maven
```bash
mvn clean package
mvn spring-boot:run
```

### Option 3: Use provided batch file (Windows)
```bash
run.bat
```

## Configuration Required

Before running, update user details in `WebhookRunner.java`:
```java
String name = "Your Name";
String regNo = "YOUR_REG_NO"; // Use even number for Question 2
String email = "your.email@example.com";
```

## Submission Links

- **GitHub Repository**: https://github.com/Anuragspace/bajaj-webhook
- **JAR Download**: https://github.com/Anuragspace/bajaj-webhook/raw/main/target/bajaj-webhook-app-1.0.0.jar
- **Clone Command**: `git clone https://github.com/Anuragspace/bajaj-webhook.git`

## Features Implemented

✅ Automatic webhook generation on startup  
✅ SQL Question 2 solution for even registration numbers  
✅ JWT token handling for API authentication  
✅ Complete error handling and logging  
✅ Executable JAR file with all dependencies  
✅ Comprehensive documentation  
✅ Clean project structure following Spring Boot best practices  

The application is ready for submission and testing!
