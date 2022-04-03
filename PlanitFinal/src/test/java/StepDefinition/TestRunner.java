package StepDefinition;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources/Features", glue = { "StepDefinition" }, plugin = { "pretty",
		"html:target/HTML Report/report.html" })

public class TestRunner extends AbstractTestNGCucumberTests {

}
