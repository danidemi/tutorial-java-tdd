package com.danidemi.tutorial.tdd.htmlunit;

import java.io.IOException;
import java.net.MalformedURLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class WebPageTest {

	private WebClient webClient;

	@Before
	public void setUp() {
		webClient = new WebClient();		
	}
	
	@After
	public void tearDown() {
		webClient.closeAllWindows();		
	}
	
	@Ignore
	@Test
	public void test() throws FailingHttpStatusCodeException, MalformedURLException, IOException {
		
		final HtmlPage page = webClient.getPage("http://localhost:8080");
		
		HtmlForm formByName = page.getFormByName("form");

		
		
		formByName.getInputByName("firstName").setValueAttribute("Mark");
		formByName.getInputByName("lastName").setValueAttribute("Ross");
		Page nextPage = formByName.getButtonByName("submit").click();
	}
	
	

}
