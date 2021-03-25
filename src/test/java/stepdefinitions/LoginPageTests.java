package stepdefinitions;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import pages.LoginPage;


public class LoginPageTests extends TestBase {


    @Given("^i am Formbay User$")
    public void i_am_Formbay_user() throws InterruptedException {

        launchbrowser(endpoint);
        LoginPage loginPage=new LoginPage(wdriver);
        Assert.assertTrue(loginPage.login());
        logger.fail("Test Failed");

    }

    @When("^i login to Formbay application$")
    public void i_login_to_Formbay_application() {
//        launchbrowser(endpoint);
//        LoginPage loginPage=new LoginPage(wdriver);
//        Assert.assertTrue(loginPage.login());

    }

    @Then("^I should see joblist page$")
    public void i_should_see_joblist_page(){

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