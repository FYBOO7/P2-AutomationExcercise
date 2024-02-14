package TestCase2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Valid_login {
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
	public void Valid() {
		
		String title =driver.getTitle();
		String expectedTitle= "Automation Exercise";
		Assert.assertEquals(title, expectedTitle, "Page title doesn't match the expected title");
		
		driver.findElement(By.xpath("//li//a[text()=\" Signup / Login\"]")).click();
		String headingLogin= driver.findElement(By.xpath("//div[@class=\"login-form\"]//h2")).getText();
		String expectedLoginTitle= "Login to your account";
		Assert.assertEquals(headingLogin, expectedLoginTitle, "Page title doesn't match the expected title");
		
		driver.findElement(By.xpath("//form//input[@data-qa=\"login-email\"]")).sendKeys("fbijapure.fb@gmail.com");
		driver.findElement(By.xpath("//form//input[@data-qa=\"login-password\"]")).sendKeys("Bijapurefb");
		driver.findElement(By.xpath("//form//button[@data-qa=\"login-button\"]")).click();
		
		String user =driver.findElement(By.xpath("//li//a[text()=\" Logged in as \"]")).getText();
		String expectedUserTitle= "Logged in as Farhan Biajpure";
		Assert.assertEquals(user, expectedUserTitle, "Page title doesn't match the expected title");
	}
	
	@Test(dependsOnMethods = "Valid")
	public void deleteAccount() {
		
		driver.findElement(By.xpath("//li//a[text()=\" Delete Account\"]")).click();
		String h2=driver.findElement(By.tagName("h2")).getText();
		String expectedh2="ACCOUNT DELETED!";
		Assert.assertEquals(h2, expectedh2, "Page title doesn't match the expected title");
		
		driver.findElement(By.xpath("//div//a[@data-qa=\"continue-button\"]")).click();
	}
	
	@AfterTest
	public void close() {
		driver.close();
	}
}
