Feature: S-Mail Functional Tests

  Background: Pre-requisite
    Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "demostoredev"

  @CreateMailCampaign
  Scenario: Create a simple Mail Campaign
    Given I click on "New campaign" option in "Mail"
    Then I should be on Mail "New campaign" page
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

  @TextStyle
  Scenario: Styling changes should reflect on product title and price display
    Given I goto Mail Campaign "AutoCreate"
    When I Set style with value "200" for clientHeight and ClientWidth
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"
    When I goto Mail Campaign "AutoCreate"
    Then I should see the style applied with value "200" in "height" in Widget Content Preview
    Then I should see the style applied with value "200" in "width" in Widget Content Preview

  @AdvancedLink
  Scenario: Verify If Show Advanced Settings Link Works
    Given I goto Mail Campaign "AutoCreate"
    And I click on link "Show Advanced Settings"
    Then I should see the HTML code for Styling
    And The Link "Hide Advanced Settings" should be visible

  @SetNumOfProducts
  Scenario: Changing Number Of Products Should Add/Remove Email Rec Slots
    Given I goto Mail Campaign "AutoCreate"
    And I click on link "2. Configuration"
    When I Set the Number of Products as "3"
    Then I Should see "3" Product Positions
#Invalid Test should be more specific rather than doing multiple task 
  @UserTopUpsEnabled
  Scenario: Verify That If Top-Ups Is Enabled, Empty Email Rec Is Topped Up By Default Email Recs
    Given I goto Mail Campaign "AutoCreate"
    And I click on link "2. Configuration"
    When I Set the Number of Products as "3"
    And I Set "Product Catalog" at position "1"
    And I Set "Cross-sell, previous purchases and views" at position "2"
    And I Set "Product Catalog" at position "3"
    And I Enable User-Top ups
    And I click on button "Next"
    And I Specify Email address as "test@peerius.com"
    And I click on button " Preview Email"
    Then Preview should Show Second Position Topped up with Default Email Rec
    And I click on link "2. Configuration"
    When I Set the Number of Products as "1"
    Then I Should see "1" Product Positions
    And I click on button "Save campaign"

  @UserTopUpsDisabled
  Scenario: Verify That If Top-Ups Is Disabled, Empty Email Rec should be returned
    Given I goto Mail Campaign "AutoCreate"
    And I click on link "2. Configuration"
    When I Set the Number of Products as "3"
    And I Set "Product Catalog" at position "1"
    And I Set "Cross-sell, previous purchases and views" at position "2"
    And I Set "Product Catalog" at position "3"
    And I Uncheck the checkbox for User-Top ups
    And I click on button "Next"
    And I Specify Email address as "test@peerius.com"
    And I click on button " Preview Email"
    Then Preview should Show Second Position Blank with No Email Rec
    And I click on link "2. Configuration"
    When I Set the Number of Products as "1"
    Then I Should see "1" Product Positions
    And I click on button "Save campaign"

  @duplicatePositions
  Scenario: Each Click on Duplicate button Should copy that position to new position
    Given I goto Mail Campaign "AutoCreate"
    And I click on link "2. Configuration"
    When I Set the Number of Products as "1"
    And I Set "Product Catalog" at position "1"
    And I click on link "Expression"
    And I select option "Sale price"
    And I select operator as "less than"
    And I Enter rule Text as "20"
    And I click on link "Hints"
    And I select option "sale-product"
    And I click on button "Duplicate"
    Then I Should See "2" Positions With Same Strategy, Expression and Hint

  #E2E to check if tracking code gets added to the email rec url
  @TrackingCode
  Scenario: To check if Tracking Code gets added in the Product URL For Email Rec
    Given I goto Mail Campaign "AutoCreate"
    And I click on link "2. Configuration"
    When I Set Tracking Code as "&test123"
    And I click on button "Next"
    And I Specify Email address as "test@peerius.com"
    And I click on button "Preview Email"
    And I click on Email Rec "1"
    Then I Should see Tracking Code "&test123" Added In The Product url

  @GenerateCode
  Scenario: Test Generate Code button functionality for single and multiple positions
    Given I goto Mail Campaign "AutoCreate"
    And I click on link "2. Configuration"
    And I click on button "Next"
    When I select option "Custom"
    And I click on button " Generate code"
    Then I should see the HTML code for Email Recs

  @CustomEmailAttributes
  Scenario: Test if Custom Email Attributes settings are saved in UI
    Given I click on "Customise email attributes" option in "Mail"
    When I set Custom Email Attribute "genre"
    And I click on button "Save custom attributes"
    Then I should see Message "Successfully saved"
    And I click on link "Product sets"
    And I click on link "Customise email attributes"
    Then I should see the saved Custom Email Attribute setting
    And I click on button "genre"
    And I click on button "Save custom attributes"

  @PauseMailCampaign
  Scenario: De-activate Mail Campaign
    Given I goto Mail Campaign "AutoCreate"
    And I Pause Mail campaign "AutoCreate"
    Then Mail Campaign Should be Paused

  @DeleteMailCampaign
  Scenario: Deleting Mail Campaign
    Given I Delete Mail Campaign "AutoCreate copy"
    Then I should not see "AutoCreate copy"
    Given I Delete Mail Campaign "AutoCreate"
    Then I should not see "AutoCreate"

  #Error validation scenarios start here
  @createMailvalidation
  Scenario Outline: Create Mail Message Validation
    Given I click on "New campaign" option in "Mail"
    Then I should be on Mail "New campaign" page
    When I Set Name as "<Name>"
    And I click on button "Save campaign"
    Then I should see Message "<Message>"

    Examples: Validation Messages
      | Name        | Message                                    |
      |             | Name must be provided                      |
      | Auto_Create | Recommendation Algorithms must be selected |

  @duplicateNameMailCampaign
  Scenario: Error Validate Duplicate Campaign Name
    Given I Create Simple Mail Campaign with name "AutoCreate"
    And I Create Simple Mail Campaign with name "AutoCreate"
    Then I should see Message "The name you have chosen is already in use."

  @emptyEmailPreview
  Scenario: Validate Empty Email For Preview
    Given I goto Mail Campaign "AutoCreate"
    And I click on link "2. Configuration"
    And I click on button "Next"
    And I click on button " Preview Email"
    Then I should see Message "Email address required"

  @sendEmailErrorValidation
  Scenario: Validate Empty Email For Send Email
    Given I goto Mail Campaign "AutoCreate"
    And I click on link "2. Configuration"
    And I click on button "Next"
    And I click on button "Send Test Email"
    Then I should see Message "Cannot send test email"

  @EmptyEmailPlaceholder
  Scenario: Validate Empty Email Placeholder
    Given I goto Mail Campaign "AutoCreate"
    And I click on link "2. Configuration"
    And I click on button "Next"
    When I select option "Custom"
    And I Enter Text "" in Email Placeholder
    And I click on button " Generate code"

  #This needs to be checked as i've check previous deployment and this field is not mandatory
  #Then I should see Message "Email placeholder is mandatory"
  @DeleteLastCampaign
  Scenario: Deleting Mail Campaign
    Given I Delete Mail Campaign "AutoCreate"
    Then I should not see "AutoCreate"
