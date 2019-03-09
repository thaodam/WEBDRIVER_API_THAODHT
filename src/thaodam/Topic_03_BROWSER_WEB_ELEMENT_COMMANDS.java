package thaodam;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_03_BROWSER_WEB_ELEMENT_COMMANDS {
    WebDriver driver;
    

	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://daominhdam.github.io/basic-form/index.html");
	}

	@Test
	public void TC_01() {
		WebElement findEmail;
		WebElement ageUnder18;
		WebElement education;
		
		findEmail = driver.findElement(By.xpath("//input[@id='mail']"));
		if(findEmail.isDisplayed()){
			findEmail.sendKeys("Automation Testing");
			}
		ageUnder18 = driver.findElement(By.xpath("//input[@id='under_18']"));
		if(ageUnder18.isDisplayed()){
			ageUnder18.click();
		}
		education = driver.findElement(By.xpath("//textarea[@id='edu']"));
		if(education.isDisplayed()){
			education.sendKeys("Automation Testing");
			}
		
	}
	@Test
	public void TC_02() {
		WebElement emailEnable;
		emailEnable = driver.findElement(By.xpath("//input[@id='mail']"));
		if(emailEnable.isEnabled()){
			System.out.println("Email is Enabled");
			}
		else System.out.println("Email is Disabled");
		WebElement ageUnder18;
		ageUnder18 = driver.findElement(By.xpath("//input[@id='under_18']"));
		if(ageUnder18.isEnabled()){
			System.out.println("Age Under 18 is Enabled");
		}
		else System.out.println("Age Under 18 is Disabled");
		WebElement education;
		education = driver.findElement(By.xpath("//textarea[@id='edu']"));
		if(education.isEnabled()){
			System.out.println("Education is Enabled");
		}
		else System.out.println("Education is Disabled");
		WebElement jobrole01;
		jobrole01 = driver.findElement(By.xpath("//select[@id='job1']"));
		if(jobrole01.isEnabled()){
			System.out.println("Job Role 01 is Enabled");
		}
		else System.out.println("Job Role 01 is Disabled");
		WebElement interestDev;
		interestDev = driver.findElement(By.xpath("//input[@id='development']"));
		if(interestDev.isEnabled()){
			System.out.println("Interests Development is Enabled");
		}
		else System.out.println("Interests Development is Disabled");
		WebElement slide01;
		slide01 = driver.findElement(By.xpath("//input[@id='slider-1']"));
		if(slide01.isEnabled()){
			System.out.println("Slide 01 is Enabled");
		}
		else System.out.println("Slide 01 is Disabled");
		WebElement pass;
		pass = driver.findElement(By.xpath("//input[@id='password']"));
		if(pass.isEnabled()){
			System.out.println("Password is Enabled");
		}
		else System.out.println("Password is Disabled");
		WebElement ageDisable;
		ageDisable = driver.findElement(By.xpath("//input[@id='radio-disabled']"));
		if(ageDisable.isEnabled()){
			System.out.println("Age button is Enabled");
		}
		else System.out.println("Age button is Disabled");
		WebElement bio;
		bio = driver.findElement(By.xpath("//textarea[@id='bio']"));
		if(bio.isEnabled()){
			System.out.println("Biography is Enabled");
		}
		else System.out.println("Biography is Disabled");
		WebElement jobRole2;
		jobRole2 = driver.findElement(By.xpath("//select[@id='job2']"));
		if(jobRole2.isEnabled()){
			System.out.println("Job Role 02 is Enabled");
		}
		else System.out.println("Job Role 02 is Disabled");
		WebElement interestDis;
		interestDis = driver.findElement(By.xpath("//input[@id='check-disbaled']"));
		if(interestDis.isEnabled()){
			System.out.println("Interests disable is Enabled");
		}
		else System.out.println("Interests disable is Disabled");
		WebElement slide02;
		slide02 = driver.findElement(By.xpath("//input[@id='slider-2']"));
		if(slide02.isEnabled()){
			System.out.println("Slide 02 is Enabled");
		}
		else System.out.println("Slide 02 disable is Disabled");
		WebElement btnDis;
		btnDis = driver.findElement(By.xpath("//button[@id='button-disabled']"));
		if(btnDis.isEnabled()){
			System.out.println("Button Disabled is Enabled");
		}
		else System.out.println("Button Disabled is Disabled");
		WebElement btnEn;
		btnEn = driver.findElement(By.xpath("//button[@id='button-enabled']"));
		if(btnEn.isEnabled()){
			System.out.println("Button Enabled is Enabled");
			}
		else System.out.println("Button Enabled is Disabled");
		
	}
	@Test
	public void TC_03() {
		WebElement under18 = driver.findElement(By.xpath("//input[@id='under_18']"));
		WebElement interestDev = driver.findElement(By.xpath("//input[@id='development']"));
		WebElement over18 = driver.findElement(By.xpath("//input[@id='over_18']"));
		over18.click();
		interestDev.click();
		interestDev.click();
		if (under18.isSelected()){
			System.out.println("Age Under 18 is selected");
		}
		else under18.click();
		System.out.println("Age Under 18 has just selected");
		if (interestDev.isSelected()){
			System.out.println("Interests Development is selected");
		}
		else interestDev.click();
		System.out.println("Interests Development has just selected");
	}

	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}