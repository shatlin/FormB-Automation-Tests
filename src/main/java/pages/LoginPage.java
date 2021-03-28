package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase{

    public LoginPage(WebDriver driver)
    {
        super(driver);

    }

    @FindBy(id="username")
    public WebElement tbx_username;

    @FindBy(id="password")
    private WebElement tbx_password;

    @FindBy(xpath="//*[@id='new_login']/input")
    private WebElement btn_Continue;

    boolean issuccessful = false;

    public boolean entercredential(String username,String password) throws InterruptedException {


        seleniumutils.waitForElement(tbx_username).sendKeys(username);
        seleniumutils.waitForElement(tbx_password).sendKeys(password);
        issuccessful=true;

        return issuccessful;
    }

    public boolean login() throws InterruptedException {

        issuccessful=false;
        seleniumutils.waitForElement(btn_Continue).click();
        Thread.sleep(3000);
        issuccessful = true;

        return issuccessful;
    }
}
