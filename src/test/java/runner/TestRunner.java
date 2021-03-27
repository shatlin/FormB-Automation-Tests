package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = {"src/test/resources/features"},
        glue={"stepdefinitions","runner"},
        tags = {"@smoke"},
        monochrome = true,
        dryRun = false
)

public class TestRunner {
}
