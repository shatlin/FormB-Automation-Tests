package stepdefinitions;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.sun.jdi.request.InvalidRequestStateException;
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
import utils.log;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

public class TestBase {
    public static WebDriver wdriver;
    public static String endpoint ;
    public static String qaendpoint;
    public static String eteendpoint;
    public String localext;
    public String strProjectLoc = null;
    public String env = null;
    private Properties prop = new Properties();
    public static String username = null;
    public static String password = null;
    private static String browser = null;
    public static FileInputStream fileInputStream;
    public  ExtentSparkReporter spark;
    public  ExtentReports extentReports;
    public  ExtentTest logger;
    public static ExcelUtils excelUtils;
    public static boolean firsttest;
    public static boolean lasttest;

    private static TestBase sInstance;

    public static TestBase GetTestBase() {

        if (null == sInstance) {

            sInstance = new TestBase();
            String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            String folderDate = new SimpleDateFormat("dd_MM_yyyy").format(new Date());
            File newfolder= new File(System.getProperty("user.dir") + "/testReport/"+"/"+folderDate+"/");
            if(!(newfolder.isDirectory()))
                newfolder.mkdir();
            String destination = System.getProperty("user.dir") + "/testReport/" +"/"+folderDate+"/"+ "/FormBayAutomation" + dateName
                    + ".html";
            sInstance.spark = new ExtentSparkReporter(destination);
            sInstance.extentReports = new ExtentReports();
            sInstance.extentReports.attachReporter(sInstance.spark);
        }
        return sInstance;
    }

    protected TestBase() {

    }

    public void BeforeScenario(String ScenarioName)
    {
        logger = extentReports.createTest(ScenarioName);
        logger.log(Status.INFO,"Start the test");
        firsttest=false;

    }
    public String executionproperties() throws IOException {

        lasttest=false;
        strProjectLoc = System.getProperty("user.dir");
        FileInputStream fis = null;

        fis = new FileInputStream(strProjectLoc + File.separator+"src" + File.separator+ "test" +  File.separator + "resources"+File.separator +"properties"+ File.separator+"execution.properties");
        prop.load(fis);


        env = prop.getProperty("env");
        qaendpoint = prop.getProperty("qaendpoint");
        eteendpoint = prop.getProperty("etendpoint");
        localext = prop.getProperty("localexecution");
        username  = prop.getProperty("username");
        password = prop.getProperty("password");
        browser = prop.getProperty("browser");


        /**
         * Description: The below logic is to ensure that to execute the code locally or through mvn commnd
         * please ensure that before pushing your code to bitbucket we need to set the local exectuion variable to No
         */
        if (localext.equalsIgnoreCase("yes"))
        {
            env =prop.getProperty("env");

            if (env.equalsIgnoreCase("qa"))
            {
                endpoint = qaendpoint;
            }
            else
            {
                endpoint = eteendpoint;
            }
        }

        else {

            if (env.equalsIgnoreCase("qa")) {

                endpoint = qaendpoint;
            } else if (env.equalsIgnoreCase("ete")) {
                endpoint = eteendpoint;
            }

        }
        return endpoint;
    }


    public void launchbrowser(String url)
    {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "src" + File.separator + "test" + File.separator + "resources" + File.separator + "webdrivers" + File.separator + "chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
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
            System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + File.separator + "src"+File.separator+"test"+File.separator+"resources"+File.separator+"webdriver"+File.separator+"IEDriverServer.exe");
            InternetExplorerOptions options = new InternetExplorerOptions();
            options.merge(cap);
            wdriver = new InternetExplorerDriver(options);
            wdriver.get(url);
            wdriver.manage().window().maximize();
        }

        else if(browser.equalsIgnoreCase("edge"))
        {
            System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + File.separator + "src"+File.separator+"test"+File.separator+"resources"+File.separator+"webdriver"+File.separator+"msedgedriver.exe");
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
        wdriver.quit();
        log.endLog(" End "+ScenarioName);
        System.out.println("Finished  scenario: "+ScenarioName);
        if(lasttest)
            extentReports.flush();
        wdriver.quit();
    }

}
