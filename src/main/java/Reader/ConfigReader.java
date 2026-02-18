package reader;

import Driver.DriverFactory;
import Driver.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop = new Properties();
    static {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/propertyFiles/env.properties");
            prop.load(fis);
        } catch (IOException e) {
            System.out.println("File Not Found");
        }
    }
    public static String getProp(String key) {
        return prop.getProperty(key);
    }
}