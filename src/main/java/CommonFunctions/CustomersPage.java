package CommonFunctions;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

public class CustomersPage {
WebDriver driver;
WebDriverWait wait;
public CustomersPage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath="//li[@id='mi_a_customers']//a[@href='a_customerslist.php'][normalize-space()='Customers']")
WebElement clickcustomers;
@FindBy(xpath="//div[@class='panel-heading ewGridUpperPanel']//span[@class='glyphicon glyphicon-plus ewIcon']")
WebElement clickAddcustomerbtn;
@FindBy(xpath="//input[@id='x_Customer_Number']")
WebElement cnumber;
@FindBy(xpath="//input[@id='x_Customer_Name']")
WebElement cname;
@FindBy(name="x_Address")
WebElement address;
@FindBy(name="x_City")
WebElement city;
@FindBy(xpath="//input[@id='x_Country']")
WebElement country;
@FindBy(xpath="//input[@id='x_Contact_Person']")
WebElement cperson;
@FindBy(xpath="//input[@id='x_Phone_Number']")
WebElement pnumber;
@FindBy(xpath="//input[@id='x__Email']")
WebElement email;
@FindBy(name="x_Mobile_Number")
WebElement mnumber;
@FindBy(xpath="//input[@id='x_Notes']")
WebElement notes;
@FindBy(name="btnAction")
WebElement clickadd;
@FindBy(xpath="//button[normalize-space()='OK!']")
WebElement clickokconfirm;
@FindBy(xpath="//button[@class='ajs-button btn btn-primary']")
WebElement clickalertok;
@FindBy(xpath="//span[@class='glyphicon glyphicon-search ewIcon']")
WebElement searchpanel;
@FindBy(xpath="//input[@id='psearch']")
WebElement searchtextbox;
@FindBy(xpath="//button[@id='btnsubmit']")
WebElement searchbtn;
@FindBy(xpath="//div[@class='ewScrollableTableBody']")
WebElement customertable;
public String verifyCustomer(String cname,String address,String city,String country,String cperson,
		String pnumber,String email,String  mnumber,String notes)
{
	String res="";
	wait= new WebDriverWait(driver, 20);
	wait.until(ExpectedConditions.elementToBeClickable(clickcustomers));
	this.clickcustomers.click();
	wait.until(ExpectedConditions.visibilityOf(clickAddcustomerbtn));
	this.clickAddcustomerbtn.click();
	wait.until(ExpectedConditions.visibilityOf(cnumber));
	String expcnumber=this.cnumber.getAttribute("value");
	this.cname.sendKeys(cname);
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
	wait.until(ExpectedConditions.visibilityOf(searchtextbox));
	this.searchtextbox.clear();
	this.searchtextbox.sendKeys(expcnumber);
	this.searchbtn.click();
	wait.until(ExpectedConditions.visibilityOf(customertable));
	//capture supplier number from table
	String actcnumber=driver.findElement(By.xpath("//table[@id='tbl_a_customerslist']/tbody/tr[1]/td[5]/div/span/span")).getText();
	if(actcnumber.equals(expcnumber))
	{
		Reporter.log("customer matching with table::"+expcnumber+"    "+actcnumber,true);
		res="Pass";
	}
	else
	{
		Reporter.log("customer Not matching with table::"+expcnumber+"    "+actcnumber,true);
		res="Fail";
	}
	return res;
	
	
}









}
