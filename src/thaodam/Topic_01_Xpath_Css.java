package thaodam;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.junit.Assert;



public class Topic_01_Xpath_Css {
    WebDriver driver;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://live.guru99.com/");
	}

	@Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		String emailError = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(emailError, "This is a required field.");
		
		String passError = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(passError, "This is a required field.");
		
	}
	
	@Test
	public void TC_02_LoginWithInvalidEmail() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys("123434234@12312.123123");
		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("123123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		String emailError = driver.findElement(By.xpath("//*[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(emailError, "Please enter a valid email address. For example johndoe@domain.com.");
		
	}
	
	@Test
	public void TC_03_LoginWithPasswordLessThanSixChars() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		String passError = driver.findElement(By.xpath("//*[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(passError, "Please enter 6 or more characters without leading or trailing spaces.");
		
	}
	
	@Test
	public void TC_04_LoginWithPasswordIncorrect() {
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		driver.findElement(By.xpath("//*[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//*[@id='pass']")).sendKeys("123123123");
		driver.findElement(By.xpath("//button[@title='Login']")).click();
		
		String loginError = driver.findElement(By.xpath("//*[@id='top']/body/div[1]/div/div[2]/div/div/div/ul/li/ul/li/span")).getText();
		Assert.assertEquals(loginError, "Invalid login or password.");
			
	}
	

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}