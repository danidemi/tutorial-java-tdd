package com.danidemi.tutorial.tdd.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemoryActorDao implements ActorDao {
	
	private static long lastId = 0;
	
	private List<Actor> actors;
	
	public MemoryActorDao() {
		actors = new ArrayList<Actor>();
		save( new Actor("aaa1", "aaa2", new Date()) );
		save( new Actor("bbb1", "bbb2", new Date()) );
		save( new Actor("ccc1", "ccc2", new Date()) );
	}

	public void save(Actor actor) {
		actor.setId(++lastId);
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
	
	@Override
	public Actor findById(Long id) {
		for (Actor actor : actors) {
			if(actor.getId() == id){
				return actor;
			}
		}
		return null;
	}	
	
	@Override
	public Actor deleteById(long actorId){
		Actor findById = findById(actorId);
		if(findById!=null){
			actors.remove(findById);
			return findById;
		}else{
			return null;
		}
	}

	public List<Actor> findAll() {
		return actors;
	}

}
