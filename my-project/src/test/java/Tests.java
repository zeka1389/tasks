import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
		String actual = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/text()")).getText();
		String expected = "4";

		Assert.assertEquals(actual, expected, "Amount need to be 4");

	}

	@Test
	public void verifyErrorHandlingPromoCode() throws InterruptedException {
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
		
		String actual = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/text()")).getText();
		
		WebElement promoCodeInput = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/input"));
		promoCodeInput.sendKeys(actual);
		
		WebElement applyPromoCodeButton = driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/button"));
		applyPromoCodeButton.click();
		
		Thread.sleep(5);
		
		WebElement errorMessage = driver.findElement(By.xpath("/html/body/div/div/div/div/div/div/span"));
		Assert.assertNotNull(errorMessage);
	}
	
	@Test
	public void verifyPlaceOrder() {
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
		
		WebElement placeOrderButton = driver.findElement(By.xpath("//button[text()='PLACE ORDER']"));
		placeOrderButton.click();
		
		WebElement chooseCountryDropDown = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/select"));
		chooseCountryDropDown.click();

		WebElement selectOption = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/div/div/select/option[1]"));
		Assert.assertFalse(selectOption.isEnabled(), "Select option should be disabled");

		List<WebElement> countryOptions = driver.findElements(By.xpath("//*[@id=\\\"root\\\"]/div/div/div/div/div/select/option[value!='']"));
		int randomIndex = ThreadLocalRandom.current().nextInt(countryOptions.size());
		WebElement selectedCountryOption = countryOptions.get(randomIndex);
		selectedCountryOption.click();

		WebElement proceedButton = driver.findElement(By.xpath("//button[text()='Proceed']"));
		proceedButton.click();
		
		WebElement termsAndConditionsMessage = driver.findElement(By.xpath("/html/body/div/div/div/div/div/span/b"));
		String messageText = termsAndConditionsMessage.getText();
		Assert.assertTrue(messageText.equals("Please accept Terms & Conditions - Required"), "Terms & Conditions message not found");


	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}
}
