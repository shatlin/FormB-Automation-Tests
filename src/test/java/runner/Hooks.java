package runner;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import stepdefinitions.TestBase;
import java.io.IOException;
import javax.swing.*;

public class Hooks extends TestBase{



    @Before
    public void before(Scenario scenario) throws IOException {

        System.out.println("Starting scenario: "+scenario.getName());
        GetTestBase().BeforeScenario(scenario.getName());

    }


    @After
    public void after(Scenario scenario) throws IOException {

        if (scenario.isFailed())
        {
            logger.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(TakeScreenShot()).build());
        }

        GetTestBase().AfterScenario(scenario.getName());
    }
}