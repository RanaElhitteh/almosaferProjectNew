import static org.testng.Assert.assertEquals;

import java.time.Duration;

import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestcase {

	WebDriver driver = new ChromeDriver();
	String Url = "https://global.almosafer.com/en/flights-home";
	String ExpectedLanguage = "en";
     String ExpectedCurrency = "SAR";
     String ExpectedContactNumber = "+966554400000";
     boolean QitafLogoIsThere = true;
	@BeforeTest
	public void mySetup() {

		driver.get(Url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary")).click();
		;

	}

	@Test
	public void CheckTheLanage() {

		WebElement HtmlTag = driver.findElement(By.tagName("html"));

		String ActualLanguageOnTheWebsite = HtmlTag.getAttribute("lang");

		Assert.assertEquals(ActualLanguageOnTheWebsite, ExpectedLanguage);

	}
	
	@Test
	public void TestTheCurrencyIsSAR() {
		
    String Actualcurrency= driver.findElement(By.xpath("//button[@data-testid = 'Header__CurrencySelector']")).getText();	
    Assert.assertEquals(Actualcurrency,ExpectedCurrency );
		
	}
	
	@Test
	public void TestTheContactNumbers() {
		
		String ActualContactNumber=driver.findElement(By.tagName("strong")).getText();
	     
		Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);
		
		}
	
	 @Test
	 public void CheckQitafLogoIfDisdlayed() {
		 
		WebElement FooterTag = driver.findElement(By.tagName("Footer"));
		boolean ActualQitafLogo =  FooterTag.findElement(By.xpath("\r\n"
				+ "//svg[@data-testid='Footer__QitafLogo']")).isDisplayed();

		 Assert.assertEquals(ActualQitafLogo, QitafLogoIsThere);
		 
	 }
	
	
	
	
	
	
	

}
