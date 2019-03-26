package thaodam;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_07_iframe_popup {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor javascript;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		waitExplicit = new WebDriverWait(driver, 30);
		javascript = (JavascriptExecutor) driver;
		
	}

	@Test
	public void TC_01() {
		driver.get("http://www.hdfcbank.com/");
		List <WebElement> notificationIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']")); 
		int notificationIframeSize = notificationIframe.size();
		
		if (notificationIframeSize > 0){
			driver.switchTo().frame(notificationIframe.get(0));
			Assert.assertTrue(driver.findElement(By.xpath("//div[@id='container-div']/img")).isDisplayed());
			
			javascript.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[@id='div-close']")));
			
			driver.switchTo().defaultContent();
			
			}
	WebElement lookingForIframe = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
	
	driver.switchTo().frame(lookingForIframe);
	
	Assert.assertTrue(driver.findElement(By.xpath("//span[@id='messageText' and text()='What are you looking for?']")).isDisplayed());
	
	driver.switchTo().defaultContent();
	
	WebElement slidingBannerIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
	
	driver.switchTo().frame(slidingBannerIframe);
	
	List <WebElement> bannerImage = driver.findElements(By.xpath("//img[@class='bannerimage']"));
	Assert.assertEquals(bannerImage.size(), 6);
	
	for (WebElement image:bannerImage){
		Assert.assertTrue(isImageLoadedSuccess(image));
		}
	driver.switchTo().defaultContent();
	List <WebElement> flipBanner = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
	Assert.assertEquals(flipBanner.size(), 8);
	
	for (WebElement image:flipBanner){
		Assert.assertTrue(isImageLoadedSuccess(image));
		Assert.assertTrue(image.isDisplayed());
		
		}
	
	}
	
	
	public boolean isImageLoadedSuccess (WebElement imageElement){
		return (boolean) javascript.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", imageElement);
		}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}