package com.danidemi.tutorial.tdd.mocks;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

public class LoginTest_1 {

	/**
	 * [1] This test check that all components are invoked as supposed in a
	 * successfull scenario.
	 * 
	 * @throws UserLockedException
	 * @throws InvalidCredentialException
	 */
	@Test
	public void shouldSearchTheUserThroughDaoAndCheckItsTokenId_1()
			throws InvalidCredentialException, UserLockedException {

		// given
		TokenService tokenService = mock(TokenService.class);
		when(tokenService.check(1283L, "112358")).thenReturn(true);

		User user = mock(User.class);
		when(user.getTokenId()).thenReturn(1283L);
		when(user.passwordMatches(any(String.class))).thenReturn(true);

		UserDao userDao = mock(UserDao.class);
		when(userDao.findUserByUsername(anyString())).thenReturn(user);

		Login login = new Login();
		login.setUserDao(userDao);
		login.setTokenService(tokenService);

		// when
		User u = login.login("username", "password", "112358");

		// then
		verify(userDao).findUserByUsername("username");
		verify(tokenService).check(1283L, "112358");
		assertEquals(user, u);

	}

	/**
	 * [2] throw exception if password check fail
	 */
	@Test
	public void shouldFailIfPasswordFail_2() {

		// given
		User user = mock(User.class);

		UserDao userDao = mock(UserDao.class);
		when(user.passwordMatches(anyString())).thenReturn(false);

		TokenService tokenService = mock(TokenService.class);
		when(tokenService.check(anyLong(), anyString())).thenReturn(false);

		when(userDao.findUserByUsername(anyString())).thenReturn(user);

		// when
		Login login = new Login();
		login.setUserDao(userDao);
		login.setTokenService(tokenService);

		try {
			User u = login.login("username", "password", "112358");
			fail("Exception expected");
		} catch (InvalidCredentialException ice) {

		} catch (Exception e) {
			fail("Wrong exception");
		}

	}

	/**
	 * [3]<-[1] This test check that all components are invoked as supposed in a
	 * successfull scenario.
	 */
	@Test
	public void shouldSearchTheUserThroughDaoAndCheckItsTokenId_3()
			throws Exception {

		// given
		TokenService tokenService = mock(TokenService.class);
		when(tokenService.check(1283L, "112358")).thenReturn(true);
		
		User user = mock(User.class);
		when(user.passwordMatches(anyString())).thenReturn(true);
		when(user.getTokenId()).thenReturn(1283L);

		UserDao userDao = mock(UserDao.class);
		when(userDao.findUserByUsername(anyString())).thenReturn(user);

		// when
		Login login = new Login();
		login.setUserDao(userDao);
		login.setTokenService(tokenService);
		User u = login.login("username", "password", "112358");

		// then
		verify(userDao).findUserByUsername("username");
		verify(tokenService).check(1283L, "112358");
		assertEquals(user, u);
	}

}
