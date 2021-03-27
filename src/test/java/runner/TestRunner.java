package runner;

import cucumber.api.CucumberOptions;
import cucumber.api.java.After;
import cucumber.api.junit.Cucumber;
import cucumber.api.java.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import stepdefinitions.TestBase;
import java.io.IOException;

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
