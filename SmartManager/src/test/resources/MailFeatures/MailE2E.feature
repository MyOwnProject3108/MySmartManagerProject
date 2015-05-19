Feature: End to End Tests for Mail

  Background: Pre-requisite
    Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "wallis"

  @SetupE2ESimpleCampaign
  Scenario: Setup to send random email for a simple campaign
    And I Create Simple Mail Campaign with name "E2EAutoCreate"
    And I click on link "2. Configuration"
    And I click on button "Next"
    And I Specify random Email address as "Peeriustest"
    And click on button " Send Test Email"
    Then I should see Message "Successfully saved."

  @E2ESimpleCampaign
  Scenario: E2E S-Mail test for simple campaign
    Given I Goto Random Inbox
    And I click on the Email "Our Recommendations"
    And Switch Frame "rendermail"
    And I click on link "Click here for debug information"
    Then I should see GeneratioStrategy "Best Sellers by Conversion (last 90 days)" in "1" Positions

  @E2ESetupMultipleSrategy
  Scenario: Setup to send random email campaign for mutlipe strategy
    Given I goto Mail Campaign "E2EAutoCreate"
    And I click on link "2. Configuration"
    And I Set "New Products(last 30 days)" at position "1"
    When I Set the Number of Products as "2"
    And I Set "Best Seller By Revenue in Smart Category" at position "2"
    And I click on button "Next"
    And I Specify random Email address as "Peeriustest"
    And click on button " Send Test Email"
    Then I should see Message "Successfully saved."

  @E2EMultipleStrategy
  Scenario: E2E S-Mail test using multiple strategy
    Given I Goto Random Inbox
    And I click on the Email "Our Recommendations"
    And Switch Frame "rendermail"
    And I click on link "Click here for debug information"
    Then I should see GeneratioStrategy "New Products(last 30 days)" in "1" Positions
    Then I should see GeneratioStrategy "Best Seller By Revenue in Smart Category" in "1" Positions

  @SetupE2ENewproductHint
  Scenario: Setup E2E S-Mail campaign using Hint new-product
    Given I goto Mail Campaign "E2EAutoCreate"
    And I click on link "2. Configuration"
    When I Set the Number of Products as "1"
    And I Set "Product Catalog" at position "1"
    And I click on link "Hints"
    And I select option "new-product"
    And I Enter Hint Parameter Text as "30" in "1" Positions
    And I click on button "Next"
    And I Specify random Email address as "Peeriustest"
    And click on button " Send Test Email"
    Then I should see Message "Successfully saved."

  @E2ENewproductHint
  Scenario: E2E S-Mail test for Hint new-product
    Given I Goto Random Inbox
    And I click on the Email "Our Recommendations"
    And Switch Frame "rendermail"
    And I click on link "Click here for debug information"
    Then I should see Debug hint "30 (new-product)"

  @SetupE2ENoSaleProductHint
  Scenario: Setup E2E S-Mail campaign using Hint no-sale-product
    Given I goto Mail Campaign "E2EAutoCreate"
    And I click on link "2. Configuration"
    And I Set "Product Catalog" at position "1"
    And I click on link "Hints"
    And I select option "no-sale-product"
    And I click on button "Next"
    And I Specify random Email address as "Peeriustest"
    And click on button " Send Test Email"
    Then I should see Message "Successfully saved."

  @E2ENoSaleProductHint
  Scenario: E2E S-Mail test for Hint no-sale-product
    Given I Goto Random Inbox
    And I click on the Email "Our Recommendations"
    And Switch Frame "rendermail"
    And I click on link "Click here for debug information"
    Then I should see Debug hint "no-sale-product"

  @SetupE2EComplexExpression
  Scenario Outline: Setup E2E S-Mail campaign using complex expression
    Given I goto Mail Campaign "E2EAutoCreate"
    And I click on link "2. Configuration"
    And I Set "Product Catalog" at position "1"
    And I click on link "Expression"
    And I click on link "Toggle Advanced"
    Then I Set Rule for "<Expression>"
    And I click on link "Hints"
    And I select option "Please Select a Hint"
    And I click on button "Next"
    And I Specify random Email address as "Peeriustest"
    And click on button " Send Test Email"
    Then I should see Message "Successfully saved."

    Examples: Expression
      | Expression                                                         |
      | (r.category="Dresses" or r.colour="black") and (r.saleprice>="10") |

  @E2EComplexExpression
  Scenario: E2E S-Mail test for Complex Expression
    Given I Goto Random Inbox
    And I click on the Email "Our Recommendations"
    And Switch Frame "rendermail"
    And I click on link "Click here for debug information"
    Then I should see Debug hint "(r.category="Dresses" or r.colour="black") and (r.saleprice>="10")"

  @SetupE2EManualSelect
  Scenario: Setup E2E S-Mail campaign using Manual Select Strategy
    Given I goto Mail Campaign "E2EAutoCreate"
    And I click on link "2. Configuration"
    And I Set "Manual Select" at position "1"
    And I click on link "Expression"
    And I select option "Category"
    And I select operator as "equals to"
    And I Enter rule Text as "Clothing>Dresses>Dresses"
    And I click on link "Hints"
    And I select option "category"
    And I Enter Hint Parameter Text as "Clothing>Dresses>Dresses" in "1" Positions
    And I click on button "Next"
    And I Specify random Email address as "Peeriustest"
    And click on button " Send Test Email"
    Then I should see Message "Successfully saved."

  @E2EManualSelect
  Scenario: E2E S-Mail test for Manual Select Strategy
    Given I Goto Random Inbox
    And I click on the Email "Our Recommendations"
    And Switch Frame "rendermail"
    And I click on link "Click here for debug information"
    Then I should see GeneratioStrategy "Manual Select" in "1" Positions

  @SetupE2EFallbackProductSet
  Scenario: Setup E2E S-Mail test for Fallback Productset
    When I click on "Define Product Sets" option in "Mail"
    And I Create Product Set "TestproductSetE2E" and products number "5" with Suffix "Dress"
    Given I goto Mail Campaign "E2EAutoCreate"
    And I click on link "2. Configuration"
    And I Set "Cross-sell, previous purchases and views" at position "1"
    And I search for "TestproductSetE2E" Fallback ProductSet
    And I click on button "Next"
    And I Specify random Email address as "Peeriustest"
    And click on button " Send Test Email"
    Then I should see Message "Successfully saved."

  @E2EFallbackProductSet
  Scenario: E2E S-Mail test for Fallback Productset
    Given I Goto Random Inbox
    And I click on the Email "Our Recommendations"
    And Switch Frame "rendermail"
    And I click on link "Click here for debug information"
    Then I should see GeneratioStrategy "Product Set" in "1" Positions
    Given I Delete Mail Campaign "E2EAutoCreate"
    Then I should not see "E2EAutoCreate"
    Given I Delete Product Set "TestproductSetE2E"
    Then Product Set "TestproductSetE2E" Should be Deleted
