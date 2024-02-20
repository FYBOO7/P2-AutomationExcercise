package TestCase19;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class brand {
	WebDriver driver;
	@BeforeTest
	public void initilize() 
	{
		
		ChromeOptions options = new ChromeOptions();
		options.addExtensions(new File("C:\\Users\\Farhan\\Desktop\\Selenium\\abp.crx"));
		driver = new ChromeDriver(options);
		
		
		driver.manage().window().maximize();
		driver.get("https://automationexercise.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@Test
	public void homePage() {
		String title =driver.getTitle();
		String expectedTitle= "Automation Exercise";
		Assert.assertEquals(title, expectedTitle, "Page title doesn't match the expected title");
	}
	
	
	@Test(dependsOnMethods = "homePage")
	public void brands() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		
		driver.findElement(By.xpath("//div[@class=\"brands-name\"]//li[1]")).click();
		
		
		String heading1= driver.findElement(By.xpath("//h2[text()=\"Brand - Polo Products\"]")).getText();
		String expected1= "BRAND - POLO PRODUCTS";
		Assert.assertEquals(heading1, expected1, "Page title doesn't match the expected title");
		
		
		driver.findElement(By.xpath("//div[@class=\"brands-name\"]//li[2]")).click();
		
		String heading2= driver.findElement(By.xpath("//h2[text()=\"Brand - H&M Products\"]")).getText();
		String expected2= "BRAND - H&M PRODUCTS";
		Assert.assertEquals(heading2, expected2, "Page title doesn't match the expected title");
	}
	
	@AfterTest
	public void close() {
		driver.quit();
	}
}
