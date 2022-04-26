Feature: App API Controller

  Background:
    Given url baseUrl
    Given path '/api'
    * def result = read('http-result.json')

  Scenario: Get app name and version
    When method GET
    Then status 200
    And match $ == result