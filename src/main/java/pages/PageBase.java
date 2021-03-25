package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import utils.JavascriptUtils;
import utils.SeleniumUtils;
import utils.WebDriverListener;

public class PageBase {

    protected WebDriver Driver;
    protected SeleniumUtils seleniumutils;
    protected JavascriptUtils javascriptutils;
    protected WebDriverListener webDriverListener;
    public PageBase(WebDriver driver)
    {
        this.Driver=driver;
        PageFactory.initElements(Driver,this);
        seleniumutils =new SeleniumUtils(Driver);
        javascriptutils=new JavascriptUtils(Driver);
        webDriverListener=new WebDriverListener();
    }

}
