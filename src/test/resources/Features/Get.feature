Feature: get Job Functionality with Scenario Outline

  Scenario Outline: Verify a job using a get request
    Given User adds Header Parameter <headerType> <headerValue>
    When User makes Get request <endPoint>
    Then User validates status code <statusCode>

    Examples: 
      | headerType     | headerValue        | endPoint                                        | statusCode |
      | 'Content-Type' | 'application/json' | 'https://hr-scrum.noortecktraining.com/job/all' |        200 |
