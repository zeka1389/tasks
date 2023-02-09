import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Tests {
	WebDriver driver;

	@BeforeTest
	public void setup() {

		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\milan\\OneDrive\\Radna površina\\AutomatedTests\\chromedriver.exe");

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

	@Test
	public void completeShoppingFlow() {

		Random random = new Random();
		for (int i = 0; i < 4; i++) {
			WebElement addToBasketButton = driver.findElement(By.xpath("//button[text()='ADD TO CART']"));
			addToBasketButton.click();
		}

		List<WebElement> addToBasketButtons = driver.findElements(By.xpath("//button[text()='ADD TO CART']"));
		for (int i = 0; i < 3; i++) {
			int randomIndex = random.nextInt(addToBasketButtons.size());
			addToBasketButtons.get(randomIndex).click();
		}

		
		WebElement basketIcon = driver.findElement(By.xpath("//*[@id=\"root\"]/div/header/div/div[3]/a[4]/img"));
		basketIcon.click();

		
		WebElement checkoutButton = driver.findElement(By.xpath("//button[text()='PROCCED TO CHECKOUT']"));
		checkoutButton.click();

		

	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}
}
