package CommonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class SupplierPage {
	WebDriver driver;
	WebDriverWait wait;
	public SupplierPage(WebDriver driver)
	{
		this.driver=driver;
	}
	@FindBy(xpath="(//a[contains(text(),'Sup')])[2]")
	WebElement clicksupplier;
	@FindBy(xpath="/html[1]/body[1]/div[2]/div[3]/div[1]/div[1]/div[3]/div[1]/div[1]/div[1]/div[1]/a[1]")
	WebElement clickAddsupbtn;
	@FindBy(name="x_Supplier_Number")
	WebElement snumber;
	@FindBy(name="x_Supplier_Name")
	WebElement sname;
	@FindBy(name="x_Address")
	WebElement address;
	@FindBy(name="x_City")
	WebElement city;
	@FindBy(name="x_Country")
	WebElement country;
	@FindBy(name="x_Contact_Person")
	WebElement cperson;
	@FindBy(name="x_Phone_Number")
	WebElement pnumber;
	@FindBy(name="x__Email")
	WebElement email;
	@FindBy(name="x_Mobile_Number")
	WebElement mnumber;
	@FindBy(name="x_Notes")
	WebElement notes;
	@FindBy(name="btnAction")
	WebElement clickadd;
	@FindBy(xpath="//button[text()='OK!']")
	WebElement clickokconfirm;
	@FindBy(xpath="(//button[text()='OK'])[6]")
	WebElement clickalertok;
	@FindBy(xpath="//span[@class='glyphicon glyphicon-search ewIcon']")
	WebElement searchpanel;
	@FindBy(xpath="//input[@id='psearch']")
	WebElement searchtextbox;
	@FindBy(xpath="//button[@id='btnsubmit']")
	WebElement searchbtn;
	@FindBy(xpath="//table[@id='tbl_a_supplierslist']")
	WebElement suptable;
	public String verifySupplier(String sname,String address,String city,String country,String cperson,
			String pnumber,String email,String mnumber,String notes)
	{
		String res="";
		wait= new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(clicksupplier));
		this.clicksupplier.click();
		wait.until(ExpectedConditions.visibilityOf(clickAddsupbtn));
		this.clickAddsupbtn.click();
		wait.until(ExpectedConditions.visibilityOf(snumber));
		String expsnumber=this.snumber.getAttribute("value");
		this.sname.sendKeys(sname);
		this.address.sendKeys(address);
		this.city.sendKeys(city);
		this.country.sendKeys(country);
		this.cperson.sendKeys(cperson);
		this.pnumber.sendKeys(pnumber);
		this.email.sendKeys(email);
		this.mnumber.sendKeys(mnumber);
		this.notes.sendKeys(notes);
		this.clickadd.sendKeys(Keys.ENTER);
		wait.until(ExpectedConditions.elementToBeClickable(clickokconfirm));
		this.clickokconfirm.click();
		wait.until(ExpectedConditions.elementToBeClickable(clickalertok));
		this.clickalertok.click();
		if(!this.searchtextbox.isDisplayed())
			this.searchpanel.click();
		wait.until(ExpectedConditions.elementToBeClickable(searchtextbox));
		this.searchtextbox.clear();
		this.searchtextbox.sendKeys(expsnumber);
		this.searchbtn.click();
		wait.until(ExpectedConditions.visibilityOf(suptable));
		//capture supplier number from table
		String actsnumber=driver.findElement(By.xpath("//table[@id='tbl_a_supplierslist']/tbody/tr[1]/td[6]/div/span/span")).getText();
		if(actsnumber.equals(expsnumber))
		{
			Reporter.log("Supplier matching with table::"+expsnumber+"    "+actsnumber,true);
			res="Pass";
		}
		else
		{
			Reporter.log("Supplier Not matching with table::"+expsnumber+"    "+actsnumber,true);
			res="Fail";
		}
		return res;	
		
		
	}
	

}
