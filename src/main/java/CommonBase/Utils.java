

package CommonBase;

import Driver.DriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class Utils {
    protected static WebDriver driver;
    public WebDriverWait wait;

//    Basic Base Create

    //    1. Old Base
    public WebDriver getDriver() {
        this.driver = new ChromeDriver();
        return driver;
    }
    public void geturl(String url) {
        driver.get(url);
    }
    public void maximizeWindow() {
        driver.manage().window().maximize();
    }
    //    Quit method
    public void quit() {
        driver.quit();
    }
    //    implicit method
    public void appyImplicit(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
    }
    //    finding element by locator
    public WebElement findElement(By locator) {
        wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element;
    }

    public List<WebElement> findElements(By locator) {
        wait = new WebDriverWait(driver,Duration.ofSeconds(20));
        List<WebElement> mobileList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
        return mobileList;
    }
    //    send keys method
    public void enter_Value(WebElement element, String value) {     // _ -> snake notation, Web element -> need one element, String Value -> need input value
        element.clear();    // delete pre loaded text
        element.sendKeys(value);    // Give this element the job I want.
    }

//    2. New Base Create

    public Utils() {
        this.driver = DriverManager.getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }
    public void click_element(WebElement element){
        WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(element));
        ele.click();
    }
    public void type_value(WebElement element, String value){
        WebElement ele = wait.until(ExpectedConditions.visibilityOf(element));
        ele.clear();
        ele.sendKeys(value);
    }
    public void click_element_byLocator(By locator){
        WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(locator));
        ele.click();
    }
    public void type_value_byLocator(By locator, String value){
        WebElement ele = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        ele.clear();
        ele.sendKeys(value);
    }
    public void launchUrl(String url){
        driver.get(url);
    }


    public static void Click(WebElement element){
        element.click();
    }

    public static void singleClick(){
        Actions actions=new Actions(driver);
        actions.click().perform();
    }
    public static void sendValues(WebElement element,String text){
        element.sendKeys(text);
    }
    public static void ESC(){
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ESCAPE).perform();

    }
    public static void jsClick(WebElement element){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].click()",element);
    }
    public static void jsScroll(int height){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,"+height+")");
    }
    public static void Scroll(WebElement element){
        JavascriptExecutor js=(JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView",element);
    }
    public static void Slide(WebElement element,int offset){
        Actions action = new Actions(driver);
        action.clickAndHold(element).moveByOffset(0, offset).release().perform();
    }
    public static void keyPress() throws AWTException {
        Robot robot=new Robot();
        robot.keyPress(KeyEvent.VK_PAGE_UP);
    }
    public static void keyRelease() throws AWTException {
        Robot robot=new Robot();
        robot.keyRelease(KeyEvent.VK_PAGE_UP);
    }
    public static void elementScroll(WebElement element,int height){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollTop = arguments[0].scrollTop+"+height+";", element);
    }
    public static void Screenshots(String name) throws IOException {
        TakesScreenshot ts=(TakesScreenshot) driver;
        File screenshotAs = ts.getScreenshotAs(OutputType.FILE);
        File path= new File("target/Screenshots/"+name+".jpg");
        FileUtils.copyFile(screenshotAs,path);
    }
    String getText(){
        return driver.getTitle();
    }

}