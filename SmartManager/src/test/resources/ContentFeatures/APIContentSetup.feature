Feature: API Setup Tests For Smart Content

  Background: Pre-requisite
    Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "ctshirts"

  @APIaddNewCreative
  Scenario Outline: Add new creatives with all field values parameterized
    Given I click on "New creative" option in "Content"
    Then I should be on creative "Configure Creatives" page
    When I add a new creative with field values name "<Name>" image url "<ImgUrl>" link url "<LinkUrl>" category "<RelCat>" attribute "<RelAtt>" and tag "<Tag>"
    Then I should see Message "Successfully saved"

    Examples: API Field values
      | Name            | ImgUrl                                                              | LinkUrl                                                    | RelCat              | RelAtt        | Tag       |
      | APIAutoCreative | http://www.ctshirts.co.uk/Content/aw14/side-hp/peerius/Knitwear.jpg | http://www.ctshirts.co.uk/mens-casualwear/mens-knitwear?q= | casualwear>knitwear | pattern:plain | knitwear, |

  @APICreateCampaignWithRule
  Scenario Outline: Create Simple Content Campaign with Condition
    When I click on "New campaign" option in "Content"
    Then I should be on Content "Create a campaign" page
    When I Create Simple Content Campaign "<Name>" with "<Placement>" for "<CreativeName>" of "<Size>" for "<Rule>" with "<Operator>" and "<Attribute>"
    Then I should see Message "Successfully saved"

    Examples: APIContentSetup
      | Name                    | Placement    | CreativeName    | Size    | Rule    | Operator  | Attribute |
      | APIAutoCampaignWithRule | Home_JSON_IP | APIAutoCreative | default | Visitor | equals to | new       |

  @APIActivateCampaignWithRule
  Scenario: Activate Content Campaign
    Given I goto Content Campaign "APIAutoCampaignWithRule"
    And I Activate Content Campaign "APIAutoCampaignWithRule"
    Then Content Campaign "APIAutoCampaignWithRule" should be Activated

  @APISourcedirectsetup
  Scenario: Create Content setup for the rule direct
    Given I goto Content Campaign "APIAutoCampaignWithRule"
    And I select creative option "Source"
    And I select conditional operator "equals to"
    And I Enter Criteria rule Text "direct"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"

  @APISourceAffiliatesetup
  Scenario: Create Content setup for the rule direct
    Given I goto Content Campaign "APIAutoCampaignWithRule"
    And I select creative option "Source"
    And I select conditional operator "equals to"
    And I Enter Criteria rule Text "affiliate"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"

  @APIcountrycodeGBsetup
  Scenario: Create Content setup for the rule Countrycode GB
    Given I goto Content Campaign "APIAutoCampaignWithRule"
    And I select creative option "Country code"
    And I select conditional operator "equals to"
    And I Enter Criteria rule Text "GB"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"

  @APIcountrycodeFRsetup
  Scenario: Create Content setup for the rule Countrycode FR
    Given I goto Content Campaign "APIAutoCampaignWithRule"
    And I select creative option "Country code"
    And I select conditional operator "equals to"
    And I Enter Criteria rule Text "FR"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"

  @APIcountrycodeUSsetup
  Scenario: Create Content setup for the rule Countrycode US
    Given I goto Content Campaign "APIAutoCampaignWithRule"
    And I select creative option "Country code"
    And I select conditional operator "equals to"
    And I Enter Criteria rule Text "US"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"

  @APIDefaultcreativesetup
  Scenario: Create Content setup for the rule Default
    Given I goto Content Campaign "APIAutoCampaignWithRule"
    And I select creative option "Default creative"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"

  @APILastcategoryviewedsetup
  Scenario: Create Content setup for the rule Default
    Given I goto Content Campaign "APIAutoCampaignWithRule"
    And I select creative option "Last category viewed"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"

  @APIPurchasehistorybycategorysetup
  Scenario: Create Content setup for the rule Default
    Given I goto Content Campaign "APIAutoCampaignWithRule"
    And I select creative option "Purchase history by category"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"

  @APIPurchasehistorybyattributesysetup
  Scenario: Create Content setup for the rule Default
    Given I goto Content Campaign "APIAutoCampaignWithRule"
    And I select creative option "Purchase history by attributes"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"
