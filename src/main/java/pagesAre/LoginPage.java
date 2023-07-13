package pagesAre;

import base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseClass {

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }
    public String loginPageUrl= "https://sakshingp.github.io/assignment/login.html";
    public String AlertMessage="Both Username and Password must be present";

    public String errorMessageWhenNoPassword = "Password must be present";
    public String errorMessageWhenNoUsername = "Username must be present";

    @FindBy(css = "#username")
    public WebElement userNameBox;

    @FindBy(css = "#password")
    public WebElement passwordBox;

    @FindBy(css = "#log-in")
    public WebElement loginButton;

    @FindBy(css = "input[type='checkbox']")
    public WebElement rememberMeCheckBox;

    @FindBy(xpath="//div[starts-with(@id, 'random_id')]")
    public WebElement passwordAlertMessage;

    @FindBy(css = "#random_id_2")
    public WebElement userNameAlertMessage;

    @FindBy(xpath = "//h4[contains(.,'Login Form')]")
    public WebElement loginFormLogo;

    @FindBy(xpath = "//div[contains(text(),'Both Username and Password must be present')]")
    public WebElement usernameAndPasswordAlertMessage;

   public boolean IsUserOnLoginPage(){
       waitForElementVisible(driver,loginButton);
       return loginButton.isDisplayed();
   }
   public void loginToApplication(String userName,String password){
       sendKeys(userNameBox, userName );
       sendKeys(passwordBox, password);
       loginButton.click();
   }
   public void methodBackward(){
       driver.navigate().back();
   }
   public void methodForward(){
       driver.navigate().forward();
   }

}