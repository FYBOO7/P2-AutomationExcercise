package TestCase8;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ProductPage {
	
	WebDriver driver;
	@BeforeTest
	public void initilize() 
	{
		driver = new ChromeDriver();
		
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
	public void productInfo() {
		
		driver.findElement(By.xpath("//ul//a[text()=' Products']")).click();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		
		//driver.findElement(By.xpath("//ul//a[text()=' Products']"));
		String h2=driver.findElement(By.xpath("//div//h2[text()=\"All Products\"]")).getText();
		
		System.out.println(h2);
		String expectedh2="ALL PRODUCTS";
		Assert.assertEquals(h2, expectedh2, "Page title doesn't match the expected title");
		
		
		
		driver.findElement(By.xpath("//a[contains(@href, '/product_details')][1]")).click();
		
		
			WebElement productNameElement = driver.findElement(By.xpath("//div[@Class=\"product-information\"]//h2"));
	        String productName = productNameElement.getText();
	        System.out.println("Product Name: " + productName);

	       
	        WebElement categoryElement = driver.findElement(By.xpath("//div[@Class=\"product-information\"]//p"));
	        String category = categoryElement.getText();
	        System.out.println("Category: " + category);

	       
	        WebElement priceElement = driver.findElement(By.xpath("//div[@Class=\"product-information\"]/span//span"));
	        String price = priceElement.getText();
	        System.out.println("Price: " + price);

	        
	        WebElement availabilityElement = driver.findElement(By.xpath("//div[@Class=\"product-information\"]//p[2]"));
	        String availability = availabilityElement.getText();
	        System.out.println( availability);

	        
	        WebElement conditionElement = driver.findElement(By.xpath("//div[@Class=\"product-information\"]//p[3]"));
	        String condition = conditionElement.getText();
	        System.out.println( condition);

	        
	        WebElement brandElement = driver.findElement(By.xpath("//div[@Class=\"product-information\"]//p[4]"));
	        String brand = brandElement.getText();
	        System.out.println( brand);
	}

	@AfterTest
	public void close() {
		driver.close();
	}
}

