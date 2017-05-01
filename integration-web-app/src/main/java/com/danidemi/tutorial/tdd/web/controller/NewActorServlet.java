package com.danidemi.tutorial.tdd.web.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
import com.danidemi.tutorial.tdd.web.view.FlashMessage;
import com.danidemi.tutorial.tdd.web.view.FlashMessage.Priority;

@WebServlet(urlPatterns="/new")
public class NewActorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ActorDao actorDao;

    public NewActorServlet() {
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		if(existingActor!=null) {
			
			ServletContext sc = getServletContext();
			
			response.sendRedirect( response.encodeRedirectURL(FlashMessageFilter.appendFlash(
					sc.getContextPath(), 
					FlashMessage.Priority.ERROR, "Actor '" + firstName + " " + lastName + "' already exists.")) );
			
		}else{
			
			Actor actor = new Actor();
			actor.setFirstName(firstName);
			actor.setLastName(lastName);
			try {
				actor.setBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(birthDate));
			} catch (ParseException e) {
				throw new ServletException(e);
			}
			actorDao.save(actor);
			
			response.sendRedirect( 
					response.encodeRedirectURL(
							FlashMessageFilter.appendFlash(
									getServletContext().getContextPath(), 
									FlashMessage.Priority.INFO, 
									"Actor '" + firstName + " " + lastName + "' added")) 
							);
			
		}
		
				
	}



	public void setActorDao(ActorDao mock) {
		this.actorDao = mock;
	}
	
	@Override
	public void init() throws ServletException {
		Initializer initializer = InitializerListener.getInitializer(getServletContext());
		if(initializer!=null) {
			initializer.init(this);
		}
	}

}
