package TestCase11;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class subscription_Cart {
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
	public void Sub() {
		
		driver.findElement(By.xpath("//div[@class=\"container\"]//li[3]")).click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight - 200)");
		
		String title =driver.findElement(By.xpath("//footer[@id=\"footer\"]//h2")).getText();
		String expectedTitle= "SUBSCRIPTION";
		Assert.assertEquals(title, expectedTitle, "Page title doesn't match the expected title");
        
        driver.findElement(By.id("susbscribe_email")).sendKeys("fbijapure.fb@gmail.com");
        
        driver.findElement(By.id("subscribe")).click();
        
        WebElement successMessageElement = driver.findElement(By.xpath("//div[@id='success-subscribe']"));

        // Verify if the success message is visible
        if (successMessageElement.isDisplayed()) {
            System.out.println("Success message 'You have been successfully subscribed!' is visible.");
        } else {
            System.out.println("Success message is not visible.");
        }
	}
	@AfterTest
	public void close() {
		driver.close();
	}
}

