package utilities;

import org.openqa.selenium.WebDriver;

//@Listeners(src.main.java.utilities.DMCWebDriverEventListener.class)
public class WebDriverManager {
	WebDriver driver;
	private static ThreadLocal<WebDriver> threadInstanceWebDriver = new ThreadLocal<WebDriver>();
	

	public synchronized void closeDriver() {
		threadInstanceWebDriver.get().quit();
	}

	public synchronized WebDriver getDriver() {
		WebDriver driver = WebDriverBuilder.getInstance().build();
		threadInstanceWebDriver.set(driver);
		return driver;
	}

}
