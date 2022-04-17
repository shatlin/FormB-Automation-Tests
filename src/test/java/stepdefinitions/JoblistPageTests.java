package stepdefinitions;

import com.aventstack.extentreports.Status;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.JoblistPage;
import pages.LoginPage;

import java.io.IOException;

public class JoblistPageTests extends TestBase {
    JoblistPage joblistPage = null;

    @When("^i login successfully$")
    public void i_login_successfully() throws IOException, InterruptedException {
        try{
            joblistPage = new JoblistPage(wdriver);
            isSuccessful = true;
        }
        catch(Exception ex){
            mylogger.error("Exception Occurred "+ ex.getMessage());
            isSuccessful=false;
        }
        finally {
            actionResult("Login Successful.", "Login Failed.");
        }


    }

    @Then("^I should see joblist page$")
    public void i_should_see_joblist_page() throws IOException {
        try{
            isSuccessful = false;
            isSuccessful = joblistPage.checkIfJoblistPage();
        }
        catch(Exception ex){
            mylogger.error("Exception Occurred "+ ex.getMessage());
            isSuccessful=false;
        }
        finally {
            actionResult("Login Successful.", "Login Failed.");
        }

    }

    @And("^I should see create job button$")
    public void i_should_see_createjob_button() throws IOException {
        try{
            isSuccessful = false;
            isSuccessful = joblistPage.checkCreateNewJobText();
        }
        catch(Exception ex){
            mylogger.error("Exception Occurred "+ ex.getMessage());
            isSuccessful=false;
        }
        finally {
            actionResult("Create New Job button found.", "Create New Job button not found.");
        }

    }
}