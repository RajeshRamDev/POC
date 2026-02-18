package Steps;

import Driver.DriverFactory;
import Driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

public class BrowserHooks {
    @Before
    public void browserLaunch() {
        WebDriver driver = DriverFactory.initBrowser("chrome");
        DriverManager.setDriver(driver);
    }

    @After
    public void closeDriver() {
        DriverManager.getDriver().quit();
        DriverManager.closeDriver();
    }
}
