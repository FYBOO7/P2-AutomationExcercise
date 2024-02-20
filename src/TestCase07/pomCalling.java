package TestCase07;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class pomCalling {

	public static void main(String[] args) {
		WebDriver driver;
		ChromeOptions options = new ChromeOptions();
		options.addExtensions(new File("C:\\Users\\Farhan\\Desktop\\Selenium\\abp.crx")); 
		driver = new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		testcase f=new testcase(driver);
		
		f.url();
		f.homePage();
		f.tcclick();
		f.tctitle();
		f.close();
		
	}

}
