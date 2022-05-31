Feature: SignUp Request Controller

  Background:
    * url baseUrl

  Scenario: Successfully sign up request
    * def input = read('http-input.json')
    Given path '/v1/auth/signup'
    And request input
    When method POST
    Then status 201