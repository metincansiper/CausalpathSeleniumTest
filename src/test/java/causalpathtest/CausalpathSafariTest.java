package causalpathtest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.safari.SafariDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CausalpathSafariTest extends CausalpathTest {
	public WebDriver makeDriver() {
		WebDriverManager.safaridriver().setup();
		WebDriver driver = new SafariDriver();
		return driver;
	}
}
