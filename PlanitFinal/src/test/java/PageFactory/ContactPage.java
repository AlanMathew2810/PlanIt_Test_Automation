package PageFactory;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

//File that contains elements in the contact page of the Jupiter Toys website

public class ContactPage {

	WebDriver driver;
	SoftAssert softAssert;

	@FindBy(id = "forename")
	WebElement foreName;
	@FindBy(id = "email")
	WebElement email;
	@FindBy(id = "message")
	WebElement message;
	@FindBy(id = "forename-err")
	WebElement foreNameError;
	@FindBy(id = "email-err")
	WebElement emailError;
	@FindBy(id = "message-err")
	WebElement messageError;
	@FindBy(css = "a.btn-contact.btn.btn-primary")
	WebElement submit;
	@FindBy(css = "div.alert.alert-error.ng-scope")
	WebElement errorMessage;
	@FindBy(css = "div.alert.alert-info.ng-scope")
	WebElement successMessage;
	@FindBy(css = "div.alert.alert-success")
	WebElement submitSuccessMessage;

	// Constructor
	public ContactPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		softAssert = new SoftAssert();
	}

	// Function to click on Submit button
	public void clickSubmit() {
		submit.click();
	}

	// Function to check Error messages in the mandatory fields
	public void checkErrorMessages() throws InterruptedException {
		Thread.sleep(1000);
		softAssert.assertTrue(foreNameError.isDisplayed(), "Fore name error message is not displayed");
		softAssert.assertTrue(emailError.isDisplayed(), "Email error message is not displayed");
		softAssert.assertTrue(messageError.isDisplayed(), "Message error message is not displayed");
		softAssert.assertTrue(errorMessage.isDisplayed(), "Error message header is not displayed");
		softAssert.assertEquals(errorMessage.getText(),
				"We welcome your feedback - but we won't get it unless you complete the form correctly.",
				"The overall error message in the header is displayed wrong.");
		softAssert.assertAll();
	}

	// Function to enter values in the mandatory fields.
	public void enterValues(String fname, String emailAdd, String msg) {
		foreName.sendKeys(fname);
		email.sendKeys(emailAdd);
		message.sendKeys(msg);
	}

	// Function to check error messages are gone after entering values in the
	// mandatory fields
	public void errorMessageGoneCheck() {

		try {
			softAssert.assertFalse(foreNameError.isDisplayed(), "Fore name error message is displayed");
			softAssert.assertAll();

		} catch (Exception NoSuchElementException) {
			// donothing
		}

		try {
			softAssert.assertFalse(emailError.isDisplayed(), "Email error message is displayed");
			softAssert.assertAll();

		} catch (Exception NoSuchElementException) {
			// donothing
		}

		try {
			softAssert.assertFalse(messageError.isDisplayed(), "Message error message is displayed");
			softAssert.assertAll();

		} catch (Exception NoSuchElementException) {
			// donothing
		}

		try {
			softAssert.assertFalse(errorMessage.isDisplayed(), "Header error message is displayed");
			softAssert.assertAll();

		} catch (Exception NoSuchElementException) {
			// donothing
		}

		softAssert.assertEquals(successMessage.getText(), "We welcome your feedback - tell it how it is.",
				"The overall message in the header is displayed wrong.");
		softAssert.assertAll();
	}

	// Function to check the successful message after submitting the feedback
	public void submitSuccessMsg(String firstname) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.alert.alert-success")));
		softAssert.assertEquals("Thanks " + firstname + ", we appreciate your feedback.",
				submitSuccessMessage.getText(), "The success message is incorrectly displayed.");
		softAssert.assertAll();
	}

	// Function to close the browser
	public void Wdrop() {
		driver.quit();
	}
}
