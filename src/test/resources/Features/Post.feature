Feature: Create Job Functionality with Scenario Outline

  Scenario Outline: Verify Create Job Functionality
    Given User provides Header Parameter <headerType> <headerValue>
    And User set the requestBody <requestBody>
    And User makes POST request <endpoint>
    Then User validates status code <statuscode>
    And User validates response body <message>

    Examples: 
      | headerType     | headerValue        | endpoint                                           | statuscode | message                         | requestBody                                                                                              |
      | 'Content-Type' | 'application/json' | "https://hr-scrum.noortecktraining.com/job/create" | "409"      | "Job id 'AD_VP' already exists" | '{"jobId": "AD_VP", "jobTitle": "Administration Vice President", "minSalary": 2000, "maxSalary": 10000}' |
      | 'Content-Type' | 'application/json' | "https://hr-scrum.noortecktraining.com/job/create" | "200"      | "Successfully created"          | '{"jobId": "fish", "jobTitle": "fisherman", "minSalary": 1000, "maxSalary": 6000}'                       |
