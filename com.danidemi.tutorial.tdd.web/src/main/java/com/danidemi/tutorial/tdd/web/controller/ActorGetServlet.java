package com.danidemi.tutorial.tdd.web.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.danidemi.tutorial.tdd.web.model.Actor;
import com.danidemi.tutorial.tdd.web.model.ActorDao;
import com.danidemi.tutorial.tdd.web.model.MemoryActorDao;

@WebServlet("/")
public class ActorGetServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private ActorDao actorDao;

    public ActorGetServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Actor> actors = actorDao.findAll();
		
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/indexxx.jsp");
		request.setAttribute("actors", actors);
		requestDispatcher.forward(request, response);
	}

	public void setActorDao(ActorDao mock) {
		this.actorDao = mock;
	}
	
	@Override
	public void init() throws ServletException {
		this.actorDao = new MemoryActorDao();
	}

}
