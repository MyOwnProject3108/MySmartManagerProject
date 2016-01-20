Feature: End to End Tests For Smart Content

  Background: Pre-requisite
    Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "floridatix"

  #Setting up creatives and set up campaugns for E2E
  @E2E_NewCreative
  Scenario Outline: Add new creatives with all field values parameterized
    Given I click on "New creative" option in "Content"
    Then I should be on creative "Configure Creatives" page
    When I add a new creative with field values name "<Name>" image url "<ImgUrl>" link url "<LinkUrl>" category "<RelCat>" attribute "<RelAtt>" and tag "<Tag>"
    Then I should see Message "Successfully saved"

    Examples: Field values
      | Name                                      | ImgUrl                                                            | LinkUrl                                              | RelCat               | RelAtt           | Tag      |
      | E2EAuto Desktop Orlando Tours Category    | https://www.floridatix.com/images/static/home-carousel/tours.jpg  | https://www.floridatix.com/orlando-tours             | Orlando Tours        | location:orlando | orlando, |
      | E2EAuto Desktop Walt Disney World Tickets | https://www.floridatix.com/images/static/home-carousel/disney.jpg | https://www.floridatix.com/park-tickets/disney-world | Florida Park Tickets | location:orlando | disney,  |

  @E2E_CreateCampaignNoCondition
  Scenario Outline: Create Simple Content Campaign with No Condition
    When I click on "Create a campaign" option in "Content"
    Then I should be on Content "Create a campaign" page
    When I Create Simple Content Campaign "<Name>" with "<Placement>" for "<CreativeName>" of "<Size>" and "<Rule>"
    Then I should see Message "Successfully saved"

    Examples: ContentSetup
      | Name                       | Placement   | CreativeName                      | Size    | Rule                 |
      | E2EAutoCampaignNoCondition | homeRecs_ip | Desktop Walt Disney World Tickets | default | Last category viewed |

  @E2E_ActivateCampaignNoCondition
  Scenario: Activate Content Campaign
    Given I goto Content Campaign "E2EAutoCampaignNoCondition"
    And I Activate Content Campaign "E2EAutoCampaignNoCondition"
    Then Content Campaign should be Activated

  @E2E_CreateCampaignWithRule
  Scenario Outline: Create Simple Content Campaign with Condition
    When I click on "Create a campaign" option in "Content"
    Then I should be on Content "Create a campaign" page
    When I Create Simple Content Campaign "<Name>" with "<Placement>" for "<CreativeName>" of "<Size>" for "<Rule>" with "<Operator>" and "<Attribute>"
    Then I should see Message "Successfully saved"

    Examples: ContentSetup
      | Name                    | Placement   | CreativeName                           | Size    | Rule    | Operator  | Attribute |
      | E2EAutoCampaignWithRule | homeRecs_ip | E2EAuto Desktop Orlando Tours Category | default | Visitor | equals to | new       |

  @E2E_ActivateCampaignWithRule
  Scenario: Activate Content Campaign
    Given I goto Content Campaign "E2EAutoCampaignWithRule"
    And I Activate Content Campaign "E2EAutoCampaignWithRule"
    Then Content Campaign should be Activated
    
  
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	  
    
    
