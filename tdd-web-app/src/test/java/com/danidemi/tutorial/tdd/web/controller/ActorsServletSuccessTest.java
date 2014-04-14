package com.danidemi.tutorial.tdd.web.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import com.danidemi.tutorial.tdd.web.model.Actor;
import com.danidemi.tutorial.tdd.web.model.ActorDao;

@RunWith(Parameterized.class)
public class ActorsServletSuccessTest {

	@Captor ArgumentCaptor<Actor> actorArg;
	String firstName;
	String lastName;
	String birthDate;
	int expectedAge;
	NewActorServlet tested;
	
	
	
	public ActorsServletSuccessTest(String firstName,
			String lastName, String birthDate, int expectedAge) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.expectedAge = expectedAge;
	}

	@Parameters
	public static List<Object[]> params(){
		return Arrays.asList( new Object[][]{
				{"Carl", "Ross", "1974-02-23", 10},
				{"Jean", "Doe", "1974-02-23", 10},
				{"X", "Y", "2010-02-23", 0}
		});
	}
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Before
	public void setUpServlet() throws ServletException {
		tested = new NewActorServlet();
		ServletConfig config = mock(ServletConfig.class);
		ServletContext context = mock(ServletContext.class);
		when(config.getServletContext()).thenReturn(context);
		when(context.getContextPath()).thenReturn("/ctx");
		tested.init( config );		
	}

	@Test
	public void should()
			throws ServletException, IOException {
		// given
		ActorDao actorDao = mock(ActorDao.class);
		

		tested.setActorDao( actorDao );
		
		
		ServletContext sc;
		MockHttpServletRequest req = new MockHttpServletRequest( mock(ServletContext.class) );
		req.setMethod("POST");
		req.setParameter("firstName", firstName);
		req.setParameter("lastName", lastName);
		req.setParameter("birthDate", birthDate);
		
		MockHttpServletResponse res = new MockHttpServletResponse();
		
		// when
		tested.service(req, res);
		
		// then
		verify(actorDao).save(actorArg.capture());
		
		assertThat(actorArg.getValue().getFirstName(), equalTo( firstName ));
		assertThat(actorArg.getValue().getLastName(), equalTo( lastName ));
		assertThat(actorArg.getValue().getAgeInYears( new DateTime(1985, DateTimeConstants.JANUARY, 10, 0, 0).toDate() ), equalTo( expectedAge ));
	}
	
}
