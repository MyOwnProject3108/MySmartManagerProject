Feature: S-Merchandising

  Background: Pre-requisite
    Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "demostoredev"

  @createsimple
  Scenario: Create Simple Merchandising Campaign
    When I click on "Create a New Campaign" option in "Merchandising"
    Then I should be on Merchandising "Create a New Campaign" page
    And I Create Simple Campaign with name "AutoCreate"
    Then I Should See Campaign "AutoCreate" on Overview Page

  Scenario: Edit Campaign
    Given I goto Campaign "AutoCreate"
    And Edit Campaign "AutoCreate"
    Then I should be on "edit" Page

  @masterrule
  Scenario: Add Master rule
    Given I goto Campaign "AutoCreate"
    Then I click on link "2. Master Rules"
    And I click on link "Toggle Advanced"
    Then I Set Master Rule "(r.saleprice<20)"
    And click on button "Save Campaign"
    Then I should see Message "Successfully saved"
    When I goto Campaign "AutoCreate"
    And I click on link "2. Master Rules"
    Then I should see Master Rule "(r.saleprice<20)"

  @positions
  Scenario Outline: Apply Rule to Campaign Positions
    Given I Create Campaign "<Campaign>" For "<Position>"
    And Edit Campaign "<Campaign>"
    When I click on link "3. Recommendation Rules"
    Then I Should Verify "<Position>"

    Examples: Rule Positions
      | Campaign            | Position |
      | AutoCreatePosition1 | 1        |
      | AutoCreatePosition2 | 2        |
      | AutoCreatePosition3 | 3        |

  @diffrules
  Scenario Outline: Different Rules At Different Positions
    Given I Create Campaign "<Campaign>" For "<Position>"
    Then I goto Campaign "<Campaign>"
    And I click on link "3. Recommendation Rules"
    And I click on button "Add New Rule"
    Then I Add New Rule "(r.saleprice<20)"

    Examples: Rule Positions
      | Campaign            | Position |
      | AutoCreatePosition1 | 1        |

  Scenario: Activate Campaign
    Given I goto Campaign "AutoCreate"
    And I activate Campaign "AutoCreate"
    Then Campaign should be Activated

  Scenario: Pause Campaign
    Given I Pause Campaign "AutoCreate"
    Then Campaign Should be Paused

  Scenario: Duplicate Campaign
    Given I goto Campaign "AutoCreate"
    And I Duplicate Campaign "AutoCreate"
    Then I Should See Campaign "AutoCreate copy" on Overview Page

  Scenario: Delete Campaign
    Given I Delete Campaign "AutoCreate copy"
    Given I Delete Campaign "AutoCreate"
    Then I should not see "AutoCreate copy"
    Then I should not see "AutoCreate"

  Scenario Outline: Message Validation
    Given I Create Campaign with "<Name>"
    Then I should see Message "<Message>"

    Examples: Validation Messages
      | Name          | Message               |
      |               | Name must be provided |
      | Test_Selenium | Recommendation Rules  |

  Scenario: Error validation for Duplicate Merchandising Campaign name
    Given I Create Simple Campaign with name "AutoCreate"
    And I Create Simple Campaign with name "AutoCreate"
    Then I should see Message "The name you have chosen is already in use"

  Scenario: Error validation for Invalid Expression
    Given I Create invalid Campaign with name "AutoCreate"
    Then I should see Message "Invalid rule"

  Scenario: Error validation for Empty Expression on Edit
    Given I Create Simple Campaign with name "AutoCreate"
    And Edit Campaign "AutoCreate"
    When I click on link "3. Recommendation Rules"
    And I click on button " Edit Rule..."
    And I Enter Text ""
    And click on button "Save Campaign"
    Then I should see Message "no expression was specified"

  Scenario: Error validation for empty preview
    Given I Create Simple Campaign with name "AutoCreate"
    And Edit Campaign "AutoCreate"
    When I click on link "5. Activation & Preview"
    When I click on link "Preview"
    Then I should preview Message "Please fill in the preview product reference code"

  
  Scenario: Error validation for empty productset name
    When I click on "Define Product Sets" option in "Merchandising"
    And I click on button "Add Product set"
    And click on button "Save Product set"
    Then I should see Message "Name is required"
    
        
   Scenario: Error validation for empty productset
    When I click on "Define Product Sets" option in "Merchandising"
    And I click on button "Add Product set"
    And I enter title "AutoproductSet"
    And click on button "Save Product set"
    Then I should see Message "An SKU set must have at least one valid product"
    
   @Validation
   Scenario: Error validation for invalid productset name
    When I click on "Define Product Sets" option in "Merchandising"
    And I click on button "Add Product set"
    And I enter title "AutoproductSet@123"
    And click on button "Save Product set"
    Then I should see Message "Name accepts alphanumeric and spaces only"
    
    @productset
    Scenario: Add Product Set
    When I click on "Define Product Sets" option in "Merchandising"
    And I Create Product Set "TestSet" and products number "2" with Suffix "D"
    Then I Should verify Product Sets "TestSet"

    
    
