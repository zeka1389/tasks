import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Tests {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\milan\\OneDrive\\Radna površina\\AutomatedTests\\chromedriver.exe");
        
        
        driver = new ChromeDriver();
        
        
        driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
        
       
        driver.manage().window().maximize();
    }

    @Test
    public void verifyHomePage() {
        
        String actualTitle = driver.getTitle();
        String expectedTitle = "GreenKart - veg and fruits kart";
        Assert.assertEquals(actualTitle, expectedTitle, "User has not landed on the home page");
    }

    @AfterTest
    public void teardown() {
        
        driver.quit();
    }
}

