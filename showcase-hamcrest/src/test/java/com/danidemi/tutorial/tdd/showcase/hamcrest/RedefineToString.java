package com.danidemi.tutorial.tdd.showcase.hamcrest;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class RedefineToString extends BaseMatcher {

	@Override
	public boolean matches(Object item) {
		boolean isDefaultToString = true;
		String string = item.toString();
		if(string.matches("[\\w\\.]*@[\\w]+")){
			String className = string.split("@")[0];
			try {
				Class.forName(className);
				isDefaultToString = true;
			} catch (ClassNotFoundException e) {
				isDefaultToString = false;
			}
		}else{
			isDefaultToString = false;
		}
		return !isDefaultToString;
	}

	@Override
	public void describeTo(Description description) {
		description.appendText("should refedine 'toString()'");
	}
	
}
