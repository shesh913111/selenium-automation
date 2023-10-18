package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test1 {
	
	public static void main(String[] args) throws InterruptedException {
		
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		// for single text box
		driver.get("http://demo.seleniumeasy.com/basic-first-form-demo.html");
		driver.manage().window().maximize();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[text()='×']")).click();
		Thread.sleep(500);
		driver.findElement(By.className("form-control")).sendKeys("PRAVIN");
		driver.findElement(By.xpath("//*[text()='Show Message']")).click();
		
	
	    // for Two text box
		Thread.sleep(500);
		driver.findElement(By.id("sum1")).sendKeys("10");
		Thread.sleep(500);
		driver.findElement(By.id("sum2")).sendKeys("20");
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[text()='Get Total']")).click();
		
	    driver.close();
	}
	
	

}
