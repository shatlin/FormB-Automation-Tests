package stepdefinitions;

import com.aventstack.extentreports.Status;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.HomePage;
import pages.LoginPage;


public class HomePageTests extends TestBase {

    @Given("^i am Formbay User for homepage$")
    public void i_am_Formbay_user_for_homepage() throws InterruptedException {
        launchbrowser(endpoint);
        HomePage homePage=new HomePage(wdriver);
        Assert.assertTrue(homePage.login());
        GetTestBase().logger.log(Status.FAIL, "Test Case (failTest) Status is failed");
    }

    @When("^i login to Formbay application for homepage$")
    public void i_login_to_Formbay_application_for_homepage() {
        GetTestBase().logger.log(Status.FAIL, "Test Case (failTest)  is failed");
    }

    @Then("^I should see joblist page for homepage$")
    public void i_should_see_joblist_page_for_homepage(){
        GetTestBase().logger.log(Status.PASS, "Test Case (failTest)  is failed");
    }

    @Then("^I should execute last test$")
    public void i_should_execute_last_test(){
        GetTestBase().logger.log(Status.PASS, "Test Case (failTest)  is failed");
        lasttest=true;
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