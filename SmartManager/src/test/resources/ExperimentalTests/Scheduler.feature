Feature: Scheduler UI Tasks

@test
Scenario: Goto Scheduler
Given I go to scheduler
#And I Run Offline-BigData Task "Merger.scala"
And I Check Completion for "Merger.scala"
