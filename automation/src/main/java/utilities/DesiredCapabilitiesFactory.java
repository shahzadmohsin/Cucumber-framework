package utilities;

import java.util.Arrays;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DesiredCapabilitiesFactory {

	public static DesiredCapabilities makeDesiredCapabilities(String browserName) {
		DesiredCapabilities cap;
		if (browserName.equalsIgnoreCase("mozilla") || browserName.equalsIgnoreCase("firefox")) {
			cap = DesiredCapabilities.firefox();
			cap.setCapability(FirefoxDriver.PROFILE, createFirefoxProfile());
		} else if (browserName.equalsIgnoreCase("chrome")) {
			cap = DesiredCapabilities.chrome();
			cap.setCapability(ChromeOptions.CAPABILITY, createChromeOptions());
		} else {
			throw new RuntimeException("The provided browser type: " + browserName + " is not supported yet");
		}
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
		cap.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		return cap;
	}

	private static ChromeOptions createChromeOptions() {
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments(Arrays.asList("--start-maximized", "--disable-hang-monitor"));
		chromeOptions.addArguments("--ignore-certificate-errors");
		chromeOptions.addArguments("--disable-infobars");
		chromeOptions.addArguments("--disable-extensions");
		return chromeOptions;
	}

	private static FirefoxProfile createFirefoxProfile() {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setAcceptUntrustedCertificates(true);
		profile.setAssumeUntrustedCertificateIssuer(false);
		profile.setPreference("intl.accept_languages", "en-us");
		profile.setPreference("xpinstall.signatures.required", false);
		return profile;
	}

}
