package selenium.pages;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MainPageResponsive
{

    @FindBy(css = "[data-gtm-list='EN YENİLER']")
    private List<WebElement> newestProducts;

    @FindBy(xpath = "//div[@data-pro-product-info]")
    private List<WebElement> productSalePrices;

    @FindBy(css = "div.swiper-button-next")
    private WebElement sliderNext;

    @FindBy(css = "[data-alias='mobile_carousel'] .swiper-slide-active")
    private WebElement activeSliderImage;


    public List<WebElement> getNewestProducts()
    {
        return newestProducts;
    }

    public List<WebElement> getProductSalePrices()
    {
        return productSalePrices;
    }

    public WebElement getSliderNext()
    {
        return sliderNext;
    }

    public WebElement getActiveSliderImage()
    {
        return activeSliderImage;
    }
}
