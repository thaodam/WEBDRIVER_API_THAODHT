package thaodam;

import java.util.List;
import java.util.Set;
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

public class Topic_08_window_popup {
	WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor javascript;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		waitExplicit = new WebDriverWait(driver, 30);
		javascript = (JavascriptExecutor) driver;
		
	}

	@Test
	public void TC_02() {
		
		driver.get("https://daominhdam.github.io/basic-form/index.html");
		String parentWindow = driver.getWindowHandle();
		driver.findElement(By.xpath("//a[contains(text(),'Click Here')]")).click();
		switchToWindowByTitle("Google");
		String childTitle = driver.getTitle();
//		System.out.println(childTitle);
		Assert.assertEquals(childTitle, "Google");
		closeAllWithoutParentWindows(parentWindow);
		String parentTitle = driver.getTitle();
//		System.out.println(parentTitle);
		Assert.assertEquals(parentTitle, "SELENIUM WEBDRIVER FORM DEMO");
	}	
	@Test
	public void TC_03() {
		driver.get("http://www.hdfcbank.com/");
		String parentWindow = driver.getWindowHandle();
		String parentTitle = driver.getTitle();
		List <WebElement> notificationIframe = driver.findElements(By.xpath("//div[@id='parentdiv']//img[@class='popupbanner']")); 
		int notificationIframeSize = notificationIframe.size();
		
		if (notificationIframeSize > 0){
			javascript.executeScript("arguments[0].click()", driver.findElement(By.xpath("//img[@class='popupCloseButton']")));
			}
		
		driver.findElement(By.xpath("//div[@class='sectionnav']//a[@target='_blank'][contains(text(),'Agri')]")).click();
		switchToWindowByTitle("HDFC Bank Kisan Dhan Vikas e-Kendra");
		driver.findElement(By.xpath("//p[contains(text(),'Account Details')]")).click();
		switchToWindowByTitle("Welcome to HDFC Bank NetBanking");
		
		WebElement privacyFrame = driver.findElement(By.xpath("//frame[@name='footer']"));
		driver.switchTo().frame(privacyFrame);
		driver.findElement(By.xpath("//a[contains(text(),'Privacy Policy')]")).click();
		driver.switchTo().defaultContent();
		switchToWindowByTitle("HDFC Bank - Leading Bank in India, Banking Services, Private Banking, Personal Loan, Car Loan");
		driver.findElement(By.xpath("//a[@title='Corporate Social Responsibility']")).click();
		closeAllWithoutParentWindows(parentWindow);
		
		Assert.assertEquals(parentTitle, "HDFC Bank: Personal Banking Services");
		
	}

	@Test
	public void TC_04() {
		driver.get("http://live.guru99.com/index.php/");
		driver.findElement(By.xpath("//a[contains(text(),'Mobile')]")).click();
		String parentWindow = driver.getWindowHandle();
		String parentTitle = driver.getTitle();
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']")).click();

		driver.findElement(By.xpath("//button[@title='Compare']//span//span[contains(text(),'Compare')]")).click();
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		String compareTitle = driver.getTitle();
		Assert.assertEquals(compareTitle, "Products Comparison List - Magento Commerce");
		closeAllWithoutParentWindows(parentWindow);		
		Assert.assertEquals(parentTitle, "Mobile");
	}
	public void switchToWindowByTitle(String title) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
                    driver.switchTo().window(runWindows);
                    String currentWin = driver.getTitle();
                    if (currentWin.equals(title)) {
                                break;
                    }
        }
}
	public boolean closeAllWithoutParentWindows(String parentWindow) {
        Set<String> allWindows = driver.getWindowHandles();
        for (String runWindows : allWindows) {
                    if (!runWindows.equals(parentWindow)) {
                                driver.switchTo().window(runWindows);
                                driver.close();
                    }
        }
        driver.switchTo().window(parentWindow);
        if (driver.getWindowHandles().size() == 1)
                   return true;
        else
                   return false;
}
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}