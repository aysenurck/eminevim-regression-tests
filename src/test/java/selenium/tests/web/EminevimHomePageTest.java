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

import java.util.List;
import java.util.stream.IntStream;

@DisplayName("NYX Costemic Main Page - Web")
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

}
