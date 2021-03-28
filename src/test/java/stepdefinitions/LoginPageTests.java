package stepdefinitions;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.Markup;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.LoginPage;
import com.aventstack.extentreports.Status;

import java.io.IOException;

public class LoginPageTests extends TestBase {
    LoginPage loginPage = null;

    @Given("^i am Formbay User logging in with \"([^\"]*)\" and \"([^\"]*)\"$")
    public void i_am_Formbay_user_loggin_in_with(String username,String password) throws InterruptedException, IOException {
        try{
            isSuccessful = false;
            launchbrowser(websiteurl);
            loginPage=new LoginPage(wdriver);
            isSuccessful = loginPage.entercredential(username,password);
        }
        catch(Exception ex){
            mylogger.error("Exception Occurred "+ ex.getMessage());
            isSuccessful=false;
        }
        finally {
            actionResult("Credential input successful.", "Credential input failed.");
        }

    }

    @When("^i login to Formbay application$")
    public void i_login_to_Formbay_application() throws IOException, InterruptedException {
        try{
            isSuccessful = false;
            isSuccessful = loginPage.login();
        }
        catch(Exception ex){
            mylogger.error("Exception Occurred "+ ex.getMessage());
            isSuccessful=false;
        }
        finally {
            actionResult("Login Successful.", "Login Failed.");
        }


    }

    @Then("^I should be able to login$")
    public void i_should_be_able_to_login() throws IOException {
        logger.log(Status.PASS, "i_should_see_joblist_page fail");

    }
}