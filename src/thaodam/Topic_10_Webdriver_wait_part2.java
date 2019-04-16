package thaodam;

import java.sql.Timestamp;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_10_Webdriver_wait_part2 {
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
		driver.get("http://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");		
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='demo-container size-narrow']")));
		WebElement selectedDay = driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel']"));
		String txt_beforeSelectedDay = selectedDay.getText();
		System.out.println(txt_beforeSelectedDay);
		driver.findElement(By.xpath("//a[contains(text(),'23')]")).click();
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='raDiv']")));
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(@class,'rcSelected')]//a[text()='23']")));
		String txt_afterSelectedDay = selectedDay.getText();
		System.out.println(txt_afterSelectedDay);
		Assert.assertEquals(txt_afterSelectedDay, "Tuesday, April 23, 2019");
	}
	
	@Test
	public void TC_02() {
		driver.get("https://daominhdam.github.io/fluent-wait/");		
		WebElement countdount =  driver.findElement(By.xpath("//div[@id='javascript_countdown_time']"));
		waitExplicit.until(ExpectedConditions.visibilityOf(countdount));

		// Khởi tạo Fluent wait
		new FluentWait<WebElement>(countdount)
		           // Tổng time wait là 15s
		           .withTimeout(15, TimeUnit.SECONDS)
		            // Tần số mỗi 1s check 1 lần
		            .pollingEvery(1, TimeUnit.SECONDS)
		           // Nếu gặp exception là find ko thấy element sẽ bỏ  qua
		            .ignoring(NoSuchElementException.class)
		            // Kiểm tra điều kiện
		            .until(new Function<WebElement, Boolean>() {
		                public Boolean apply(WebElement element) {
		                           // Kiểm tra điều kiện countdount = 00
		                           boolean flag =  element.getText().endsWith("00");
		                           System.out.println("Time = " +  element.getText());
		                           // return giá trị cho function apply
		                           return flag;
		                }
		           });
	}
	

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}