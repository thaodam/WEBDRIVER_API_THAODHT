package thaodam;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.junit.Assert;
import java.util.UUID;



public class Topic_02_Xpath_Css {
    WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.guru99.com/");
	}

	@Test
	public void TC_05_CreateAnAccount() throws Exception {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Thao");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Dam");
		
		String random = UUID.randomUUID().toString();
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(random + "@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123123123");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123123123");
		driver.findElement(By.xpath("//span[contains(text(),'Register')]")).click();
		
		String success = driver.findElement(By.xpath("//li[@class='success-msg']//ul//li")).getText();
		Assert.assertEquals(success, "Thank you for registering with Main Website Store.");
		
		driver.findElement(By.xpath("//span[@class='label'][contains(text(),'Account')]")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		String logoutsuccess = driver.findElement(By.xpath("//h1[contains(text(),'You are now logged out')]")).getText();
		Assert.assertEquals(logoutsuccess,"YOU ARE NOW LOGGED OUT");
		
		Thread.sleep(10000);
		
		String navigateHomepage = driver.getTitle();
		Assert.assertEquals(navigateHomepage,"Home page");
		System.out.println(navigateHomepage);
	}
	

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}