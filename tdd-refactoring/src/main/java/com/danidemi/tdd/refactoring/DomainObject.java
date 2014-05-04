package com.danidemi.tdd.refactoring;

/**
 * DomainObject is a general class that does a few standard things, such as hold a name.
 */
public class DomainObject {
	
	protected String name = "no name";	

	public DomainObject (String name)	{
		this.name = name;
	};

	public DomainObject ()	{};

	public String name ()	{
		return name;
	};

	public String toString() {
		return name;
	};

}