package CommonFunctions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import Utilites.PropertyFileUtil;

public class FunctionLibrary {
	public static WebDriver driver;
	public static WebDriver startBrowser() throws Throwable
	{
		if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("chrome"))
		{
			Reporter.log("Executing on chrome browser",true);
			System.setProperty("webdriver.chrome.driver", "C:\\Selenium_OJT\\ERP_Maven\\CommonDrivers\\chromedriver.exe");
			driver = new ChromeDriver();

		}
		else if(PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("firefox"))
		{
			Reporter.log("Executing on firefox browser",true);
			System.setProperty("webdriver.gecko.driver", "C:\\Selenium_OJT\\ERP_Maven\\CommonDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();

		}
		else
		{
			Reporter.log("Browser value is Not matching",true);
		}
		return driver;

	}
	//method for Launching url
	public static void openApplication(WebDriver driver) throws Throwable
	{
		driver.get(PropertyFileUtil.getValueForKey("Url"));
		driver.manage().window().maximize();
	}
	//method for type Action
	public static void typeAction(WebDriver driver,String LocatorType,String LocatorValue,String TestData)
	{
		if(LocatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(LocatorValue)).clear();
			driver.findElement(By.name(LocatorValue)).sendKeys(TestData);
		}
		else if(LocatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(LocatorValue)).clear();
			driver.findElement(By.xpath(LocatorValue)).sendKeys(TestData);
		}
		else if(LocatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(LocatorValue)).clear();
			driver.findElement(By.id(LocatorValue)).sendKeys(TestData);
		}
	}
	//method for click button
	public static void clickAction(WebDriver driver,String LocatorType,String LocatorValue)
	{
		if(LocatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(LocatorValue)).sendKeys(Keys.ENTER);
		}
		else if(LocatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(LocatorValue)).click();
		}
		else if(LocatorType.equalsIgnoreCase("xpath"))
		{
			driver.findElement(By.xpath(LocatorValue)).click();
		}
		else
		{
			System.out.println("Unable to execute clickAction");
		}

	}
	//method for wait for element
	public static void waitForElement(WebDriver driver,String LocatorType,String LocatorValue,String waitTime)
	{
		WebDriverWait myWait= new WebDriverWait(driver, Integer.parseInt(waitTime));
		if(LocatorType.equalsIgnoreCase("xpath"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorValue)));
		}
		else if(LocatorType.equalsIgnoreCase("id"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LocatorValue)));
		}
		else if(LocatorType.equalsIgnoreCase("name"))
		{
			myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(LocatorValue)));
		}
		else
		{
			System.out.println("Unable to execute wait for element method");
		}

	}
	//method for close browser
	public static void closeBrowser(WebDriver driver)
	{
		driver.close();
	}
	//title validation
	public static void titleValidation(WebDriver driver,String Exp_title)
	{
		String act_title=driver.getTitle();
		Assert.assertEquals(act_title, Exp_title,"Title is Not matching");
	}
	//method for date generate
	public static String generateDate()
	{
		Date d = new Date();
		SimpleDateFormat datef = new SimpleDateFormat("YYYY_MM_dd_ss");
		return datef.format(d);


	}
	//capture suppler number into notepad
	public static void captureData(WebDriver driver,String Locatortype,String Locatorvalue) throws Throwable
	{
		String data="";
		if(Locatortype.equalsIgnoreCase("name"))
		{
			data=driver.findElement(By.name(Locatorvalue)).getAttribute("value");

		}
		else if(Locatortype.equalsIgnoreCase("id"))
		{
			data=driver.findElement(By.id(Locatorvalue)).getAttribute("value");

		}
		//write into notepad
		FileWriter fw = new FileWriter("C:\\Selenium_OJT\\ERP_Maven\\CaptureData\\suppliernum.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(data);
		bw.flush();
		bw.close();	

	}
	//supplier table validation
	public static void tableValidation(WebDriver driver,String ColNum) throws Throwable
	{
		//read data from notepad
		FileReader fr = new FileReader("C:\\Selenium_OJT\\ERP_Maven\\CaptureData\\suppliernum.txt");
		BufferedReader br = new BufferedReader(fr);
		String exp_data = br.readLine();
		// String to int
		int colnum1=Integer.parseInt(ColNum);
		//if search text is not visible click on search panel
		if(!driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.textbox"))).isDisplayed())
			//click on search panel button
			driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.panel"))).click();
		Thread.sleep(5000);
		//Enter supplier number
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.textbox"))).clear();
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.textbox"))).sendKeys(exp_data);
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("search.btn"))).click();
		Thread.sleep(5000);
		WebElement table = driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("table.supplier")));
		//couunt no of rows in a table
		List<WebElement> rows = table.findElements(By.tagName("tr"));
		for(int i=1; i<rows.size();i++)
		{
			String act_data=driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr["+i+"]/td["+colnum1+"]/div/span/span")).getText();
			Assert.assertEquals(act_data, exp_data);
			System.out.println(act_data+"    "+exp_data);
			break;

		}

	}
	//method for stockCategories
public static void stockCategories(WebDriver driver) throws Throwable
{
	Actions ac = new Actions(driver);
	ac.moveToElement(driver.findElement(By.linkText("Stock Items"))).perform();
	Thread.sleep(4000);
	ac.moveToElement(driver.findElement(By.xpath("//li[@id='mi_a_stock_categories']//a[@href='a_stock_categorieslist.php'][normalize-space()='Stock Categories']"))).click().perform();
	Thread.sleep(4000);
}
//method for Stockitems(table)
public static void sttableValidation(WebDriver driver,String testdata) throws Throwable
{
	if(!driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchtextbox1"))).isDisplayed())
		driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("serachpanel1"))).click();
	    Thread.sleep(5000);
	    WebElement searchtextbox = driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchtextbox1")));
	    searchtextbox.clear();
	    Thread.sleep(5000); 
	    searchtextbox.sendKeys(testdata);
	    driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("searchbtn1"))).click();
	    Thread.sleep(5000);
	   //WebElement table1=driver.findElement(By.xpath(PropertyFileUtil.getValueForKey("stocktable")));
	  //capture table data

String actdata=driver.findElement(By.xpath("//table[@id='tbl_a_stock_categorieslist']/tbody/tr[1]/td[4]/div/span/span")).getText();
Assert.assertEquals(testdata, actdata);
Reporter.log(testdata+"      "+actdata,true);
	
}
}
