package com.danidemi.tutorial.tdd.showcase.mockito;

import com.danidemi.tutorial.tdd.showcase.authentication.LoginException;
import com.danidemi.tutorial.tdd.showcase.authentication.Repository;
import com.danidemi.tutorial.tdd.showcase.authentication.Service;
import com.danidemi.tutorial.tdd.showcase.authentication.User;
import org.junit.Test;
import org.mockito.InOrder;

import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ServiceTest {

	@Test
	public void testGeneratePasswordWithSeedHappyPath() {
		//given
		long seed = 875115L;
		Service service = new Service();
		
		//when
		String result = service.generatePassword(seed);
		
		//then
		assertThat(result, is(equalTo("_b24ecd68b9!")));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testGeneratePasswordWithInvalidSeed() {
		//given
		long seed = -1L;
		Service service = new Service();
		
		//when
		String result = service.generatePassword(seed);
		
		//then
		//expect exception
	}
	
	@Test
	public void testLoginHappyPath() {
		Repository repo = mock(Repository.class);
		String username = "username";
		String password = "password";
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		Service service = new Service();
		service.setRepository(repo);
		
		try {
			when(repo.findUserByUsername(username)).thenReturn(user);
			doNothing().when(repo).logAccess(user);
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
		try {
			User result = service.login(username, password);
			assertNotNull(result);
			assertSame(user, result);
		} catch (LoginException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		try {
			verify(repo, times(1)).findUserByUsername(username);
			InOrder inOrder = inOrder(repo);
			inOrder.verify(repo).findUserByUsername(username);
			inOrder.verify(repo).logAccess(user);
		} catch (SQLException e) {
			e.printStackTrace();
			fail(e.getMessage());
		}
		
	}
}
