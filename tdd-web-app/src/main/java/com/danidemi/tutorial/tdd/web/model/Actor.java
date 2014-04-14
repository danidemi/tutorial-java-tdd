package com.danidemi.tutorial.tdd.web.model;

import java.util.Date;

import org.joda.time.Interval;

public class Actor {

	private String firstName;
	private String lastName;
	private Date birthDate;
	private Long id;
	
	public Actor() {
	
	}
	
	public Actor(String firstName, String lastName, Date birthDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

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

	public int getAgeInYears(Date currentDate) {
		return currentDate.after(birthDate) ? new Interval(birthDate.getTime(), currentDate.getTime()).toPeriod().getYears() : 0;
	}
	
	public Long getId() {
		return id;
	}
	
	void setId(Long id) {
		this.id = id;
	}

}
