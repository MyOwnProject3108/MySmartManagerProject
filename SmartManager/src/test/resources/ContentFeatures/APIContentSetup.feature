Feature: API Setup Tests For Smart Content

  Background: Pre-requisite
    Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "ctshirts"

  @APIaddNewCreative
  Scenario Outline: Add new creatives with all field values parameterized for API Setup
    Given I click on "New creative" option in "Content"
    Then I should be on creative "Configure Creatives" page
    When I add a new creative with field values name "<Name>" image url "<ImgUrl>" link url "<LinkUrl>" category "<RelCat>" attribute "<RelAtt>" and tag "<Tag>"
    Then I should see Message "Successfully saved"

    Examples: API Field values
      | Name            | ImgUrl                                                              | LinkUrl                                                    | RelCat              | RelAtt        | Tag       |
      | APIAutoCreative | http://www.ctshirts.co.uk/Content/aw14/side-hp/peerius/Knitwear.jpg | http://www.ctshirts.co.uk/mens-casualwear/mens-knitwear?q= | casualwear>knitwear | pattern:plain | knitwear, |

  @APICreateCampaignWithRule
  Scenario Outline: Create Simple Content Campaign with Condition for API Setup
    When I click on "New campaign" option in "Content"
    Then I should be on Content "Create a campaign" page
    When I Create Simple Content Campaign "<Name>" with "<Placement>" for "<CreativeName>" of "<Size>" for "<Rule>" with "<Operator>" and "<Attribute>"
    Then I should see Message "Successfully saved"

    Examples: APIContentSetup
      | Name                       | Placement    | CreativeName    | Size    | Rule         | Operator  | Attribute |
      | APIAutoVisitorNewRule      | Home_JSON_IP | APIAutoCreative | default | Visitor      | equals to | new       |
      | APIAutoSourceAffiliateRule | Home_JSON_IP | APIAutoCreative | default | Source       | equals to | affiliate |
      | APIAutoSourceDirectRule    | Home_JSON_IP | APIAutoCreative | default | Source       | equals to | direct    |
      | APIAutoCountryGBRule       | Home_JSON_IP | APIAutoCreative | default | Country code | equals to | GB        |
      | APIAutoCountryUsRule       | Home_JSON_IP | APIAutoCreative | default | Country code | equals to | US        |
      | APIAutoCountryFRRule       | Home_JSON_IP | APIAutoCreative | default | Country code | equals to | FR        |

  @APICreateCampaignNoCondition
  Scenario Outline: Create Simple Content Campaign with No Condition for API Setup
    When I click on "New campaign" option in "Content"
    Then I should be on Content "Create a campaign" page
    When I Create Simple Content Campaign "<Name>" with "<Placement>" for "<CreativeName>" of "<Size>" and "<Rule>"
    Then I should see Message "Successfully saved"

    Examples: APIContentSetup
      | Name                             | Placement    | CreativeName    | Size    | Rule                           |
      | AutoAPIDefaultRule               | Home_JSON_IP | APIAutoCreative | default | Default creative               |
      | AutoAPILastViewedcatRule         | Home_JSON_IP | APIAutoCreative | default | Last category viewed           |
      | AutoAPIPurchaseHistoryBycatRule  | Home_JSON_IP | APIAutoCreative | default | Purchase history by category   |
      | AutoAPIPurchaseHistoryByAttrRule | Home_JSON_IP | APIAutoCreative | default | Purchase history by attributes |

  @APIActivateCampaignWithRule
  Scenario Outline: Activate API Content Campaigns
    Given I goto Content Campaign "<CampaignName>"
    And I Activate Content Campaign "<CampaignName>"
    Then Content Campaign "<CampaignName>" should be Activated

    Examples: APIActivateSetup
      | CampaignName                     |
      | APIAutoVisitorNewRule            |
      | APIAutoSourceAffiliateRule       |
      | APIAutoSourceDirectRule          |
      | APIAutoCountryGBRule             |
      | APIAutoCountryUsRule             |
      | APIAutoCountryFRRule             |
      | AutoAPIDefaultRule               |
      | AutoAPILastViewedcatRule         |
      | AutoAPIPurchaseHistoryBycatRule  |
      | AutoAPIPurchaseHistoryByAttrRule |

  @APIDeleteCampaignWithRule
  Scenario Outline: Delete API Content Campaigns
    Given I Delete Content Campaign "<CampaignName>"
    Then I should not see Content Campaign "<CampaignName>"

    Examples: APIDeleteSetup
      | CampaignName                     |
      | APIAutoVisitorNewRule            |
      | APIAutoSourceAffiliateRule       |
      | APIAutoSourceDirectRule          |
      | APIAutoCountryGBRule             |
      | APIAutoCountryUsRule             |
      | APIAutoCountryFRRule             |
      | AutoAPIDefaultRule               |
      | AutoAPILastViewedcatRule         |
      | AutoAPIPurchaseHistoryBycatRule  |
      | AutoAPIPurchaseHistoryByAttrRule |

 