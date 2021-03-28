package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class JoblistPage extends PageBase{

    public JoblistPage(WebDriver driver)
    {
        super(driver);

    }

    @FindBy(xpath = "/html/body/div[1]/div[2]/div")
    public WebElement lbl_welcome;

    @FindBy(id="formid")
    public WebElement tbx_formid_search;

    @FindBy(xpath = "//a[contains(text(),'Create New Job')]")
    public WebElement btn_create_newjob;

    @FindBy(id="tlwhatsnew")
    public WebElement link_whats_new;




    public boolean checkIfJoblistPage() throws InterruptedException {
        isSuccessful=false;
        Thread.sleep(5000);

        if (seleniumutils.waitForElement(lbl_welcome) != null)
            isSuccessful=false;

            if (seleniumutils.waitForElement(link_whats_new) != null)
            isSuccessful=false;

        if (seleniumutils.waitForElement(tbx_formid_search) != null)
            isSuccessful = true;

        return isSuccessful;
    }

    public boolean checkCreateNewJobText() throws InterruptedException {

        if (seleniumutils.waitForElement(btn_create_newjob) != null)
            return true;

        return false;
    }


}
