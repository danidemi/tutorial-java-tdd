package com.danidemi.tutorial.tdd.web.controller;

import org.junit.Test;

import com.danidemi.tutorial.tdd.web.view.FlashMessage;
import com.danidemi.tutorial.tdd.web.view.FlashMessage.Priority;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
public class FlashMessageFilterTest {

	@Test
	public void shouldAppendFlashInfo() {
		
		// given
		String originalUrl = "/url";
		
		// when
		String urlWithFlash = FlashMessageFilter.appendFlash(originalUrl, new FlashMessage(Priority.INFO, "this is info"));
		
		// then
		assertThat( urlWithFlash, equalTo("/url?flash.priority=INFO&flash.message=this is info") );
		
	}
	
	@Test
	public void shouldAppendFlashInfoWithoutQuestionMark() {
		
		// given
		String originalUrl = "/url?param1=hello";
		
		// when
		String urlWithFlash = FlashMessageFilter.appendFlash(originalUrl, new FlashMessage(Priority.INFO, "this is info"));
		
		// then
		assertThat( urlWithFlash, equalTo("/url?param1=hello&flash.priority=INFO&flash.message=this is info") );
		
	}	
	
}
