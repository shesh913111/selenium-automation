package utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestUtility
{
	//Properties
	public RemoteWebDriver driver;
	//Constructor method 
	public TestUtility()
	{
		driver=null;
	}
	//Operational methods
	public RemoteWebDriver openBrowser(String bn)
	{
		if(bn.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();  //dynamic binding
		}
		else if(bn.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver","D:\\Softwaretestingimportance\\geckodriver.exe");
			//WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}
		else if(bn.equalsIgnoreCase("Opera"))
		{
			System.setProperty("webdriver.opera.driver","D:\\Softwaretestingimportance\\operadriver(1).exe");
			//WebDriverManager.operadriver().setup();
			driver=new OperaDriver();
		}
		else if(bn.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();
		}
		else
		{
			//Set IE browser zoom level to 100% manually
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();	
		}
		return(driver);
	}
	public void launchSite(String url)
	{
		driver.get(url);
		driver.manage().window().maximize();
	}
	public String screenshot() throws Exception
	{
		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt=new Date();
		String fn=sf.format(dt)+".png";
		File src=driver.getScreenshotAs(OutputType.FILE);
		File dest=new File(fn);
		FileHandler.copy(src,dest);
		return(dest.getAbsolutePath());
	}
	public void closeSite()
	{
		driver.quit();
	}
	public Properties accessProperties() throws Exception
	{
		String pfpath="D:\\Softwaretestingimportance\\APITestingProject\\sample_POM\\gmaildata.properties";
		FileReader fr=new FileReader(pfpath);
		BufferedReader br=new BufferedReader(fr);
		Properties p=new Properties();
		p.load(br);
		return(p);
	}
}











