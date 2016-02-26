package stepsdefinitions;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions( features="./src/test/resources/",glue={"com.stepdefinitions", "stepsdefinitions"},
plugin={"pretty", "html:target/cucumber-html-report","json:target/cucumber.json", "rerun:target/rerun.txt"})
public class MailTests {

}
	