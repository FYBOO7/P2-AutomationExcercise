package TestCase07;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class testcase {

	WebDriver driver;
	
	testcase(WebDriver driver){
		this.driver=driver;
	}
	//repository
	
	By tc= By.xpath("//div[@class=\"container\"]//li[5]");
	By tctitle1= By.xpath("//section[@id=\"form\"]//b");
	
	
	public void url() {
		driver.get("https://automationexercise.com/");
	}
	
	public void homePage() {
		
		String title =driver.getTitle();
		String expectedTitle= "Automation Exercise";
		Assert.assertEquals(title, expectedTitle, "Page title doesn't match the expected title");
	}
	
	public void tcclick() {
		driver.findElement(tc).click();
	}
	
	public void tctitle() {
		String title = driver.findElement(tctitle1).getText();
		System.out.println(title);
		String expectedTitle= "TEST CASES";
		Assert.assertEquals(title, expectedTitle, "Page title doesn't match the expected title");
	}
	
	public void close() {
		driver.close();
	}
}
