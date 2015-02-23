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

  @editcampaign
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
    Then I Add New Rule "2" with Rule "(r.saleprice<20)"

    Examples: Rule Positions
      | Campaign            | Position |
      | AutoCreatePosition1 | 1        |

  @selectrule
  Scenario: Create Simple Merchandising Campaign using select list for the rules
    Given I goto Campaign "AutoCreate"
    And Edit Campaign "AutoCreate"
    When I click on link "3. Recommendation Rules"
    And I click on button "Edit Rule..."
    And I select option "r.colour"
    And I select operator "equals to"
    And I Enter rule Text "black"
    And click on button "Save Campaign"
    Then I should see Message "Successfully saved"
    When I goto Campaign "AutoCreate"
    When I click on link "3. Recommendation Rules"
    Then I should see Rule "(r.colour="black")" on Position "1"
    
    @productsetrule
  Scenario: Create Simple Merchandising Campaign using productset as a rule
  	When I click on "Define Product Sets" option in "Merchandising"
    And I Create Product Set "TestSet" and products number "2" with Suffix "D"
    Given I goto Campaign "AutoCreate"
    And Edit Campaign "AutoCreate"
    When I click on link "3. Recommendation Rules"
    And I click on button "Edit Rule..."
    And I select option "r.productset"
    And I select operator "equals to"
    And I Enter rule Text "TestSet"
    And click on button "Save Campaign"
    Then I should see Message "Successfully saved"
    When I goto Campaign "AutoCreate"
    When I click on link "3. Recommendation Rules"
    Then I should see Rule "(r.colour="black")" on Position "1"
    

  @activecamp
  Scenario: Activate Campaign
    Given I goto Campaign "AutoCreate"
    And I activate Campaign "AutoCreate"
    Then Campaign should be Activated

  @pausecamp
  Scenario: Pause Campaign
    Given I Pause Campaign "AutoCreate"
    Then Campaign Should be Paused

  @duplicamp
  Scenario: Duplicate Campaign
    Given I goto Campaign "AutoCreate"
    And I Duplicate Campaign "AutoCreate"
    Then I Should See Campaign "AutoCreate copy" on Overview Page

  @deletecamp
  Scenario: Delete Campaign
    Given I Delete Campaign "AutoCreate copy"
    Given I Delete Campaign "AutoCreate"
    Given I Delete Campaign "AutoCreatePosition1"
    Given I Delete Campaign "AutoCreatePosition2"
    Given I Delete Campaign "AutoCreatePosition3"
    Then I should not see "AutoCreate copy"
    Then I should not see "AutoCreate"
    Then I should not see "AutoCreatePosition1"
    Then I should not see "AutoCreatePosition2"
    Then I should not see "AutoCreatePosition3"

  @validation
  Scenario Outline: Message Validation
    Given I Create Campaign with "<Name>"
    Then I should see Message "<Message>"

    Examples: Validation Messages
      | Name          | Message               |
      |               | Name must be provided |
      | Test_Selenium | Recommendation Rules  |

  @duplicampaign
  Scenario: Error validation for Duplicate Merchandising Campaign name
    Given I Create Simple Campaign with name "AutoCreate"
    And I Create Simple Campaign with name "AutoCreate"
    Then I should see Message "The name you have chosen is already in use"

  @invalidexp
  Scenario: Error validation for Invalid Expression
    Given I Create invalid Campaign with name "AutoCreate"
    Then I should see Message "Invalid rule"

  @emptyexp
  Scenario: Error validation for Empty Expression on Edit
    Given I Create Simple Campaign with name "AutoCreate"
    And Edit Campaign "AutoCreate"
    When I click on link "3. Recommendation Rules"
    And I click on button "Edit Rule..."
    And I Enter Text ""
    And click on button "Save Campaign"
    Then I should see Message "no expression was specified"

  @invalidmasterrule
  Scenario: Error message validation for invalid Master rule
    Given I Create Simple Campaign with name "AutoCreate"
    And Edit Campaign "AutoCreate"
    Then I click on link "2. Master Rules"
    And I click on link "Toggle Advanced"
    Then I Set Master Rule "(p.fit)"
    And click on button "Save Campaign"
    Then I should see Message "a valid expression should be provided"

  @emptyrefpreview
  Scenario: Error validation for empty product ref codes for preview
    Given I Create Simple Campaign with name "AutoCreate"
    And Edit Campaign "AutoCreate"
    When I click on link "5. Activation & Preview"
    When I click on link "Preview"
    Then I should preview Message "Please fill in the preview product reference code"

  @emptyskuname
  Scenario: Error validation for empty productset name
    When I click on "Define Product Sets" option in "Merchandising"
    And I click on button "Add Product set"
    And click on button "Save Product set"
    Then I should see Message "Name is required"

  @emptysku
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

  @delete
  Scenario: Delete Product Set
    Given I Delete Product Set "TestSet"
    Then Product Set "TestSet" Should be Deleted

  @skuduplicate
  Scenario: Error message validation for duplicate sku
    When I click on "Define Product Sets" option in "Merchandising"
    And I Create Product Set "TestSet" and products number "2" with Suffix "D"
    Then I Should verify Product Sets "TestSet"
    And I Create Product Set "TestSet" and products number "2" with Suffix "D"
    Then I should see Message "The name you have chosen is already in use"

  @campaignsuccessmsg
  Scenario: Success message validation for setup campaign
    When I click on "Create a New Campaign" option in "Merchandising"
    Then I should be on Merchandising "Create a New Campaign" page
    And I Create Simple Campaign with name "AutoCreate"
    Then I should see Message "Successfully saved"

  @campaigndeletemsg
  Scenario: Success message validation for delete campaign
    When I click on "Create a New Campaign" option in "Merchandising"
    And I Create Simple Campaign with name "AutoCreate"
    Given I Delete Campaign "AutoCreate"
    Then I should see Message "Successfully deleted"
