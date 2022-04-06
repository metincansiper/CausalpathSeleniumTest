package causalpathtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CausalpathChromeTest extends CausalpathTest {


	public WebDriver makeDriver() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	
}
