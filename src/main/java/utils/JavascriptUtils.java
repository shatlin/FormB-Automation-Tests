package utils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptUtils {

    WebDriver Driver;
    public JavascriptUtils(WebDriver driver)
    {
        Driver=driver;
    }

    public static void flash(WebDriver driver, WebElement element)

    {
       // JavascriptExecutor js=((JavascriptExecutor)driver);
        String getColor= element.getCssValue("backgroundColor");
        for(int i=0; i<100; i++) {
            changecolor("rgb(0,200,0)", driver, element);
            changecolor(getColor, driver, element);
        }
    }

    public static void changecolor(String color, WebDriver driver, WebElement element)
    {
        JavascriptExecutor js= ((JavascriptExecutor)driver);
        js.executeScript("arguments[0].style.backgroundColor = '"+color+"'", element);

    }


    public static void drawBorder(WebDriver driver, WebElement element)
    {
        JavascriptExecutor js= ((JavascriptExecutor)driver);
        js.executeScript("arguments[0].style.border = '3px solid red'", element);

    }

    public static void genrateAlert(WebDriver driver, String mesage)
    {
        JavascriptExecutor js= ((JavascriptExecutor)driver);
        js.executeScript("alert('"+mesage+"')");

    }


    public static void refreshPagebyJS(WebDriver driver)
    {
        JavascriptExecutor js= ((JavascriptExecutor)driver);
        js.executeScript("history.go(0)");

    }

    public static String getPageTitlebyJs(WebDriver driver)
    {
        JavascriptExecutor js= ((JavascriptExecutor)driver);
        String title= js.executeScript("return document.title;").toString();
        return  title;
    }

    public static  String getPageInnerTextbyJS(WebDriver driver)
    {
        JavascriptExecutor js= ((JavascriptExecutor)driver);
        String pageText= js.executeScript("return document.documentElement.innerText;").toString();
        return  pageText;
    }

    public static void scrollPageDown(WebDriver driver)
    {
        JavascriptExecutor js= ((JavascriptExecutor)driver);
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");

    }

    public  void scrollPageDown()
    {
        JavascriptExecutor js= ((JavascriptExecutor) Driver);
        js.executeScript("window.scrollTo(0,document.body.scrollHeight)");

    }
    
    public  void scrollPageUp()
    {
        JavascriptExecutor js= ((JavascriptExecutor)Driver);
        js.executeScript("window.scrollTo(0,-document.body.scrollHeight)");

    }
    
    public  void scrollIntoView( WebElement element)
    {
        JavascriptExecutor js= ((JavascriptExecutor)Driver);
        js.executeScript("arguments[0].scrollIntoView(true)", element);
    }

    public static void selectDate(WebDriver driver, WebElement element, String date)
    {
        JavascriptExecutor js= ((JavascriptExecutor)driver);
        js.executeScript("arguments[0].setAttribute('value', '"+date+"');", element);
    }


    public static void focus(WebDriver driver, WebElement element)
    {
        JavascriptExecutor js= ((JavascriptExecutor)driver);

        js.executeScript ("document.getElementById(element).focus()");
    }
    public void clickByJs(WebElement element)
    {
        JavascriptExecutor js = (JavascriptExecutor) Driver;
        js.executeScript("arguments[0].click()", element);
    }




}
