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
    
    @E2ESetupMultipleSrategy
    Scenario: Setup to send random email for a simple campaign
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
    Scenario:E2E S-Mail test for Fallback Productset
    Given I Goto Random Inbox
    And I click on the Email "Our Recommendations"
    And Switch Frame "rendermail"
    And I click on link "Click here for debug information"
    Then I should see GeneratioStrategy "New Products(last 30 days)" in "1" Positions
    Then I should see GeneratioStrategy "Best Seller By Revenue in Smart Category" in "1" Positions
    
    
  @E2ESetupProductSetRule
  Scenario: Setup S-Mail campaign with productset rule
    When I click on "Define Product Sets" option in "Mail"
    And I Create Product Set "TestproductSetE2E" and products number "1" with Suffix "WL311337007"
    Given I goto Mail Campaign "E2EAutoCreate"
    And I click on link "2. Configuration"
    And I Set "Product Catalog" at position "1"
    And I Enable User-Top ups
    And I click on link "Expression"
    And I select option "r.productset"
    And I select operator as "equals to"
    And I Enter rule Text as "TestproductSetE2E"
    And I click on button "Next"
    And I Specify random Email address as "Peeriustest"
    And click on button " Send Test Email"
    Then I should see Message "Successfully saved."
    
    @E2EProductSetRule
    Scenario: E2E S-Mail campaign with productset rule
    Given I Goto Random Inbox
    And I click on the Email "Our Recommendations"
    And Switch Frame "rendermail"
    And I click on link "Click here for debug information"
    Then I should see GeneratioStrategy "Best Sellers by Units (last 7 days)" in "1" Positions
    Then I should see Rule "(r.productset="TestproductSetE2E")" in "1" Positions
        
    
  @SetupE2EFallbackProductSet
  Scenario: Setup E2E S-Mail test for Fallback Productset
    Given I goto Mail Campaign "E2EAutoCreate"
    And I click on link "2. Configuration"
    And I Set "Cross-sell, previous purchases and views" at position "1"
    And I search for "TestproductSetE2E" Fallback ProductSet
    And I click on button "Next"
    And I Specify random Email address as "Peeriustest"
    And click on button " Send Test Email"
    Then I should see Message "Successfully saved."
    
    @E2EFallbackProductSet
    Scenario:E2E S-Mail test for Fallback Productset
    Given I Goto Random Inbox
    And I click on the Email "Our Recommendations"
    And Switch Frame "rendermail"
    And I click on link "Click here for debug information"
    Then I should see GeneratioStrategy "Product Set" in "1" Positions
   

     

    
    
    
    
    
    
     
     
