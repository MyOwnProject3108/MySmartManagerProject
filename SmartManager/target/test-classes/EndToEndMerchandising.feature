Feature: End to End Tests for Merchandising

  Background: Pre-requisite
    Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "demostoredev"

  @setup1
  Scenario: End to End Test With Simple Rule alongwith Master Rule
    Given I Create Simple Campaign with name "E2EAutoCreate"
    Then I click on link "2.  Master Rules"
    And I click on button "Toggle Advanced"
    Then I Set Master Rule "(r.saleprice<20)"
    And I click on button "Save Campaign"
    Then I Should See Campaign "E2EAutoCreate" on Overview Page
    And I activate Campaign "E2EAutoCreate"
    Then Campaign should be Activated

  @E2E
<<<<<<< HEAD
  Scenario: Verify merch rule on client site
    Given I navigate to URL "http://showcase-dev.peerius.com/index.php/electricals/cameras/10641945.html"
    Then I should see "producthorizontal" in the debug
    Then I should see Rule "(r.saleprice<20)" in "5" Positions
    When I navigate to URL "http://showcase-dev.peerius.com/index.php/electricals/cameras/10599547.html"
    Then I should not see Rule "(r.saleprice<20)" in "5" Positions
    
    
  @setup2
  Scenario: End to End Test With Simple Rule alongwith Master Rule
    Given I Create Simple Campaign with name "E2EAutoCreate"
    Then I click on link "3. Recommendation Rules"
    And I click on button "Add New Rule"
    Then I Add New Rule "<RuleNumber>" with Rule "<Rule>"
    And Apply Rule "<RuleNumber>" To Position "<Position>"
    And I click on button "Save Campaign"
    Then I Should See Campaign "E2EAutoCreate" on Overview Page
    And I activate Campaign "E2EAutoCreate"
    Then Campaign should be Activated
  
  Examples: Rule Positions
      | RuleNumber | Rule             | Position |
      | 2          | (r.category="Electricals>Audio" and r.pricerange="expensive") | 2        |
      
   @E2E
  Scenario: Verify merch rule on client site
    Given I navigate to URL "http://showcase-dev.peerius.com/index.php/electricals/cameras/10641945.html"
    Then I should see "producthorizontal" in the debug
    
    
  @setup
=======
  Scenario: Verify rules on client's website

  @setupproductset
>>>>>>> a0a4653a4f82bdb674be3f383bbe3b086e77a15f
  Scenario: Setup simple rule with productset for End-End scenario
    When I click on "Define Product Sets" option in "Merchandising"
    And I Create Product Set "TestSet" and products number "2" with Suffix "D"
    And I Create Simple Campaign with name "AutoCreate"
    When I click on link "3. Recommendation Rules"
    And I select option "Handpick"
    And I click on button "Edit Rule..."
    And I select option "r.productset"
    And I select operator "equals to"
    And I Enter rule Text "TestSet"
    And click on button "Save Campaign"
    Then I should see Message "Successfully saved"
    And I activate Campaign "AutoCreate"

  @end2end
  Scenario: Verify merch rule on client site
    Given I navigate to URL "http://showcase-dev.peerius.com/index.php/electricals/cameras/10641945.html"
    Then I should see "producthorizontal" in the debug
    Then I should see Rule "(r.productset="TestSet")" in "2" Positions

  @setupABgroup
  Scenario: Setup simple rule with productset for End-End scenario
    Given I Create AB Group with Details
      | Group | Page    | Widget            | Group A Percent | Group B Percent |
      | A     | Product | producthorizontal | 100             | 0               |
      
    @deactivateABgroup
    Scenario Deactivate AB Group
    
