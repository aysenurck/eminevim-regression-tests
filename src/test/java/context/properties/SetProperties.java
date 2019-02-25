package context.properties;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class SetProperties {

    private Logger logger = Logger.getLogger(SetProperties.class);

    private File filePath = new File("src/test/resources/config.properties");

    public void setProperties() throws Exception {

        logger.info("har.file.path --> " + System.getProperty("user.dir") + "\\src\\test\\resources\\");
        logger.info("galen.report.path --> " + System.getProperty("user.dir") + "\\target\\GalenLayoutReports\\");
        logger.info("take.a.video --> " + System.getProperties().getProperty("take.a.video"));
        logger.info("browser.type --> " + System.getProperties().getProperty("browser.type"));
        logger.info("base.url --> " + System.getProperties().getProperty("base.url"));
        logger.info("user.home --> " + System.getProperties().getProperty("user.home"));
        logger.info("user.dir --> " + System.getProperties().getProperty("user.dir"));

        Properties prop = new Properties();
        OutputStream output = null;

        output = new FileOutputStream(filePath.getAbsolutePath());

        prop.setProperty("windows.chrome.driver", System.getProperties().getProperty("user.dir") + "\\drivers\\chromedriver.exe");
        prop.setProperty("mac.chrome.driver", System.getProperties().getProperty("user.dir") + "\\drivers\\chromedriver");
        prop.setProperty("implicitly.wait", "30");
        prop.setProperty("wait.timeout.seconds", "30");
        prop.setProperty("page.load.timeout", "20");
        prop.setProperty("base.url", System.getProperty("base.url"));
        prop.setProperty("har.file.path", System.getProperty("user.dir") + "\\src\\test\\resources/");
        prop.setProperty("galen.report.path", System.getProperty("user.dir") + "\\target\\GalenLayoutReports/");
        prop.setProperty("take.a.video", System.getProperties().getProperty("take.a.video"));
        prop.setProperty("browser.type", System.getProperties().getProperty("browser.type"));
        prop.store(output, "NYX Costemics Regression Test Configuration");
    }
}