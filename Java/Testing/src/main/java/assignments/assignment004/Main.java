package assignments.assignment004;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.List;

public class Main {
	static WebDriver driver;
	static JavascriptExecutor js;

	static void sleep(long ms){try{Thread.sleep(ms);}catch(InterruptedException e){}}

	static WebElement find(String css) {
		return driver.findElement(By.cssSelector(css));
	}
	static List<WebElement> findAll(String css) {
		return driver.findElements(By.cssSelector(css));
	}
	static void scroll(WebElement e) {
		js.executeScript("arguments[0].scrollIntoView({block:'center'});", e);
	}

	static void click(String css) {
		WebElement e = driver.findElement(By.cssSelector(css));
		scroll(e);
		e.click();
	}
	static void clickAll(String css) {
		while(true)
			try{scroll(find(css));click(css);}catch(Exception e){return;}
	}

	static void type(String css, String data) {
		WebElement e = driver.findElement(By.cssSelector(css));
		scroll(e);
		e.clear();
		e.sendKeys(data);
	}

	static String text(String css) {
		return find(css).getText().trim();
	}

	

	static void verify(boolean condition, String msg) {
		if (condition)
			System.out.println("PASS -> " + msg);
		else
			System.out.println("FAIL -> " + msg);
	}

	
	
	
	
	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver",
				"/home/san-zstk426/Educational/Java/Testing/requirements/geckodriver-v0.36.0-linux64/geckodriver");
		driver = new FirefoxDriver();
		js = (JavascriptExecutor) driver;

		try {
			driver.get("https://www.carandbike.com/");

			System.out.println("Search Functionality");
			click("button[aria-label='Search'][type='button']");
			verify(driver.getTitle().toLowerCase().contains("car"), "Search with empty input");

			driver.navigate().back();
			sleep(1500);
			find("#brand-input").clear();
			find("#brand-input").sendKeys("zzzzzzz");
			click("button[aria-label='Search'][type='button']");
			verify(driver.getPageSource().toLowerCase().contains("no")
					|| driver.getPageSource().toLowerCase().contains("invalid"), "Search with invalid brand");

			driver.navigate().back();
			sleep(1500);

			find("#brand-input").clear();
			find("#brand-input").sendKeys("Hyundai");
			sleep(1500);
			verify(findAll("div[data-search-portal='true'] li").size() > 0, "Auto-suggestion dropdown appears");

			find("[data-search-portal='true'] li").click();
			click("button[aria-label='Search'][type='button']");
			verify(driver.getTitle().toLowerCase().contains("hyundai"), "Search with valid brand");

			driver.navigate().back();
			sleep(2000);

			System.out.println("Filter Functionality");

			click("h2+.flex>button:nth-of-type(2)");
			sleep(2000);

			click("#price-input");
			click("[data-search-portal='true'] li:nth-child(2)");
			click("[data-search-portal='true'] li:nth-child(3)");
			click("button[aria-label='Search'][type='button']");
			click("input[value='SUV']");
			sleep(1500);

			verify(findAll("ul.grid>li").size() > 0, "Filter by body type SUV");

			click("input[value='Sedan']");
			sleep(1500);
			verify(findAll("ul.grid > li").size() > 0, "Multiple filters applied");

			sleep(1500);
			clickAll("div.flex-wrap:nth-child(1) button");
			sleep(1500);
			verify(findAll("ul.grid>li").size() > 0, "Filters cleared");

			System.out.println("Car Details Page UI and Navigation");

			find("ul.grid>li").click();
			sleep(2000);
			verify(driver.getTitle().toLowerCase().contains(find("h1").getText().split(" ")[0].toLowerCase()),
					"Car detail page loaded");
			verify(find("h1").isDisplayed(), "Car name displayed");
			verify(find("img").isDisplayed(), "Car image displayed");
			verify(find("div.grid.mb-3").isDisplayed(), "Key specs displayed");

			driver.navigate().back();
			sleep(1500);
			verify(find("ul.grid").isDisplayed(), "Navigation back to listing works");

			System.out.println("Navigation & Menu");
			click(".h-11 > div:nth-child(1) > div:nth-child(1)>div:first-child");
			verify(driver.getTitle().toLowerCase().contains("new"), "Navigate to New Cars");

			click(".h-11 > div:nth-child(1) > div:nth-child(1)>div:nth-child(3)");
			verify(driver.getTitle().toLowerCase().contains("used"), "Navigate to Used Cars");

			click(".h-11 > div:nth-child(1) > div:nth-child(1)>div:nth-child(2)");
			verify(driver.getTitle().toLowerCase().contains("bike"), "Navigate to Bikes");

		} finally {
			driver.quit();
			driver=null;
			js=null;
			System.out.println("TEST EXECUTION COMPLETED !!!!!!!");
		}
	}
}
