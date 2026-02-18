package TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features ="src/test/resources/Features",
        glue = "Steps",
        dryRun = false,
        plugin = {"pretty","html:target/Reports/CleartripPOC.html"}

)
public class AppTest {
}
