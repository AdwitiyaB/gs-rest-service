Feature: Greeting API

  @GreetingAPI
  Scenario: No parameter greeting should return default message
    Given the server is running
    When the client sends a GET request to "/greeting"
    Then the response status should be 200
    And the response content should be "Hello, World!"

  @GreetingAPI
  Scenario: Parameterized greeting should return tailored message for User
    Given the server is running
    When the client sends a GET request to "/greeting" with the parameter "name" set to "User"
    Then the response status should be 200
    And the response content should be "Hello, User!"
    
    @GreetingAPI
  Scenario: Parameterized greeting should return tailored message for Adwitiya
    Given the server is running
    When the client sends a GET request to "/greeting" with the parameter "name" set to "Adwitiya"
    Then the response status should be 200
    And the response content should be "Hello, Adwitiya!"

  @GreetingAPI
  Scenario: Parameterized greeting should return tailored message for empty name
    Given the server is running
    When the client sends a GET request to "/greeting" with the parameter "name" set to ""
    Then the response status should be 200
    And the response content should be "Hello, World!"

  @GreetingAPI
  Scenario: Parameterized greeting should return tailored message for long name
    Given the server is running
    When the client sends a GET request to "/greeting" with the parameter "name" set to "VeryLongNameForTestingPurposes"
    Then the response status should be 200
    And the response content should be "Hello, VeryLongNameForTestingPurposes!"

  @GreetingAPI
  Scenario: Parameterized greeting should return tailored message for special characters in name
    Given the server is running
    When the client sends a GET request to "/greeting" with the parameter "name" set to "@#$%^&()"
    Then the response status should be 200
    And the response content should be "Hello, @#$%^&()!"
@GreetingAPI
  Scenario: Parameterized greeting should return tailored message for numeric name
    Given the server is running
    When the client sends a GET request to "/greeting" with the parameter "name" set to "12345"
    Then the response status should be 200
    And the response content should be "Hello, 12345!"

  @GreetingAPI
  Scenario: Parameterized greeting should return tailored message for mixed characters in name
    Given the server is running
    When the client sends a GET request to "/greeting" with the parameter "name" set to "User123!@#"
    Then the response status should be 200
    And the response content should be "Hello, User123!@#!"

  @GreetingAPI
  Scenario: Parameterized greeting should handle case sensitivity (User vs user)
    Given the server is running
    When the client sends a GET request to "/greeting" with the parameter "name" set to "user"
    Then the response status should be 200
    And the response content should be "Hello, user!"