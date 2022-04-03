package PageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

// File that contains elements in the home page of the Jupiter Toys website
public class HomePage {

	WebDriver driver;

	@FindBy(id = "nav-contact")
	WebElement contact;
	@FindBy(partialLinkText = "Start Shopping »")
	WebElement startShoping;

	// Constructor
	public HomePage(WebDriver driver) { // A constructor.
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Function to check user landed on the home page of the Jupiter Toys Website.
	public void checkHomePage() throws InterruptedException {
		Thread.sleep(1000);
		Assert.assertEquals(driver.getTitle(), "Jupiter Toys", "User is not landed in the home pageof Jupiter Toys");
	}

	// Function to click on contact
	public void contactClick() {
		contact.click(); // clicking on contact in homepage
	}

	// Function to click on Start Shopping button
	public void startShopingClick() {
		startShoping.click();
	}

}
