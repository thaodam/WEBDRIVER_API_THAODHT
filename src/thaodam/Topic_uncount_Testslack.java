package thaodam;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_uncount_Testslack {
    WebDriver driver;
    Actions action;

	@BeforeTest
	public void beforeTest() {
		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		action = new Actions(driver);
		driver.get("https://slack.com/");
	}

	@Test
	public void TC_01() throws InterruptedException {
		driver.findElement(By.xpath("//a[contains(text(),'Sign in')]")).click();
		driver.findElement(By.xpath("//input[@placeholder='your-workspace-url']")).sendKeys("automationtestadvance");
		driver.findElement(By.xpath("//span[@class='ladda-label align_middle']")).click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("damthanhthao12@gmail.com");
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("thaoque14");
		
		driver.findElement(By.xpath("//button[@id='signin_btn']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//button[@id='channel_title']")).isDisplayed());
		Thread.sleep(5000);
		driver.findElement(By.xpath("//span[@class='p-channel_sidebar__name']//span[contains(text(),'Damthanhthao12')]")).click();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.titleContains("Damthanhthao12 "));
		WebElement sendChat = driver.findElement(By.id("msg_input"));
		Actions actions = new Actions(driver);
		actions.moveToElement(sendChat);
		actions.click();
		actions.sendKeys("hello world, from Selenium");
		actions.sendKeys(Keys.RETURN);
		actions.build().perform();
	}
	

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}