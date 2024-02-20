package TestCase21;

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

public class reviewProduct {
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
		
		String h2=driver.findElement(By.xpath("//div//h2[text()=\"All Products\"]")).getText();
		
		//System.out.println(h2);
		String expectedh2="ALL PRODUCTS";
		Assert.assertEquals(h2, expectedh2, "Page title doesn't match the expected title");
		
		List<WebElement> products = driver.findElements(By.xpath("//div[@class=\"productinfo text-center\"]//p"));

        // Verify visibility of each product
        for (WebElement productNames : products) {
            if (productNames.isDisplayed()) {
                System.out.println("Visible Prodct Name ::" + productNames.getText());
                
                System.out.println(" ");
            } else {
                System.out.println("Product is not visible: " + productNames.getText());
            }
        }
		
        driver.findElement(By.xpath("//a[contains(@href, '/product_details')][1]")).click();
	}
	
	@Test(dependsOnMethods = "productInfo")
	public void review() {
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,300)");
		
		String name=driver.findElement(By.xpath("//li[@class=\"active\"]")).getText();
		
		System.out.println(name);
		String expectedh="WRITE YOUR REVIEW";
		Assert.assertEquals(name, expectedh, "Page title doesn't match the expected title");
		
		driver.findElement(By.id("name")).sendKeys("Farhan");
		
		driver.findElement(By.id("email")).sendKeys("fbijapure.fb@gmail.com");
		
		driver.findElement(By.name("review")).sendKeys("Good Product");
		
		driver.findElement(By.id("button-review")).click();
		
		WebElement successMessageElement = driver.findElement(By.xpath("//form//div[@class=\"alert-success alert\"]"));
		
		String successMessage = successMessageElement.getText();

        
        String expectedMessage = "Thank you for your review.";

        
        Assert.assertEquals(successMessage, expectedMessage, "Success message does not match expected");
	}
	
	@AfterTest
	public void close() {
		driver.close();
	}
}
