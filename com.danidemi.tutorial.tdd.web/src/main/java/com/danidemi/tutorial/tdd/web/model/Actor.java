package com.danidemi.tutorial.tdd.web.model;

import java.util.Date;

import org.joda.time.Interval;

public class Actor {

	private String firstName;
	private String lastName;
	private Date birthDate;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public int getAgeInYears(Date date) {
		return new Interval(birthDate.getTime(), date.getTime()).toPeriod().getYears();
	}

}
