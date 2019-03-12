package thaodam;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_04_TEXTBOX_TEXTAREA {
    WebDriver driver;
    String customerId, customerName, gender, dateOfBirth, address, city, state, pin, mobileNumber, email, passWord;
    String addressEdit, cityEdit, stateEdit, pinEdit, mobileNumberEdit, emailEdit;
    By customerNameTextbox = By.xpath("//input[@name='name']");
    By genderRadiobtn = By.xpath("//input[@value='m']");
    By dateOfBirthTextbox = By.xpath("//input[@id='dob']");
    By addTextarea = By.xpath("//textarea[@name='addr']");
    By cityTextbox = By.xpath("//input[@name='city']");
    By stateTextbox = By.xpath("//input[@name='state']");
    By pinTextbox = By.xpath("//input[@name='pinno']");
    By mobileTextbox = By.xpath("//input[@name='telephoneno']");
    By emailTextbox = By.xpath("//input[@name='emailid']");
    By passTextbox = By.xpath("//input[@name='password']");
    By submitBtn = By.xpath("//input[@value='Submit']");
    
    
	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4");
		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr181358");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("berydUp");
		driver.findElement(By.xpath("//input[@value='LOGIN']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		customerName = "Automation Thao";
		gender = "male";
		dateOfBirth = "1991-12-02";
		address = "69 Le Huu Trac";
		city = "Da Nang";
		state = "Son Tra";
		pin = "123456";
		mobileNumber = "0987654321";
		email = "automation" + randomNumber() + "@gmail.com";
		passWord = "123456";
		
		addressEdit = "9696 Nguyen Duy Hieu";
		cityEdit = "Ha Noi";
		stateEdit = "Cau Giay";
		pinEdit = "789987";
		mobileNumberEdit = "0907513398";
		emailEdit = "automation" + randomNumber() + "@gmail.com";
		
	}

	@Test
	public void TC_01_CreateNewCustomer() {
		driver.findElement(By.xpath("//a[contains(text(),'New Customer')]")).click();
		driver.findElement(customerNameTextbox).sendKeys(customerName);
		driver.findElement(genderRadiobtn).click();
		driver.findElement(dateOfBirthTextbox).sendKeys(dateOfBirth);
		driver.findElement(addTextarea).sendKeys(address);
		driver.findElement(cityTextbox).sendKeys(city);
		driver.findElement(stateTextbox).sendKeys(state);
		driver.findElement(pinTextbox).sendKeys(pin);
		driver.findElement(mobileTextbox).sendKeys(mobileNumber);
		driver.findElement(emailTextbox).sendKeys(email);
		driver.findElement(passTextbox).sendKeys(passWord);
		driver.findElement(submitBtn).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()=\"Customer Registered Successfully!!!\"]")).isDisplayed());
		customerId = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		System.out.println(customerId);
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), dateOfBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), mobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), email);
		
		driver.findElement(By.xpath("//a[contains(text(),'Edit Customer')]")).click();
		driver.findElement(By.xpath("//input[@name='cusid']")).sendKeys(customerId);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		
		driver.findElement(addTextarea).clear();
		driver.findElement(addTextarea).sendKeys(addressEdit);
		driver.findElement(cityTextbox).clear();
		driver.findElement(cityTextbox).sendKeys(cityEdit);
		driver.findElement(stateTextbox).clear();
		driver.findElement(stateTextbox).sendKeys(stateEdit);
		driver.findElement(pinTextbox).clear();
		driver.findElement(pinTextbox).sendKeys(pinEdit);
		driver.findElement(mobileTextbox).clear();
		driver.findElement(mobileTextbox).sendKeys(mobileNumberEdit);
		driver.findElement(emailTextbox).clear();
		driver.findElement(emailTextbox).sendKeys(emailEdit);
		driver.findElement(submitBtn).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()=\"Customer details updated Successfully!!!\"]")).isDisplayed());
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(),customerId);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),addressEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),cityEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),stateEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),pinEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),mobileNumberEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),emailEdit);
	}
	

	@AfterTest
	public void afterTest() {
		driver.quit();
	}
	public int randomNumber(){
		Random random = new Random();
		int number = random.nextInt(999999);
		return number;
	}
}