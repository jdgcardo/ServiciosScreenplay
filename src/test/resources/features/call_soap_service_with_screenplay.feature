Feature: Request a SOAP API with Screenplay
  I want to call an API using the Screenplay ability CallAnApi

  @SimpleGet
  Scenario: Simple SOAP Request
    Given that jorge calls a SOAP API with Screenplay
    When jorge obtains the SOAP response
    Then jorge verifies the quality OF SOAP response