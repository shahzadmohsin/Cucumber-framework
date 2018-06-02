package runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

// feature
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/functionalTests", monochrome = true, strict = true, glue = "stepDefinations", plugin = {
		"pretty", "html:target/cucumber", "junit:target/test.xml" })
public class TestRunner {

}
