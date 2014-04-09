package com.danidemi.tutorial.tdd.mocks;

public class InvalidCredentialException extends Exception {

	public InvalidCredentialException(String string) {
		super(string);
	}
	
	public InvalidCredentialException() {
		super();
	}

}
