Feature: End to End Tests For Smart Content

  Background: Pre-requisite
    Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "floridatix"

  @E2ENewCreative
  Scenario Outline: Add new creatives with all field values parameterized
    Given I click on "New creative" option in "Content"
    Then I should be on creative "Configure Creatives" page
    When I add a new creative with field values name "<Name>" image url "<ImgUrl>" link url "<LinkUrl>" category "<RelCat>" attribute "<RelAtt>" and tag "<Tag>"
    Then I should see Message "Successfully saved"

    Examples: Field values
      | Name        				              | ImgUrl                                                            | LinkUrl                                              | RelCat               | RelAtt           | Tag     |
      | E2EAuto Desktop Orlando Tours Category    | https://www.floridatix.com/images/static/home-carousel/tours.jpg  | https://www.floridatix.com/orlando-tours 			 | Orlando Tours        | location:orlando | orlando,|
      | E2EAuto Desktop Walt Disney World Tickets | https://www.floridatix.com/images/static/home-carousel/disney.jpg | https://www.floridatix.com/park-tickets/disney-world | Florida Park Tickets | location:orlando | disney, |
