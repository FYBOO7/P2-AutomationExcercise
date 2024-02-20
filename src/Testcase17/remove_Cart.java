package TestCase17;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class remove_Cart {

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
		
		driver.findElement(By.xpath("//div[@class=\"container\"]//li[3]")).click();// cart
		
		
		
		driver.findElement(By.xpath("//td[@class=\"cart_delete\"]//a")).click(); //remove
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//h4//a[text()=\"Blue Top\"]")));

        // Verify that the product is no longer present in the cart
        if (driver.findElements(By.xpath("//h4//a[text()=\"Blue Top\"]")).isEmpty()) {
            System.out.println("Product successfully removed from the cart");
        } else {
            System.out.println("Product is still present in the cart after removal");
        }
	}
	
	
	@AfterTest
	public void close() {
		driver.close();
	}
}
