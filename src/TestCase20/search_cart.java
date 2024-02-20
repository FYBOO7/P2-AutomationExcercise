package TestCase20;

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

public class search_cart {
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
	public void search() {
		
		driver.findElement(By.xpath("//ul//a[text()=' Products']")).click();
		
		//driver.findElement(By.xpath("//ul//a[text()=' Products']"));
		String h2=driver.findElement(By.xpath("//div//h2[text()=\"All Products\"]")).getText();
		
		//System.out.println(h2);
		String expectedh2="ALL PRODUCTS";
		Assert.assertEquals(h2, expectedh2, "Page title doesn't match the expected title");
		
		driver.findElement(By.name("search")).sendKeys("Blue top");
		driver.findElement(By.id("submit_search")).click();
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,500)");
		
		
		String h3=driver.findElement(By.xpath("//div//h2[text()=\"Searched Products\"]")).getText();
		
		//System.out.println(h3);
		String expectedh3="SEARCHED PRODUCTS";
		Assert.assertEquals(h3, expectedh3, "Page title doesn't match the expected title");
	}
	@Test(dependsOnMethods = "search")
	public void search_product() {
		
		System.out.println( "----- Search result-----");
		List<WebElement> productElements = driver.findElements(By.xpath("//div[@class=\"features_items\"]"));

        // Verify visibility of each product
        for (WebElement productElement : productElements) {
            if (productElement.isDisplayed()) {
                System.out.println(" " + productElement.getText());
                
                System.out.println(" \n");
            } else {
                System.out.println("Product is not visible: " + productElement.getText());
            }
        }
	}
	
	@Test(dependsOnMethods = "search_product")
	public void cart() {
		
		driver.findElement(By.xpath("//a[@data-product-id=\"1\"]")).click();
		
		driver.findElement(By.xpath("//button[@data-dismiss=\"modal\"]")).click();
		
		driver.findElement(By.xpath("//div[@class=\"container\"]//li[3]")).click(); 
		
List<WebElement> productElements = driver.findElements(By.xpath("//td[@class=\"cart_description\"]"));
		
		List<WebElement> productPrice = driver.findElements(By.xpath("//td[@class=\"cart_price\"]"));
		
		List<WebElement> productQuantity = driver.findElements(By.xpath("//td[@class=\"cart_quantity\"]"));
		
		List<WebElement> productTotal = driver.findElements(By.xpath("//td[@class=\"cart_total\"]"));
		
        // Verify visibility of each product
        for (WebElement productElement : productElements) {
            if (productElement.isDisplayed() ) {
                System.out.println("Product is visible in Cart: " + productElement.getText());
                
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
	
	@Test(dependsOnMethods = "cart")
	public void login() {
		
		driver.findElement(By.xpath("//div[@class=\"container\"]//li[4]")).click(); 
		
		driver.findElement(By.xpath("//form//input[@data-qa=\"login-email\"]")).sendKeys("fbijapure.fb@gmail.com");
		driver.findElement(By.xpath("//form//input[@data-qa=\"login-password\"]")).sendKeys("Bijapurefb");
		driver.findElement(By.xpath("//form//button[@data-qa=\"login-button\"]")).click();
		
		driver.findElement(By.xpath("//div[@class=\"container\"]//li[3]")).click();
		
		
List<WebElement> productElements = driver.findElements(By.xpath("//td[@class=\"cart_description\"]"));
		
		List<WebElement> productPrice = driver.findElements(By.xpath("//td[@class=\"cart_price\"]"));
		
		List<WebElement> productQuantity = driver.findElements(By.xpath("//td[@class=\"cart_quantity\"]"));
		
		List<WebElement> productTotal = driver.findElements(By.xpath("//td[@class=\"cart_total\"]"));
		
        // Verify visibility of each product
		System.out.println("Product after login ");
        for (WebElement productElement : productElements) {
            if (productElement.isDisplayed() ) {
                System.out.println("Product is visible in Cart: " + productElement.getText());
                
            } else {
                System.out.println("Product is not visible: " + productElement.getText());
            }
        }
        
        for (WebElement productPrices : productPrice) {
            if (productPrices.isDisplayed() ) {
                System.out.println("Price " + productPrices.getText());
               
            } else {
                System.out.println("Product is not visible: " + productPrices.getText());
            }
        }
        
        
        for (WebElement Quantity : productQuantity) {
            if (Quantity.isDisplayed() ) {
                System.out.println("Quantity: " + Quantity.getText());
                
            } else {
                System.out.println("Product is not visible: " + Quantity.getText());
            }
        }
        
        
        for (WebElement total : productTotal) {
            if (total.isDisplayed() ) {
                System.out.println("Total: " + total.getText());
                System.out.println("\n");
            } else {
                System.out.println("Product is not visible: " + total.getText());
            }
        }
	}
	
	@AfterTest
	public void close() {
		driver.close();
	}
}
