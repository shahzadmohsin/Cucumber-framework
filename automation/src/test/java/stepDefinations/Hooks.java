package stepDefinations;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import utilities.LocalProperties;
import utilities.Log;
import utilities.WebDriverManager;

public class Hooks {

	WebDriver driver;
	WebDriverManager webDriverManager;
	private final String customerURL;

	public Hooks() {
		webDriverManager = new WebDriverManager();
		customerURL = LocalProperties.getKfzUrl();
	}

	@Before
	public void setUp() {
		driver = webDriverManager.getDriver();
		driver.get(customerURL);
		Log.info("Browser opened.");
	}

	@After
	public void teardown() {
		webDriverManager.closeDriver();
	}

}
