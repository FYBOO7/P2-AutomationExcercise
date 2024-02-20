package TestCase24;

import java.io.File;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class invoice {
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
		
		driver.findElement(By.xpath("//div[@class=\"modal-body\"]//a")).click();
	}
	
	@Test(dependsOnMethods = "AddtoCart")
	public void signup() {
		
		driver.findElement(By.xpath("//li//a[text()=\" Signup / Login\"]")).click();
		
		String titleLogin =driver.getTitle();
		String expectedTitleLogin= "Automation Exercise - Signup / Login";
		Assert.assertEquals(titleLogin, expectedTitleLogin, "Page title doesn't match the expected title");
		
		driver.findElement(By.name("name")).sendKeys("Farhan");
		driver.findElement(By.xpath("//form//input[@data-qa=\"signup-email\"]")).sendKeys("fbijapure.fb@gmail.com");
		driver.findElement(By.xpath("//form//button[@data-qa=\"signup-button\"]")).click();
	}
	
	@Test(dependsOnMethods = "signup")
	public void form() {
		
		driver.findElement(By.id("id_gender1")).click();
		
		driver.findElement(By.xpath("//input[@data-qa=\"password\"]")).sendKeys("Bijapurefb");
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		
		WebElement day = driver.findElement(By.id("days"));
		Select select = new Select(day);
		select.selectByVisibleText("1");
		
		WebElement month = driver.findElement(By.id("months"));
		Select month1= new Select(month);
		month1.selectByVisibleText("August");
		
		WebElement year = driver.findElement(By.id("years"));
		Select select_year = new Select(year);
		select_year.selectByVisibleText("1996");
		
		driver.findElement(By.id("newsletter")).click();
		driver.findElement(By.id("optin")).click();
		
		
		WebElement username=driver.findElement(By.name("first_name"));
				username.sendKeys("Farhan");
				
		driver.findElement(By.name("last_name")).sendKeys("Bijapure");
		driver.findElement(By.name("company")).sendKeys("ExcelR");
		driver.findElement(By.name("address1")).sendKeys("Solapur");
		driver.findElement(By.name("address2")).sendKeys("Pune");
		
		js.executeScript("window.scrollBy(0,500)");
		
		driver.findElement(By.name("state")).sendKeys("Maharashtra");
		driver.findElement(By.name("city")).sendKeys("Solapur");
		driver.findElement(By.name("zipcode")).sendKeys("413003");
		driver.findElement(By.name("mobile_number")).sendKeys("9021050023");
		
		js.executeScript("window.scrollBy(0,500)");
		
		driver.findElement(By.xpath("//form//button[@data-qa=\"create-account\"]")).click();
		
		String h3=driver.findElement(By.tagName("h2")).getText();
		String expectedh21="ACCOUNT CREATED!";
		Assert.assertEquals(h3, expectedh21, "Page title doesn't match the expected title");
		
		driver.findElement(By.xpath("//div//a[@data-qa=\"continue-button\"]")).click();	
		//String user =driver.findElement(By.xpath("//li//a[text()=\" Logged in as \"]//child::b")).getText();;
		
		//System.out.println(user);
		//Assert.assertEquals(user, , "Page title doesn't match the expected title");
		String user =driver.findElement(By.xpath("//li//a[text()=\" Logged in as \"]")).getText();
		String expectedUserTitle= "Logged in as Farhan";
		Assert.assertEquals(user, expectedUserTitle, "Page title doesn't match the expected title");
	}
	
	@Test(dependsOnMethods = "form")
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
	public void download() {
		driver.findElement(By.xpath("//a[text()=\"Download Invoice\"]")).click();
		try {
            Thread.sleep(5000); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

		  File downloadedFile = new File("C:\\Users\\Farhan\\Downloads\\Invoice.txt");
	        if (downloadedFile.exists()) {
	            System.out.println("Invoice downloaded successfully.");
	        } else {
	            System.out.println("Invoice download failed.");
	        }
       
		driver.findElement(By.xpath("//a[text()=\"Continue\"]")).click();
	}
	@Test(dependsOnMethods = "download")
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
