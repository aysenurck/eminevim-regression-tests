package selenium.pages.web;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import selenium.pages.PageObject;

import java.util.List;


public class MainPageWebPage extends PageObject
{

    public MainPageWebPage(WebDriver driver)
    {
        super(driver);
    }

    @FindBy(id= "slider_form_input_1")
    private WebElement CustomerName;

    @FindBy(id = "slider_form_input_2")
    private WebElement CustomerSurname;

    @FindBy(id = "slider_form_input_3")
    private WebElement CustomerTelephone;

    @FindBy(css = "#slider_form_input_4")
    private WebElement checkBox;

    @FindBy(linkText = "GÖNDER")
    private WebElement senderButton;

    @FindBy(css = "[class='pro-product-image init-swipe'] img[data-src]")
    private List<WebElement> mainPageAllProduct;

    @FindBy(css = ".pro-spotlight-line-pagination-wrapper .swiper-pagination-bullet")
    private List<WebElement> sliderNext;

    @FindBy(css = ".swiper-slide-active")
    private WebElement activeSliderImage;


    public List<WebElement> getSliderNext()
    {
        return sliderNext;
    }

    public WebElement getName()
    {
        return CustomerName;
    }
    public WebElement getSurname()
    {
        return CustomerSurname;
    }
    public WebElement getTelephone()
    {
        return CustomerTelephone;
    }

    public WebElement getSendButton()
    {
        return senderButton;
    }


}
