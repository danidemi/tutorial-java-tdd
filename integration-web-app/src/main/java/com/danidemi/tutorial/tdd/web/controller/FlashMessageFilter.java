package com.danidemi.tutorial.tdd.web.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import com.danidemi.tutorial.tdd.web.view.FlashMessage;
import com.danidemi.tutorial.tdd.web.view.FlashMessage.Priority;

@WebFilter(urlPatterns={"/*"})
public class FlashMessageFilter implements Filter{

	public static final String FLASH_ATTRIBUTE = "flash";
	public static final String FLASH_MESSAGE_PARAM = "flash.message";
	public static final String FLASH_PRIORITY_PARAM = "flash.priority";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		String parameter = request.getParameter(FLASH_PRIORITY_PARAM);
		String parameter2 = request.getParameter(FLASH_MESSAGE_PARAM);
		try{
			FlashMessage flashMessage = new FlashMessage(FlashMessage.Priority.valueOf( parameter ), parameter2);
			request.setAttribute(FLASH_ATTRIBUTE, flashMessage);
		}catch(Exception e){
			// no flash message available
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {		
	}
	
	public static String appendFlash(String originalUrl, FlashMessage flash){
		return computeUrl(originalUrl, flash.getPriority(), flash.getMessage());
	}
	
	public static String computeUrl(String originalUrl, Priority priority, String message) {
		String string = originalUrl;
		if(string.contains("?")){
			string += "&";
		}else{
			string += "?";
		}
		string += FlashMessageFilter.FLASH_PRIORITY_PARAM + "=" + priority + "&" + FlashMessageFilter.FLASH_MESSAGE_PARAM + "=" + message;
		return string;
	}

	public static String appendFlash(String originalUrl, Priority info, String string) {
		return computeUrl(originalUrl, info, string);
	}

}
