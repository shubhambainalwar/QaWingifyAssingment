package testCases;

import base.BaseClass;
import org.slf4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesAre.HomePage;
import pagesAre.LoginPage;

public class LoginPageTestCase extends BaseClass {

    LoginPage loginPage;
    HomePage homePage;


    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        createDriver();
        loginPage=new LoginPage();
        homePage=new HomePage();
    }
    @Test(description = "TC-001 Verify the user on login page")
    public void verifyUserOnLoginPage(){
       boolean login= loginPage.IsUserOnLoginPage();
       Assert.assertTrue(login,"User is not on login page");
       Assert.assertEquals(driver.getCurrentUrl(),loginPage.loginPageUrl,"User is not on login page");
    }
    @Test(description = "TC-002 Verify the login Form Text visible on login page")
    public void verifyLoginFormVisible() {
       loginPage.loginFormLogo.isDisplayed();
    }
    @Test(description = "TC-003 Verify the username box enabled")
    public void verifyUsernameBoxEnabledOrNot() {
        loginPage.userNameBox.isEnabled();
    }
    @Test(description = "TC-004 Verify the password box enabled")
    public void verifyPasswordBoxEnabledOrNot() {
        loginPage.passwordBox.isEnabled();
    }
    @Test(description = "TC-005 Verify the login button is clickable")
    public void verifyIsLoginButtonClickAble(){
        loginPage.clickOnElement(loginPage.loginButton);
        String actualMessage=loginPage.usernameAndPasswordAlertMessage.getText();
        System.out.println("Actual Message is :"+loginPage.usernameAndPasswordAlertMessage.getText());
        Assert.assertEquals(actualMessage,loginPage.AlertMessage,"Username and password not required");
    }
    @Test(description = "TC-006 Verify the remember me check box is clickable")
    public void verifyRememberMeCheckBox(){
        boolean remember =loginPage.clickOnElement(loginPage.rememberMeCheckBox);
        Assert.assertTrue(remember,"Remember box is nor clickable");
    }
    @Test(description = "TC-007 verify after click on remember me same credential use next login also")
    public void rememberMeTestWithSameCredential() throws InterruptedException {
       loginPage.loginToApplication(prop.getProperty("userName"), prop.getProperty("password") );
       Assert.assertEquals(driver.getCurrentUrl(),homePage.homePageUrl,"User is not on home page");
       loginPage.methodBackward();
       loginPage.methodForward();
       Thread.sleep(2000);
       Assert.assertEquals(driver.getCurrentUrl(),homePage.homePageUrl,"User not on homepage");

    }
    @Test(description = "TC-008 Verify login with only username")
    public void verifyLoginWithUsername(){
        loginPage.loginToApplication("admin","");
        String actualMessage = loginPage.passwordAlertMessage.getText();
        Assert.assertEquals(actualMessage,loginPage.errorMessageWhenNoPassword,"password not present");

    }
    @Test(description = "TC-009 Verify login with only password")
    public void verifyLoginWithPassword(){
        loginPage.loginToApplication("","admin");
        String actualMsg=loginPage.userNameAlertMessage.getText();
        Assert.assertEquals(actualMsg,loginPage.errorMessageWhenNoUsername,"username not present");
    }
    @Test(description = "TC-010 Verify login with valid credential")
    public void verifyLoginWithValidCredential(){
        loginPage.loginToApplication("admin","admin");
        Assert.assertEquals(driver.getCurrentUrl(),homePage.homePageUrl,"User is not on home page");
    }




    @AfterMethod(alwaysRun = true)
    public void tearDown(){
        driver.quit();
    }

}
