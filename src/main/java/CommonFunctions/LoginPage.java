package CommonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
	WebDriver driver;
	WebDriverWait wait;
	public LoginPage(WebDriver driver)
	{
		this.driver=driver; 
	}
	@FindBy(xpath="//button[normalize-space()='Reset']")
	WebElement resetbtn;
	@FindBy(name="username")
	WebElement username;
	@FindBy(css="#password")
	WebElement password;
	@FindBy(id="btnsubmit")
	WebElement loginbtn;
	public String verifyLogin(String username,String password)
	{
		String res="";
		wait=new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.elementToBeClickable(resetbtn));
		this.resetbtn.click();
		wait.until(ExpectedConditions.visibilityOf(this.username));
		this.username.sendKeys(username);
		wait.until(ExpectedConditions.visibilityOf(this.password));
		this.password.sendKeys(password);
		wait.until(ExpectedConditions.elementToBeClickable(loginbtn));
		this.loginbtn.click();
		String expected = "dashboard";
		String actual = driver.getCurrentUrl();
		if(actual.contains(expected))
		{
			res="Login Success";
		}
		else
		{
			res="Login Fail";
		}
		return res;
		
			
			
			
	}
}
