package tdd.selenium.webdriver;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

public class WebDriverBacked {
	private Selenium selenium;

	@Before
	public void setUp() throws Exception {
		WebDriver driver = new FirefoxDriver();
		String baseUrl = "http://localhost:8080/tdd.web/";
		selenium = new WebDriverBackedSelenium(driver, baseUrl);
	}

	@Test
	public void testWebDriverBacked() throws Exception {
		selenium.open("/tdd.web/?flash.priority=INFO&flash.message=Actor%20%27John%20Smith%27%20added");
		selenium.type("id=firstName", "Carl");
		selenium.type("id=lastName", "Reed");
		selenium.type("id=birthDate", "1910-10-10");
		selenium.click("css=#form > form > input[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
		selenium.type("id=firstName", "Carl");
		selenium.type("id=lastName", "Reed");
		selenium.type("id=birthDate", "1910-11-24");
		selenium.click("css=#form > form > input[type=\"submit\"]");
		selenium.waitForPageToLoad("30000");
	}

	@After
	public void tearDown() throws Exception {
		selenium.stop();
	}
}
