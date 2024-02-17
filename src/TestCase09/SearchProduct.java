package TestCase09;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SearchProduct {
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
                System.out.println("Product is visible: " + productElement.getText());
                
                System.out.println(" \n");
            } else {
                System.out.println("Product is not visible: " + productElement.getText());
            }
        }
	}
}
