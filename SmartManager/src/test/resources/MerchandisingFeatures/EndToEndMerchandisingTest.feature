Feature: End to End Tests for Merchandising

  Background: Pre-requisite
    Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "demostoredev"

  @setupMaster
  Scenario: End to End Test Set-up for Simple Rule alongwith Master Rule
    Given I Create Simple Campaign with name "E2EMasterRule"
    Then I should see Message "Successfully saved"
    When I goto Campaign "E2EMasterRule"
    Then I click on link "Master rules"
    And I click on link "Toggle advanced"
    Then I Set Master Rule "(p.saleprice<"20")"
    And I click on button "Save campaign"
    Then I Should See Campaign "E2EMasterRule" on Overview Page
    And I activate Campaign "E2EMasterRule"
    Then Campaign should be Activated

  @e2eMaster
  Scenario: Verify master rule alongwith simple rule on client site
    Given I navigate to URL "http://showcase-dev.peerius.com/index.php/electricals/accessories/10364791.html"
    Then I should see "producthorizontal" in the debug
    Then I should see Rule "(r.pricerange="expensive")" in "5" Positions
    When I navigate to URL "http://showcase-dev.peerius.com/index.php/electricals/cameras/10599547.html"
    Then I should see Rule "(r.pricerange="expensive")" in "0" Positions

  @deleteE2EMaster
  Scenario: Delete E2E Data
    Given I Delete Campaign "E2EMasterRule"
    Then I should not see "E2EMasterRule"

  @setupComplex
  Scenario Outline: End to End Test Set-up for Simple Rule alongwith AND Rule
    Given I Create Simple Campaign with name "E2EComplex"
    And I click on button "Add new rule"
    Then I Add New Rule "<RuleNumber>" with Rule "<Rule>"
    And Apply Rule "<RuleNumber>" To Position "<Position>"
    And I click on button "Save campaign"
    Then I Should See Campaign "E2EComplex" on Overview Page
    And I activate Campaign "E2EComplex"
    Then Campaign should be Activated

    Examples: Rule Positions
      | RuleNumber | Rule                                   | Position |
      | 2          | (r.category="DVD" and r.saleprice<"6") | 2        |

  @e2eComplex
  Scenario: Verify Simple Rule alongwith AND Rule on client site
    Given I navigate to URL "http://showcase-dev.peerius.com/index.php/electricals/cameras/10641945.html"
    Then I should see "producthorizontal" in the debug
    Then I should see Rule "(r.pricerange="expensive")" in "3" Positions
    And I should see Rule "(r.category="DVD" and r.saleprice<"6")" in "1" Positions

  @deleteE2eComplex
  Scenario: Delete E2E Data
    Given I Delete Campaign "E2EComplex"
    Then I should not see "E2EComplex"

  @setupproductset
  Scenario: Setup simple rule with productset for End-End scenario
    When I click on "Product sets" option in "Merchandising"
    And I Create Product Set "TestSetE2E" and products number "2" with Suffix "D"
    And I Create Simple Campaign with name "E2EProductSet"
    And I select option "Handpick"
    And I select option "Product set"
    And I select operator "equals to"
    And I Enter rule Text "TestSetE2E"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"
    And I activate Campaign "E2EProductSet"

  @end2endProduct
  Scenario: Verify merch rule on client site
    Given I navigate to URL "http://showcase-dev.peerius.com/index.php/electricals/cameras/10641945.html"
    Then I should see "producthorizontal" in the debug
    Then I should see Rule "(r.productset="TestSetE2E")" in "2" Positions

  @deleteProductSet
  Scenario: Delete E2E Data
    Given I Delete Campaign "E2EProductSet"
    Then I should not see "E2EProductSet"
    Given I Delete Product Set "TestSetE2E"
    Then I should not see "TestSetE2E"

  @setupABgroup
  Scenario: Activate AB group
    Given I Create AB Group with Details
      | Group | Page    | Widget            | Group A Percent | Group B Percent |
      | A     | Product | producthorizontal | 100             | 0               |
    And I Create Simple Campaign with name "E2EAutoABCreate"
    And I select option "Sale Price"
    And I select operator "less than"
    And I Enter rule Text "10"
    And I click on button "Save campaign"
    And I activate Campaign "E2EAutoABCreate"

  @end2endABGroup
  Scenario: Verify merch rule on client site
    Given I navigate to URL "http://showcase-dev.peerius.com/index.php/10035099.html"
    Then I should see "producthorizontal" in the debug
    Then I should see Rule "(r.saleprice<"10")" in "5" Positions

  @deleteE2eABGroup
  Scenario: Delete E2E Data
    Given I Delete Campaign "E2EAutoABCreate"

  @deactivateABgroup
  Scenario: Deactivate AB Group
    Given I goto URL "/shop-admin/abtesting/abtests.page"
    And I deactivate AB group

  @E2EInactiveCampaign
  Scenario: Rules should not be applied if campaign is not active
    Given I Create Simple Campaign with name "E2EAutoCreate1"
    And I navigate to URL "http://showcase-dev.peerius.com/index.php/electricals/accessories/10364791.html"
    Then I should see Rule "(r.pricerange="expensive")" in "0" Positions

  @deleteInActiveCampaign
  Scenario: Delete E2E Data
    Given I Delete Campaign "E2EAutoCreate1"
    Then I should not see "E2EAutoCreate1"
