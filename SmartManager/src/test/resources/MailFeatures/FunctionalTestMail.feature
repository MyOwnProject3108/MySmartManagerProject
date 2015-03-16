Feature: S-Mail

  Background: Pre-requisite
    Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "demostoredev"

  @CreateMailCampaign
  Scenario: Create a simple Mail Campaign
    Given I click on "Create a New Campaign" option in "Mail"
    Then I should be on Mail "Create a New Campaign" page
    And I Create Simple Mail Campaign with name "AutoCreate"
    Then I should see Message "Successfully saved"
    Then I Should See Mail Campaign "AutoCreate" on Mail Overview Page

  @EditMailCmpaign
  Scenario: Editing An Mail Campaign
    Given I goto Mail Campaign "AutoCreate"
    And I Edit Mail campaign "AutoCreate"
    Then I should be on "edit" Page

  @CopyMailCampaign
  Scenario: Duplicating Mail Campaign
    Given I goto Mail Campaign "AutoCreate"
    And I Duplicate Mail Campaign "AutoCreate"
    Then I Should See Mail Campaign "AutoCreate copy" on Mail Overview Page

  @PauseMailCampaign
  Scenario: De-activate Mail Campaign
    Given I goto Mail Campaign "AutoCreate"
    And I Pause Mail campaign "AutoCreate"
    Then Mail Campaign Should be Paused

  @TextStyle
  Scenario Outline: Styling changes should reflect on product title and price display
    Given I goto Mail Campaign "AutoCreate"
    When I Set style with "<value>"
    Then I should see the style applied with "<value>" in "<Attribute>" in Widget Content Preview
    And click on button "Save Campaign"
    When I goto Mail Campaign "AutoCreate"
    Then I should see the style applied with "<value>" in "<Attribute>" in Widget Content Preview

    Examples: Style changes
      | Attribute    | Value |
      | clientHeight | 200   |
      | clientWidth  | 300   |

  @AdvancedLink
  Scenario: Verify If Show Advanced Settings Link Works
    Given I goto Mail Campaign "AutoCreate"
    And I click on link "Show Advanced Settings"
    Then I should see the HTML code for Email Recs
    And The Link "Hide Advanced Settings" should be visible

  @SetNumOfProducts
  Scenario Outline: Changing Number Of Products Should Add/Remove Email Rec Slots
    Given I goto Mail Campaign "AutoCreate"
    And I click on link "2. Configuration"
    When I Set the Number of Products as "3"
    Then I Should "<Products>" Product Positions

    Examples: Number of products
      | Products |
      | 1        |
      | 2        |
      | 3        |


  #End of functional scenarios
  @DeleteMailCampaign
  Scenario: Deleting Mail Campaign
    Given I Delete Mail Campaign "AutoCreate copy"
    Given I Delete Mail Campaign "AutoCreate"
    Then I should not see "AutoCreate"
    Then I should not see "AutoCreate copy"

  #Error validation scenarios start here
  @createMailvalidation
  Scenario Outline: Create Mail Message Validation
    Given I click on "Create a New Campaign" option in "Mail"
    Then I should be on Mail "Create a New Campaign" page
    When I Set Name as "<Name>"
    And click on button "Create mail campaign"
    Then I should see Message "<Message>"

    Examples: Validation Messages
      | Name        | Message                                    |
      |             | Name must be provided                      |
      | Auto_Create | Recommendation Algorithms must be selected |

  @emptyEmailPreview
  Scenario: Validate Empty Email For Preview
    Given I click on "Create a New Campaign" option in "Mail"
    Then I should be on Mail "Create a New Campaign" page
    When I click on link "3.Preview"
    And click on button " Preview Email"
    Then I should see Message "Cannot generate preview Email address required"

  @sendEmailErrorValidation
  Scenario: Validate Empty Email For Send Email
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
