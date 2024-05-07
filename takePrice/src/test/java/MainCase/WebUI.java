package MainCase;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import utils.driver.DriverManager;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class WebUI {

    private final static int TIMEOUT = 10;
    private final static double STEP_TIME = 0;
    private final static int PAGE_LOAD_TIMEOUT = 20;


    public static void Sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void LogConsole(Object message) {
        System.out.println(message);
    }


    public static void CleanAdd(By xpath, String NewText) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.elementToBeClickable(xpath));
        DriverManager.getDriver().findElement(xpath).clear();
        DriverManager.getDriver().findElement(xpath).sendKeys(NewText);
    }


    public static void ClickElementBy(By xpath) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOfElementLocated(xpath));
        Sleep(STEP_TIME);
        DriverManager.getDriver().findElement((xpath)).click();
//        LogConsole("Click Element : " + xpath);
    }


    public static void SendKeysEnter(By byXpath, String text) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
        Sleep(STEP_TIME);
        DriverManager.getDriver().findElement((byXpath)).sendKeys(text);
        DriverManager.getDriver().findElement((byXpath)).submit();
//        logConsole("Set text on element : " + byXpath + " on with " + text);
    }


    public static void SendKeys(By byXpath, String text) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
        Sleep(STEP_TIME);
        DriverManager.getDriver().findElement((byXpath)).sendKeys(text);
//        logConsole("Set text on element : " + byXpath + " on with " + text);
    }


    public static String GetText(By byXpath) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
        wait.until(ExpectedConditions.visibilityOfElementLocated(byXpath));
        Sleep(STEP_TIME);
//        LogConsole("Get text of element: " + byXpath);
        return DriverManager.getDriver().findElement(byXpath).getText();
    }


    public static void OpenURL(String url) {
        DriverManager.getDriver().get(url);
        LogConsole("Open :" + url);
    }


    public static Boolean CheckElementExist(By by) {
        List<WebElement> listElement = DriverManager.getDriver().findElements(by);

        if (listElement.size() > 0) {
//            System.out.println("Element " + by + " existing.");
            return true;
        } else {
//            System.out.println("Element " + by + " NOT exist.");
            return false;
        }
    }


    public static Boolean CheckElementExist(String xpath) {
        List<WebElement> listElement = DriverManager.getDriver().findElements(By.xpath(xpath));

        if (listElement.size() > 0) {
            System.out.println("Element " + xpath + " existing.");
            return true;
        } else {
            System.out.println("Element " + xpath + " NOT exist.");
            return false;
        }
    }


    /**
     * Js
     */
    public static void DomainURL() {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        String domainName = js.executeScript("return document.domain;").toString();
        System.out.println(domainName);
        String url = js.executeScript("return document.URL;").toString();
        System.out.println(url);
    }


    public static WebElement highLightElement(By by) {
        // Tô màu border ngoài chính element chỉ định - màu đỏ (có thể đổi màu khác)
        if (DriverManager.getDriver() instanceof JavascriptExecutor) {
            ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].style.border='3px solid red'", DriverManager.getDriver().findElement(by));
            Sleep(1);
        }
        return DriverManager.getDriver().findElement(by);
    }

    /**
     * Action selenium
     */
    public static void ActionCopy(WebElement copy, WebElement paste) throws AWTException {
        Actions act = new Actions(DriverManager.getDriver());
        act.click(copy).doubleClick(copy).keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).click(paste).keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();
    }

    public static void Move(By det) {
        Actions act = new Actions(DriverManager.getDriver());
        act.moveToElement(DriverManager.getDriver().findElement(det));
    }

    public static void MoveJS(By det) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
//        WebElement element = DriverManager.getDriver().findElement(By.id("id_of_element"));
        js.executeScript("arguments[0].scrollIntoView(true);", DriverManager.getDriver().findElement(det));
    }


    public static void DrapDrop(By src, By det) {
        Actions act = new Actions(DriverManager.getDriver());
        act.dragAndDrop(DriverManager.getDriver().findElement(src), DriverManager.getDriver().findElement(det));
    }

    // rê chuột nhưng k làm gì cả
    public static boolean moveToElement(By toElement) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(DriverManager.getDriver().findElement(toElement)).release(DriverManager.getDriver().findElement(toElement)).build().perform();
            return true;
        } catch (Exception e) {
            LogConsole(e.getMessage());
            return false;
        }
    }

    public static boolean hoverElement(By by) {
        try {
            Actions action = new Actions(DriverManager.getDriver());
            action.moveToElement(DriverManager.getDriver().findElement(by)).perform();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * Action robot
     */
    public static void ScreenCap() throws InterruptedException, AWTException, IOException {

        Robot robot = new Robot();
        //Get size screen browser
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        System.out.println(screenSize);
        //Khởi tạo kích thước khung hình với kích cỡ trên
        Rectangle screenRectangle = new Rectangle(screenSize);
        //Tạo hình chụp với độ lớn khung đã tạo trên
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        //Lưu hình vào dạng file với dạng png
        File file = new File("TestImageRobot.png");
        ImageIO.write(image, "png", file);

        Thread.sleep(1000);
    }

    public static boolean pressENTER() {
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    /**
     * Alert
     */
    public static void ADismiss() {
        DriverManager.getDriver().switchTo().alert().dismiss();
    }

    public static void AAccept() {
        DriverManager.getDriver().switchTo().alert().accept();
    }

    public static void AGettext() {
        DriverManager.getDriver().switchTo().alert().getText();
    }

    public static void ASenkey(String text) {
        DriverManager.getDriver().switchTo().alert().sendKeys(text);
    }


    /**
     * Tab mini(popup) or new tab
     */
    public void PopUp(int numTab) throws InterruptedException {
        // Get all new opened tab Window.
        ArrayList<String> tab = new ArrayList(DriverManager.getDriver().getWindowHandles());
        // start with Main tab with 0
        DriverManager.getDriver().switchTo().window(tab.get(0));
        // Closing the Child Window.
        DriverManager.getDriver().close();
        // Switching Main tab
        DriverManager.getDriver().switchTo().window(tab.get(0));
        Thread.sleep(2000);
    }


    /**
     * Iframe
     */
    public void Iframe01() throws InterruptedException {
        DriverManager.getDriver().navigate().to("https://anhtester.com/contact");
        Thread.sleep(9000);
        System.out.println("iframe total: " + DriverManager.getDriver().findElements(By.tagName("iframe")).size());
        DriverManager.getDriver().switchTo().frame(0);
        Thread.sleep(1000);
        System.out.println(DriverManager.getDriver().findElement(By.tagName("strong")).getText());
        //1. Switch to Parent WindowHandle
        DriverManager.getDriver().switchTo().parentFrame();
        //2. Switch to iframe icon of Messenger
        DriverManager.getDriver().switchTo().frame(1);
        DriverManager.getDriver().findElement(By.tagName("svg")).click(); //Nhấn icon để ẩn messenger chat đi
        DriverManager.getDriver().switchTo().parentFrame();
        Thread.sleep(2000);
    }


    /**
     * Assert
     */
    public static void HAssert(String a, String b) {
        Assert.assertEquals(a, b);
    }

    public static void SAssert(String a, String b) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(a, b);
        // must add  softassert.assertAll()
    }

    /**
     * Wait for Page loaded
     * Chờ đợi trang tải xong (Javascript tải xong)
     */


    //Wait for Element
    public static void WaitForElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
            LogConsole("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void WaitForElementVisible(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element Visible. " + by.toString());
            LogConsole("Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static void WaitForElementPresent(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            Assert.fail("Element not exist. " + by.toString());
            LogConsole("Element not exist. " + by.toString());
        }
    }

    public static void WaitForElementPresent(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (Throwable error) {
            Assert.fail("Element not exist. " + by.toString());
            LogConsole("Element not exist. " + by.toString());
        }
    }

    public static void WaitForElementClickable(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(DriverManager.getDriver().findElement(by)));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
            LogConsole("Timeout waiting for the element ready to click. " + by.toString());
        }
    }

    public static void WaitForElementClickable(By by, int timeOut) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeOut), Duration.ofMillis(500));
            wait.until(ExpectedConditions.elementToBeClickable(DriverManager.getDriver().findElement(by)));
        } catch (Throwable error) {
            Assert.fail("Timeout waiting for the element ready to click. " + by.toString());
            LogConsole("Timeout waiting for the element ready to click. " + by.toString());
        }
    }


    // click into network check
    public static void WaitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(PAGE_LOAD_TIMEOUT), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = driver -> ((JavascriptExecutor) DriverManager.getDriver()).executeScript("return document.readyState")
                .toString().equals("complete");

        //Get JS is Ready , go to console and print document.readyState in the table for checking
        boolean jsReady = js.executeScript("return document.readyState").toString().equals("complete");

        //Wait Javascript until it is Ready!
        if (!jsReady) {
            LogConsole("Javascript in NOT Ready!");
            //Wait for Javascript to load
            try {
                wait.until(jsLoad);
            } catch (Throwable error) {
                error.printStackTrace();
                Assert.fail("Timeout waiting for page load (Javascript). (" + PAGE_LOAD_TIMEOUT + "s)");
            }
        }
    }


}
