
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class Tests {
    WebDriver driver;

    @BeforeTest
    public void setup() {
      
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\milan\\OneDrive\\Radna površina\\AutomatedTests\\chromedriver");
        
       
        driver = new ChromeDriver();
       driver.get("https://www.example.com");
        
        
        driver.manage().window().maximize();
        
  
    }

    @Test
    public void verifyHomePage() {
    	
        String actualTitle = driver.getTitle();
        String expectedTitle = "Example - Home Page";
        Assert.assertEquals(actualTitle, expectedTitle, "User has not landed on the home page");
    }

    @AfterTest
    public void teardown() {
       
        driver.quit();
    }
}

