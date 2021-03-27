package stepdefinitions;

import com.aventstack.extentreports.Status;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.HomePage;

import java.io.IOException;


public class    HomePageTests extends TestBase {

    @Given("^i am Formbay User for homepage testing with (\\d+)$")
    public void i_am_Formbay_user_for_homepage(int row) throws InterruptedException, IOException {
        System.out.println("Row Number is "+ row);
        ReadExcelRow(row);
        launchbrowser(websiteurl);
        HomePage homePage=new HomePage(wdriver);
        logger.log(Status.FAIL,TestDataRow[0].toString());
    }

    @When("^i login to Formbay application for homepage$")
    public void i_login_to_Formbay_application_for_homepage() throws IOException {
        logger.log(Status.FAIL, "Test Case (failTest)  is failed");
    }

    @Then("^I should see joblist page for homepage$")
    public void i_should_see_joblist_page_for_homepage() throws IOException {
        logger.log(Status.PASS, "Test Case (failTest)  is failed");
    }

    @And("^I should execute last test$")
    public void i_should_execute_last_test() throws IOException {
        logger.log(Status.PASS, "Test Case (failTest)  is failed");
        lastTest=true;
    }

//    @After
//    public void teardown(Scenario login){
//        if (login.isFailed())
//        {
//            try {
//                wdriver.quit();
//            } catch (Exception e){
//                //logger.error(String.format("Login scenario failed", login));
//            }
//        }
//    }
}