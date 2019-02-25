package context.driver;

import net.lightbody.bmp.client.ClientUtil;
import org.apache.log4j.Logger;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Platform;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class ChromeDriverManagerResponsive extends DriverManager {
    private Logger logger = Logger.getLogger(ChromeDriverManagerResponsive.class);

    private Map<String, String> mobileEmulation;
    private ChromeOptions chromeOptions;
    private DesiredCapabilities desiredCapabilities;

    private String USER_AGENT = "Mozilla/5.0 (Linux; Android 7.0; SM-G930V Build/NRD90M) AppleWebKit/537.36 (KHTML, " +
            "like Gecko) Chrome/59.0.3071.125 Mobile Safari/537.36";

    @Override
    public void createDriver(Boolean withProxy) throws Exception {
        mobileEmulation = mobileEmulation();
        chromeOptions = chromeOptions(mobileEmulation);
        desiredCapabilities = desiredCapabilities(withProxy, chromeOptions);

        String driverPath = System.getProperty("user.dir") + "/drivers/chromedriver.exe";

        if (Platform.getCurrent().is(Platform.MAC)) {
            System.setProperty("webdriver.chrome.driver", prop.getProperty("mac.chrome.driver"));
        } else if (Platform.getCurrent().is(Platform.WINDOWS)) {
            System.setProperty("webdriver.chrome.driver", driverPath);
        }

        logger.info("This test is local execute ...");
        driver = new ChromeDriver(desiredCapabilities);

        driver.manage().window().setSize(new Dimension(414, 736));

        session = (driver).getSessionId().toString();
        logger.info("=================================================================");
        logger.info("This Execute Session ID --> " + session);
        logger.info("=================================================================");

    }

    private DesiredCapabilities desiredCapabilities(Boolean withProxy, ChromeOptions chromeOptions) throws Exception {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();

        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
        capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        capabilities.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
        //capabilities.setCapability("browser", "Chrome");
        capabilities.setCapability("platform", "MAC");

        if (withProxy) {
            Proxy seleniumProxy = ClientUtil.createSeleniumProxy(proxy);

            String host = seleniumProxy.getHttpProxy().substring(0, seleniumProxy.getHttpProxy().indexOf(":"));
            String port = seleniumProxy.getHttpProxy().substring(seleniumProxy.getHttpProxy().indexOf(":") + 1);

            capabilities.setCapability(CapabilityType.PROXY, seleniumProxy);

            logger.info("=================================================================");
            logger.info("This Execute Browser Host --> " + host);
            logger.info("This Execute Browser Port --> " + port);
            logger.info("=================================================================");
        }

        capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);

        return capabilities;
    }

    private ChromeOptions chromeOptions(Map<String, String> mobileEmulation) {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("disable-infobars");
        chromeOptions.addArguments("--user-agent=" + USER_AGENT);
        chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);

        return chromeOptions;
    }

    private Map<String, String> mobileEmulation() {
        Map<String, String> mobileEmulation = new HashMap<>();

        mobileEmulation.put("device", "iPhone 8 Plus");
        mobileEmulation.put("realMobile", "true");
        mobileEmulation.put("version", "70.0");

        return mobileEmulation;
    }
}
