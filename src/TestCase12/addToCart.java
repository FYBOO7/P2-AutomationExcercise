package TestCase12;

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
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class addToCart {
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
	public void AddtoCart() {
		
		driver.findElement(By.xpath("//ul//a[text()=' Products']")).click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		
		driver.findElement(By.xpath("//a[@data-product-id=\"1\"]")).click();
		
		driver.findElement(By.xpath("//button[@data-dismiss=\"modal\"]")).click();
		
		
		driver.findElement(By.xpath("//a[@data-product-id=\"2\"]")).click();
		
		driver.findElement(By.xpath("//button[@data-dismiss=\"modal\"]")).click();
		
		driver.findElement(By.xpath("//div[@class=\"container\"]//li[3]")).click(); 
		
		System.out.println( "----- Products in Cart -----");
		List<WebElement> productElements = driver.findElements(By.xpath("//td[@class=\"cart_description\"]"));
		
		List<WebElement> productPrice = driver.findElements(By.xpath("//td[@class=\"cart_price\"]"));
		
		List<WebElement> productQuantity = driver.findElements(By.xpath("//td[@class=\"cart_quantity\"]"));
		
		List<WebElement> productTotal = driver.findElements(By.xpath("//td[@class=\"cart_total\"]"));
		
        // Verify visibility of each product
        for (WebElement productElement : productElements) {
            if (productElement.isDisplayed() ) {
                System.out.println("Product is visible: " + productElement.getText());
                
                System.out.println(" \n");
            } else {
                System.out.println("Product is not visible: " + productElement.getText());
            }
        }
        
        for (WebElement productPrices : productPrice) {
            if (productPrices.isDisplayed() ) {
                System.out.println("Price " + productPrices.getText());
                
                System.out.println(" \n");
            } else {
                System.out.println("Product is not visible: " + productPrices.getText());
            }
        }
        
        
        for (WebElement Quantity : productQuantity) {
            if (Quantity.isDisplayed() ) {
                System.out.println("Quantity: " + Quantity.getText());
                
                System.out.println(" \n");
            } else {
                System.out.println("Product is not visible: " + Quantity.getText());
            }
        }
        
        
        for (WebElement total : productTotal) {
            if (total.isDisplayed() ) {
                System.out.println("Total: " + total.getText());
                
                System.out.println(" \n");
            } else {
                System.out.println("Product is not visible: " + total.getText());
            }
        }
	}
	
	@AfterTest
	public void close() {
		//driver.close();
	}
}
