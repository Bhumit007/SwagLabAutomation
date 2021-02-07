package drivermanager;

import Pages.SwagLabItemCheckPage;
import Utility.Utilities;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverFactory {
    static {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy-hh-mm-ss");
        System.setProperty("current.date.time", dateFormat.format(new Date()));
    }

    public static WebDriver driver;
    public static String filePath, folderPath, cmd;
    public static Properties prop;
    public SwagLabItemCheckPage swagLabPage;

    public static class Log {

        //Initialize Log4j instance
        private static Logger Log = Logger.getLogger(Log.class.getName());

        //Info Level Logs
        public static void info(String message) {
            Log.info(message);
        }

        //Error Level Logs
        public static void error(String message) {
            Log.error(message);
        }
    }


    public void property() {
        if (this.getClass().getCanonicalName().contains("SwagLabItemCheckTest")) {
            filePath = System.getProperty("user.dir") + "/src/main/resources/swagLab.properties";
        }
        try {
            prop = new Properties();
            FileInputStream fip = new FileInputStream(filePath);
            try {
                prop.load(fip);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Browser Configuration
     */
    @BeforeSuite

    public void setUp() {
        property();
        if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
            HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", chromePrefs);
            options.setPageLoadStrategy(PageLoadStrategy.NONE);
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setJavascriptEnabled(true);
            cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
            cap.setCapability(ChromeOptions.CAPABILITY, options);

            //   BasicConfigurator.configure();
            DOMConfigurator.configure("log4j.xml");

            WebDriverManager.chromedriver().setup();

            //without headless
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            System.out.println("Start Time: " + Utilities.getUtilities().getDateTime());

        }else{
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setCapability("marionatte", false);
            FirefoxOptions opt = new FirefoxOptions();
            opt.merge(dc);

            //   BasicConfigurator.configure();
            DOMConfigurator.configure("log4j.xml");

            WebDriverManager.firefoxdriver().setup();

            //without headless
            driver = new FirefoxDriver(opt);
            driver.manage().window().maximize();
            System.out.println("Start Time: " + Utilities.getUtilities().getDateTime());
        }
        swagLabPage = PageFactory.initElements(driver, SwagLabItemCheckPage.class);
    }

    @AfterSuite
    public void tearDown() throws InterruptedException, IOException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy_MM_dd_HH.mm.ss");

        String currentDateTime = format.format(date);

        if (getClass().getCanonicalName().contains("SwagLabItemCheckTest")) {
            folderPath = System.getProperty("user.dir") + "/allure-results/Reports/SwagLabItemCheck/" + "__" + currentDateTime;
        }
        File theDir = new File(folderPath);

        // if the directory does not exist, create it
        if (!theDir.exists()) {
            System.out.println("creating directory: " + theDir.getName());
            boolean result = false;

            try {

                theDir.mkdirs();
                result = true;
            } catch (SecurityException se) {
                // handle it
                System.out.println(se.getMessage());
            }
            if (result) {
                System.out.println("Folder created");
            }
        } else if (theDir.exists()) {
            System.out.println("Folder exist");
        }


        if (getClass().getCanonicalName().contains("SwagLabItemCheckTest")) {
            cmd = Constant.allurePathWin + " generate" + " " + System.getProperty("user.dir") + "/allure-results -o" + " " + System.getProperty("user.dir") + "\\allure-results\\Reports\\SwagLabItemCheck\\" + theDir.getName();
            System.out.println(cmd);
        }
        System.out.println("Before Report Process");
        System.out.println("This is CMD : " + cmd);
        Process process = Runtime.getRuntime().exec(cmd);

        Thread.sleep(10000);
        System.out.println("Generating Report");
        process.waitFor(60, TimeUnit.SECONDS);
        System.out.println("After Report Process");
        Thread.sleep(10000);
        killChromeDriver();
    }

    /**
     * kill ChromeDriver
     */

    public void killChromeDriver() {
        String cmd;
        if (prop.getProperty("browser").equalsIgnoreCase("chrome")) {
             cmd = "taskkill /f /t /IM chromedriver.exe";
        }else{
             cmd = "taskkill /f /t /IM geckodriver.exe";
        }
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            process.waitFor();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
