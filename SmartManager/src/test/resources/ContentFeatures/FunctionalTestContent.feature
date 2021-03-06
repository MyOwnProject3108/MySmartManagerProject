Feature: Smart Content Functional tests

  Background: Pre-requisite
    Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "floridatix"

  @addNewCreative
  Scenario Outline: Add new creatives with all field values parameterized
    Given I click on "New creative" option in "Content"
    Then I should be on creative "Configure Creatives" page
    When I add a new creative with field values name "<Name>" image url "<ImgUrl>" link url "<LinkUrl>" category "<RelCat>" attribute "<RelAtt>" and tag "<Tag>"
    Then I should see Message "Successfully saved"

    Examples: Field values
      | Name         | ImgUrl                                                            | LinkUrl                                              | RelCat      | RelAtt           | Tag     |
      | AutoCreative | https://www.floridatix.com/images/static/home-carousel/disney.jpg | https://www.floridatix.com/park-tickets/disney-world | Miami Tours | location:orlando | disney, |

  @editCreative
  Scenario: Edit existing creative
    Given I goto creative "AutoCreative"
    When I edit creative "AutoCreative"
    Then I should be on "edit" Page

  @copyCreative
  Scenario: Copy existing creative
    Given I goto creative "AutoCreative"
    When I copy creative "AutoCreative"
    Then I should see creative "AutoCreative copy" on Configure Creatives Page

  @hideCreativePreview
  Scenario: Test the functionality of Hide Preview button
    Given I goto creative "AutoCreative"
    And I click in the in the img url field and then in link url field
    Then creative preview should be visible
    When I click on button "Hide preview"
    Then creative preview should not be visible


  @CreateCampaignNoCondition
  Scenario Outline: Create Simple Content Campaign with No Condition
    When I click on "Create a campaign" option in "Content"
    Then I should be on Content "Create a campaign" page
    When I Create Simple Content Campaign "<Name>" with "<Placement>" for "<CreativeName>" of "<Size>" and "<Rule>"
    Then I should see Message "Successfully saved"

    Examples: ContentSetup
      | Name                | Placement   | CreativeName                      | Size    | Rule             |
      | AutoContentCampaign | homeRecs_ip | Desktop Walt Disney World Tickets | default | Default creative |

  @ActivateContentCampaign
  Scenario: Activate Content Campaign
    Given I goto Content Campaign "AutoContentCampaign"
    And I Activate Content Campaign "AutoContentCampaign"
    Then Content Campaign "AutoContentCampaign" should be Activated

  @DuplicateContentcampaign
  Scenario: Duplicate Content Campaign
    Given I goto Content Campaign "AutoContentCampaign"
    And I Duplicate Content Campaign "AutoContentCampaign"
    Then I Should See Content Campaign "AutoContentCampaign copy" on list Page

  @EditContentCampaign
  Scenario: Edit Content Campaign
    Given I goto Content Campaign "AutoContentCampaign"
    And I Edit Content Campaign "AutoContentCampaign"
    Then I should be on "edit" Page

  @VerifyToggleAdvancedButton
  Scenario: Verify Toggle Advanced button
    Given I goto Content Campaign "AutoContentCampaign"
    And I click on link "Toggle advanced"
    And I Enter criteria ""
    Then I Set Criteria "(u.visitor="new")"
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"


  @CreateCampaignWithRule
  Scenario Outline: Create Simple Content Campaign with Condition
    When I click on "Create a campaign" option in "Content"
    Then I should be on Content "Create a campaign" page
    When I Create Simple Content Campaign "<Name>" with "<Placement>" for "<CreativeName>" of "<Size>" for "<Rule>" with "<Operator>" and "<Attribute>"
    Then I should see Message "Successfully saved"
   
    Examples: ContentSetup
      | Name                 | Placement   | CreativeName                      | Size    | Rule   | Operator  | Attribute |
      | AutoCampaignWithRule | homeRecs_ip | Desktop Walt Disney World Tickets | default | Gender | equals to | male      |

  @MultipleRules
  Scenario: Create Content Campaign with Multiple Rules
    Given I goto Content Campaign "AutoCampaignWithRule"
    And I click on "Add a new row" for the Creative
    And I select Creative "Desktop Orlando Tours Category" in "1" index
    And I select Creative Size "default" in "1" index
    And I Select Creative Rule "Default creative" in "2" row
    And I click on button "Save campaign"
    Then I should see Message "Successfully saved"
    Given I goto Content Campaign "AutoCampaignWithRule"
    Then I Should see "Desktop Orlando Tours Category" in "1" index

  #Error validations
  @creativeErrorValidation
  Scenario Outline: Verify error message for empty name, empty image url
    Given I click on "New creative" option in "Content"
    And I enter name as "<Name>"
    And I enter image url as "<ImgUrl>"
    And I enter link url as "<LinkUrl>"
    When I click on button "Save creative"
    Then I should see Message "<Message>"

    Examples: Validation Messages
      | Name         | ImgUrl                     | LinkUrl                    | Message                                                            |
      |              | https://www.floridatix.com | https://www.floridatix.com | Name must be provided.                                             |
      | TestCreative |                            | https://www.floridatix.com | You must provide an Image URL for the content you wish to display. |

  @creativeDuplicateNameError
  Scenario Outline: Verify error message for creative with duplicate name
    Given I click on "New creative" option in "Content"
    When I add a new creative with field values name "<Name>" image url "<ImgUrl>" link url "<LinkUrl>" category "<RelCat>" attribute "<RelAtt>" and tag "<Tag>"
    Then I should see Message "Creative with the same name already exists"

    Examples: Field values
      | Name         | ImgUrl                                                            | LinkUrl                                              | RelCat      | RelAtt           | Tag     |
      | AutoCreative | https://www.floridatix.com/images/static/home-carousel/disney.jpg | https://www.floridatix.com/park-tickets/disney-world | Miami Tours | location:orlando | disney, |

  @EmptyCreativeAdvancedHTML
  Scenario: Test that user can edit the Advanced HTML section for Image and Link Urls
    Given I goto creative "AutoCreative"
    And I click on link "Advanced"
    When I remove html from advanced html field
    And I click on button "Save creative"
    Then I should see Message "You must provide an Image URL for the content you wish to display."

  @Setuperrorvalidations
  Scenario Outline: Error validation for empty setup name
    When I click on "Create a campaign" option in "Content"
    Then I should be on Content "Create a campaign" page
    When I Set Campaign Name as "<Name>"
    And I Select Placement "<Placement>"
    And I Select Creative Rule "<Criteria>" in "1" row
    And I click on button "Save campaign"
    Then I should see Message "<Message>"

    Examples: Validation messages
      | Name         | Placement   | Criteria         | Message                                      |
      |              | homeRecs_ip | Default creative | Please provide a name                        |
      | AutoCampaign |             | Default creative | Please provide a placement                   |
      | AutoCampaign | homeRecs_ip |                  | Criteria cannot be empty                     |
      | AutoCampaign | homeRecs_ip | Default creative | You must provide a value for 'creative size' |

  @IPErrorvalidation
  Scenario: Error validation for ip address
    Given I goto Content Campaign "AutoCampaignWithRule"
    When I Set ip as "Test"
    And I click on button "Save campaign"
    Then I should see Message "The IP address Test was not valid"
        
  @DoNotDeleteContentCampaign
  Scenario: 'No' to Delete Content Campaign
    Given I say No to Delete Content Campaign "AutoContentCampaign"
    Then Content Campaign "AutoContentCampaign" should not be deleted
    
    
  @DeleteDuplicateCampaign
  Scenario: 'Yes' to Delete Duplicate Content Campaign
    Given I Delete Content Campaign "AutoContentCampaign copy"
    Then I should not see Content Campaign "AutoContentCampaign copy"

  @DeleteContentCampaign
  Scenario: 'Yes' to Delete Content Campaign
    Given I Delete Content Campaign "AutoContentCampaign"
    Then I should not see Content Campaign "AutoContentCampaign"
    And I Delete Content Campaign "AutoCampaignWithRule"
    Then I should not see Content Campaign "AutoCampaignWithRule"    
    
  @deleteCreativeNo
  Scenario: Delete existing creative by selecting No
    When I delete creative "AutoCreative"
    And I select option "no" in confirmation dialog
    Then I should see creative "AutoCreative" on Configure Creatives Page

  @deleteCreativeYes
  Scenario: Delete existing creative by selecting Yes
    When I delete creative "AutoCreative"
    And I select option "yes" in confirmation dialog
    Then I should not see creative "AutoCreative" on Configure Creatives Page

  @deleteduplicateCreative
  Scenario: Delete copy of the creative
    When I delete creative "AutoCreative copy"
    And I select option "yes" in confirmation dialog
    Then I should not see creative "AutoCreative copy" on Configure Creatives Page
