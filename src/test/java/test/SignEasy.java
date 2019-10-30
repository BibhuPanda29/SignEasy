package test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SignEasy {
	WebDriver driver = null;
	String projectPath = System.getProperty("user.dir");
	WebDriverWait wait = null;
  
	@BeforeTest
	public void launch() {
		System.setProperty("webdriver.chrome.driver", "D:\\SeleniuimDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		//navigate to URL
		driver.get("https://app.signeasy.com/");
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		//Login user
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("bbhpanda681@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Bibhu@123");
		driver.findElement(By.xpath("//button[@id='auto_login']")).click();
		
		System.out.println(projectPath);
		
	}
	@Test
	public void selfSigning() throws InterruptedException, IOException {
		Thread.sleep(5000);
		//Clicking on Start Signing
		driver.findElement(By.id("auto_start_signing")).click();
		driver.findElement(By.xpath("//p[text()='Sign Yourself']")).click();
		Thread.sleep(3000);
		//Script to upload file using autoit
		Runtime.getRuntime().exec(projectPath +"/Fileupload/fileuploadscript.exe");
		wait = new WebDriverWait(driver, 80);
		WebElement Signature = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//a[@class='signature marker']"))));
		driver.findElement(By.xpath("//a[@class='date marker']")).click();
		Actions act1 = new Actions(driver);
		act1.moveByOffset(788, 330).click().build().perform();
		Thread.sleep(8000);
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", Signature);		
		driver.findElement(By.xpath("//tbody/tr[1]/td[@id='initial0']")).click();
		driver.findElement(By.xpath("//button[contains(text(),'Use')]")).click();
		act1.moveByOffset(488, 230).click().build().perform();	
		driver.findElement(By.id("auto_docViewer_finish")).click();	  
	}
	@AfterTest
	public void close() {
		driver.close();
	}
}
