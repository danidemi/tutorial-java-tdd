package tdd.selenium.webdriver;

import java.util.Arrays;
import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

public class WebDriverBacked {
	private Selenium selenium;
	private String baseUrl;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		baseUrl = "http://127.0.1.1:8080/tdd-web-app/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void shouldAddNewActor() throws Exception {
		
		String firstName = "Carl" + new Date().getTime();
		String lastName = "Reed" + new Date().getTime();
		String birthDate = "1910-10-10";
		
		
		
		selenium.open(baseUrl);
		
		int rowsBefore = selenium.getCssCount("tr").intValue();
		selenium.type("id=firstName", firstName);
		selenium.type("id=lastName", lastName);
		selenium.type("id=birthDate", birthDate);
		selenium.click("css=#form > form > input[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		int rowsAfter = selenium.getCssCount("tr").intValue();

		String flash = selenium.getText("id=flash");
		assertThat(flash, stringContainsInOrder( Arrays.asList( new String[]{firstName, lastName} )) );
		
		assertThat( rowsAfter- rowsBefore, equalTo(1));

	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
