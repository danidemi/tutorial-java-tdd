package com.danidemi.tutorial.tdd.web.model;

import java.util.List;

public interface ActorDao {

	void save(Actor capture);

	Actor findBy(String firstName, String lastName);

	List<Actor> findAll();

	Actor deleteById(long actorId);

	Actor findById(Long id);

}
