package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonFunctions.CustomersPage;
import CommonFunctions.LoginPage;
import Utilites.ExcelFileUtil;

public class DataDrivenCustomerScript {
WebDriver driver;
String inputpath="C:\\Selenium_OJT\\ERP_Maven\\TestInput\\Customerdata.xlsx";
String outputpath="C:\\Selenium_OJT\\ERP_Maven\\TestOutPut\\DatadrivenCustomerResults.xlsx";
@BeforeTest
public void setUp()
{
	System.out.println("hi");
	System.setProperty("webdriver.chrome.driver", "C:\\Selenium_OJT\\ERP_StockAccounting\\CommonDrivers\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("http://projects.qedgetech.com/webapp/login.php");
	driver.manage().window().maximize();
	LoginPage login=PageFactory.initElements(driver, LoginPage.class);
	String results=login.verifyLogin("admin", "master");
	Reporter.log(results,true);
}
@Test
public void customercreation() throws Throwable
{
	CustomersPage customer=PageFactory.initElements(driver, CustomersPage.class);
	ExcelFileUtil xl=new ExcelFileUtil(inputpath);
	//count no of rows
	int rc=xl.rowCount("Customers");
	//count no  of cell in first row
	int cc=xl.colCount("Customers");
	Reporter.log("No of rows are::"+rc+"   "+"No of cells in first row::"+cc,true);
	for(int i=1; i<=rc;i++)
	{
		String cname=xl.getCellData("Customers", i, 0);
		String address=xl.getCellData("Customers", i, 1);
		String city=xl.getCellData("Customers", i, 2);
		String country=xl.getCellData("Customers", i, 3);
		String cperson=xl.getCellData("Customers", i, 4);
		String pnumber=xl.getCellData("Customers", i, 5);	
		String email=xl.getCellData("Customers", i, 6);
		String mnumber=xl.getCellData("Customers", i, 7);
		String notes=xl.getCellData("Customers", i, 8);
		String result=customer.verifyCustomer(cname, address, city, country, cperson, pnumber, email, mnumber, notes);
		xl.setCellData("Customers", i, 9, result, outputpath);		
		
		
	}
}
@AfterTest
public void tearDown()
{
	driver.close();
}
}
