package base;

import static properties.LoadProperties.LoadConfigProperty;
import static properties.SetProperties.SetValueProperties;

import java.io.File;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;

import driver.DriverManager;
import driver.DriverResponsiveTestFactory;
import net.lightbody.bmp.BrowserMobProxyServer;
import net.lightbody.bmp.core.har.Har;
import properties.LoadProperties;
import recorder.VideoRecorder;

;

public abstract class BaseResponsiveTest extends AbstractSeleniumTest
{

    private final static Logger LOGGER = Logger.getLogger(BaseResponsiveTest.class.getName());


    private VideoRecorder videoRecorder;
    String TAKEAVIDEO = System.getProperty("TakeVideo").toLowerCase();

    @Override
    protected void createDriver()
    {
    }

    @Rule
    public final TestName testName = new TestName();

    @BeforeClass
    public static void browserstackLocalExec() throws InterruptedException, IOException
    {
        proxy = new BrowserMobProxyServer();
        proxy.start();

        if (EXEC_COMMAND_BY_JENKINS.equals("true") & REMOTE_TEST.equals("true"))
        {
            String EXEC_LOCAL_PROXY_BY_JENKINS = System.getProperty("user.dir") + "/browserstacklocal/linux" +
                    "/BrowserStackLocal --key " + AUTOMATE_KEY + " --proxy-host www.nyxcosmetics.com.tr --proxy-port "
                    + proxy.getPort() + " --force-local --force-proxy";

            LOGGER.info("Execute Terminal Command -> " + EXEC_LOCAL_PROXY_BY_JENKINS);
            Runtime.getRuntime().exec(EXEC_LOCAL_PROXY_BY_JENKINS);
        }
        else if (EXEC_COMMAND_BY_JENKINS.equals("false"))
        {
            String EXEC_LOCAL_PROXY = System.getProperty("user.dir") + "/browserstacklocal/mac" +
                    "/BrowserStackLocal --key " + AUTOMATE_KEY + " --proxy-host www.nyxcosmetics.com.tr --proxy-port "
                    + proxy.getPort() + " --force-local --force-proxy";

            LOGGER.info("Execute Terminal Command -> " + EXEC_LOCAL_PROXY);
            Runtime.getRuntime().exec(EXEC_LOCAL_PROXY);
        }

        Thread.sleep(10000);
    }

    @Before
    public void init() throws Exception
    {

        SetValueProperties();
        LoadConfigProperty();

        DriverManager driverManager;
        driverManager = DriverResponsiveTestFactory.getManager();
        driver = driverManager.getDriver();


        if (TAKEAVIDEO.equals("true"))
        {
            videoRecorder.startRecording(testName.getMethodName());
        }
        else
        {
            LOGGER.info("Scenarios will not take video");
        }

    }


    @After
    public void tearDown() throws Exception
    {

        setHarFile(testName.getMethodName());

        if (TAKEAVIDEO.equals("true"))
        {
            VideoRecorder.stopRecording();
        }
        else
        {
        }

        proxy.stop();

        if (driver != null)
        {
            driver.close();
            driver.quit();
            driver = null;
        }

    }


    // --------

    private void setHarFile(String harFileName)
    {

        String sFileName = LoadProperties.config.getProperty("HarFilePath") + harFileName + ".har";

        try
        {
            Har har = proxy.getHar();
            File harFile = new File(sFileName);
            try
            {
                har.writeTo(harFile);
            }
            catch (IOException ex)
            {
                System.out.println(ex.toString());
                System.out.println("Could not find file " + sFileName);
            }
        }
        catch (Exception ex)
        {
        }
    }

}
