Feature: SMART-Triggers Test

  Background: Pre-requisite
    Given I login as "zach"
    Then I should be on "Peerius Smart Manager" page
    And I search for site "demostoredev"

  @CreateESPConnection
  Scenario Outline: Create an ESP Connection
    Given I click on "Triggers" option in "Mail"
    Then I should be on Mail "Triggers" page
    When click on button "ESP Connections"
    And click on button "Add an ESP connection"
    And I Create Connection "<Name>" for ESP "<ESP>"
    Then I Should See ESP Connection "<Verification>" on Mail Triggers Page

    Examples: Connections
      | Name      | ESP       |Verification|
      | Silverpop | Silverpop |Silverpop   |
      | ECircle   | ECircle   |ECircle     |
      | SmartCast | SmartCast |SmartCast   |
      

  @CreateESPAction
  Scenario Outline: Create an ESP Action
    Given I click on "Triggers" option in "Mail"
    Then I should be on Mail "Triggers" page
    When click on button "ESP Actions"
    And click on button "Add an ESP Action"
    And I Create Action "<Name>" for ESP "<Connection>"

    Examples: Actions
      | Name      | Connection       |
      | ECircle   | ECircle          |
#     | SmartCast | SmartCast        |
#     | Silverpop | Silverpop        |


  @TestOnlyESPConnection
  Scenario: Create Test only ESP Connection
    Given I click on "Triggers" option in "Mail"
    Then I should be on Mail "Triggers" page
    When click on button "ESP Connections"
    And click on button "Add an ESP connection"
    And I Create Test Connection "ECircleTest" for ESP "ECircle"
    Then I Should not See ESP Connection "ECircleTest" on Mail Triggers Page

	@EditESPConnection
	 Scenario: Edit ESP Connection
	 Given I Edit ESP Connection "ECircle"
	 Then I Should See ESP Connection "AutoECircle" on Mail Triggers Page
	 
	 @DeleteESPConnection
	 Scenario: Edit ESP Connection
	 Given I Delete ESP Connection "ECircle"
	 Then I Should not See ESP Connection "ECircle" on Mail Triggers Page
	 

