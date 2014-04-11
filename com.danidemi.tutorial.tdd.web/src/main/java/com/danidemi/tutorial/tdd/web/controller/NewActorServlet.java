package com.danidemi.tutorial.tdd.web.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.danidemi.tutorial.tdd.web.init.Initializer;
import com.danidemi.tutorial.tdd.web.init.InitializerListener;
import com.danidemi.tutorial.tdd.web.model.Actor;
import com.danidemi.tutorial.tdd.web.model.ActorDao;

@WebServlet(urlPatterns="/new")
public class NewActorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ActorDao actorDao;

    public NewActorServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String birthDate = request.getParameter("birthDate");
		
		if(StringUtils.isBlank( birthDate )){
			throw new ServletException();
		}
		if(StringUtils.isBlank( lastName )){
			throw new ServletException();
		}		
		if(StringUtils.isBlank( firstName )){
			throw new ServletException();
		}				
		
		Actor existingActor = actorDao.findBy(firstName, lastName);
		if(existingActor!=null) throw new ServletException("Actor is already present.");
		
		Actor actor = new Actor();
		actor.setFirstName(firstName);
		actor.setLastName(lastName);
		try {
			actor.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(birthDate));
		} catch (ParseException e) {
			throw new ServletException(e);
		}
		actorDao.save(actor);
		
		List<Actor> findAll = actorDao.findAll();
		
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/actors.jsp");
		request.setAttribute("actors", findAll);
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
