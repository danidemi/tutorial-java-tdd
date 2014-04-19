package com.danidemi.tutorial.tdd.showcase.junit.theories;

class Radio implements Device {

	private boolean isOff;

	@Override
	public void turnOn() {
		this.isOff = false;
		
	}

	@Override
	public void turnOff() {
		this.isOff = true;
	}

	@Override
	public boolean isOn() {
		return !isOff;
	}
	
}