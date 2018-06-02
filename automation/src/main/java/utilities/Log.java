package utilities;

/**
 * Class that provides functions used for logging
 * @author Riffat shahzad
 *
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log {

	private static Logger log = null;

	private static synchronized void initializeLogger() {
		if (log == null) {
			log = LoggerFactory.getLogger(Log.class);
			ch.qos.logback.classic.Logger logbackLogger = (ch.qos.logback.classic.Logger) log;
			String logLevel = LocalProperties.getLogLevel();

			if (logLevel == null) {
				logbackLogger.setLevel(ch.qos.logback.classic.Level.INFO);
			} else if (logLevel.equals("ERROR")) {
				logbackLogger.setLevel(ch.qos.logback.classic.Level.ERROR);
			} else if (logLevel.equals("WARN")) {
				logbackLogger.setLevel(ch.qos.logback.classic.Level.WARN);
			} else if (logLevel.equals("INFO")) {
				logbackLogger.setLevel(ch.qos.logback.classic.Level.INFO);
			} else if (logLevel.equals("DEBUG")) {
				logbackLogger.setLevel(ch.qos.logback.classic.Level.DEBUG);
			} else if (logLevel.equals("TRACE")) {
				logbackLogger.setLevel(ch.qos.logback.classic.Level.TRACE);
			} else if (logLevel.equals("ALL")) {
				logbackLogger.setLevel(ch.qos.logback.classic.Level.ALL);
			} else {
				logbackLogger.setLevel(ch.qos.logback.classic.Level.INFO);
			}
		}
	}

	public static void startTestCase(String testMethodName) {
		initializeLogger();

		log.info(
				"                                                                                ");
		log.info(
				"**********************************************************************************************");
		log.info("Start of " + testMethodName + " Test Case execution !!!");
		log.info(
				"**********************************************************************************************");
		log.info(
				"                                                                                ");
	}

	public static void endTestCaseWithPassedStatus(String testMethodName) {
		initializeLogger();

		log.info(
				"                                                                                ");
		log.info(
				"**********************************************************************************************");
		log.info("End of " + testMethodName + " Test Case execution as Passed");
		log.info(
				"**********************************************************************************************");
		log.info(
				"                                                                                ");
	}

	public static void endTestCaseWithFailedStatus(String testMethodName) {
		initializeLogger();

		log.info(
				"                                                                                ");
		log.info(
				"**********************************************************************************************");
		log.info("End of " + testMethodName + " Test Case execution as Failed");
		log.info(
				"**********************************************************************************************");
		log.info(
				"                                                                                ");
	}

	public static void printText(String textToHighlight) {
		initializeLogger();

		log.info(
				"----------------------------------------------------------------------------------------------");
		log.info("  " + textToHighlight);
		log.info(
				"----------------------------------------------------------------------------------------------");
	}

	public static void trace(String message) {
		initializeLogger();
		log.trace(message);
	}

	public static void debug(String message) {
		initializeLogger();
		log.debug(message);
	}

	public static void info(String message) {
		initializeLogger();
		log.info(message);
	}

	public static void warn(String message) {
		initializeLogger();
		log.warn(message);
	}

	public static void error(String message, Throwable exceptionObject) {
		initializeLogger();
		log.error(message, exceptionObject);
	}

	public static void error(String message) {
		initializeLogger();
		log.error(message);
	}
}
