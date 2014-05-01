package com.danidemi.tdd.refactoring;
 /**
  * The tape class represents a physical tape.
  */
public class Tape extends DomainObject {
	public Movie movie() {
		return _movie;
	}

	public Tape(String serialNumber, Movie movie) {
		_serialNumber = serialNumber;
		_movie = movie;
	}

	private String _serialNumber;
	private Movie _movie;
}