package com.danidemi.tutorial.tdd.web.model;

public interface ActorDao {

	void save(Actor capture);

	Actor findBy(String firstName, String lastName);

}
