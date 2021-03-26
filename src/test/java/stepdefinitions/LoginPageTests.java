package stepdefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.LoginPage;

import com.aventstack.extentreports.Status;
public class LoginPageTests extends TestBase {

    @Given("^i am Formbay User$")
    public void i_am_Formbay_user() throws InterruptedException {
        firsttest=true;
        launchbrowser(endpoint);
        LoginPage loginPage=new LoginPage(wdriver);
        Assert.assertTrue(loginPage.login());
        GetTestBase().logger.log(Status.FAIL, "Test Case (failTest) Status is failed");
    }

    @When("^i login to Formbay application$")
    public void i_login_to_Formbay_application() {
        GetTestBase().logger.log(Status.PASS, "Test Case (failTest) Status is passed");
    }

    @Then("^I should see joblist page$")
    public void i_should_see_joblist_page(){
        GetTestBase().logger.log(Status.FAIL, "Test Case (failTest) Status is failed");
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