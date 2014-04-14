package com.danidemi.tutorial.tdd.web.view;

import org.apache.commons.lang.StringUtils;

import com.danidemi.tutorial.tdd.web.view.FlashMessage.Priority;

public class FlashMessage {
	
	public enum Priority {
		ERROR, WARNING, INFO
	}
	
	private Priority priority;
	private String message;
	
	public FlashMessage(Priority priority, String message) {
		super();
		
		if(StringUtils.isBlank(message)){
			throw new IllegalArgumentException("message cannot be blank");
		}
		
		this.priority = priority;
		this.message = message;
	}
	
	public String getPriorityName() {
		return priority.toString().toLowerCase();
	}
	
	public String getMessage() {
		return message;
	}

	public Priority getPriority() {
		return priority;
	}
	
}
