package thaodam;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import org.junit.Assert;

import java.util.Random;
import java.util.UUID;



public class Topic_09_javascript_executor {
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

		System.setProperty("webdriver.chrome.driver", ".\\lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
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
	public void TC_01(){
		navigateToUrlByJS("http://live.guru99.com/");
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String domain = (String) js.executeScript("return document.domain");
		Assert.assertEquals(domain, "live.guru99.com");
		String url = (String) js.executeScript("return document.URL");
		Assert.assertEquals(url, "http://live.guru99.com/");
		WebElement mobile = driver.findElement(By.xpath("//a[contains(text(),'Mobile')]"));
		clickToElementByJS(mobile);
		WebElement addToCart = driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button"));
		clickToElementByJS(addToCart);		
		String innerText = js.executeScript("return document.documentElement.innerText;").toString();
		Assert.assertTrue(innerText.contains("Samsung Galaxy was added to your shopping cart."));
		WebElement privacyPolicy = driver.findElement(By.xpath("//a[contains(text(),'Privacy Policy')]"));
		clickToElementByJS(privacyPolicy);
		String title = (String) js.executeScript("return document.title");
		Assert.assertEquals(title, "Privacy Policy");
		scrollToBottomPage();
		Assert.assertTrue(driver.findElement(By.xpath("//th[text()='WISHLIST_CNT']/following-sibling::td[text()= 'The number of items in your Wishlist.']")).isDisplayed());
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		String demoPage = (String) js.executeScript("return document.domain");
		Assert.assertEquals(demoPage, "demo.guru99.com");
	}
	@Test
	public void TC_02() throws Exception {
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sendkeyToElementByJS(driver.findElement(By.xpath("//input[@name='uid']")), "mngr181358");
		sendkeyToElementByJS(driver.findElement(By.xpath("//input[@name='password']")), "berydUp");
		clickToElementByJS(driver.findElement(By.xpath("//input[@value='LOGIN']")));
		Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		
		clickToElementByJS(driver.findElement(By.xpath("//a[contains(text(),'New Customer')]")));
		sendkeyToElementByJS(driver.findElement(customerNameTextbox), customerName);
		clickToElementByJS(driver.findElement(genderRadiobtn));
		removeAttributeInDOM(driver.findElement(By.xpath("//input[@id='dob']")), "type");
		sendkeyToElementByJS(driver.findElement(dateOfBirthTextbox), dateOfBirth);
		driver.findElement(addTextarea).sendKeys(address);
		sendkeyToElementByJS(driver.findElement(cityTextbox), city);
		sendkeyToElementByJS(driver.findElement(stateTextbox), state);
		sendkeyToElementByJS(driver.findElement(pinTextbox), pin);
		sendkeyToElementByJS(driver.findElement(mobileTextbox), mobileNumber);
		sendkeyToElementByJS(driver.findElement(emailTextbox), email);
		sendkeyToElementByJS(driver.findElement(passTextbox), passWord);
		clickToElementByJS(driver.findElement(submitBtn));
		
		Thread.sleep(10000);
		
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
		
		clickToElementByJS(driver.findElement(By.xpath("//a[contains(text(),'Edit Customer')]")));
		sendkeyToElementByJS(driver.findElement(By.xpath("//input[@name='cusid']")), customerId);
		clickToElementByJS(driver.findElement(By.xpath("//input[@value='Submit']")));
		
		
		driver.findElement(addTextarea).clear();
		driver.findElement(addTextarea).sendKeys(addressEdit);
		driver.findElement(cityTextbox).clear();
		sendkeyToElementByJS(driver.findElement(cityTextbox), cityEdit);
		driver.findElement(stateTextbox).clear();
		sendkeyToElementByJS(driver.findElement(stateTextbox), stateEdit);
		driver.findElement(pinTextbox).clear();
		sendkeyToElementByJS(driver.findElement(pinTextbox), pinEdit);
		driver.findElement(mobileTextbox).clear();
		sendkeyToElementByJS(driver.findElement(mobileTextbox), mobileNumberEdit);
		driver.findElement(emailTextbox).clear();
		sendkeyToElementByJS(driver.findElement(emailTextbox), emailEdit);
		clickToElementByJS(driver.findElement(submitBtn));
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()=\"Customer details updated Successfully!!!\"]")).isDisplayed());
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText(),customerId);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),addressEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),cityEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),stateEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),pinEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),mobileNumberEdit);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),emailEdit);
	}
	@Test
	public void TC_03() {
		navigateToUrlByJS("http://live.guru99.com/");		
		clickToElementByJS(driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")));
		clickToElementByJS(driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")));
		sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='firstname']")), "thao");
		sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='lastname']")), "Dam");
		
		String random = UUID.randomUUID().toString();
		sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='email_address']")), random + "@gmail.com");
		sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='password']")), "123123123");
		sendkeyToElementByJS(driver.findElement(By.xpath("//input[@id='confirmation']")), "123123123");
		clickToElementByJS(driver.findElement(By.xpath("//span[contains(text(),'Register')]")));
		
		String success = driver.findElement(By.xpath("//li[@class='success-msg']//ul//li")).getText();
		Assert.assertEquals(success, "Thank you for registering with Main Website Store.");
		
		clickToElementByJS(driver.findElement(By.xpath("//span[@class='label'][contains(text(),'Account')]")));
		clickToElementByJS(driver.findElement(By.xpath("//a[@title='Log Out']")));
		
		
		String logoutsuccess = driver.findElement(By.xpath("//h1[contains(text(),'You are now logged out')]")).getText();
		Assert.assertEquals(logoutsuccess,"YOU ARE NOW LOGGED OUT");
		
		Assert.assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'This is demo site for')]")).isDisplayed());
		
	}
	
	public void highlightElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.border='6px groove red'", element);
    }

    public Object executeForBrowser(String javaSript) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript(javaSript);
    }

    public Object clickToElementByJS(WebElement element) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("arguments[0].click();", element);
    }

    public Object sendkeyToElementByJS(WebElement element, String value) {
           JavascriptExecutor js = (JavascriptExecutor) driver;
           return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
    }

    public Object removeAttributeInDOM(WebElement element, String attribute) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
    }

    public Object scrollToBottomPage() {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public Object navigateToUrlByJS(String url) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            return js.executeScript("window.location = '" + url + "'");
    }
    public int randomNumber(){
		Random random = new Random();
		int number = random.nextInt(999999);
		return number;
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}