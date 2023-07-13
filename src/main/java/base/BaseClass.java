package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.Properties;


public class BaseClass {

    public static WebDriver driver;
    public static Properties prop;
    public static WebDriverWait wait;
    private static final String propertyFilePath ="config.properties";

    private JavascriptExecutor jsExecutor;

   private static final Logger logger = LoggerFactory.getLogger(BaseClass.class);

    public BaseClass(){
       try{
           prop=new Properties();
           FileInputStream fis = new FileInputStream(propertyFilePath);
           prop.load(fis);
       }
       catch (FileNotFoundException e) {
           e.printStackTrace();
           logger.error("File not found exception");
       } catch (IOException e) {
           e.printStackTrace();
           logger.error("IO Exception");
       }
    }

    public static void createDriver(){
        switch (String.valueOf(prop.getProperty("browser"))){
            case "CHROME":
                driver=new ChromeDriver();
                logger.info("Chrome driver created");
                break;
            case "FIREFOX":
                driver=new FirefoxDriver();
                logger.info("Firefox driver created");
                break;
            case "EDGE":
                driver=new EdgeDriver();
                logger.info("Edge driver created");
                break;
            case "SAFARI":
                driver = new SafariDriver();
                logger.info("Safari driver created");
                break;
            default:
                logger.info("No matching driver found");
                System.exit(0);
                break;
        }
        driver.manage().window().maximize();
        logger.info("Browser window is maximize");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Long.parseLong(prop.getProperty("implicitWait"))));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Long.parseLong(prop.getProperty("pageLoadTimeout"))));
        driver.get(prop.getProperty("url"));
    }
    public void sendKeys(WebElement ele,String testData){
     waitForVisibilityOfElement(driver,ele);
     ele.clear();
     ele.sendKeys(testData);
     logger.info("user logged in successfully");
    }
    public void waitForElementVisible(WebDriver driver,WebElement ele){
     wait=new WebDriverWait(driver,Duration.ofSeconds(10));
     wait.until(ExpectedConditions.visibilityOf(ele));

    }
    public boolean clickOnElement(WebElement ele){
        waitForElementVisibleAndClickable(driver,ele);
        ele.click();
        return true;
    }

    public String getAttributeValue(WebElement element,String Attribute){
        return element.getAttribute(Attribute);
    }
    public WebElement waitForVisibilityOfElement(WebDriver driver, WebElement ele){
        Wait <WebDriver> wait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(ele));
        return ele;

    }
    public void waitForElementVisibleAndClickable(WebDriver driver,WebElement ele){
        wait=new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(ele));
        wait.until(ExpectedConditions.elementToBeClickable(ele));
    }
}



