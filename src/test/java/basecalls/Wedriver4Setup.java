package basecalls;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import config.ConfigMapping;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigCache;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;
import java.net.URL;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Wedriver4Setup {

    private static final Logger LOG = LogManager.getLogger(Wedriver4Setup.class);

    private static final String RESOLUTION_FULL_HD = "1920x1080x24";
    private static final Dimension DIMENSION_FULL_HD = new Dimension(1920, 1080);
    private ConfigMapping cfg = ConfigCache.getOrCreate(ConfigMapping.class, System.getProperties());
    protected static final int TIMEOUT = 5000;
    WebDriver driver;

    @BeforeClass
    public void beforeEach() throws Exception {
        System.out.println("beforeEach");
        initDriver();
    }

    @AfterClass
    public void afterEach() throws Exception {
        getWebDriver().quit();
    }

/*
    @Override
    public void handleTestExecutionException(ExtensionContext extensionContext, Throwable throwable) throws Throwable {
       // sendScreenshotOnFailure(throwable);
        throw throwable;
    }
*/

    private void initDriver() {

     //   System.setProperty("webdriver.chrome.driver", "/Users/shashank/Documents/kibana_traningn/AutomateAll/driver/chromedriver");
driver = new ChromeDriver();
    }


    }
/*
    private void sendScreenshotOnFailure(Throwable exception) {
    String screenshotFileName = Long.toString(System.currentTimeMillis());
    LOG.error("RP_MESSAGE#FILE#{}#{}", new File(takeScreenShot(screenshotFileName)), exception.getMessage());
}*/
