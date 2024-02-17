package TestCase13;

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

public class quantity {
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
	@Test
	public void quantity_cart() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		
		
		driver.findElement(By.xpath("//a[contains(@href, '/product_details')][1]")).click();
		
		WebElement quantityInput = driver.findElement(By.xpath("//input[@id='quantity']"));

        // Clear the existing value and enter the new quantity (4)
        quantityInput.clear();
        quantityInput.sendKeys("4");
        
        driver.findElement(By.xpath("//button[@type=\"button\"]")).click();
        
        driver.findElement(By.xpath("//div[@class=\"modal-content\"]//a")).click(); 
        
        String productQuantity = driver.findElement(By.xpath("//td[@class=\"cart_quantity\"]")).getText();
        
        String expectedQuantity = "4";

        // Verify if the displayed quantity matches the expected quantity
        if (productQuantity.equals(expectedQuantity)) {
            System.out.println("Product is displayed in the cart with the exact quantity: " + expectedQuantity);
        } else {
            System.out.println("Product is not displayed in the cart with the exact quantity. Expected: "
                    + expectedQuantity + ", Actual: " + productQuantity);
        }
	}
	
	@AfterTest
	public void close() {
		driver.close();
	}
}
