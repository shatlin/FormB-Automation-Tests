package runner;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.tools.ant.taskdefs.Get;
import org.junit.BeforeClass;
import stepdefinitions.TestBase;
import java.io.IOException;
import javax.swing.*;

public class Hooks extends TestBase{



    @Before
    public void before(Scenario scenario) throws IOException {

        System.out.println("Starting scenario: "+scenario.getName());
        GetTestBase().BeforeScenario(scenario.getName());

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





    @After
    public void after(Scenario scenario) throws IOException {
        GetTestBase().AfterScenario(scenario.getName());
    }
}