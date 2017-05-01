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
import com.danidemi.tutorial.tdd.web.view.FlashMessage;

@WebServlet(urlPatterns="/delete")
public class DeleteActorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private ActorDao actorDao;

    public DeleteActorServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Long id = Long.parseLong( request.getParameter("id") );
						
		Actor deleted = actorDao.deleteById(id);
		
		
		response.sendRedirect( response.encodeRedirectURL(FlashMessageFilter.appendFlash(getServletContext().getContextPath(), FlashMessage.Priority.INFO, "Actor '" + deleted.getFirstName() + " " + deleted.getLastName() + "' removed")) );		
		
	}

	public void setActorDao(ActorDao mock) {
		this.actorDao = mock;
	}
	
	@Override
	public void init() throws ServletException {
		InitializerListener.getInitializer(getServletContext()).init(this);
	}

}
