package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test2 {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("http://demo.seleniumeasy.com/basic-checkbox-demo.html");
		driver.manage().window().maximize();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[text()='Click on this check box']")).click();
		Thread.sleep(500);
		driver.findElement(By.id("check1")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//*[text()='Option 2']")).click();
	
	   driver.close();
	}

}
