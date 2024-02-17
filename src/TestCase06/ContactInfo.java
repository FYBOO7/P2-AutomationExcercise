package TestCase06;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ContactInfo {

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
	public void contactInfo() {
		
		driver.findElement(By.xpath("//ul//a[text()=' Contact us']")).click();
		
		String h2=driver.findElement(By.xpath("//div//h2[text()=\"Get In Touch\"]")).getText();
		
		System.out.println(h2);
		String expectedh2="GET IN TOUCH";
		Assert.assertEquals(h2, expectedh2, "Page title doesn't match the expected title");
		
		
		driver.findElement(By.name("name")).sendKeys("Farhan");
		driver.findElement(By.name("email")).sendKeys("fbijapure.fb@gmail.com");
		driver.findElement(By.name("subject")).sendKeys("Damaged Product");
		driver.findElement(By.id("message")).sendKeys("Faulty product");
		
		
		
		WebElement Fileinput = driver.findElement(By.xpath("//div//input[@type=\"file\"]"));
		File filetoUpload =new File("C:\\Users\\Farhan\\Desktop\\Test.txt");
		Fileinput.sendKeys(filetoUpload.getAbsolutePath());
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		driver.findElement(By.xpath("//div//input[@name=\"submit\"]")).click();
		
		Alert a= driver.switchTo().alert();
		a.accept();
		
		
		WebElement msg= driver.findElement(By.xpath("//div[@class=\"status alert alert-success\"]"));
		
        if (msg.isDisplayed()) {
            System.out.println("Success message is visible");
        } else {
            System.out.println("Success message is not visible");
		}
        
        WebElement homePageVerify = driver.findElement(By.xpath("//div//span"));
        homePageVerify.click();
        
        if (homePageVerify.isDisplayed()) {
            System.out.println("Landed on the home page successfully");
        } 
        else {
            System.out.println("Failed to land on the home page");
        }
	}
	
	@AfterTest
	public void close() {
		driver.close();
	}
}
