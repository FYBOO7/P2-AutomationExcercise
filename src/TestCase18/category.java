package TestCase18;

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

public class category {
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
	public void categories() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		
		//Women
		
		driver.findElement(By.xpath("//h4[@class=\"panel-title\"]//a")).click();
		
		driver.findElement(By.xpath("//div[@id=\"Women\"]//a[text()=\"Dress \"]")).click();
		
		String headingW= driver.findElement(By.xpath("//div[@class=\"features_items\"]//h2")).getText();
		String expectedW= "WOMEN - DRESS PRODUCTS";
		Assert.assertEquals(headingW, expectedW, "Page title doesn't match the expected title");
		
		//Men
		
		driver.findElement(By.xpath("//h4[@class=\"panel-title\"]//a[@href=\"#Men\"]")).click();
		
		driver.findElement(By.xpath("//div[@id=\"Men\"]//a[text()=\"Tshirts \"]")).click();
		
		String headingM= driver.findElement(By.xpath("//h2[@class=\"title text-center\"]")).getText();
		System.out.println("headingM");
		String expectedM= "MEN - TSHIRTS PRODUCTS";
		Assert.assertEquals(headingM, expectedM, "Page title doesn't match the expected title");
	}
	
	@AfterTest
	public void close() {
		driver.close();
	}
}
