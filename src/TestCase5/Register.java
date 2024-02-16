package TestCase5;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Register {

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
	public void info() {
		
		String title =driver.getTitle();
		String expectedTitle= "Automation Exercise";
		Assert.assertEquals(title, expectedTitle, "Page title doesn't match the expected title");
		
		driver.findElement(By.xpath("//li//a[text()=\" Signup / Login\"]")).click();
		String headingLogin= driver.findElement(By.xpath("//div[@class=\"signup-form\"]//h2")).getText();
		String expectedLoginTitle= "New User Signup!";
		Assert.assertEquals(headingLogin, expectedLoginTitle, "Page title doesn't match the expected title");
		
		driver.findElement(By.name("name")).sendKeys("Farhan");
		driver.findElement(By.xpath("//form//input[@data-qa=\"signup-email\"]")).sendKeys("fbijapure.fb@gmail.com");
		driver.findElement(By.xpath("//form//button[@data-qa=\"signup-button\"]")).click();
		
		String Warning=driver.findElement(By.xpath("//form//p")).getText();
		//System.out.println(Warning);
		String expectedWarning= "Email Address already exist!";
		Assert.assertEquals(Warning, expectedWarning, "Page title doesn't match the expected title");
	}
	
	@AfterTest
	public void close() {
		driver.close();
	}
}
