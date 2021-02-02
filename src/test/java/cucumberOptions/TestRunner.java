package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/features",
		glue={"src/test/java/stepDefinitions"},
		monochrome=true,
		dryRun=false,
		plugin= {"pretty","html:target/cucumber-reports", "json:target/cucumber.json"}
		)

public class TestRunner {

}
