package stepsdefinitions;


import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions( features="./src/test/resources/TriggerFeatures/",glue={"com.stepdefinitions", "stepsdefinitions"}, tags={"@NewDeleteButtonTrigger"}, plugin={"pretty", "html:target/cucumber-html-report","json:target/cucumber.json", "rerun:target/rerun.txt"})
public class TriggersTest {

}
				