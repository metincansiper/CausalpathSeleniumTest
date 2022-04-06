package causalpathtest;

import io.github.bonigarcia.wdm.WebDriverManager;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class CausalpathTestOld {
	List displaySampleTimes = Collections.synchronizedList(new ArrayList<String>());
	List visualizeAnalysisTimes = Collections.synchronizedList(new ArrayList<String>());
	
//	@Test(invocationCount = 20, threadPoolSize = 20)
//	public void causalpathHome() {
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
////		try {
////			Thread.sleep(20000);
////		} catch (InterruptedException e) {
////			e.printStackTrace();
////		}
//		System.out.printf("%n[START] Thread Id : %s is started!", 
//                Thread.currentThread().getId());
//		driver.get("https://google.com/");
//		System.out.println("Page Title is " + driver.getTitle());
//		Assert.assertEquals("CausalPath", driver.getTitle());
//		System.out.printf("%n[END] Thread Id : %s", 
//                Thread.currentThread().getId());
//		driver.quit();
//	}
	
//	@Test(invocationCount = 20, threadPoolSize = 20)
//	public void causalpathDisplaySample() {
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		System.out.printf("%n[START] Thread Id : %s is started!", 
//                Thread.currentThread().getId());
//		driver.manage().window().maximize();
//		driver.get("https://causalpath.org/");
//		// TODO: could be a more robust way of finding this element?
//		WebElement demoButton = driver.findElement(By.xpath("//*[contains(text(),'Display a sample result')]"));
//		long startTime = System.currentTimeMillis();
//		demoButton.click();
//		long endTime   = System.currentTimeMillis();
//		
//		addTimeToList(startTime, endTime, displaySampleTimes);
////		displaySampleTimes.add(endTime - startTime);
//		
////		printExecutionTime(startTime, endTime);
//		
//		JavascriptExecutor j = (JavascriptExecutor) driver;
//		String cyLengthStr = (String)j.executeScript("return window.cy.elements().length.toString()");
//		Assert.assertNotEquals("0", cyLengthStr);
//		System.out.printf("%n[END] Thread Id : %s", 
//                Thread.currentThread().getId());
//		driver.quit();
//	}
	
	@Test(invocationCount = 20, threadPoolSize = 20)
	public void causalpathVisualizeAnalysis() {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		System.out.printf("%n[START] Thread Id : %s is started!", 
                Thread.currentThread().getId());
		driver.manage().window().maximize();
		driver.get("https://causalpath.org/");
		WebElement uploadButton = driver.findElement(By.id("graph-file-input"));
		
		String projectPath = System.getProperty("user.dir");
		Path inputPath = Paths.get(projectPath, "src", "test", "resources", "round2");
		
		long startTime = System.currentTimeMillis();
		uploadButton.sendKeys(inputPath.toString());
		long endTime   = System.currentTimeMillis();
		
		addTimeToList(startTime, endTime, visualizeAnalysisTimes);
//		visualizeAnalysisTimes.add(endTime - startTime);
//		printExecutionTime(startTime, endTime);
		
		WebElement folderTreeEl = driver.findElement(By.id("folder-tree"));
		Assert.assertNotNull(folderTreeEl);
		driver.quit();
	}
	
	@AfterClass
	public void afterClass() {
		System.out.println("Display sample times in seconds: " + displaySampleTimes);
		System.out.println("Visualize analysis times in seconds: " + visualizeAnalysisTimes);	
	}
	
	public void printExecutionTime(long startTime, long endTime) {
		NumberFormat formatter = new DecimalFormat("#0.00000");
		System.out.println("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds");
	}
	
	public void addTimeToList(long startTime, long endTime, List list) {
		NumberFormat formatter = new DecimalFormat("#0.00000");
		list.add(formatter.format((endTime - startTime) / 1000d));
//		System.out.println("Execution time is " + formatter.format((endTime - startTime) / 1000d) + " seconds");
	}
}
