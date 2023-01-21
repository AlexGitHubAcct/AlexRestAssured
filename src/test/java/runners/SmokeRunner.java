package runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
					features = "src/test/resources/Features/Post.feature",
					glue = "stepFeatures",
					plugin = {"summary", "pretty", "html:target/cucumber-reports.html",
							"json:target/cucumber-reports",					
							"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"},
					monochrome = true
				)

public class SmokeRunner {

}
