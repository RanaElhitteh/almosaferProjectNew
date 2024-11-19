import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import javax.lang.model.element.Element;
import javax.xml.xpath.XPath;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByClassName;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class MyTestcase {

	WebDriver driver = new ChromeDriver();
	String Url = "https://www.almosafer.com/en";
	String ExpectedEnblishLanguage = "en";
	String ExpectedArabicLanguage="ar";
     String ExpectedCurrency = "SAR";
     String ExpectedContactNumber = "+966554400000";
     boolean QitafLogoIsThere = true;
     Random rand= new Random();
	@BeforeTest
	public void mySetup() {

		driver.get(Url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(8));
		driver.findElement(By.cssSelector(".sc-jTzLTM.hQpNle.cta__button.cta__saudi.btn.btn-primary")).click();
		;

	}

	@Test (enabled=false)
	public void CheckTheLanage() {

		WebElement HtmlTag = driver.findElement(By.tagName("html"));

		String ActualLanguageOnTheWebsite = HtmlTag.getAttribute("lang");

		Assert.assertEquals(ActualLanguageOnTheWebsite, ExpectedEnblishLanguage);

	}
	
	@Test (enabled=false)
	public void TestTheCurrencyIsSAR() {
		
    String Actualcurrency= driver.findElement(By.xpath("//button[@data-testid = 'Header__CurrencySelector']")).getText();	
    Assert.assertEquals(Actualcurrency,ExpectedCurrency );
		
	}
	
	@Test (enabled=false)
	public void TestTheContactNumbers() {
		
		String ActualContactNumber=driver.findElement(By.tagName("strong")).getText();
	     
		Assert.assertEquals(ActualContactNumber, ExpectedContactNumber);
		
		}
	
	 @Test (enabled= false)
	 public void CheckQitafLogoIfDisdlayed() {
		 
		WebElement FooterTag = driver.findElement(By.tagName("Footer"));
		boolean Expectedresulte = true ;
	 boolean Actualresulte=   FooterTag.findElement(By.cssSelector(".sc-fihHvN.eYrDjb")).findElement(By.tagName("svg")).isDisplayed();
	    Assert.assertEquals(Actualresulte, Expectedresulte);
		 
	 }
	 
	 @Test (enabled= false)
	 public void CheckHotelTabIsNotSelected() {
		 String ExpectedValue = "false";
	String ActualValue =	driver.findElement(By.id("uncontrolled-tab-example-tab-hotels")).getAttribute("aria-selected"); 
	 Assert.assertEquals(ActualValue, ExpectedValue);
	 
		  
	 }
	 @Test (enabled= false)
	 public void CheckDepatureAndReturnDate() {
		 
		 LocalDate today  = LocalDate.now();
         int ExpectedDepatureDate = today.plusDays(1).getDayOfMonth();
         int ExpectedReturnDate = today.plusDays(2).getDayOfMonth();
         String ActualDepatureDate = driver.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-kqlzXE blwiEW'] span[class='sc-cPuPxo LiroG']")).getText();
         String ActualReturnDate = driver.findElement(By.cssSelector("div[class='sc-iHhHRJ sc-OxbzP edzUwL'] span[class='sc-cPuPxo LiroG']")).getText();
         int  ActualDepatureDateAsIn= Integer.parseInt(ActualDepatureDate);
         int ActualReturnDateAsIn = Integer.parseInt(ActualReturnDate);
         Assert.assertEquals(ActualDepatureDateAsIn, ExpectedDepatureDate);
         Assert.assertEquals(ActualReturnDateAsIn, ExpectedReturnDate);
         
	 
	 }
	 @Test (priority = 1)
	 public void ChangeTheLanguageOfTheWebSiteRandomly () {   
		 String [] websites = {"https://www.almosafer.com/en","https://www.almosafer.com/ar"};
		int randomindex = rand.nextInt(websites.length);
		 driver.get(websites[randomindex]);
		 if(driver.getCurrentUrl().contains("en")) {
			 
			 WebElement HtmlTag = driver.findElement(By.tagName("html"));

				String ActualLanguageOnTheWebsite = HtmlTag.getAttribute("lang");

				Assert.assertEquals(ActualLanguageOnTheWebsite, ExpectedEnblishLanguage);
		 }
				else if(driver.getCurrentUrl().contains("ar")) {
					
					WebElement HtmlTag = driver.findElement(By.tagName("html"));

					String ActualLanguageOnTheWebsite = HtmlTag.getAttribute("lang");

					Assert.assertEquals(ActualLanguageOnTheWebsite, ExpectedArabicLanguage);
					
				}

			 
		 
	 }
	 
	 @Test (priority = 2)
	 public void HotelSelection() {
		 
	WebElement HotelTab =driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
	HotelTab.click();
	WebElement SearchHotel = driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input")); 
	
	if ( driver.getCurrentUrl().contains("en")) {
		String [] EnglishCities = {"dubai","jeddeh","riyadh"};
		int randomindex = rand.nextInt(EnglishCities.length);
		 SearchHotel.sendKeys(EnglishCities[randomindex]);
	}
	else if (driver.getCurrentUrl().contains("ar"));
	String [] ArabicCities = {"جده","دبي"};
	int randomindex = rand.nextInt(ArabicCities.length);
	 SearchHotel.sendKeys(ArabicCities[randomindex]);
	
	
 	 }
	 @Test (priority = 3)
	 public void SelectNumberOfPeople() {
		WebElement MyElemnte = driver.findElement(By.xpath("//select[@data-testid='HotelSearchBox__ReservationSelect_Select']"));
		Select mySelector= new Select(MyElemnte);
		
		int RandomIndex=rand.nextInt(2);
		mySelector.selectByIndex(RandomIndex);
		  
		driver.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']")).click();
		 
	 }
	 
	@Test (priority = 4)
	public void CheckThePageIsFullyLoded() {
		
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofMinutes(1));
		WebElement resultsTab = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[@data-testid='srp_properties_found']")));
		
        Assert.assertEquals(resultsTab.getText().contains("found")||resultsTab.getText().contains(" مكان إقامة"), true);		
	}
	 
	 
	 
	
	
	
	
	
	
	
	

}
