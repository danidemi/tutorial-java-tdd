package com.danidemi.tutorial.tdd.web.init;

import com.danidemi.tutorial.tdd.web.controller.DeleteActorServlet;
import com.danidemi.tutorial.tdd.web.controller.GetActorsListServlet;
import com.danidemi.tutorial.tdd.web.controller.NewActorServlet;
import com.danidemi.tutorial.tdd.web.model.MemoryActorDao;

public class MemoryInitializer implements Initializer {

	private MemoryActorDao memoryDao;

	@Override
	public void init(GetActorsListServlet getActorsListServlet) {
		getActorsListServlet.setActorDao(  getMemoryDao() );
		
	}

	private MemoryActorDao getMemoryDao() {
		if(memoryDao == null ){
			memoryDao = new MemoryActorDao(); 
		}
		return memoryDao;
	}

	@Override
	public void init(NewActorServlet newActorServlet) {
		newActorServlet.setActorDao( getMemoryDao() );
	}

	@Override
	public void init(DeleteActorServlet deleteActorServlet) {
		deleteActorServlet.setActorDao( getMemoryDao() );
		
	}

}
