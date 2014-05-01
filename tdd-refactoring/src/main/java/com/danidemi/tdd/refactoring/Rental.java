package com.danidemi.tdd.refactoring;

/**
 * The rental class represents a customer renting a movie.
 */
class Rental extends DomainObject
    {
    public int daysRented() {
    	return _daysRented;
    }
    public Tape tape() {
    	return _tape;
    }
    private Tape _tape;
    public Rental(Tape tape, int daysRented) {
    	_tape = tape;
    	_daysRented = daysRented;
    }
    private int _daysRented;
    }
