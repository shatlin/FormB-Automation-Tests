package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends PageBase{

    public HomePage(WebDriver driver)
    {
        super(driver);

    }

    @FindBy(id="username")
    public WebElement username;

    @FindBy(id="password")
    private WebElement password;

    @FindBy(xpath="//*[@id='new_login']/input")
    private WebElement Continue;


    public boolean login() throws InterruptedException {

        seleniumutils.waitForElement(username).sendKeys("client_trade");
        seleniumutils.waitForElement(password).sendKeys("client_trade");
       // seleniumutils.waitForElement(Continue).click();
        Thread.sleep(3000);
        System.out.println("Login Page accessed");
        return true;
    }
}
