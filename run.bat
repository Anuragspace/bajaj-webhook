@echo off
echo Bajaj Webhook Application - Build and Run Script
echo ================================================

REM Check if Java is installed
java -version >nul 2>&1
if %errorlevel% neq 0 (
    echo Error: Java is not installed or not in PATH
    echo Please install Java 11 or higher
    pause
    exit /b 1
)

echo Java is available. Proceeding with build...

REM Try Maven first
where mvn >nul 2>&1
if %errorlevel% equ 0 (
    echo Using Maven to build the project...
    mvn clean compile
    if %errorlevel% equ 0 (
        echo Build successful! Running the application...
        mvn spring-boot:run
    ) else (
        echo Maven build failed. Please check the errors above.
        pause
    )
) else (
    REM Try Gradle if Maven is not available
    where gradle >nul 2>&1
    if %errorlevel% equ 0 (
        echo Using Gradle to build the project...
        gradle clean build
        if %errorlevel% equ 0 (
            echo Build successful! Running the application...
            gradle bootRun
        ) else (
            echo Gradle build failed. Please check the errors above.
            pause
        )
    ) else (
        echo Neither Maven nor Gradle found in PATH
        echo Please install Maven or Gradle, or use an IDE to run the project
        echo Alternatively, if you have the JAR file, run: java -jar target/bajaj-webhook-app-1.0.0.jar
        pause
    )
)

pause