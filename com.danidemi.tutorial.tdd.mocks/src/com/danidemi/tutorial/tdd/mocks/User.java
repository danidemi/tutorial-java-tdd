package com.danidemi.tutorial.tdd.mocks;

public abstract class User {

	protected Long id;
	protected String password;
	protected boolean isLocked;
	protected int failures;
	protected String username;
	
	public void setLocked(boolean locked) {
		this.isLocked = locked;
	}

	void setUsername(String string) {
		this.username = string;
	}

	void setPassword(String string) {
		this.password = string;
	}

	public void lock() {
		setLocked(true);
	}	

	public long getTokenId() {
		return 0L;
	}

	public boolean passwordMatches(String password) {
		return this.password.equals(password);
	}
 
	public boolean isLocked() {
		return this.isLocked;
	}

	public void increaseFailuers() {
		failures++;
	}
	
	public int getFailures() {
		return failures;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
}
