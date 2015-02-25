Feature: End to End Tests for Merchandising

  Background: Pre-requisite
    Given I login as "Zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "demostoredev"

@setup
  Scenario: End to End Test With Simple Rule alongwith Master Rule
  	Given I Create Simple Campaign with name "E2EAutoCreate"
    Then I click on Link "2.  Master Rules"
    And I click on button "Toggle Advanced"
    Then I Set Master Rule "(r.saleprice<20)"
    And I click on button "Save Campaign"
    Then I Should See Campaign "E2EAutoCreate" on Overview Page
    And I activate Campaign "E2EAutoCreate"
    Then Campaign should be Activated
     
 @E2E
  Scenario: Verify rules on client's website
