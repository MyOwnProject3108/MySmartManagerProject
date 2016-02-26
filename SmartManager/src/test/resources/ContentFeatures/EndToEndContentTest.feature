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
      | Name                       | Placement   | CreativeName                              | Size    | Rule                 |
      | E2EAutoCampaignNoCondition | homeRecs_ip | E2EAuto Desktop Walt Disney World Tickets | default | Last category viewed |

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

  @E2EVisitorNew
  Scenario: Verify visitor=new rule is applied on the client website
    Given I navigate to URL "www.floridatix.com"
    Then I should see campaign name "E2EAutoCampaignWithRule" and Rule "(u.visitor="new")" in the debug

  @E2Erecentsitesearchkeywordsetup
  Scenario: Create Content setup for the rule recentsitesearchkeyword
    Given I goto Content Campaign "E2EAutoCampaignWithRule"
    And I select creative option "Site-search keyword (recent from URL)"
    And I select conditional operator "equals to"
    And I Enter Criteria rule Text "orlando"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"

  @E2Erecentsitesearchkeyword
  Scenario: Verify Content for the rule recentsitesearchkeyword
    Given I navigate to URL "www.floridatix.com/search?keyword=orlando"
    And I goto URL "www.floridatix.com"
    Then I should see campaign name "E2EAutoCampaignWithRule" and Rule "(u.recentsitesearchkeyword="orlando")" in the debug

  @E2Edirectsetup
  Scenario: Create Content setup for the rule direct
    Given I goto Content Campaign "E2EAutoCampaignWithRule"
    And I select creative option "Source"
    And I select conditional operator "equals to"
    And I Enter Criteria rule Text "direct"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"

  @E2Edirect
  Scenario: Verify Content for the rule Direct
    Given I navigate to URL "www.floridatix.com"
    Then I should see campaign name "E2EAutoCampaignWithRule" and Rule "(u.source="direct")" in the debug

  @E2EcountrycodeGBsetup
  Scenario: Create Content setup for the rule Countrycode GB
    Given I goto Content Campaign "E2EAutoCampaignWithRule"
    And I select creative option "Country code"
    And I select conditional operator "equals to"
    And I Enter Criteria rule Text "GB"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"

  @E2EcountrycodeGB
  Scenario: Verify Content for the rule Countrycode GB
    Given I navigate to URL "www.floridatix.com?peeriusIPSimulate=89.187.117.101"
    Then I should see campaign name "E2EAutoCampaignWithRule" and Rule "(countrycode="GB")" in the debug

  @E2EcountrycodeFRsetup
  Scenario: Create Content setup for the rule Countrycode FR
    Given I goto Content Campaign "E2EAutoCampaignWithRule"
    And I select creative option "Country code"
    And I select conditional operator "equals to"
    And I Enter Criteria rule Text "FR"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"

  @E2EcountrycodeFR
  Scenario: Verify Content for the rule Countrycode FR
    Given I navigate to URL "www.floridatix.com?peeriusIPSimulate=178.251.201.141"
    Then I should see campaign name "E2EAutoCampaignWithRule" and Rule "(countrycode="FR")" in the debug

  @E2EcountrycodeUSsetup
  Scenario: Create Content setup for the rule Countrycode US
    Given I goto Content Campaign "E2EAutoCampaignWithRule"
    And I select creative option "Country code"
    And I select conditional operator "equals to"
    And I Enter Criteria rule Text "US"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"

  @E2EcountrycodeUS
  Scenario: Verify Content for the rule Countrycode US
    Given I navigate to URL "www.floridatix.com?peeriusIPSimulate=12.25.205.51"
    Then I should see campaign name "E2EAutoCampaignWithRule" and Rule "(countrycode="US")" in the debug
    
    @E2Ebasketvaluesetup
  Scenario: Create Content setup for the rule Basketvalue
    Given I goto Content Campaign "E2EAutoCampaignWithRule"
    And I select creative option "Basket value"
    And I select conditional operator "greater than"
    And I Enter Criteria rule Text "50"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"

  @E2Ebasketvalue
  Scenario: Verify Content for the rule Basketvalue
    Given I navigate to URL "www.floridatix.com/orlando-tours/dolphin-experience-st-augustine"
    And I add the product to the basket
    And I navigate to URL "www.floridatix.com"
   Then I should see campaign name "E2EAutoCampaignWithRule" and Rule "(basketvalue>"50")" in the debug
  
  
  @E2EvisitorReturningsetup
  Scenario: Create Content setup for the rule Countrycode US
    Given I goto Content Campaign "E2EAutoCampaignWithRule"
    And I select creative option "Visitor"
    And I select conditional operator "equals to"
    And I Enter Criteria rule Text "returning"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"

  @E2EVisitorreturning
  Scenario: Verify visitor=new rule is applied on the client website
    Given I navigate to URL "www.floridatix.com"
    And I delete "test_peerius_sess" cookie
    Then I should see campaign name "E2EAutoCampaignWithRule" and Rule "(u.visitor="returning")" in the debug

  @E2Ecustomernewgsetup
  Scenario: Create Content setup for the rule Countrycode US
    Given I goto Content Campaign "E2EAutoCampaignWithRule"
    And I select creative option "Customer"
    And I select conditional operator "equals to"
    And I Enter Criteria rule Text "new"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"

  @E2Ecustomernew
  Scenario: Verify visitor=new rule is applied on the client website
    Given I navigate to URL "www.floridatix.com"
    And I delete "test_peerius_sess" cookie
    Then I should see campaign name "E2EAutoCampaignWithRule" and Rule "(u.customer="new")" in the debug
    
  #Ana's changes  
  @E2ELastViewedCategory
  Scenario: Verify Content for the rule Last Viewed Category
    Given I goto URL "www.floridatix.com/orlando-tours"
    When I goto URL "www.floridatix.com/"
    Then I should see campaign name "E2EAutoCampaignNoCondition" and Rule "(lastviewedcategory)" in the debug
    
  @E2EDefaultRule_setup
  Scenario: Create Content setup for the rule Default 
  	Given I goto Content Campaign "E2EAutoCampaignNoCondition"
  	And I select creative option "Default creative"
  	And I click on button "Save campaign"
    Then I should see Message "Successfully saved"
    
  @E2EDefaultRule
  Scenario: Verify Content for the rule Default
  	Given I navigate to URL "floridatix.com"
  	Then I should see campaign name "E2EAutoCampaignNoCondition" and Rule "(default)" in the debug
  
  @E2ESearchEngineKeywordRule_setup
  Scenario: Create Content setup for the rule SearchEngineKeyword 
  	Given I goto Content Campaign "E2EAutoCampaignNoCondition"
  	And I select creative option "Search-engine keyword (match to Tags)"
  	And I click on button "Save campaign"
  	Then I should see Message "Successfully saved"
  	
  @E2ESearchEnginekeywordRule	
  Scenario: Verify Content for the rule SearchEngineKeyword
    Given I goto URL "www.floridatix.com?peeriusTestGoogle=orlando"
    Then I should see campaign name "E2EAutoCampaignNoCondition" and Rule "(tags)" in the debug
    
  @E2EDirectRule_setup
  Scenario: Create Content setup for the rule Direct
  	Given I goto Content Campaign "E2EAutoCampaignNoCondition"
  	And I select creative option "Arrived direct to site"
  	And I click on button "Save campaign"
  	Then I should see Message "Successfully saved"
  	
  @E2EDirectRule
  Scenario: Verify Content for the rule Direct
  	Given I navigate to URL "floridatix.com"
  	Then I should see campaign name "E2EAutoCampaignNoCondition" and Rule "(direct)" in the debug
  	
  @E2ESmartRule_setup
  Scenario: Create Content setup for the rule Smart
  	Given I goto Content Campaign "E2EAutoCampaignNoCondition"
  	And I select creative option "Smart"
  	And I click on button "Save campaign"
  	Then I should see Message "Successfully saved"
  	
  @E2ESmartRule
  Scenario: Verify Content for the rule Smart
  	Given I navigate to URL "floridatix.com"
  	Then I should see campaign name "E2EAutoCampaignNoCondition" and Rule "(smart)" in the debug
  	
  @E2ECityRule_setup
  Scenario: Create Content setup for the rule City=London
  	Given I goto Content Campaign "E2EAutoCampaignWithRule"
    And I select creative option "City"
    And I select conditional operator "equals to"
    And I Enter Criteria rule Text "London"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"
    
  @E2ECityRule
  	Scenario: Verify Content for the rule City=London
  	Given I goto URL "www.floridatix.com?peeriusIPSimulate=131.228.17.26"
  	Then I should see campaign name "E2EAutoCampaignWithRule" and Rule "(city="London")" in the debug
  
  @E2EOrganicRule_setup
  Scenario: Create Content setup for the rule Organic
  	Given I goto Content Campaign "E2EAutoCampaignNoCondition"
  	And I select creative option "Arrived from organic search"
  	And I click on button "Save campaign"
  	Then I should see Message "Successfully saved"	
    
  @E2EOrganicRule
  Scenario: Verify Content for the rule Organic
  	Given I goto URL "www.bing.com"
  	And I search for "floridatix"
  	When I click on the organic link for floridatix website
  	Then I should see campaign name "E2EAutoCampaignNoCondition" and Rule "(organic)" in the debug
    
  @E2ECurrentSearchEngineKeywordRule_setup
  Scenario: Create Content setup for the rule CurrentSearchEngineKeyword 
  	Given I goto Content Campaign "E2EAutoCampaignWithRule"
    And I select creative option "Search-engine keyword (current)"
    And I select conditional operator "equals to"
    And I Enter Criteria rule Text "floridatix"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"	
    
  @E2ECurrentSearchEngineKeywordRule
  Scenario: Verify Content for the rule CurrentSearchEngineKeyword 
  	Given I goto URL "www.bing.com"
  	And I search for "floridatix"
   	When I click on the organic link for floridatix website
  	Then I should see campaign name "E2EAutoCampaignWithRule" and Rule "(currentsearchenginekeyword="floridatix")" in the debug
    
  @E2ELastSearchEngineKeywordRule_setup
  Scenario: Create Content setup for the rule LastSearchEngineKeyword 
  	Given I goto Content Campaign "E2EAutoCampaignWithRule"
    And I select creative option "Search-engine keyword (last)"
    And I select conditional operator "equals to"
    And I Enter Criteria rule Text "floridatix"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"	
    
  #failing as the rule is not followed
  @E2ELastSearchEngineKeywordRule   
  Scenario: Verify Content for the rule LastSearchEngineKeyword
  	Given I goto URL "www.bing.com"
  	And I search for "floridatix"
  	When I click on the organic link for floridatix website
  	Then I should see campaign name "E2EAutoCampaignWithRule" and Rule "(lastsearchenginekeyword="floridatix")" in the debug
  	
  #put this scenario at the end as it changes the placement for the campaign which will affect other E2E scenarios   
  @E2ECurrentCategoryViewedRule_setup
  Scenario: Create Content setup for the rule CurrentCategoryViewed
  	Given I goto Content Campaign "E2EAutoCampaignWithRule"
  	And I select placement option"categoryRecentlyViewed_ip"
  	And I select creative option "Current category viewed"
  	And I click on button "Save campaign"
  	Then I should see Message "Successfully saved"	
  	
  @E2ECurrentCategoryViewedRule
  Scenario: Verify Content for the rule CurrentCategoryViewed
  	Given I goto URL "www.floridatix.com/orlando-tours"
  	Then I should see campaign name "E2EAutoCampaignWithRule" and Rule "(currentcategory)" in the debug
    
