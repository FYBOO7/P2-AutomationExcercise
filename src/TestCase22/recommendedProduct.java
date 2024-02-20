package TestCase22;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class recommendedProduct {
		WebDriver driver;
		@BeforeTest
		public void initilize() 
		{
			ChromeOptions options = new ChromeOptions();
			options.addExtensions(new File("C:\\Users\\Farhan\\Desktop\\Selenium\\abp.crx")); 
			driver = new ChromeDriver(options);;
			
			driver.manage().window().maximize();
			driver.get("https://automationexercise.com/");
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		@Test
		public void recommend() {
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,7500)");
			
			
			String h2=driver.findElement(By.xpath("//h2[text()=\"recommended items\"]")).getText();
			
			String expectedh2="RECOMMENDED ITEMS";
			Assert.assertEquals(h2, expectedh2, "Page title doesn't match the expected title");
			
			//List<WebElement> products = driver.findElements(By.xpath("//div[@class=\"recommended_items\"]//p"));
			// Verify visibility of each product
	        //for (WebElement productNames : products) {
	          //  if (productNames.isDisplayed()) {
	           //     System.out.println("Visible Prodct Name ::" + productNames.getText());
	                
	         //       System.out.println(" ");
	          //  } else {
	         //       System.out.println("Product is not visible: " + productNames.getText());
	        //    }
	      //  }
	        
	        driver.findElement(By.xpath("//div[@class=\"recommended_items\"]//a[@data-product-id=\"1\"]")).click();
	        
	        driver.findElement(By.xpath("//div[@class=\"modal-body\"]//a")).click();
	        
	        System.out.println( "----- Products in Cart -----");
			List<WebElement> productElements = driver.findElements(By.xpath("//td[@class=\"cart_description\"]"));
			// Verify visibility of each product
	        for (WebElement productElement : productElements) {
	            if (productElement.isDisplayed() ) {
	                System.out.println("Product is visible: " + productElement.getText());
	                
	                System.out.println(" ");
	            } else {
	                System.out.println("Product is not visible: " + productElement.getText());
	            }
	        }
		}
}
