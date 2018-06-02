package utilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.LocalFileDetector;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class WebDriverBuilder {

	public final static int IMPLICIT_WAIT_TIME = 30;
	public final static int JS_SCRIPT_WAIT_TIME = 10;
	public final static int SCREEN_HEIGHT = 1080;
	public final static int SCREEN_WIDTH = 1920;

	private static WebDriverBuilder instance = null;
	private final String userDir = System.getProperty("user.dir");
	private String browserName = LocalProperties.getBrowserName();
	private WebDriverEventListener eventListener;
	private String executionMode = LocalProperties.getExecutionMode();

	private WebDriverBuilder() {

	}

	public static WebDriverBuilder getInstance() {
		if (instance == null) {
			instance = new WebDriverBuilder();
		}
		return instance;
	}

	public WebDriver build() {
		WebDriver driver = instantiateDriver().get();
		// driver = new EventFiringWebDriver(driver).register(eventListener);
		org.openqa.selenium.WebDriver.Options options = driver.manage();
		setTimeouts(options);
		setScreenDimentions(options);
		return driver;
	}

	public WebDriverBuilder setScreenDimentions(WebDriver.Options options) {
		options.window().setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		return this;
	}

	public WebDriverBuilder setTimeouts(WebDriver.Options options) {
		options.timeouts().implicitlyWait(IMPLICIT_WAIT_TIME, TimeUnit.SECONDS).setScriptTimeout(JS_SCRIPT_WAIT_TIME, TimeUnit.SECONDS);
		return this;
	}

	public WebDriverBuilder eventListener(WebDriverEventListener eventListener) {
		this.eventListener = eventListener;
		return this;
	}

	private Optional<WebDriver> instantiateDriver() {
		WebDriver driver = null;

		if (executionMode.equalsIgnoreCase("local")) {
			driver = newLocalWebDriver(browserName);
		} else if (executionMode.equalsIgnoreCase("grid")) {
			driver = newRemoteWebDriver(browserName);
		}
		return Optional.of(driver);
	}

	private URL createGridRemoteAddress() {
		URL remoteAddress = null;
		try {
			remoteAddress = new URL(LocalProperties.getGridHubName());
		} catch (MalformedURLException e) {
		}
		return remoteAddress;
	}

	private WebDriver newLocalWebDriver(String browserName) {

		WebDriver driver;

		DesiredCapabilities cap = DesiredCapabilitiesFactory.makeDesiredCapabilities(browserName);

		if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", userDir + File.separator + "resources" + File.separator + "browserDrivers" + File.separator + "geckodriver.exe");
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");

			String browserBinary = LocalProperties.getBrowserBinary();
			if (browserBinary != null && !browserBinary.equals("")) {
				File pathToBinary = new File(browserBinary);
				FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
				cap.setCapability(FirefoxDriver.BINARY, ffBinary);
			}
			cap.setCapability("marionette", true);
			FirefoxOptions firefoxOptions = new FirefoxOptions(cap);
			driver = new FirefoxDriver(firefoxOptions);
		} else if (browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", userDir + File.separator + "resources" + File.separator + "browserDrivers" + File.separator + "chromedriver.exe");
			driver = new ChromeDriver(cap);
		} else {
			throw new RuntimeException("This browser is not supported yet by the automation team");
		}
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		return driver;
	}

	private WebDriver newRemoteWebDriver(String browserName) {
		DesiredCapabilities cap = DesiredCapabilitiesFactory.makeDesiredCapabilities(browserName);

		if (browserName.equalsIgnoreCase("chrome")) {
			String exeDriver = userDir + File.separator + "resources" + File.separator + "browserDrivers" + File.separator + "chromedriver";
			System.setProperty("webdriver.chrome.driver", exeDriver);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", userDir + File.separator + "resources" + File.separator + "browserDrivers" + File.separator + "geckodriver");
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
			String browserBinary = LocalProperties.getBrowserBinary();
			if (browserBinary != null && !browserBinary.equals("")) {
				File pathToBinary = new File(browserBinary);
				FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
				cap.setCapability(FirefoxDriver.BINARY, ffBinary);
			}
			cap.setCapability("marionette", true);
		}
		RemoteWebDriver remoteDriver = new RemoteWebDriver(createGridRemoteAddress(), cap);
		remoteDriver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		remoteDriver.setFileDetector(new LocalFileDetector());
		return remoteDriver;
	}
}
