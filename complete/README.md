# Cucumber Testing in Spring Boot Documentation

## Table of Contents

1. [Introduction](#introduction)
2. [Setup Cucumber in a Spring Boot Project](#setup-cucumber-in-a-spring-boot-project)
    - [Add Cucumber Dependencies](#add-cucumber-dependencies)
    - [Configure Cucumber in the Project](#configure-cucumber-in-the-project)
3. [Writing Feature Files](#writing-feature-files)
    - [Create Feature Files](#create-feature-files)
    - [Write Gherkin Syntax](#write-gherkin-syntax)
4. [Implementing Step Definitions](#implementing-step-definitions)
    - [Create Step Definition Class](#create-step-definition-class)
    - [Injecting Dependencies](#injecting-dependencies)
5. [Executing Cucumber Tests](#executing-cucumber-tests)
    - [Run Cucumber Tests](#run-cucumber-tests)
6. [Troubleshooting](#troubleshooting)

## 1. Introduction

Cucumber is a popular tool for behavior-driven development (BDD) that allows you to write feature files describing the expected behavior of your application in a natural language format called Gherkin. This documentation provides a step-by-step guide on setting up Cucumber in a Spring Boot project, writing feature files, implementing step definitions, and executing tests.

## 2. Setup Cucumber in a Spring Boot Project

### Add Cucumber Dependencies

1. Open your `pom.xml` file.
2. Add the following dependencies for Cucumber in the `<dependencies>` section:

```xml
<dependencies>
    <!-- ... other dependencies ... -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>7.15.0</version> <!-- Use the latest version available -->
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-spring</artifactId>
        <version>7.14.0</version> <!-- Use the latest version available -->
        <scope>test</scope>
    </dependency>
</dependencies>
```

### Configure Cucumber in the Project

Creating a Cucumber context configuration using Annotation `@CucumberContextConfiguration`:

```java
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@CucumberContextConfiguration
public class CucumberConfig {
    // Add any additional configuration if needed
}
```

## 3. Writing Feature Files

### Create Feature Files

1. Create a `src/test/resources` directory in your project.
2. Inside the `resources` directory, create a `features` directory.
3. Inside the `features` directory, create `.feature` files for your scenarios. For example, `greeting.feature`.

### Write Gherkin Syntax

Write Gherkin syntax in your feature files. Example:

```gherkin
Feature: Greeting API

  @GreetingAPI
  Scenario: No parameter greeting should return default message
    Given the server is running
    When the client sends a GET request to "/greeting"
    Then the response status should be 200
    And the response content should be "Hello, World!"

  @GreetingAPI
  Scenario: Parameterized greeting should return tailored message for Adwitiya
    Given the server is running
    When the client sends a GET request to "/greeting" with the parameter "name" set to "Adwitiya"
    Then the response status should be 200
    And the response content should be "Hello, Adwitiya!"
```

## 4. Implementing Step Definitions

### Create Step Definition Class

1. Create a new Java class, for example, `GreetingControllerTests.java`.
2. Annotate the class with `@SpringBootTest`, `@AutoConfigureMockMvc`, and `@CucumberContextConfiguration`.
3. Implement step definitions in this class using Cucumber annotations (`@When`, `@Then`, etc.).

### Injecting Dependencies

Use dependency injection to inject dependencies, such as `MockMvc`. Example:

```java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@CucumberContextConfiguration
public class GreetingControllerTests {

    @Autowired
    private MockMvc mockMvc;

    // Implement step definitions
}
```

## 5. Executing Cucumber Tests

### Run Cucumber Tests

1. Right click on your project, go to `Run As`, click `Run Configurations...`.
![Alt text](2.png)
2. Create a configuration similar to the given image below.
![Alt text](1.png)
3. Select `Run` to execute the Cucumber tests.
*Note: We need not create new configurations everytime, we can execute the configuration created earlier.*


## 6. Troubleshooting

If your tests fail, consider the following steps:

- Check the console output for error messages.
- Ensure that your step definitions match the Gherkin syntax in the feature files.
- Verify that dependencies are injected correctly in your test class.
- Check for typos or syntax errors in your feature files.

By following these steps, you should be able to set up Cucumber in your Spring Boot project, write feature files, implement step definitions, and execute tests. If you encounter issues, refer to the troubleshooting section for guidance.