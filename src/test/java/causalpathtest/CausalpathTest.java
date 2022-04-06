package causalpathtest;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public abstract class CausalpathTest {
	String baseUrl = "http://35.192.108.199:3001";
//	List displaySampleTimes = Collections.synchronizedList(new ArrayList<String>());
//	List visualizeAnalysisTimes = Collections.synchronizedList(new ArrayList<String>());
	
	public abstract WebDriver makeDriver();
	
	@Test(invocationCount = 2, threadPoolSize = 2)
	public void causalpathDisplayDemoGraphs() {
		WebDriver driver = makeDriver();
		Actions actions = new Actions(driver);
		System.out.printf("%n[START] Thread Id : %s is started!", 
                Thread.currentThread().getId());
		driver.manage().window().maximize();
		driver.get(baseUrl);
		// TODO: could be a more robust way of finding this element?
		WebElement demoButton = driver.findElement(By.xpath("//*[contains(text(),'Display demo graphs')]"));
//		long startTime = System.currentTimeMillis();
		demoButton.click();
//		long endTime   = System.currentTimeMillis();
		
//		addTimeToList(startTime, endTime, displaySampleTimes);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("___samples")));
		
		WebElement treeItem = driver.findElement(By.id("___samples"));
		actions.doubleClick(treeItem).perform();
		
		JavascriptExecutor j = (JavascriptExecutor) driver;
		String cyLengthStr = (String)j.executeScript("return window.cy.elements().length.toString()");
		Assert.assertNotEquals("0", cyLengthStr);
		System.out.printf("%n[END] Thread Id : %s", 
                Thread.currentThread().getId());
		driver.quit();
	}
	
	@Test(invocationCount = 2, threadPoolSize = 2)
	public void causalpathVisualizeAnalysis() {
		WebDriver driver = makeDriver();
		Actions actions = new Actions(driver);
		System.out.printf("%n[START] Thread Id : %s is started!", 
                Thread.currentThread().getId());
		driver.manage().window().maximize();
		driver.get(baseUrl);
		WebElement uploadButton = driver.findElement(By.id("graph-file-input"));
		
		String projectPath = System.getProperty("user.dir");
		Path inputPath = Paths.get(projectPath, "src", "test", "resources", "round2");
		
//		long startTime = System.currentTimeMillis();
		uploadButton.sendKeys(inputPath.toString());
//		long endTime   = System.currentTimeMillis();
		
//		addTimeToList(startTime, endTime, visualizeAnalysisTimes);
		
		WebElement folderTreeEl = driver.findElement(By.id("folder-tree"));
		Assert.assertNotNull(folderTreeEl);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("___round2")));
		
		WebElement treeItem = driver.findElement(By.id("___round2"));
		actions.doubleClick(treeItem).perform();
		
		JavascriptExecutor j = (JavascriptExecutor) driver;
		String cyLengthStr = (String)j.executeScript("return window.cy.elements().length.toString()");
		Assert.assertNotEquals("0", cyLengthStr);
		driver.quit();
	}
	
//	@AfterClass
//	public void afterClass() {
//		System.out.println("Display sample times in seconds: " + displaySampleTimes);
//		System.out.println("Visualize analysis times in seconds: " + visualizeAnalysisTimes);	
//	}
	
//	public void printExecutionTime(long startTime, long endTime) {
//		NumberFormat formatter = new DecimalFormat("#0.00000");
//		System.out.println("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds");
//	}
//	
//	public void addTimeToList(long startTime, long endTime, List list) {
//		NumberFormat formatter = new DecimalFormat("#0.00000");
//		list.add(formatter.format((endTime - startTime) / 1000d));
////		System.out.println("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds");
//	}
}
