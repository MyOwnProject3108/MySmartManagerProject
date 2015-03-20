Feature: S-Mail

  Background: Pre-requisite
    Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "leekes"

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
  Scenario: Changing Number Of Products Should Add/Remove Email Rec Slots
    Given I goto Mail Campaign "AutoCreate"
    And I click on link "2. Configuration"
    When I Set the Number of Products as "3"
    Then I Should see "3" Product Positions

  @UserTopUpsDisabled
  Scenario: Verify That If Top-Ups Is Disabled, Empty Email Rec should be returned
    Given I goto Mail Campaign "AutoCreate"
    And I click on link "2. Configuration"
    When I Set the Number of Products as "3"
    And I Set "Product Catalog" at position "1"
    And I Set "Cross-sell, previous purchases and views" at position "2"
    And I Set "Product Catalog" at position "3"
    And I Uncheck the checkbox for User-Top ups
    And click on button "Next"
    And I Specify Email address for Preview as "test@peerius.com"
    And click on button " Preview Email"
    Then Preview should Show Second Position Blank with No Email Rec
  
  @UserTopUpsEnabled
  Scenario: Verify That If Top-Ups Is Enabled, Empty Email Rec Is Topped Up By Default Email Recs
    Given I goto Mail Campaign "AutoCreate"
    And I click on link "2. Configuration"
    When I Set the Number of Products as "3"
    And I Set "Product Catalog" at position "1"
    And I Set "Cross-sell, previous purchases and views" at position "2"
    And I Set "Product Catalog" at position "3"
    And I Enable User-Top ups
    And click on button "Next"
    And I Specify Email address for Preview as "test@peerius.com"
    And click on button " Preview Email"
    Then Preview should Show Second Position Topped up with Default Email Rec
 
  @duplicatePositions
  Scenario: Each Click on Duplicate button Should copy that position to new position
    Given I goto Mail Campaign "AutoCreate"
    And I click on link "2. Configuration"
    When I Set the Number of Products as "1"
    And I Set "Product Catalog" at position "1"
    And I click on link "Expression"
    And I select option "Sale Price"
    And I select operator as "less than"
    And I Enter rule Text as "20"
    And I click on link "Hints"
    And I select option "sale-product"
    And click on button " Duplicate"
    Then I Should See a "2" Positions With Same Strategy, Expression and Hint.

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
