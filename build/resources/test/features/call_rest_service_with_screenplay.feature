Feature: Request a REST API with Screenplay
  I want to call an API using the Screenplay ability CallAnApi

  @SimpleGet
  Scenario: Simple GET Request
    Given that jorge calls a REST API with Screenplay
    When jorge obtains the response
    Then jorge verifies the quality response

  @SimplePost
  Scenario: Simple POST Request
    Given that jorge calls a REST API with Screenplay
    When jorge obtains the POST response
    Then jorge verifies the POST quality response

  @SimplePut
  Scenario: Simple PUT Request
    Given that jorge calls a REST API with Screenplay
    When jorge obtains the PUT response
    Then jorge verifies the PUT quality response

  @SimpleDelete
  Scenario: Simple DELETE Request
    Given that jorge calls a REST API with Screenplay
    When jorge obtains the DELETE response
    Then jorge verifies the DELETE quality response

