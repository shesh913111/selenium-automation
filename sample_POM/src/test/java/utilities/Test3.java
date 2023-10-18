package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Test3 {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver=new ChromeDriver();
		driver.get("http://demo.seleniumeasy.com/basic-radiobutton-demo.html");
		driver.manage().window().maximize();
		Thread.sleep(500);
		driver.findElement(By.name("optradio")).click();
		Thread.sleep(200);
		driver.findElement(By.xpath("//*[text()='Get Checked value']")).click();
        Thread.sleep(200);
        driver.findElement(By.name("gender")).click();
        Thread.sleep(200);
        driver.findElement(By.xpath("//*[text()='15 to 50']")).click();
        Thread.sleep(200);
        driver.findElement(By.xpath("//*[text()='Get values']")).click();
	
	    driver.close();
	}

}
