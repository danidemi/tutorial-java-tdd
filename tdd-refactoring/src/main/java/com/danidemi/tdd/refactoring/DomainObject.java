package com.danidemi.tdd.refactoring;

/**
 * DomainObject is a general class that does a few standard things, such as hold a name.
 */
public class DomainObject {
	
	protected String _name = "no name";	

	public DomainObject (String name)	{
		_name = name;
	};

	public DomainObject ()	{};

	public String name ()	{
		return _name;
	};

	public String toString() {
		return _name;
	};

}