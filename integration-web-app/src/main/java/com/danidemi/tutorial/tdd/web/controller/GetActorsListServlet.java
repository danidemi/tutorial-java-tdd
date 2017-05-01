package com.danidemi.tutorial.tdd.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.danidemi.tutorial.tdd.web.init.Initializer;
import com.danidemi.tutorial.tdd.web.init.InitializerListener;
import com.danidemi.tutorial.tdd.web.model.Actor;
import com.danidemi.tutorial.tdd.web.model.ActorDao;

@WebServlet(urlPatterns={"/", "/index,jsp"})
public class GetActorsListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ActorDao actorDao;

    public GetActorsListServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Actor> actors = actorDao.findAll();
		
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/actors.jsp");
		request.setAttribute("actors", actors);
		requestDispatcher.forward(request, response);
	}

	public void setActorDao(ActorDao mock) {
		this.actorDao = mock;
	}
	
	@Override
	public void init() throws ServletException {
		InitializerListener.getInitializer(getServletContext()).init(this);
	}

}
