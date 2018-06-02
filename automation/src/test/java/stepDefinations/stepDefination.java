package stepDefinations;

import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.junit.Cucumber;
import utilities.WebDriverManager;

@RunWith(Cucumber.class)
public class stepDefination {

	WebDriver driver;
	WebDriverManager WebDriverManager=new WebDriverManager();

	@Given("^User is on check Home Page$")
	public void user_is_on_check_Home_Page() throws Throwable {


	}

	@When("^User Logins with multiple values (.+) and (.+)$")
	public void user_logins_with_multiple_values_and(String username, String password) throws Throwable {
		System.out.println(username);
		System.out.println(password);
	}

	@Then("^Homepage is displayed$")
	public void homepage_is_displayed() throws Throwable {
		System.out.println("User has been logged in.");
	}

}
