package basecalls;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import config.ConfigMapping;
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

public class WedriverSetup {

    private static final Logger LOG = LogManager.getLogger(WedriverSetup.class);

    private static final String RESOLUTION_FULL_HD = "1920x1080x24";
    private static final Dimension DIMENSION_FULL_HD = new Dimension(1920, 1080);
    private ConfigMapping cfg = ConfigCache.getOrCreate(ConfigMapping.class, System.getProperties());
    protected static final int TIMEOUT = 5000;

    @BeforeClass
    public void beforeEach() throws Exception {
        System.out.println("beforeEach");
        initDriver(cfg.selenideBrowser());
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

    private void initDriver(String browser) {
        Configuration.screenshots = false;
        Configuration.timeout = TIMEOUT;
        WebDriver webDriver;
        if (cfg.isRemote()) {
            webDriver = initRemoteDriver(browser, true);
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities(browser, "", Platform.ANY);
            //TODO add more browsers
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.merge(capabilities);
            webDriver = new ChromeDriver(chromeOptions);
        }
        WebDriverRunner.setWebDriver(webDriver);
    }

    private RemoteWebDriver initRemoteDriver(String browser, boolean retryIfError) {
        DesiredCapabilities capability = new DesiredCapabilities();
        capability.setBrowserName(browser);
       // capability.setCapability("name", testName);
        capability.setCapability("screenResolution", RESOLUTION_FULL_HD);
        capability.setCapability("enableVideo", true);
        try {
            RemoteWebDriver driver = new RemoteWebDriver(new URL(cfg.remoteDriverUrl()), capability);
            driver.setFileDetector(new LocalFileDetector());
            driver.manage().window().setSize(DIMENSION_FULL_HD);
            if (isAlive(driver)) {
                return driver;
            } else {
                if (retryIfError) {
                    LOG.warn("Session not found, try to re-init");
                    return initRemoteDriver(browser, false);
                } else {
                    throw new IllegalStateException("Remote session is die");
                }
            }
        } catch (MalformedURLException e) {
            throw new IllegalStateException("Error while configuring url for remote web driver");
        }
    }

    private boolean isAlive(WebDriver driver) {
        try {
            driver.getCurrentUrl();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
/*
    private void sendScreenshotOnFailure(Throwable exception) {
    String screenshotFileName = Long.toString(System.currentTimeMillis());
    LOG.error("RP_MESSAGE#FILE#{}#{}", new File(takeScreenShot(screenshotFileName)), exception.getMessage());
}*/
}