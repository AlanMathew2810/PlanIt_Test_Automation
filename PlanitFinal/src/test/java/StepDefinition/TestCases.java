package StepDefinition;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import PageFactory.CartPage;
import PageFactory.ContactPage;
import PageFactory.HomePage;
import PageFactory.ShopingPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TestCases {

	WebDriver driver;
	HomePage home;
	ContactPage contact;
	ShopingPage shoping;
	CartPage cart;

	String name;
	
	HashMap<String, String> Items = new HashMap<String, String>();
	HashMap<String, Float> hashMapPrice = new HashMap<String, Float>();

	// This is a Background code. It will execute before each test scenario in the
	// feature file.
	

	@Given("Browser is open")
	public void browser_is_open() {
		// Setting the Chrome driver property
		System.setProperty("webdriver.chrome.driver",
				"C:/Users/alanj/eclipse-workspace/Planit/src/test/resources/Driver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize(); // Maximizing the chrome window..
		
		contact = new ContactPage(driver);
		home = new HomePage(driver);
		shoping = new ShopingPage(driver);
		cart = new CartPage(driver);
	}
	

	@When("User enters the Jupiter Toys website link.")
	public void user_enters_the_jupiter_toys_website_link() {
		driver.get("http://jupiter.cloud.planittesting.com"); // Launching the website
	}
	

	@Then("User land on Jupiter Toys website home page")
	public void user_land_on_jupiter_toys_website_home_page() throws InterruptedException {
		home.checkHomePage();
	}
	

	// Background code finished
	

	@When("User clicks on contact")
	public void user_clicks_on_contact() {
		home.contactClick(); // calling the function in the HomePage class
	}
	

	@And("User click on submit button")
	public void user_click_on_submit_button() throws InterruptedException {
		Thread.sleep(2000);
		contact.clickSubmit(); // calling the function in the ContactPage class
	}
	

	@Then("Error message is shown for mandatory fields")
	public void error_message_is_shown_for_mandatory_fields() throws InterruptedException {
		contact.checkErrorMessages(); // calling the function in the ContactPage class
	}
	

	@When("^User enter (.*) and (.*) and (.*) in the mandatory fields$")
	public void user_enter_forename_and_email_and_message_in_the_mandatory_fields(String sforename, String semail,
			String smessage) throws InterruptedException {
		Thread.sleep(2000);
		contact.enterValues(sforename, semail, smessage); // calling the function in the ContactPage class
		name = sforename;
	}
	

	@Then("Error messages are disappeard")
	public void error_messages_are_disappeard() {
		contact.errorMessageGoneCheck(); // calling the function in the ContactPage class
		contact.Wdrop(); // calling the function in the ContactPage class
	}
	

	@Then("User receive successful submission message")
	public void user_receive_successful_submission_message() {
		contact.submitSuccessMsg(name); // calling the function in the ContactPage class
		contact.Wdrop(); // calling the function in the ContactPage class
	}
	

	@When("User clicks on Start Shopping")
	public void user_clicks_on_start_shopping() {
		home.startShopingClick(); // calling the function in the HomePage class
	}
	

	@And("User buy product {string} with quanity {string}")
	public void user_buy_product_with_quanity(String prod, String qunty) throws InterruptedException {
		hashMapPrice = shoping.buyProduct(prod, qunty); // calling the function in the ShopingPage class
		Items.put(prod, qunty); // Storing the values in the form key value pair
	}
	

	@Then("User clicks on Cart")
	public void user_clicks_on_cart() throws InterruptedException {
		shoping.cartClick(); // calling the function in the ShopingPage class
	}
	

	@And("verify the price for each item")
	public void verify_the_price_for_each_item() {
		cart.checkIndividualPrice(Items, hashMapPrice);  // calling the function in the CartPage class
	}
	

	@And("verify the subtotal for each product")
	public void verify_the_subtotal_for_each_product() {
		cart.checkSubTotal(Items, hashMapPrice);		// calling the function in the CartPage class
	}
	

	@And("verify the total price")
	public void verify_the_total_price() {
		cart.checkTotal();				// calling the function in the CartPage class	
		contact.Wdrop(); // calling the function in the ContactPage class		
	}

}
