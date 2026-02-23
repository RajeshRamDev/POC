package CommonBase;

import Driver.DriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.time.Duration;
import java.util.List;

public class Utils {
    protected static WebDriver driver;
    public WebDriverWait wait;
// GET WEB DRIVER
    public WebDriver getDriver() {
        this.driver = new ChromeDriver();
        return driver;
    }
    // GET URL
    public void geturl(String url) {
        driver.get(url);
    }
    // MAXIMIZE WINDOW
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }
    //  QUIT METHOD
    public void quit() {
        driver.quit();
    }
    //  ADD IMPLICIT WAIT
    public void appyImplicit(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
    // USE LOCATOR TO FIND ELEMENT
    public WebElement findElement(By locator) {
        wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }
// USE LOCATOR TO FIND LIST OF ELEMENTS
    public List<WebElement> findElements(By locator) {
        wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        List<WebElement> mobileList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        return mobileList;
    }
    //  INPUT USING SENDKEYS
    public void enter_Value(WebElement element, String value) {     // _ -> snake notation, Web element -> need one element, String Value -> need input value
        element.clear();    // delete pre loaded text
        element.sendKeys(value);    // Give this element the job I want.
    }
    //CONSTRUCTOR

    public Utils() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }
    // CLICK ELEMENT
    public void click_element(WebElement element){
        WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(element));
        ele.click();
    }
    // TYPE VALUE
    public void type_value(WebElement element, String value){
        WebElement ele = wait.until(ExpectedConditions.visibilityOf(element));
        ele.clear();
        ele.sendKeys(value);
    }
    // CLICK ELEMENT BY LOCATOR
    public void click_element_byLocator(By locator){
        WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ele.click();
    }
    // TYPE VALUE BY LOCATOR
    public void type_value_byLocator(By locator, String value){
        WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        ele.clear();
        ele.sendKeys(value);
    }
    //LAUNCH URL
    public void launchUrl(String url){
        driver.get(url);
    }

// CLICK BY ELEMENT
    public static void Click(WebElement element){
        element.click();
    }
//ACTIONS
    public static void singleClick(){
        Actions actions=new Actions(driver);
        actions.click().perform();
    }
    public static void sendValues(WebElement element,String text){
        element.sendKeys(text);
    }

    //JS CLICK ELEMENT
    public static void jsClick(WebElement element){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()",element);
    }
    //JS SCROLL
    public static void jsScroll(int height){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+height+")");
    }


}