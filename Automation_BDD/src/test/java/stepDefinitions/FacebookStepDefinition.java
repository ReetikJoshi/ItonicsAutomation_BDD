package stepDefinitions;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import helper.HelperMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.facebook.Homepage;
import pages.facebook.LoginPage;

public class FacebookStepDefinition {

	private WebDriver driver;
	private LoginPage loginP;

	@Given("Intialize The Driver")
	public void intialize_the_driver() throws IOException, ParseException {
		driver = HelperMethods.initializeDriver();
	}

	@And("Visit the link {string}")
	public void visit_the_link(String string) throws InterruptedException {
		driver.get(string);
		Thread.sleep(2000);
	}

	@When("User enters email {string} and password {string}")
	public void user_enters_email_and_password(String string, String string2) throws InterruptedException {
		loginP = new LoginPage(driver);
		loginP.enterEmail(string);
		loginP.enterPassword(string2);
		Thread.sleep(1000);
	}

	@And("User clicks on login button")
	public void user_clicks_on_login_button() throws InterruptedException {
		loginP.clickLoginBtn();
	}

	@Then("User is redirected to homepage")
	public void user_is_redirected_to_homepage() {
		// validate homepage title
		String homepageTitle = driver.getTitle();
		Assert.assertTrue(homepageTitle.contains("Facebook"));
		Assert.assertFalse(homepageTitle.contains("Facebook - Log In or Sign Up"));
	}

	@And("User checks if the full name is {string} and welcome text contains firstName {string}")
	public void user_checks_if_the_full_name_is_and_welcome_text_contains_firstName(String string, String string2) {
		Homepage homeP = new Homepage(driver);
		homeP.checkFullName(string);
		homeP.checkWelcomeText(string2);
	}

	@And("Close the browser")
	public void close_the_browse() {
		HelperMethods.tearDown();
	}
}
