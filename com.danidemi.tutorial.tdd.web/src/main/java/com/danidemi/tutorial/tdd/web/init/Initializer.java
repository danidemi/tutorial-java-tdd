package com.danidemi.tutorial.tdd.web.init;

import com.danidemi.tutorial.tdd.web.controller.DeleteActorServlet;
import com.danidemi.tutorial.tdd.web.controller.GetActorsListServlet;
import com.danidemi.tutorial.tdd.web.controller.NewActorServlet;

public interface Initializer {

	void init(GetActorsListServlet getActorsListServlet);

	void init(NewActorServlet newActorServlet);

	void init(DeleteActorServlet deleteActorServlet);

}
