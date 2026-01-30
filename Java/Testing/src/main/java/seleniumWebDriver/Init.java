package seleniumWebDriver;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Init {
	public static void main(String[] args) {
		
		WebDriver driver;
		
		
		System.setProperty("webdriver.gecko.driver", "/home/san-zstk426/Educational/Java/Testing/requirements/geckodriver-v0.36.0-linux64/geckodriver");
		System.setProperty("webdriver.chrome.driver", "/home/san-zstk426/Educational/Java/Testing/requirements/chromedriver-linux64/chromedriver");
		boolean useFirefox = false;
		if (useFirefox)driver = new FirefoxDriver();
		else driver = new ChromeDriver();
    	
    	
    	driver.get("https://www.google.com");
    	System.out.println("Title: "+driver.getTitle());
        try {
        	Thread.sleep(5000);
        	Scanner sc = new Scanner(System.in);
        	sc.nextLine();
        	sc.close();
        	driver.quit();
        } catch(Exception e) {
        	e.printStackTrace();
        }
	}
}
