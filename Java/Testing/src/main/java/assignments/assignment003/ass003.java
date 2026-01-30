package assignments.assignment003;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.File;

public class ass003 {

	static WebDriver driver;
	static JavascriptExecutor jsEngine;

	public static void main(String[] args) {

		System.setProperty("webdriver.gecko.driver",
				"/home/san-zstk426/Educational/Java/Testing/requirements/geckodriver-v0.36.0-linux64/geckodriver");
		System.setProperty("webdriver.chrome.driver",
				"/home/san-zstk426/Educational/Java/Testing/requirements/chromedriver-linux64/chromedriver");

		boolean useFirefox = true;
		driver = useFirefox ? new FirefoxDriver() : new ChromeDriver();
		try {
			jsEngine = (JavascriptExecutor) driver;
			driver.manage().window().maximize();

			herokuAppLoginPageTest();
			demoQAPracticeFormTest();
			orangeHRMTest();
		} finally {
			driver.quit();
		}
	}

	static void sleep(long t){try{Thread.sleep(t);}catch(InterruptedException e){}}

	static void openURL(String url) {
		driver.get(url);
		System.out.println("Opened URL: " + url);
	}

	static void checkTitle(String expected) {
		String actual = driver.getTitle();
		if (actual.contains(expected))
			System.out.println("PASS: Title verified -> " + actual);
		else
			System.out.println("FAIL: Title mismatch -> " + actual);
	}

	static void checkElementPresent(By locator, String name) {
		try {
			WebElement element = driver.findElement(locator);
			jsEngine.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
			if (element.isDisplayed())
				System.out.println("PASS: Element present -> " + name);
		} catch (Exception e) {
			System.out.println("FAIL: Element missing -> " + name);
		}
	}

	static void type(By locator, String value, String field) {
		try {

			WebElement element = driver.findElement(locator);
			jsEngine.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
			element.clear();
			element.sendKeys(value);
			element.click();
			System.out.println("Typed into " + field);
		} catch (Exception e) {
			System.out.println("FAIL typing into " + field);
		}
	}

	static void click(By locator, String name) {
		try {
			WebElement element = driver.findElement(locator);
			jsEngine.executeScript("arguments[0].scrollIntoView({block:'center'});", element);
			element.click();
			System.out.println("Clicked -> " + name);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("FAIL clicking -> " + name);
		}
	}

	static void checkTextPresent(By locator, String expectedText) {
		try {
			WebElement element = driver.findElement(locator);
			jsEngine.executeScript("arguments[0].scrollIntoView({block:'center'});", element);

			String actual = element.getText();
			if (actual.contains(expectedText))
				System.out.println("PASS: Text verified -> " + expectedText);
			else
				System.out.println("FAIL: Text mismatch -> " + actual);
		} catch (Exception e) {
			System.out.println("FAIL: Text not found");
		}
	}

	static void herokuAppLoginPageTest() {

		System.out.println("\n===== HEROKUAPP LOGIN TEST =====");

		openURL("https://the-internet.herokuapp.com/login");
		checkTitle("The Internet");

		checkElementPresent(By.id("username"), "Username field");
		checkElementPresent(By.id("password"), "Password field");
		checkElementPresent(By.cssSelector("button[type='submit']"), "Login button");

		click(By.cssSelector("button[type='submit']"), "Login without credentials");
		checkTextPresent(By.id("flash"), "Your username is invalid");

		type(By.id("username"), "wrong", "Username");
		type(By.id("password"), "wrong", "Password");
		click(By.cssSelector("button[type='submit']"), "Invalid login");
		checkTextPresent(By.id("flash"), "Your username is invalid");

		type(By.id("username"), "tomsmith", "Username");
		type(By.id("password"), "SuperSecretPassword!", "Password");

		sleep(1000);

		click(By.cssSelector("button[type='submit']"), "Valid login");

		sleep(2000);

		checkTextPresent(By.id("flash"), "You logged into a secure area");
		checkElementPresent(By.cssSelector(".icon-signout"), "Logout button");

		sleep(2000);

		click(By.cssSelector(".icon-signout"), "Logout");
		checkTextPresent(By.id("flash"), "You logged out");
	}

	static void demoQAPracticeFormTest() {

		System.out.println("\n===== DEMOQA PRACTICE FORM TEST =====");

		openURL("https://demoqa.com/automation-practice-form");
		checkTitle("DEMOQA");

		type(By.id("firstName"), "John", "First Name");
		type(By.id("lastName"), "Doe", "Last Name");

		type(By.id("userEmail"), "invalidEmail", "Email");
		click(By.id("submit"), "Submit with invalid email");

		type(By.id("userEmail"), "john@test.com", "Email");
		click(By.xpath("//label[text()='Male']"), "Gender Male");

		type(By.id("userNumber"), "123", "Mobile");
		click(By.id("submit"), "Invalid mobile length");
		type(By.id("userNumber"), "9876543210", "Mobile");

		click(By.id("dateOfBirthInput"), "DOB Picker");
		new Select(driver.findElement(By.className("react-datepicker__month-select"))).selectByVisibleText("May");
		new Select(driver.findElement(By.className("react-datepicker__year-select"))).selectByVisibleText("1998");
		click(By.xpath("//div[text()='15']"), "DOB Day");

		File file = new File(
				"/home/san-zstk426/Educational/Java/Testing/requirements/geckodriver-v0.36.0-linux64/geckodriver");
		driver.findElement(By.id("uploadPicture")).sendKeys(file.getAbsolutePath());
		System.out.println("File uploaded");

		click(By.id("submit"), "Final submit");
		checkElementPresent(By.id("example-modal-sizes-title-lg"), "Submission modal");
	}

	static void orangeHRMTest() {

		System.out.println("\n===== ORANGEHRM TEST =====");

		openURL("https://opensource-demo.orangehrmlive.com/");
		checkTitle("OrangeHRM");
		sleep(2000);

		checkElementPresent(By.cssSelector("[name=username]"), "Username field");
		checkElementPresent(By.cssSelector("[name=password]"), "Password field");

		type(By.cssSelector("[name=username]"), "wrong", "Username");
		type(By.cssSelector("[name=password]"), "wrong", "Password");
		click(By.cssSelector("button"), "Invalid login");

		sleep(2000);
		checkTextPresent(By.cssSelector(".oxd-alert-content-text"), "Invalid credentials");

		type(By.name("username"), "Admin", "Username");
		type(By.name("password"), "admin123", "Password");
		click(By.cssSelector("button[type='submit']"), "Valid login");

		sleep(2000);

		checkElementPresent(By.xpath("//span[text()='Dashboard']"), "Dashboard");

		click(By.cssSelector(".oxd-userdropdown-tab"), "Profile menu");
		click(By.xpath("//a[text()='Logout']"), "Logout");

		sleep(2000);

		checkElementPresent(By.name("username"), "Login page after logout");
	}
}
