package pagesAre;

import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BaseClass {

    public HomePage(){
        PageFactory.initElements(driver,this);
    }
    public String homePageUrl="https://sakshingp.github.io/assignment/home.html";

    @FindBy(css = "#logged-user-name")
    public WebElement userNameLoggedIn;

    @FindBy(css =".btn.btn-success.btn-sm")
    public WebElement makePaymentButton;

    @FindBy(css = "#amount")
    public WebElement amountHeader;

    @FindBy(xpath = "//td[@class='text-right bolder nowrap']//span")
    public List<WebElement> amountValue;

    public boolean IsUserOnHomePage(){
        waitForElementVisible(driver,makePaymentButton);
        return makePaymentButton.isDisplayed();

    }
}
