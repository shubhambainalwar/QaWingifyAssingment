package testCases;

import base.BaseClass;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pagesAre.HomePage;
import pagesAre.LoginPage;

public class homePageTestCase extends BaseClass {
    LoginPage loginPage;
    HomePage homePage;

    @BeforeMethod(alwaysRun = true)
    public void setUp(){
        createDriver();
        loginPage=new LoginPage();
        loginPage.loginToApplication(prop.getProperty("userName"), prop.getProperty("password"));
        homePage=new HomePage();
    }

    @Test(description = "TC-001 Verify user on home page of application")
    public void verifyUserOnHomePage(){
        boolean val = homePage.IsUserOnHomePage();
        Assert.assertTrue(val,"User is not on home page");
        System.out.println("Home page Url"+driver.getCurrentUrl());
        Assert.assertEquals(driver.getCurrentUrl(),homePage.homePageUrl,"Excepted home page url not appearing");
    }
    @AfterTest(alwaysRun = true)
    public void TearDown(){
        driver.quit();
    }
}
