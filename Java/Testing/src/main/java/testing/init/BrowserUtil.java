package testing.init;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserUtil {
	
    public WebDriver open(String url) {

		System.setProperty("webdriver.gecko.driver", "/home/san-zstk426/Educational/Java/Testing/requirements/geckodriver-v0.36.0-linux64/geckodriver");
		System.setProperty("webdriver.chrome.driver", "/home/san-zstk426/Educational/Java/Testing/requirements/chromedriver-linux64/chromedriver");
        WebDriver driver = new FirefoxDriver();
        driver.get(url);
        return driver;
    }

    public String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public boolean titleEquals(WebDriver driver, String expectedTitle) {
        return getTitle(driver).equals(expectedTitle);
    }

    public void close(WebDriver driver) {
        if (driver != null) {
            driver.quit();
        }
    }
}
