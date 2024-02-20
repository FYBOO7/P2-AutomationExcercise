package TestCase25;

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

public class scrollUp {
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
	public void Sub() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight - 200)");
		
		String title =driver.findElement(By.xpath("//footer[@id=\"footer\"]//h2")).getText();
		String expectedTitle= "SUBSCRIPTION";
		Assert.assertEquals(title, expectedTitle, "Page title doesn't match the expected title");
		
		driver.findElement(By.id("scrollUp")).click();
		
	
		String title1 =driver.findElement(By.xpath("//div//h2[text()=\"Full-Fledged practice website for Automation Engineers\"]")).getText();
		String expectedTitle2= "Full-Fledged practice website for Automation Engineers";
		Assert.assertEquals(title1, expectedTitle2, "Page title doesn't match the expected title");
}
	
	@AfterTest
	public void close() {
		driver.close();
	}
}