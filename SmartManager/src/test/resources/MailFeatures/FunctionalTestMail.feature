Feature: S-Mail

  Background: Pre-requisite
    Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "demostoredev"

  @createMailvalidation
  Scenario Outline: Create Mail Message Validation
    Given I click on "Create a New Campaign" option in "Mail"
    Then I should be on Mail "Create a New Campaign" page
    When Set Name as "<Name>"
    And click on button "Create mail campaign"
    Then I should see Message "<Message>"

    Examples: Validation Messages
      | Name        | Message                                    |
      |             | Name must be provided                      |
      | Auto_Create | Recommendation Algorithms must be selected |

  @emptyEmailPreview
  Scenario: Validate Empty Email
    Given I click on "Create a New Campaign" option in "Mail"
    Then I should be on Mail "Create a New Campaign" page
    When I click on link "3.Preview"
    And click on button " Preview Email"
    Then I should see Message "Cannot generate preview Email address required"
    
    @sendEmailErrorValidation
     Given I click on "Create a New Campaign" option in "Mail"
    Then I should be on Mail "Create a New Campaign" page
    When I click on link "3.Preview"
    And click on button "Send Test Email"
    Then I should see Message "Cannot generate preview Email address required"

  @duplicateNameMailCampaign
  Scenario: Error Validate Duplicate Campaign Name
    Given I Create Simple Mail Campaign with name "AutoCreate"
    And I Create Simple Mail Campaign with name "AutoCreate"
    Then I should see Message "The name you have chosen is already in use"
