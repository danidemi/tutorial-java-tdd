package com.danidemi.tutorial.tdd.web.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.danidemi.tutorial.tdd.web.model.Actor;
import com.danidemi.tutorial.tdd.web.model.ActorDao;

@WebServlet("/col")
public class ActorGetServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private ActorDao actorDao;

    public ActorGetServlet() {
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/indexxx.jsp");
		requestDispatcher.forward(request, response);
	}

	public void setActorDao(ActorDao mock) {
		this.actorDao = mock;
	}
	
	

}
