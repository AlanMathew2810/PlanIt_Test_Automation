package PageFactory;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShopingPage {

	WebDriver driver;

	int cartCnt = 0;
	int newcartCount = 0;
	String p;
	float price;

	HashMap<String, Float> individualPrice = new HashMap<String, Float>();

	@FindBy(xpath = "//span[contains(@class,'cart-count')]")
	WebElement cartCount;

	// Constructor
	public ShopingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Function to buy the product.
	public HashMap<String, Float> buyProduct(String product, String quantity) throws InterruptedException {
		Thread.sleep(2000);
		int qunty = Integer.parseInt(quantity); // Changing the type of the quantity to Integer
		p = driver
				.findElement(
						By.xpath("//h4[text()='" + product + "']/parent::div/p/span[contains(@class,'product-price')]"))
				.getText(); // Getting individual price
		String prc = p.replaceAll("[$]*", ""); // Removing the special character from the string
		price = Float.parseFloat(prc); // Changing the individual price to Float

		individualPrice.put(product, price); // Storing the individual price of each item.

		for (int i = 0; i < qunty; i++) { // For loop to buy the product specified number of times.
			cartCnt = Integer.parseInt(cartCount.getText()); // get and Change the number of cart items type to Integer
			driver.findElement(By.xpath("//h4[text()='" + product + "']/parent::div/p/a[text() = 'Buy']")).click(); // Clicking
																													// on
																													// But
																													// button
			newcartCount = Integer.parseInt(cartCount.getText()); // Get and Changing the number of cart items type to
																	// Integer
			if ((cartCnt + 1) != newcartCount) { // If loop to check the cart number increased by one. If not click one
													// more time
				i = i - 1;
			}
		}
		return individualPrice;

	}

	// Function to click on cart
	public void cartClick() throws InterruptedException {
		cartCount.click();
		Thread.sleep(2000);
	}

}
