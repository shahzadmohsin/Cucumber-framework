package utilities;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;

public class LocalProperties {

	private static PropertiesConfiguration properties = null;
	static int count = 0;

	private static PropertiesConfiguration initializePropertiesFiles() {
		try {
			String filePath = System.getProperty("user.dir") + "/resources/configFiles/local.properties";
			properties = new PropertiesConfiguration(filePath);
			FileChangedReloadingStrategy strategy = new FileChangedReloadingStrategy();
			strategy.setRefreshDelay(500);
			properties.setReloadingStrategy(strategy);
			properties.reload();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return properties;
	}

	private static String getProperty(String key) {
		properties = initializePropertiesFiles();
		String value;
		if ((value = System.getProperty(key)) != null)
			return value;

		value = properties.getString(key);
		return value;
	}

	public static String getKfzUrl() {
		return getProperty("kfz.url");
	}
	
	public static String getLogLevel() {
		return getProperty("log.level");
	}

	public static String getBrowserName() {
		return getProperty("browserName");
	}

	public static String getExecutionMode() {
		return getProperty("execution.mode");
	}

	public static String getBrowserBinary() {
		return getProperty("browser.binary");
	}

	public static String getGridHubName() {
		return getProperty("grid.hub");
	}

	public static String getRetryMode() {
		return getProperty("retry.mode");
	}

}
