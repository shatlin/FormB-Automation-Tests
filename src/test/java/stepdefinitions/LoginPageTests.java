package stepdefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.LoginPage;
import com.aventstack.extentreports.Status;

import java.io.IOException;

public class LoginPageTests extends TestBase {

    @Given("^i am Formbay User logging in with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_am_Formbay_user_loggin_in_with(String username,String password) throws InterruptedException, IOException {
        launchbrowser(websiteurl);
        LoginPage loginPage=new LoginPage(wdriver);
        Assert.assertTrue(loginPage.login(username,password));
        logger.log(Status.FAIL, "Test Case (failTest) Status is failed");
    }

    @When("^i login to Formbay application$")
    public void i_login_to_Formbay_application() throws IOException {
        logger.log(Status.PASS, "Test Case (failTest) Status is passed");
    }

    @Then("^I should see joblist page$")
    public void i_should_see_joblist_page() throws IOException {
        logger.log(Status.FAIL, "Test Case (failTest) Status is failed");
    }
}