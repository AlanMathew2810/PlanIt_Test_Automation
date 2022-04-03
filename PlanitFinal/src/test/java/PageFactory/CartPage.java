package PageFactory;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

public class CartPage {

	WebDriver driver;
	SoftAssert sfAssert;
	
	float total;
	
	@FindBy(xpath = "//strong[contains(@class,\"total\")]")
	WebElement totalPrice;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		sfAssert = new SoftAssert();
	}

	// Function to verify the individual price of each item.
	public void checkIndividualPrice(HashMap<String, String> prodItem, HashMap<String, Float> expectedProdPrice) {

		String itemP;
		float itemPrice;
		float expectedPrice;

		for (String i : prodItem.keySet()) {
			// Finding the individual price of each product
			itemP = driver.findElement(By.xpath("//td[text()=' " + i + "']//parent::tr/td[2]")).getText();
			itemP = itemP.replaceAll("[$]*", "");
			itemPrice = Float.parseFloat(itemP);
			expectedPrice = expectedProdPrice.get(i);
			sfAssert.assertEquals(itemPrice, expectedPrice,
					"The individual price of the item " + i + " is displayed incorrectly");
			sfAssert.assertAll();
		}
	}

	
	//Function to verify the subtotal for each product
	public void checkSubTotal(HashMap<String, String> prodItem, HashMap<String, Float> expectedProdPrice) {
		
		String itemQnty;
		float itemQn;
		float expectedSubTotal;
		String actualSubtot;
		float actualSubtotal;

		for (String i : prodItem.keySet()) {
			// Finding the quantity of each product
			itemQnty = driver.findElement(By.xpath("//td[text()=' " + i + "']//parent::tr/td[3]/input"))
					.getAttribute("value");
			sfAssert.assertEquals(itemQnty, prodItem.get(i),
					"The quantity of the product " + i + " is displayed incorrectly");
			itemQn = Float.parseFloat(itemQnty);
			expectedSubTotal = itemQn * expectedProdPrice.get(i); // Calculating the expected subtotal
			expectedSubTotal = (float) (Math.round(expectedSubTotal * 100.0) / 100.0); // Rounding of the value to two
																						// decimal point			
			actualSubtot = driver.findElement(By.xpath("//td[text()=' " + i + "']//parent::tr/td[4]"))
					.getText();
			actualSubtot = actualSubtot.replaceAll("[$]*", "");
			actualSubtotal = Float.parseFloat(actualSubtot);
			sfAssert.assertEquals(actualSubtotal, expectedSubTotal,
					"The subtotal for the product " + i + " is displayed incorrectly");			
			total = total + actualSubtotal;    //Adding the subtotal of each item to find the grand total			
			total = (float) (Math.round(total * 100.0)/100.0) ;
			sfAssert.assertAll();
		}
	}
	
	
	//Function to check the total price
	public void checkTotal() {		
		String totPrc = totalPrice.getText();
		totPrc = totPrc.replaceAll("Total: ", "");
		float totPrice = Float.parseFloat(totPrc);	
		sfAssert.assertEquals(totPrice, total, "The grand total displayed incorrectly");
		sfAssert.assertAll();		
	}	

}
