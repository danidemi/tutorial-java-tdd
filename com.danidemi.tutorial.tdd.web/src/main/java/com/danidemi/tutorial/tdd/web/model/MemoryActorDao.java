package com.danidemi.tutorial.tdd.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Singleton;

@Singleton
public class MemoryActorDao implements ActorDao {
	
	private List<Actor> actors;
	
	public MemoryActorDao() {
		actors = new ArrayList<Actor>();
		actors.add( new Actor("aaa1", "aaa2", new Date()) );
		actors.add( new Actor("bbb1", "bbb2", new Date()) );
		actors.add( new Actor("ccc1", "ccc2", new Date()) );
	}

	public void save(Actor actor) {
		actors.add(actor);
	}

	public Actor findBy(String firstName, String lastName) {
		for (Actor actor : actors) {
			if(actor.getFirstName().equals(firstName) && actor.getLastName().equals(lastName)){
				return actor;
			}
		}
		return null;
	}

	public List<Actor> findAll() {
		return actors;
	}

}
