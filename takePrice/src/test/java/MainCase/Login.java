package MainCase;

import helpers.PropertiesHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import utils.driver.DriverManager;

import java.time.Duration;


public class Login {

//    public Login() {
//        PropertiesHelper.loadAllFiles();
//    }


    @BeforeMethod
    @Parameters({"browser"})
    public static void createDriver(@Optional("chrome") String browser) {
        WebDriver driver = SetupBrowser(browser);
        DriverManager.setDriver(driver);
    }

    public static WebDriver SetupBrowser(String browserName) {
        WebDriver driver;
        switch (browserName.toLowerCase()) {
            case "chrome":
                driver = initChromeDriver();
                break;
            case "firefox":
                driver = initFirefoxDriver();
                break;
            case "edge":
                driver = initEdgeDriver();
                break;
            default:
                System.out.println("Browser: " + browserName + " is invalid, Launching Chrome as browser of choice...");
                driver = initChromeDriver();
        }
        return driver;
    }


    private static WebDriver initChromeDriver() {
        WebDriver driver;

        System.out.println("Launching Chrome browser...");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }


    private static WebDriver initEdgeDriver() {
        WebDriver driver;

        System.out.println("Launching Edge browser...");
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().window().maximize();
        return driver;
    }


    private static WebDriver initFirefoxDriver() {
        WebDriver driver;

        System.out.println("Launching Firefox browser...");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        return driver;
    }


    @AfterMethod
    public static void close() throws InterruptedException {
        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
        }
    }
}
