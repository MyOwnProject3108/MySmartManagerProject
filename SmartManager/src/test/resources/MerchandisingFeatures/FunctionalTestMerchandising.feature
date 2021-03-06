Feature: S-Merchandising Functional Tests

  Background: Pre-requisite
    Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "demostoredev"

  @createsimple
  Scenario: Create Simple Merchandising Campaign
    When I click on "New campaign" option in "Merchandising"
    Then I should be on Merchandising "New campaign" page
    And I Create Simple Campaign with name "AutoCreate"
    Then I should see Message "Successfully saved"
    Then I Should See Campaign "AutoCreate" on Overview Page

  @editcampaign
  Scenario: Edit Campaign
    Given I goto Campaign "AutoCreate"
    And Edit Campaign "AutoCreate"
    Then I should be on "edit" Page

  @masterrule
  Scenario: Add Master rule
    Given I goto Campaign "AutoCreate"
    Then I click on link "2. Master rules"
    And I click on link "Toggle advanced"
    Then I Set Master Rule "(r.saleprice<20)"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"
    When I goto Campaign "AutoCreate"
    And I click on link "2. Master rules"
    Then I should see Master Rule "(r.saleprice<20)"

  @positions
  Scenario Outline: Apply Rule to Campaign Positions
    Given I Create Campaign "<Campaign>" For "<Position>"
    Then I should see Message "Successfully saved"
    And Edit Campaign "<Campaign>"
    When I click on link "3. Recommendation rules"
    Then I Should Verify "<Position>"

    Examples: Rule Positions
      | Campaign            | Position |
      | AutoCreatePosition1 | 1        |
      | AutoCreatePosition2 | 2        |


  @diffrules
  Scenario Outline: Different Rules At Different Positions
    Given I goto Campaign "AutoCreate"
    And I click on link "3. Recommendation rules"
    And I click on button "Add new rule"
    Then I Add New Rule "<RuleNumber>" with Rule "<Rule>"
    And Apply Rule "<RuleNumber>" To Position "<Position>"
    Given I goto Campaign "AutoCreate"
    And I click on link "Recommendation rules"
    Then I Should Verify Rule  "<RuleNumber>" at "<Position>"

    Examples: Rule Positions
      | RuleNumber | Rule             | Position |
      | 2          | (r.saleprice<20) | 4       |
   #   | 3          | (r.saleprice<20) | 3        |

  @selectrule
  Scenario: Create Simple Merchandising Campaign using select list for the rules
    Given I goto Campaign "AutoCreate"
    And Edit Campaign "AutoCreate"
    When I click on link "3. Recommendation rules"
    And I click on button "Edit rule..."
    And I select option "r.colour"
    And I select operator "equals to"
    And I Enter rule Text "black"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"
    When I goto Campaign "AutoCreate"
    When I click on link "3. Recommendation rules"
    And I click on button "Edit expression for this rule"
    Then I should see Rule "(r.colour="black")" on Position "1"

  @productsetrule
  Scenario: Create Simple Merchandising Campaign using productset as a rule
    When I click on "Product sets" option in "Merchandising"
    And I Create Product Set "TestSet" and products number "2" with Suffix "D"
    Given I goto Campaign "AutoCreate"
    And Edit Campaign "AutoCreate"
    When I click on link "3. Recommendation rules"
    And I click on button "Edit expression"
    And I select option "Product set"
    And I select operator "equals to"
    And I Enter rule Text "TestSet"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"
    When I goto Campaign "AutoCreate"
    When I click on link "3. Recommendation rules"
    Then I should see Rule "(r.productset="TestSet")" on Position "1"

  @deleteproductsetusedinrule
  Scenario: Delete Product Set with is used as a rule in merch campaign
    When I click on "Product sets" option in "Merchandising"
    And I click Delete On Product Set "TestSet"
    Then I should see Message "SKU set is in use and cannot be deleted"

  @activecamp
  Scenario: Activate Campaign
    Given I goto Campaign "AutoCreate"
    And I activate Campaign "AutoCreate"
    Then Campaign should be Activated

  @duplicatecamp
  Scenario: Duplicate Campaign
    Given I goto Campaign "AutoCreate"
    And I Duplicate Campaign "AutoCreate"
    Then I Should See Campaign "AutoCreate copy" on Overview Page

  @createCampaignWithNoRule
  Scenario: Create a simple campaign without adding any rule
    When I click on "New campaign" option in "Merchandising"
    Then I should be on Merchandising "Create a New Campaign" page
    When I create simple camapign "AutoCreateNoRule" with no rule
    When I click on link "4. Exclusions"
    And I add "10649631" for exclusion
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"
    Then I Should See Campaign "AutoCreateNoRule" on Overview Page

  @pausecamp
  Scenario: Pause Campaign
    Given I Pause Campaign "AutoCreate"
    Then Campaign Should be Paused

  @deletecamp
  Scenario: Delete Campaign
    Given I Delete Campaign "AutoCreate copy"
    Given I Delete Campaign "AutoCreate"
    Given I Delete Campaign "AutoCreatePosition1"
    Given I Delete Campaign "AutoCreatePosition2"
    Given I Delete Campaign "AutoCreateNoRule"
    Then I should not see "AutoCreate copy"
    Then I should not see "AutoCreate"
    Then I should not see "AutoCreatePosition1"
    Then I should not see "AutoCreatePosition2"
    Then I should not see "AutoCreateNoRule"

  #Error validation starts here
  @createcapvalidation
  Scenario Outline: Message Validation
    Given I Create Campaign with "<Name>"
    Then I should see Message "<Message>"

    Examples: Validation Messages
      | Name        | Message               |
      |             | Name must be provided |
      | Auto_Create | Recommendation Rules  |

  @duplicatenamecamp
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
    When I click on link "3. Recommendation rules"
    And I click on button "Edit expression for this rule"
    And I Enter Text ""
    And I click on button "Save campaign"
    Then I should see Message "no expression was specified"

  @invalidmasterrule
  Scenario: Error message validation for invalid Master rule
    Given I Create Simple Campaign with name "AutoCreate"
    And Edit Campaign "AutoCreate"
    Then I click on link "2. Master rules"
    And I click on link "Toggle advanced"
    Then I Set Master Rule "(p.fit)"
    And I click on button "Save campaign"
    Then I should see Message "a valid expression should be provided"

  @emptyrefpreview
  Scenario: Error validation for empty product ref codes for preview
    Given I Create Simple Campaign with name "AutoCreate"
    And Edit Campaign "AutoCreate"
    When I click on link "5. Activation & Preview"
    When I click on link "Preview"
    Then I should preview Message "Please fill in the preview product reference code"

  @merchpreview
  Scenario: Merchandising Preview Product Refcode
    Given I goto Campaign "AutoCreate"
    And Edit Campaign "AutoCreate"
    When I click on link "Activation & Preview"
    And Select Preview Products "2" with Ref "A"
    And Select Preview Category "Tops"
    When I click on link "Preview"
    Then I Should See Preview with "2" Products
    And I click on button "Save campaign"

  @emptyskuname
  Scenario: Error validation for empty productset name
    When I click on "Product sets" option in "Merchandising"
    And I click on link "Create a product set"
    And I click on button "Save product set"
    Then I should see Message "Name is required"

  @emptysku
  Scenario: Error validation for empty productset
    When I click on "Product sets" option in "Merchandising"
    And I click on link "Create a product set"
    And I enter title "AutoproductSet"
    And I click on button "Save product set"
    Then I should see Message "An SKU set must have at least one valid product"

  @productsetvalidation
  Scenario: Error validation for invalid productset name
    When I click on "Product sets" option in "Merchandising"
    And I click on link "Create a product set"
    And I enter title "AutoproductSet@123"
    And I click on button "Save product set"
    Then I should see Message "Name accepts alphanumeric and spaces only"

  @productset
  Scenario: Add Product Set
    When I click on "Product sets" option in "Merchandising"
    And I Create Product Set "TestSet" and products number "2" with Suffix "D"
    Then I Should verify Product Sets "TestSet"

  @delete
  Scenario: Delete Product Set with simple rule
    Given I Delete Product Set "TestSet"
    Then Product Set "TestSet" Should be Deleted

  @clearAllTags
  Scenario: Save productset by clearing products from ProductSet
    When I click on "Product sets" option in "Merchandising"
    And I Create Product Set "TestSet" and products number "2" with Suffix "D"
    And I click Edit On Product Set "TestSet"
    Then I click on link "Clear All Tags"
    And I click on button "Save product set"
    Then I should see Message "An SKU set must have at least one valid product"

  @editProductSet
  Scenario: Edit productset
    Given I goto ProductSet "TestSet"
    And I click Edit On Product Set "TestSet"
    Then I click on link "Clear All Tags"
    And I Add "1" Products with Suffix "A"
    Then I should see "1" products in "TestSet"

  @copyEditProductSet
  Scenario: Duplicate and Edit productset
    Given I Duplicate ProductSet "TestSet"
    And I click Edit On Product Set "TestSet copy"
    Then I click on link "Clear All Tags"
    And I Add "1" Products with Suffix "dora"
    Then I should see "1" products in "TestSet copy"

  @skuduplicate
  Scenario: Error message validation for duplicate sku
    When I click on "Product sets" option in "Merchandising"
    And I Create Product Set "TestSet" and products number "2" with Suffix "D"
    Then I Should verify Product Sets "TestSet"
    And I Create Product Set "TestSet" and products number "2" with Suffix "D"
    Then I should see Message "The name you have chosen is already in use"
    Given I Delete Product Set "TestSet"
    And I Delete Product Set "TestSet copy"
    Then Product Set "TestSet" Should be Deleted
    Then Product Set "TestSet" Should be Deleted

  @campaignsuccessmsg
  Scenario: Success message validation for setup campaign
    When I click on "New campaign" option in "Merchandising"
    Then I should be on Merchandising "Create a New Campaign" page
    And I Create Simple Campaign with name "AutoCreateSuccess"
    Then I should see Message "Successfully saved"

  @campaigndeletemsg
  Scenario: Success message validation for delete campaign
   	Given I Create Simple Campaign with name "AutoCreateDelete"
   	When I Delete Campaign "AutoCreateDelete"
   	Then I should not see "AutoCreateDelete"
    Given I Delete Campaign "AutoCreateSuccess"
    Then I should not see "AutoCreateSuccess"
    And I Delete Campaign "AutoCreate"
    Then I should not see "AutoCreate"
    
  
