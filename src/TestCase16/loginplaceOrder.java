package TestCase16;

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

public class loginplaceOrder {
	WebDriver driver;
	@BeforeTest
	public void initilize() 
	{
		//driver = new ChromeDriver();
		ChromeOptions options = new ChromeOptions();
		options.addExtensions(new File("C:\\Users\\Farhan\\Desktop\\Selenium\\abp.crx")); 
		driver = new ChromeDriver(options);;
		
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
		String expectedUserTitle= "Logged in as Farhan";
		Assert.assertEquals(user, expectedUserTitle, "Page title doesn't match the expected title");
	}
	
	@Test(dependsOnMethods = "Valid")
	public void AddtoCart() {
		
		driver.findElement(By.xpath("//ul//a[text()=' Products']")).click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		
		driver.findElement(By.xpath("//a[@data-product-id=\"1\"]")).click();
		
		driver.findElement(By.xpath("//button[@data-dismiss=\"modal\"]")).click();
		
		
		driver.findElement(By.xpath("//a[@data-product-id=\"2\"]")).click();
		
		driver.findElement(By.xpath("//button[@data-dismiss=\"modal\"]")).click();
		
		driver.findElement(By.xpath("//div[@class=\"container\"]//li[3]")).click(); 
		
		driver.findElement(By.xpath("//section[@id=\"do_action\"]//a")).click();
		
		//driver.findElement(By.xpath("//div[@class=\"modal-body\"]//a")).click();
	}
	
	@Test(dependsOnMethods = "AddtoCart")
	public void checkout() {
		driver.findElement(By.xpath("//div[@class=\"container\"]//li[3]")).click(); 
		
		driver.findElement(By.partialLinkText("Proceed To Checkout")).click();
		
		System.out.println( "----Review Address-----");
		List<WebElement> address = driver.findElements(By.xpath("//ul[@id=\"address_delivery\"]"));

        // Verify visibility of each product
        for (WebElement productElement : address) {
            if (productElement.isDisplayed()) {
                System.out.println("YOUR DELIVERY ADDRESS " + productElement.getText());
                
                System.out.println(" \n");
            } else {
                System.out.println("Product is not visible: " + productElement.getText());
            }
        }
        JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		
		driver.findElement(By.className("form-control")).sendKeys("Order Placed");
	
		driver.findElement(By.partialLinkText("Place Order")).click();
        
	}
	
	@Test(dependsOnMethods = "checkout")
	public void payment() {
		
		driver.findElement(By.name("name_on_card")).sendKeys("SBI");;
		
		driver.findElement(By.name("card_number")).sendKeys("123456789");;
		
		driver.findElement(By.xpath("//input[@name=\"cvc\"]")).sendKeys("007");
		
		driver.findElement(By.name("expiry_month")).sendKeys("08");
		
		driver.findElement(By.name("expiry_year")).sendKeys("30");
		
		driver.findElement(By.xpath("//button[@id=\"submit\"]")).click();
	}
	
	@Test(dependsOnMethods = "payment")
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
