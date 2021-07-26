package stepDefinitions;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import helper.HelperMethods;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import pages.amazon.Header;

public class AmazonStepDefinition {

	private WebDriver driver;
	private Header header;

	@Given("Initialize the driver")
	public void initialize_the_driver() throws IOException, ParseException {
		driver = HelperMethods.initializeDriver();
	}

	@And("The user visits {string}")
	public void the_user_visits(String string) {
		driver.get(string);
	}

	@When("User generates random number and selects dropdown option based on it")
	public void User_generates_random_number_and_selects_dropdown_option_based_on_it() throws InterruptedException {
		header = new Header(driver);
		// ! Object instances
		int dropdownOptionsCount = header.getDropdownOptionsCount();
		// adding guard point to continue only if there are dropdown options
		if (dropdownOptionsCount == 0)
			return;
		// generate random number between 1 and the length of dropdown options
		// Not taking 0 as it is for 'All Departments' option
		float number = (float) (Math.random() * (dropdownOptionsCount - 1) + 1);
		int randomNumber = (int) Math.floor(number);
		header.selectDropdown(randomNumber);
		Thread.sleep(2000);
	}

	@Then("validate random department is selected")
	public void validate_random_department_is_selected() {
		Assert.assertFalse(header.getSelectedOptionText().matches("All.*"));
	}

	@And("Quit the browser")
	public void quit_the_browser() {
		HelperMethods.tearDown();
	}

}
