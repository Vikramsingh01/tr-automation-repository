package com.meganexus.utills;

import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;

public class BasicUtill {
	public BasicUtill() {

	}

	private static WebDriver driver;

	public static WebDriver getDriver() {
		return driver;
	}

	public static void openBrowser(String browser) {

		if (browser.equalsIgnoreCase("firefox")) {

			FirefoxDriverManager.firefoxdriver().setup();
			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setCapability("marionette", true);
			// firefoxOptions.setLogLevel(FirefoxDriverLogLevel.TRACE);
			driver = new FirefoxDriver(firefoxOptions);
		}

		else if (browser.equalsIgnoreCase("chrome")) {

			ChromeDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("ie")) {
			InternetExplorerDriverManager.iedriver().setup();
			InternetExplorerOptions option = new InternetExplorerOptions();

			option.setCapability("requireWindowFocus", true);
			option.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, false);
			option.setCapability("ie.ensureCleanSession", true);
			option.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			option.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);
			driver = new InternetExplorerDriver(option);
		} else if (browser.equalsIgnoreCase("edge")) {
			EdgeDriverManager.edgedriver().setup();

			driver = new EdgeDriver();

		} else {
			System.out.println("Unable to create browser intance");
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void pageToLoadImplicitWait(int waitTime) {
		driver.manage().timeouts().implicitlyWait(waitTime, TimeUnit.SECONDS);

	}

	public static void waitForElement(String locatorType, String webElementNamePropfile) {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.elementToBeClickable(getElement(locatorType, webElementNamePropfile)));

	}

	public static void waitForElementVisible(String locatorType, String webElementNamePropfile) {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.visibilityOf(getElement(locatorType, webElementNamePropfile)));

	}

	public static void waitForElementPresent(By by) {
		WebDriverWait wait = new WebDriverWait(driver, 100);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));
	}

	public static Wait<WebDriver> waitABit(int time) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(time))
				.pollingEvery(Duration.ofMillis(5)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class);
		return wait;
	}

	public static WebElement getElement(String locatorType, String webElementNamePropfile) {
		WebElement element = null;
		final long startTime = System.currentTimeMillis();
		boolean found = false;
		while ((System.currentTimeMillis() - startTime) < 20000)
			try {
				if (locatorType.equals("id")) {
					element = driver.findElement(By.id(ApplicationProperties.getProperty(webElementNamePropfile)));
					found = true;
					break;
				} else if (locatorType.equals("name")) {
					element = driver.findElement(By.name(ApplicationProperties.getProperty(webElementNamePropfile)));
					found = true;
					break;
				} else if (locatorType.equals("xpath")) {
					element = driver.findElement(By.xpath(ApplicationProperties.getProperty(webElementNamePropfile)));
					found = true;
					break;
				} else if (locatorType.equals("className")) {
					element = driver
							.findElement(By.className(ApplicationProperties.getProperty(webElementNamePropfile)));
					found = true;
					break;
				} else if (locatorType.equals("tagName")) {
					element = driver.findElement(By.tagName(ApplicationProperties.getProperty(webElementNamePropfile)));
					found = true;
					break;
				} else if (locatorType.equals("linkText")) {
					element = driver
							.findElement(By.linkText(ApplicationProperties.getProperty(webElementNamePropfile)));
					found = true;
					break;
				} else if (locatorType.equals("partialLinkText")) {
					element = driver
							.findElement(By.partialLinkText(ApplicationProperties.getProperty(webElementNamePropfile)));
					found = true;
					break;
				} else if (locatorType.equals("cssSelector")) {
					element = driver
							.findElement(By.cssSelector(ApplicationProperties.getProperty(webElementNamePropfile)));
					found = true;
					break;
				} else {
					System.out.println("Locator type does not match");
				}
			} catch (Exception e) {
				System.out.println(webElementNamePropfile + " not found " + e.getMessage());
			}
		long endTime = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		if (found) {
			System.out.println("Successfully found the element: " + webElementNamePropfile + " - waited for: "
					+ totalTime + " ms");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("COULD NOT FIND THE ELEMENT: " + webElementNamePropfile + " - after waiting for: "
					+ totalTime + " ms");

		}

		return element;
	}

	public static void scrollToClickAnElement(String locatorType, String webElementNamePropfile) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", getElement(locatorType, webElementNamePropfile));
	}

	public static void tearDown() {
		if (driver != null)
			driver.quit();

	}

	// The below function verifyLink(String urlLink) verifies any broken links
	// and return the server status.

	public static void verifyLink(String urlLink) {
		// Sometimes we may face exception "java.net.MalformedURLException".
		// Keep the code in try catch block to continue the broken link analysis
		try {
			// Use URL Class - Create object of the URL Class and pass the
			// urlLink as parameter
			URL link = new URL(urlLink);
			// Create a connection using URL object (i.e., link)
			HttpURLConnection httpConn = (HttpURLConnection) link.openConnection();
			// Set the timeout for 2 seconds
			httpConn.setConnectTimeout(2000);
			// connect using connect method
			httpConn.connect();
			// use getResponseCode() to get the response code.
			if (httpConn.getResponseCode() == 200) {
				System.out.println(urlLink + " - " + httpConn.getResponseMessage());
			}
			if (httpConn.getResponseCode() == 404) {
				System.out.println(urlLink + " - " + httpConn.getResponseMessage());
			}
		}

		catch (Exception e) {
			e.getMessage();
		}

	}

	public static void selectAnElementWithText(String locatorType, String webElementNamePropfile, String text) {
		Select select = new Select(getElement(locatorType, webElementNamePropfile));
		select.selectByVisibleText(text);
	}

	public static void selectAnElementWithValue(String locatorType, String webElementNamePropfile, String value) {
		Select select = new Select(getElement(locatorType, webElementNamePropfile));
		select.selectByValue(value);
	}

	public static void selectAnElementWithIndex(String locatorType, String webElementNamePropfile, int indexNum) {
		Select select = new Select(getElement(locatorType, webElementNamePropfile));
		select.selectByIndex(indexNum);
	}

	public static void scrollToViewAnElement(String locatorType, String webElementNamePropfile) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				getElement(locatorType, webElementNamePropfile));
	}

	public void setZoomLevelForBrowser() {
		((JavascriptExecutor) driver).executeScript("document.body.style.zoom='90%';");
	}
}
