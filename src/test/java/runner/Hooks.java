package runner;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import stepdefinitions.TestBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import com.aventstack.extentreports.Status;
import utils.log;

import javax.swing.*;

public class Hooks extends TestBase{

    private TestBase base;


    @Before
    public void before(Scenario scenario) throws IOException {
        System.out.println("Starting scenario: "+scenario.getName());
        base=new TestBase();
        base.BeforeScenario(scenario.getName());
        base.executionproperties();
    }

//    @Rule
//    public final TestRule watchman = new TestWatcher() {
//        @Override
//        public Statement apply(Statement base, Description description) {
//            return super.apply(base, description);
//        }
//        // This method gets invoked if the test fails for any reason:
//        @Override
//        protected void failed(Throwable e, Description description) {
//            // Print out the error message:
//            System.out.println(description.getDisplayName() + " Test Failed " + e.getClass().getSimpleName() + "\n");
////            // Now you can do whatever you need to do with it, for example take a screenshot
////            File scrFile = ((TakesScreenshot)base.D).getScreenshotAs(OutputType.FILE);
////            try {
////                File currPath = new File(new JFileChooser().getFileSystemView().getDefaultDirectory().toString() + "\\screenshot.png");
////                System.out.println("Screenshot at: " + currPath.toString());
////                FileHandler.copy(scrFile,  currPath );
////            } catch (IOException e1) {
////                e1.printStackTrace();
////            }
//        }
//
//    };

//    @BeforeAll
//    public void BeforeAll()
//    {
//        System.out.println("Before All is called");
//        base.BeforeAll();
//    }
//
//    @AfterAll
//    public void AfterAll()
//    {
//        System.out.println("After all is called");
//    }




    @After
    public void after(Scenario scenario)
    {
        base.AfterScenario(scenario.getName());
    }
}