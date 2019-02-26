package selenium.tests.web;

import context.base.AbstractEminevimTest;
import context.base.Description;
import net.lightbody.bmp.core.har.HarEntry;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import selenium.pages.UrlFactory;
import selenium.pages.web.MainPageWebPage;

import java.io.Console;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@DisplayName("Eminevim Main Page - Web")
public class EminevimHomePageTest extends AbstractEminevimTest
{
    private static Logger logger = Logger.getLogger(EminevimHomePageTest.class);

    private MainPageWebPage mainPage;

    @Before
    public void init() throws Exception
    {
        super.init(true);
        mainPage = new MainPageWebPage(driver);
    }
/*
   @Test
   @Description("Anasayfa daki png lerin larının 200 (ok) olduğunun kontrolü")
    public void testHomePageLoadPNG()
    {
        navigateToURL(UrlFactory.MAIN_URL);

        List<HarEntry> entries = proxy.getHar().getLog().getEntries();

        entries.stream().filter(link -> link.getRequest().getUrl().contains(".png") | link.getRequest().getUrl().contains(".jpg"))
                .forEach(png -> {
                    logger.info("Check Response This Url -> " + png.getRequest().getUrl());
                    Assert.assertEquals("This image not load " + png.getRequest().getUrl(), 200, png.getResponse().getStatus());
                });
    }
 */
    @Test
    @Description("Anasayfa daki formların kontrolü")
    public void HomePageFormControl() throws InterruptedException {
        int length = 9;
        String chars = "0123456789";
        String str = new Random().ints(length, 0, chars.length())
                .mapToObj(i -> "" + chars.charAt(i))
                .collect(Collectors.joining());

        navigateToURL(UrlFactory.MAIN_URL);
        click(mainPage.getName());
        sendKeys(mainPage.getName(), "AutomatedTestUser");
        click(mainPage.getSurname());
        sendKeys(mainPage.getSurname(),"AutomatedTestUser");
        click(mainPage.getTelephone());
        sendKeys(mainPage.getTelephone(),str);
        click(mainPage.getSendButton());
        Thread.sleep(3000);
    }
}
