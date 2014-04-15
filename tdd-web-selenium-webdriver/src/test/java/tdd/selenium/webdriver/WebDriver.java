package tdd.selenium.webdriver;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class WebDriver {
	
  private FirefoxDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://127.0.1.1:8080/tdd-web-app/";
    driver.manage().timeouts().implicitlyWait(4, TimeUnit.SECONDS);
  }
  
  @Test
  public void shouldNotAddSameActorTwice() {
	  
		String firstName = "Carl" + new Date().getTime();
		String lastName = "Reed" + new Date().getTime();
		String birthDate = "1910-10-10";
		
	    driver.get(baseUrl);
	    
	    int rowsBefore = driver.findElementsByTagName("tr").size();
	    
	    WebElement firstNameField = driver.findElement(By.id("firstName"));
	    WebElement lastNameField = driver.findElement(By.id("lastName"));
	    WebElement birthDateField = driver.findElement(By.id("birthDate"));
	    WebElement submitButton = driver.findElement(By.cssSelector("#form > form > input[type=\"submit\"]"));
	    
	    
	    // compiles first name
		firstNameField.clear();
		firstNameField.sendKeys(firstName);
	    
	    // compiles last name
		lastNameField.clear();
		lastNameField.sendKeys(lastName);
	    
	    // compiles birth date
		birthDateField.clear();
		birthDateField.sendKeys(birthDate);
	    
	    // clicks submit
		submitButton.click();
		
		
		// element should be reloaded after click(), see click() javadoc.
	    firstNameField = driver.findElement(By.id("firstName"));
	    lastNameField = driver.findElement(By.id("lastName"));
	    birthDateField = driver.findElement(By.id("birthDate"));
	    submitButton = driver.findElement(By.cssSelector("#form > form > input[type=\"submit\"]"));
		
		
	    // compiles first name
		firstNameField.clear();
		firstNameField.sendKeys(firstName);
	    
	    // compiles last name
		lastNameField.clear();
		lastNameField.sendKeys(lastName);
	    
	    // compiles birth date
		birthDateField.clear();
		birthDateField.sendKeys(birthDate);
		
	    // clicks submit
		submitButton.click();		
	    
	    // gets the pop up
	    String infoText = driver.findElementByClassName("flash-error").getText();
	    
	    int rowsAfter = driver.findElementsByTagName("tr").size();
	    
	    // check the name is reported in the message
	    assertThat(infoText, stringContainsInOrder(Arrays.asList(new String[]{ firstName, lastName })));
	    assertThat(rowsAfter - rowsBefore, equalTo(1));

	  
  }

  @Test
  public void shouldAddNewActor() throws Exception {
	  	  
	String firstName = "Carl" + new Date().getTime();
	String lastName = "Reed" + new Date().getTime();
	String birthDate = "1910-10-10";

    driver.get(baseUrl);
    
    int rowsBefore = driver.findElementsByTagName("tr").size();
    
    WebElement firstNameField = driver.findElement(By.id("firstName"));
    WebElement lastNameField = driver.findElement(By.id("lastName"));
    WebElement birthDateField = driver.findElement(By.id("birthDate"));
    WebElement submitButton = driver.findElement(By.cssSelector("#form > form > input[type=\"submit\"]"));
    
    
    // compiles first name
	firstNameField.clear();
	firstNameField.sendKeys(firstName);
    
    // compiles last name
	lastNameField.clear();
	lastNameField.sendKeys(lastName);
    
    // compiles birth date
	birthDateField.clear();
	birthDateField.sendKeys(birthDate);
    
    // clicks submit
	submitButton.click();
    
    // gets the pop up
    String infoText = driver.findElementByClassName("flash-info").getText();
    
    int rowsAfter = driver.findElementsByTagName("tr").size();
    
    // check the name is reported in the message
    assertThat(infoText, stringContainsInOrder(Arrays.asList(new String[]{ firstName, lastName })));
    assertThat(rowsAfter - rowsBefore, equalTo(1));
    
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
