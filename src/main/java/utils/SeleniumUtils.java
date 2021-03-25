package utils;

import com.google.common.io.Files;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PageBase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;

public class SeleniumUtils {

    WebDriver Driver;
    public SeleniumUtils(WebDriver driver)
    {
        Driver=driver;
    }

    static String value;
    static String screenshot;

    public void click_on(WebElement element) {
        new WebDriverWait(Driver, WaitUtils.WAIT_FOR_ELEMENT).ignoring(StaleElementReferenceException.class).
                until(ExpectedConditions.elementToBeClickable(element)).click();

    }

    public  WebElement waitForElement(WebElement element) {
        return new WebDriverWait(Driver, WaitUtils.WAIT_FOR_ELEMENT).ignoring(StaleElementReferenceException.class).
                until(ExpectedConditions.elementToBeClickable(element));
    }



    public  WebElement waitForDisplayElement(WebElement element) {
        return new WebDriverWait(Driver, WaitUtils.WAIT_FOR_ELEMENT).ignoring(StaleElementReferenceException.class).
                until(ExpectedConditions.visibilityOf(element));
    }

    public  boolean presenceOfAnElement(WebElement element, String txt) {
        return new WebDriverWait(Driver, WaitUtils.WAIT_FOR_ELEMENT).ignoring(StaleElementReferenceException.class).
                until(ExpectedConditions.textToBePresentInElement(element, txt));


    }

    public boolean waitForStaleElement(WebElement element) {
        return new WebDriverWait(Driver, WaitUtils.WAIT_FOR_ELEMENT).ignoring(StaleElementReferenceException.class).
                until(ExpectedConditions.refreshed(ExpectedConditions.stalenessOf(element)));


    }

    public  List<WebElement> waitForPresenceElement(By element) {
        return new WebDriverWait(Driver, WaitUtils.WAIT_FOR_ELEMENT).ignoring(StaleElementReferenceException.class).
                until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));


    }



    public  List<WebElement> waitForStaleElement(By by)
    {
        int attemp=0;
        try
        {
            return Driver.findElements(by);
        }
        catch(StaleElementReferenceException e)
        {
            while(attemp<3)
                attemp++;
            return waitForStaleElement(by);

        }


    }


    public boolean waitForElementTxt(WebElement element) {
        return new WebDriverWait(Driver, WaitUtils.WAIT_FOR_ELEMENT).ignoring(StaleElementReferenceException.class).
                until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver Driver) {
                        return element.getAttribute("value").length() != 0;
                    }
                });
    }


    public  WebElement waitForElementClickable(WebElement element) {
        return new WebDriverWait(Driver, WaitUtils.WAIT_FOR_ELEMENT).ignoring(StaleElementReferenceException.class).
                until(ExpectedConditions.elementToBeClickable(element));


    }


    public  WebDriver clickOnAlertIfPresent() {
        WebDriverWait wait=  new WebDriverWait(Driver, WaitUtils.WAIT_FOR_ELEMENT);


        try
        {
            if(wait.until(ExpectedConditions.alertIsPresent()) != null)
                Driver.switchTo().alert().accept();
            return switchToDefault();

        }
        catch (Exception e)
        {
            return switchToDefault();
        }
    }


    public boolean waitForText(WebElement element, String txt) {

        return new WebDriverWait(Driver, WaitUtils.WAIT_FOR_ELEMENT).ignoring(StaleElementReferenceException.class).
                until(ExpectedConditions.textToBePresentInElement(element, txt));


    }


    public  List<WebElement> waitForListElements(List <WebElement> element) {

        return new WebDriverWait(Driver, WaitUtils.WAIT_FOR_ELEMENT).ignoring(StaleElementReferenceException.class).
                until(ExpectedConditions.visibilityOfAllElements(element));


    }

    public  WebDriver switchToFrame( String txt) {
        return new WebDriverWait(Driver, WaitUtils.WAIT_FOR_ELEMENT).ignoring(StaleElementReferenceException.class).
                until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(txt));

    }

    public  WebDriver switchToDefault() {
        return  Driver.switchTo().defaultContent();

    }
    public  Boolean waitForElementInvisible(WebElement element) {
        return new WebDriverWait(Driver, WaitUtils.WAIT_FOR_ELEMENT).
                until(ExpectedConditions.invisibilityOf(element));

    }

    public  Boolean waitForElementsInvisible(List <WebElement> elements) {
        return new WebDriverWait(Driver, WaitUtils.WAIT_FOR_ELEMENT).
                until(ExpectedConditions.invisibilityOfAllElements(elements));

    }

    public  void mouseHover(WebElement element) {
        Actions action = new Actions(Driver);
        action.moveToElement(element).build().perform();

    }


    public void selectDropDown(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);

    }


    public  String takeScreenshot() throws IOException {
        File scrFile = ((TakesScreenshot) Driver).getScreenshotAs(OutputType.FILE);
        String currentDir = System.getProperty("user.dir");
        String destination = System.getProperty(currentDir) + "/screenshots/" + System.currentTimeMillis() + ".png";
        Files.copy(scrFile, new File(destination));
        return  destination;
    }


    public  String getScreenshotByName(String screenshotName) throws IOException {

//        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//        TakesScreenshot ts = (TakesScreenshot) Driver;
//        File source = ts.getScreenshotAs(OutputType.FILE);
//        // after execution, you could see a folder "Screenshots"
//        // under src folder
//
//        String folderDate = new SimpleDateFormat("dd_MM_yyyy").format(new Date());
//        File newfolder= new File(System.getProperty("user.dir") + "/screenshots/"+"/"+folderDate+"/");
//        if(!(newfolder.isDirectory()))
//            newfolder.mkdir();
//
//        String destination = System.getProperty("user.dir") + "/screenshots/" +"/"+folderDate+"/"+ screenshotName + dateName
//                + ".png";
//        File finalDestination = new File(destination);
//        Files.copy(source, finalDestination);
//        return destination;

        return "";
    }

    public  void getSearchResult(List<WebElement> webElements, String search)
    {

        for(WebElement element: webElements)
            if (element.getText().contentEquals(search))
                element.click();
    }


    public  int genrateCaseNumber()
    {
        int number= (int)(Math.random()*1000+3);
        return number;
    }


    public  boolean selectAnOptionFromDropDwon(List <WebElement> elements, String Option, By by)
    {
        int size= elements.size();
        boolean flag = false;

        System.out.println("the size of an elements opion ++++++"+size);
        waitForPresenceElement(by);
        waitForStaleElement(by);
        for(WebElement option: elements)
        {
            String result= option.getText();
            System.out.println("the visible option are "+result);

            if(result.toLowerCase().trim().replaceAll("\\s+"," ").contains(Option.toLowerCase().trim().replaceAll("\\s+"," ")))
            {
                waitForElementClickable(option);
                click(option);
                //option.click();
                //JavaScriptFunction.clickByJs(option);
                flag= true;
                break;
            }
            else
            {
                //log.info("Option does not exist in the system");
                flag= false;
            }
        }
        return flag;
    }



    public  boolean selectFirstValueFromDropDwon(List <WebElement> elements, By by)
    {

        int size= elements.size();
        boolean flag = false;

        System.out.println("the size of an elements opion ++++++"+size);
        waitForPresenceElement(by);
        waitForStaleElement(by);
        for(WebElement option: elements)
        {
            waitForElementClickable(option);
            //option.click();
        //    javaScriptFunction.clickByJs(option); [todo]

            flag= true;
            break;
        }
        return flag;
    }



    public  boolean getOptionsFromDropDown(List <WebElement> elements, By by, String query)
    {

        int size= elements.size();
        Boolean flag= false;
        System.out.println("the size of an elements opion ++++++"+size);
        waitForPresenceElement(by);
        waitForStaleElement(by);
      //  logger.log(Status.INFO, "the visible option are ");
        int count=0;
        for(WebElement option: elements)
        {
            String result= option.getText();
           // logger.log(Status.INFO, result );
            if(getValue(result).contains(getValue(query)))
            {
                flag=true;
            }
            count++;
        }

//        if(flag)
//        {
//            logger.log(Status.PASS,query+ "     contain in the list");
//        }
//        else
//            logger.log(Status.FAIL,query+ "    does not contain in the list");
//
//        logger.log(Status.INFO, "total count of visible item "+count);
        return flag;
    }


    public  boolean setOptionsFromDropDwon(List <WebElement> elements, By by, String query)
    {

        int size= elements.size();
        Boolean flag= false;
        System.out.println("the size of an elements opion ++++++"+size);
        waitForPresenceElement(by);
        waitForStaleElement(by);

        int count=0;
        for(WebElement option: elements)
        {
            String result= option.getText();

            if(getValue(result).contains(getValue(query)))
            {
                option.click();
                flag =true;
                break;
            }
            count++;
        }

        return flag;
    }

    public  boolean verifyResult(String expected, String actual)
    {
        boolean flag= false;
        //String Expected=getValue(expected);
        //String Actual=getValue(actual);
        if(getValue(expected).contains(getValue(actual)))
            flag=true;
        else
            flag=false;
        return flag;
    }


    public  String getValue(String text)

    {
        String value= text.toLowerCase().trim().replaceAll("\\s+"," ");
        return value;
    }



    public  void insertTextInSearchBox(WebElement element, String customertName)
    {
        Actions actions = new Actions(Driver);
        actions.moveToElement(element).build().perform();
        actions.click();
        actions.sendKeys(customertName).sendKeys(Keys.ENTER);
        //actions.clickAndHold();
        actions.build().perform();
        actions.release();
    }


    public void updateFieldValue(WebElement element, String value)
    {
        Actions actions = new Actions(Driver);
        actions.moveToElement(element);
        actions.click();
        actions.clickAndHold().sendKeys(value).build().perform();
        actions.release();
    }


    public WebDriver refreshPage()
    {
        Driver.navigate().to(Driver.getCurrentUrl());
        return clickOnAlertIfPresent();
    }


    public void perviousPage()
    {
        Driver.navigate().back();
        //CommonUtils.clickOnAlertIfPresent();

    }

    public int countStringsize(String str)
    {

        return str.length();

    }


    public boolean clickElementIFavailble(List<WebElement> element) {

        Boolean flag=false;
        if(!(element.size()==0))
        {
            for(WebElement btn:element)
            {
                waitForElementClickable(btn).click();
                flag=true;
                break;
            }
        }

        return flag;

    }

    public String generateString(int length){
        String alphabet =
                new String("1234567890!@#$%^&*()ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        int n = alphabet.length();
        String result = new String();
        Random r = new Random();
        for (int i=0; i<length; i++)
            result = result + alphabet.charAt(r.nextInt(n));

        result="Test Automation Test data" +result;
        return result;
    }


    public String generateEmailString(int length){
        String alphabet =
                new String("1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
        int n = alphabet.length();
        String result = new String();
        Random r = new Random();
        for (int i=0; i<length; i++)
            result = result + alphabet.charAt(r.nextInt(n));

        result=result+"@"+"test"+ ".com";
        return result;
    }



    public Boolean fieldLengthValidation(String fieldValue, int expectedFieldLength)
    {

        boolean flag=false;
        int actualFieldLength= countStringsize(fieldValue);
        if(actualFieldLength==expectedFieldLength)
            flag=true;
        return flag;
    }

    public String setValue(WebElement element, String value)
    {
        value=value + " " +genrateCaseNumber();
        waitForElement(element).click();
        waitForElement(element).clear();
        waitForElement(element).sendKeys(value);
        return value;
    }


    public void insertTxtByAction(WebElement element, String text)
    {
        Actions actions = new Actions(Driver);
        actions.moveToElement(element);
        actions.click();
        actions.sendKeys(text);
        actions.build().perform();
    }

    public void click(WebElement element)
    {
        try
        {
            waitForElement(element).click();
        }
        catch (StaleElementReferenceException e)
        {
            click(element);
        }

        catch (Exception e)
        {
            click(element);
        }
    }


    public void takeScreenshot( String testID) throws IOException
    {
        screenshot= getScreenshotByName(testID);
       // logger.addScreenCaptureFromPath(screenshot);
    }
}
