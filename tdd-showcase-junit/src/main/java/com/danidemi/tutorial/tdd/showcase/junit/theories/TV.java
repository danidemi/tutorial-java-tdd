package com.danidemi.tutorial.tdd.showcase.junit.theories;

class TV implements Device {

	private boolean isOn;

	@Override
	public void turnOn() {
		this.isOn = true;
		
	}

	@Override
	public void turnOff() {
		this.isOn = false;
	}

	@Override
	public boolean isOn() {
		return isOn;
	}
	
}