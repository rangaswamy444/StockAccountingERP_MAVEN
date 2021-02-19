package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonFunctions.LoginPage;
import CommonFunctions.SupplierPage;
import Utilites.ExcelFileUtil;

public class DataDrivenDriverScript {
WebDriver driver;
String inputpath="C:\\Selenium_OJT\\ERP_Maven\\TestInput\\Supplierdata.xlsx";
String outputpath="C:\\Selenium_OJT\\ERP_Maven\\TestOutPut\\DatadrivenResults.xlsx";
@BeforeTest
public void setUp()
{
	System.out.println("hi");
	System.setProperty("webdriver.chrome.driver", "C:\\Selenium_OJT\\ERP_StockAccounting\\CommonDrivers\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("http://projects.qedgetech.com/webapp/login.php");
	driver.manage().window().maximize();
	LoginPage login=PageFactory.initElements(driver,  LoginPage.class);
	String results=login.verifyLogin("admin", "master");
	Reporter.log(results,true);
}
@Test
public void suppliercreation() throws Throwable
{
	SupplierPage sup=PageFactory.initElements(driver, SupplierPage.class);
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	//count no of rows
	int rc=xl.rowCount("Suppliers");
	//count no  of cell in first row
	int cc=xl.colCount("Suppliers");
	Reporter.log("No of rows are::"+rc+"   "+"No of cells in first row::"+cc,true);
	for(int i=1; i<=rc;i++)
	{
		String sname=xl.getCellData("Suppliers", i, 0);	
		String address=xl.getCellData("Suppliers", i, 1);
		String city=xl.getCellData("Suppliers", i, 2);
		String country=xl.getCellData("Suppliers", i, 3);
		String cperson=xl.getCellData("Suppliers", i, 4);
		String pnumber=xl.getCellData("Suppliers", i, 5);	
		String email=xl.getCellData("Suppliers", i, 6);
		String mnumber=xl.getCellData("Suppliers", i, 7);
		String notes=xl.getCellData("Suppliers", i, 8);
		String result=sup.verifySupplier(sname, address, city, country, cperson, pnumber, email, mnumber, notes);
		xl.setCellData("Suppliers", i, 9, result, outputpath);
			
	}	
}
@AfterTest
public void tearDown()
{
	driver.close();
}
}

