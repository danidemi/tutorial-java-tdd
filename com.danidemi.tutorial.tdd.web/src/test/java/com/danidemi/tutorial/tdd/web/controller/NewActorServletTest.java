package com.danidemi.tutorial.tdd.web.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.danidemi.tutorial.tdd.web.model.Actor;
import com.danidemi.tutorial.tdd.web.model.ActorDao;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
@RunWith(MockitoJUnitRunner.class)
public class NewActorServletTest {

	@Mock ActorDao actorDao;
	@Mock Actor foundActor;

	@Test public void shouldNotCreateAnActorCalledTheSameWay() throws IOException {
		
		// given
		NewActorServlet tested = new NewActorServlet();
		tested.setActorDao(actorDao);
		when(actorDao.findBy( any(String.class), any(String.class) )).thenReturn( foundActor );
		
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.setMethod("POST");
		req.setParameter("firstName", "Mark");
		req.setParameter("lastName", "Zuck");
		req.setParameter("birthDate", "2010-12-31");
		
		MockHttpServletResponse res = new MockHttpServletResponse();
		
		ServletException sex = null;
		try{
			// when
			tested.service(req, res);			
		}catch(ServletException se){
			sex = se;
		}
		
		// then
		assertThat( sex.getMessage(), equalTo("Actor is already present.") );
		
	}
	
}
