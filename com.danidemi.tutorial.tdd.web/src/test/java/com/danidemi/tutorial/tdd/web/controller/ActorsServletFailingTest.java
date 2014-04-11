package com.danidemi.tutorial.tdd.web.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletResponse;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.danidemi.tutorial.tdd.web.model.Actor;
import com.danidemi.tutorial.tdd.web.model.ActorDao;

import static org.hamcrest.Matchers.*;

@RunWith(Parameterized.class)
public class ActorsServletFailingTest {

	@Captor ArgumentCaptor<Actor> actorArg;
	String firstName;
	String lastName;
	String birthDate;
	
	
	public ActorsServletFailingTest(String firstName,
			String lastName, String birthDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	@Parameters
	public static List<Object[]> params(){
		return Arrays.asList( new Object[][]{
				// nulls
				{"Carl", null, "1974-02-23"}, 
				{null, "Doe", "1974-02-23"}, 
				{"X", "Y", null},
				// empties
				{"", "Ross", "1974-02-23"},
				{"Carl", "", "1974-02-23"},
				{"Carl", "Ross", ""}
		});
	}
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void should()
			throws ServletException, IOException {
		// given
		ActorDao actorDao = mock(ActorDao.class);
		
		NewActorServlet tested = new NewActorServlet();
		tested.setActorDao( actorDao );
		
		MockHttpServletRequest req = new MockHttpServletRequest();
		req.setMethod("POST");
		req.setParameter("firstName", firstName);
		req.setParameter("lastName", lastName);
		req.setParameter("birthDate", birthDate);
		
		ServletResponse res = new MockHttpServletResponse();
		
		// when
		Exception ex = null;
		try{
			tested.service(req, res);					
		}catch (Exception e) {
			ex = e;
		}
		
		// then
		assertThat( ex, instanceOf(ServletException.class));
		
	}
	
}
