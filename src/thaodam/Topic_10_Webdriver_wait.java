package thaodam;

import java.sql.Timestamp;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_10_Webdriver_wait {
    WebDriver driver;
    WebDriverWait  waitExplicit;
    By btn_start = By.xpath("//div[@id='start']/button");
    By ico_loading = By.xpath("//div[@id='loading']/img");
    By txt_hello = By.xpath("//div[@id='finish']/h4[text()='Hello World!']");
    
    
	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		waitExplicit = new WebDriverWait(driver, 5);
	}

	@Test
	public void TC_01() {
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");		
		driver.findElement(btn_start).click();
		Assert.assertTrue(driver.findElement(txt_hello).isDisplayed());
	}
	
	@Test
	public void TC_02() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");		
		driver.findElement(btn_start).click();
		Assert.assertTrue(driver.findElement(txt_hello).isDisplayed());
	}
	@Test
	public void TC_03() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 2);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");		
		driver.findElement(btn_start).click();
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(ico_loading));		
		Assert.assertTrue(driver.findElement(txt_hello).isDisplayed());
	}
	@Test
	public void TC_04() {
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		waitExplicit = new WebDriverWait(driver, 5);
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");		
		
		//Ko visible + ko có trong DOM
		System.out.println("Start time = "+ getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(txt_hello));
		System.out.println("End time = "+ getDateTimeSecond());
		
		System.out.println("Start time = "+ getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(ico_loading));
		System.out.println("End time = "+ getDateTimeSecond());
		
		System.out.println("Start time = "+ getDateTimeSecond());
		driver.findElement(btn_start).click();
		System.out.println("End time = "+ getDateTimeSecond());
		
		System.out.println("Start time = "+ getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(txt_hello));
		System.out.println("End time = "+ getDateTimeSecond());
		
		System.out.println("Start time = "+ getDateTimeSecond());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(txt_hello));
		System.out.println("End time = "+ getDateTimeSecond());
	}
	
	public Date getDateTimeSecond() {
        Date date = new Date();
        date = new Timestamp(date.getTime());
        return date;
}
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}