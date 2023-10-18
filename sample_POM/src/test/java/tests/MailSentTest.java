package tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.ComposePage;
import pages.HomePage;
import pages.LoginPage;
import pages.LogoutPage;
import utilities.TestUtility;

public class MailSentTest
{
	public static void main(String[] args) throws Exception
	{
		//get browser name and test data from Excel file sheet
		File f=new File(System.getProperty("user.dir")+"\\src\\test\\java\\Book1.xlsx");
		FileInputStream fi=new FileInputStream(f);
		Workbook wb=WorkbookFactory.create(fi);
		Sheet sh=wb.getSheet("Sheet2");
		int nour=sh.getPhysicalNumberOfRows(); //count of used rows
		System.out.println(nour);
		int nouc=sh.getRow(0).getLastCellNum();//count of used columns
		//Create result column with current date and time as heading
		SimpleDateFormat sf=new SimpleDateFormat("dd-MMM-yyyy-hh-mm-ss");
		Date dt=new Date();
		Cell rc=sh.getRow(0).createCell(nouc);
		rc.setCellValue("Results on "+sf.format(dt));
		//Create object to Utility class
		TestUtility util=new TestUtility();
		//Load properties file into RAM
		Properties pro=util.accessProperties();
		//Mail compose testing with multiple test data in cross browser environment
		//loop from 2nd row(index=1) in excel file due to 1st row has names of columns
		for(int i=1;i<nour;i++)
		{
			try
			{
				DataFormatter df=new DataFormatter();
				Row r=sh.getRow(i);
				String bn=df.formatCellValue(r.getCell(0));
				String u=df.formatCellValue(r.getCell(1));
				String p=df.formatCellValue(r.getCell(2));
				String t=df.formatCellValue(r.getCell(3));
				String s=df.formatCellValue(r.getCell(4));
				String b=df.formatCellValue(r.getCell(5));
				String fp=df.formatCellValue(r.getCell(6));
				//Open browser
				RemoteWebDriver driver=util.openBrowser(bn);
				//Create objects to page classes
				HomePage hp=new HomePage(driver);
				LoginPage lp=new LoginPage(driver);
				ComposePage cp=new ComposePage(driver);
				LogoutPage lop=new LogoutPage(driver);
				//Launch site
				util.launchSite(pro.getProperty("url"));
				int max=Integer.parseInt(pro.getProperty("maxwait"));
				WebDriverWait w=new WebDriverWait(driver,max);
				w.until(ExpectedConditions.visibilityOf(hp.uid));
				//Do login using valid data
				hp.uidfill(u); //parameterization
				hp.uidnextclick();
				Thread.sleep(5000);
				w.until(ExpectedConditions.visibilityOf(lp.pwd));
				lp.pwdfill(p); //parameterization
				lp.pwdnextclick();
				Thread.sleep(5000);
				w.until(ExpectedConditions.elementToBeClickable(cp.comp));
				//Goto compose operation
				cp.clickCompose();
				w.until(ExpectedConditions.visibilityOf(cp.toaddress));
				cp.fillTo(t);
				cp.fillSubject(s);
				cp.fillBody(b);
				cp.attachment(fp);
				w.until(ExpectedConditions.visibilityOf(cp.uploaded));
				cp.clickSend();
				//validation
				try
				{
					w.until(ExpectedConditions.visibilityOf(cp.outputmsg));
					String msg=cp.outputmsg.getText();
					Cell c=sh.getRow(i).createCell(nouc);
					c.setCellValue("Compose test passed and we got "+msg);
				}
				catch(Exception ex)
				{
					Cell c=sh.getRow(i).createCell(nouc);
					c.setCellValue("Compose test failed and see "+util.screenshot());
				}
				//Do logout
				w.until(ExpectedConditions.elementToBeClickable(lop.profilepic));
				lop.clickProfilePic();
				w.until(ExpectedConditions.elementToBeClickable(lop.signout));
				lop.clickSignOut();
				w.until(ExpectedConditions.visibilityOf(lop.relogin));
				//close site
				util.closeSite();
			}
			catch(Exception ex)
			{
				//close site
				util.closeSite();
				Cell c=sh.getRow(i).createCell(nouc);
				c.setCellValue(ex.getMessage());
			}
		} //loop ending
		//Save and close excel file
		sh.autoSizeColumn(nouc); //auto fit on column size
		FileOutputStream fo=new FileOutputStream(f);
		wb.write(fo); //save
		wb.close();
		fo.close();
		fi.close();
	}
}

