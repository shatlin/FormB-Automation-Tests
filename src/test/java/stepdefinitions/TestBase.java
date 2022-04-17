package stepdefinitions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.PropertyConfigurator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.ExcelUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

public class TestBase {
    public static WebDriver wdriver;
    public static String websiteurl ;
    private static Properties prop = new Properties();
    private static String browser = null;
    public  static ExtentSparkReporter spark;
    public  static ExtentReports extentReports;
    public static  ExtentTest logger;
    public static ExcelUtils excelUtils;
    public static boolean lastTest;
    private static TestBase sInstance;
    public static Object[][] TestData=null;
    public static Object[] TestDataRow=null;
    public static String TestDataExcelFile=null;
    public static String TestDataExcelSheet=null;
    public static File  testreportfolder=null;
    public static Logger mylogger=null;

    public static boolean isSuccessful = false;

    public static TestBase GetTestBase() throws IOException {

        if (sInstance==null)
        {
            sInstance = new TestBase();
            setexecutionproperties();
        }
        return sInstance;
    }

    protected TestBase() {
    }

    public static void setexecutionproperties() throws IOException {

        lastTest=false;
        prop.load(new FileInputStream(System.getProperty("user.dir") +"/src" +"/test" +"/resources" +"/properties"+"/execution.properties"));
        BasicConfigurator.configure();
        PropertyConfigurator.configure(System.getProperty("user.dir") +"/src/main/resources/log4j2.properties");
        browser = prop.getProperty("browser");
        websiteurl=prop.getProperty("websiteUrl");
        mylogger= LogManager.getLogger(TestBase.class);
        mylogger.info("Testing Log4J");
        testreportfolder= new File(System.getProperty("user.dir") + "/testReport/"+"/"+new SimpleDateFormat("dd_MM_yyyy").format(new Date())+"/");
        if(!(testreportfolder.isDirectory())) testreportfolder.mkdir();
        spark = new ExtentSparkReporter(testreportfolder + "/FormBayAutomation" + new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+ ".html");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sInstance.spark);
        TestDataExcelFile=System.getProperty("user.dir") +"\\src" +"\\test" +"\\resources" +"\\Data"+"\\FormBayAutomationData.xlsx";
        TestDataExcelSheet="Sheet1";
        TestData=new ExcelUtils().getExcelData(TestDataExcelFile,TestDataExcelSheet);
    }

    public static void ReadExcelRow(int row)
    {
        TestDataRow=new ExcelUtils().ReadExcelRow(TestDataExcelFile,TestDataExcelSheet,row);
    }

    public void BeforeScenario(String ScenarioName)
    {
        logger = extentReports.createTest(ScenarioName);
        logger.log(Status.INFO,ScenarioName+" Scenario Started ");
        mylogger.info("Starting  scenario: "+ScenarioName);
    }

    public void launchbrowser(String url)
    {
        if (browser.equalsIgnoreCase("chrome"))
        {
            System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") +"/src/test/resources/webdrivers/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary(prop.getProperty("chromeBinary"));
            options.setExperimentalOption("useAutomationExtension", false);
            wdriver = new ChromeDriver(options);
            wdriver.get(url);
            wdriver.manage().window().maximize();
        }

        else if(browser.equalsIgnoreCase("ie"))
        {
            DesiredCapabilities cap = new DesiredCapabilities();
            cap.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
            cap.setCapability("requireWindowFocus", true);
            cap.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
            cap.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
            cap.setCapability("ignoreProtectedModeSettings", true);
            System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") +"/src/test/resources/webdriver/IEDriverServer.exe");
            InternetExplorerOptions options = new InternetExplorerOptions();
            options.merge(cap);
            wdriver = new InternetExplorerDriver(options);
            wdriver.get(url);
            wdriver.manage().window().maximize();
        }

        else if(browser.equalsIgnoreCase("edge"))
        {
            System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + "/src/test/resources/webdriver/msedgedriver.exe");
            HashMap<String, Object> edgePrefs = new HashMap<String, Object>();
            edgePrefs.put("profile.default_content_settings.popups", 0);
            EdgeOptions options = new EdgeOptions();
            options.setCapability("prefs", edgePrefs);
            options.setCapability("useAutomationExtension", false);
            wdriver = new EdgeDriver(options);
            wdriver.get(url);
            wdriver.manage().window().maximize();
        }
    }
    public void AfterScenario(String ScenarioName)
    {
        logger.log(Status.INFO,ScenarioName+ " Scenario Completed");
        mylogger.info("Finished  scenario: "+ScenarioName);
        if(lastTest) {
            System.out.println("All tests completed");
            extentReports.flush();
        }
        wdriver.quit();
    }

    public static String TakeScreenShot() throws IOException {
        String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        TakesScreenshot ts = (TakesScreenshot) wdriver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = testreportfolder.getAbsolutePath()+"/"+ dateName+ ".png";
        Files.copy(source, new File(destination));
        return destination;
    }

    public static void actionResult(String successMsg, String failMsg) throws IOException {
        Assert.assertTrue(isSuccessful);

        if (isSuccessful){

            logger.log(Status.PASS, successMsg);
        }
        else{
            logger.log(Status.FAIL, failMsg);
            logger.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(TakeScreenShot()).build());
        }
    }


}
