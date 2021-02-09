package cucumberOptions;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features="src/test/java/features",
		glue={"stepDefinitions"},
		tags="@Sanity and @Regression and @DeletePlace", // here as per this tag only Delete step definition will get call, but we have hooks which runs before @DeletePlace tag, that hooks will execute Addplace code
		monochrome=true,
		dryRun=false,
		plugin= {"pretty","html:target/cucumber-reports", "json:target/jsonReports/cucumber-report.json"}
		)

public class TestRunner {

}
