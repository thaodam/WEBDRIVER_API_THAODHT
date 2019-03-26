package thaodam;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_06_USER_INTERACTIONS {
    WebDriver driver;
    Actions action;

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}

	@Test
	public void TC_01_Move_mouse_to_element() {
		driver.get("https://www.myntra.com/");
		WebElement profileButton = driver.findElement(By.xpath("//span[contains(text(),'Profile')]"));
		action.moveToElement(profileButton).perform();
		WebElement loginButton = driver.findElement(By.xpath("//a[contains(text(),'log in')]"));
		loginButton.click();
		WebElement loginForm = driver.findElement(By.xpath("//*[@class='login-title']"));
		Assert.assertEquals(loginForm.getText(), "Login to Myntra");
	}
	
	@Test
	public void TC_02_Click_and_hold_element_select_multiple_item() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");
		List<WebElement> listItems = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		Actions moveItem = new Actions(driver);
		moveItem.clickAndHold(listItems.get(0)).moveToElement(listItems.get(3)).release().perform();
	}
	@Test
	public void TC_03_Click_and_hold_element_select_multiple_item_random() {
		List<WebElement> elements = driver.findElements(By.xpath("//*[@id='selectable']/li"));
		Actions action = new Actions(driver);
		action.keyDown(Keys.CONTROL).perform();
		elements.get(0).click();
		elements.get(2).click();
		elements.get(4).click();
		elements.get(6).click();
		action.keyUp(Keys.CONTROL).perform();
	}
	@Test
	public void TC_04_Double_click() {
		driver.get("http://www.seleniumlearn.com/double-click");
		WebElement dbclickButton = driver.findElement(By.xpath("//button[contains(text(),'Double-Click Me!')]"));
		Actions action = new Actions(driver);
		action.doubleClick(dbclickButton).perform();
		Alert alert = driver.switchTo().alert();
		String textOnAlert = alert.getText();
		Assert.assertEquals(textOnAlert, "The Button was double-clicked.");
		alert.accept();
	}
	@Test
	public void TC_05_Right_click_to_element() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		WebElement rightClickMeBtn = driver.findElement(By.xpath("//span[text()='right click me']"));
		Actions action = new Actions(driver);
		action.contextClick(rightClickMeBtn).perform();
		WebElement quitButton = driver.findElement(By.xpath("//li[@class='context-menu-item context-menu-icon context-menu-icon-quit']//span[contains(text(),'Quit')]"));
		action.moveToElement(quitButton).perform();
		action.click(quitButton).perform();
		Alert alert = driver.switchTo().alert();
		alert.accept();
	}
	@Test
	public void TC_06_Drag_and_drop_element() {
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
		WebElement sourceButton = driver.findElement(By.xpath("//*[@id='draggable']"));
		WebElement targetButton = driver.findElement(By.xpath("//*[@id='droptarget']"));
		Actions action = new Actions(driver);
		action.dragAndDrop(sourceButton, targetButton).build().perform();
		String verifyText = targetButton.getText();
		Assert.assertEquals(verifyText,"You did great!");
		
	}
	

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}