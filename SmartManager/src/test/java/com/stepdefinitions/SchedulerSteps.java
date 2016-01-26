package com.stepdefinitions;

import com.peerius.SchedulerUI;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class SchedulerSteps extends SchedulerUI{
	
	

@Given("^I go to scheduler$")
public void i_go_to_scheduler() throws Throwable {
   gotoScheduler("https://zach.suleiman@peerius.com:n4mhc4q4134@qa1.lan:6443/scheduler/ui");
 
}

@Given("^click \"(.*?)\" Under \"(.*?)\"$")
public void click_Under(String arg1, String arg2) throws Throwable {
    
	selectMenu("Test Data", "Create User");
}

@Given("^I Check Completion for \"(.*?)\"$")
public void i_Check_Completion_for(String task) throws Throwable {

	checkTaskFinished(task);

}


@When("^I Run Offline-BigData Task \"(.*?)\"$")
public void i_Run_Offline_BigData_Task(String task) throws Throwable {
	
	startTask(task);
	
}





}