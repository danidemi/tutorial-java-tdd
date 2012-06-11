package com.danidemi.tutorial.tdd.mocks;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

public class LoginTest_2 {
	
	Login login;
	@Mock User user;
	@Mock UserDao userDao;
	@Mock TokenService tokenService;
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
		login = new Login();
		login.setTokenService(tokenService);
		login.setUserDao(userDao);
	}
	
	/**
	 * [2] throw exception if password check fail
	 */	
	@Test
	public void shouldFailIfPasswordFail_2() {
		
		// given
		userDaoWillReturnUserForAnyPassword();		
		tokenServiceWillAlwaysFailVerification();
		userWillFailPasswordMatch();
		
		// when then
		try{
			User u = login.login("username", "password", "112358");
			fail("Exception expected");
		}catch(InvalidCredentialException ice){
			
		}catch(Exception e){
			fail("Wrong exception");
		}
		
	}
	
	/**
	 * [3]<-[1] This test check that all components are invoked
	 * as supposed in a successfull scenario.  
	 */
	@Test
	public void shouldSearchTheUserThroughDaoAndCheckItsTokenId_3() 
			throws Exception {
		
		// given
		userDaoWillReturnUserForAnyPassword();
		tokenServiceWillConfirm();
		userWillMatchPassword();
		userWillReturnTokenId(1283L);
		
		// when
		User u = login.login("username", "password", "112358");
		
		// then
		verify( userDao ).findUserByUsername( "username" );
		verify( tokenService ).check( 1283L, "112358" );
		assertEquals( user, u );
		
	}
	
	/**
	 * [4]
	 */
	@Test
	public void shouldFailIfUsernameNotFound_4(){
		
		// given
		userDaoWontFindAnyUser();
		
		// when
		// when then
		try{
			User u = login.login("username", "password", "112358");
			fail("Exception expected");
		}catch(InvalidCredentialException ice){
			
		}catch(Exception e){
			fail("Wrong exception " + e);
		}
	}
	
	/**
	 * [5]
	 */
	@Test
	public void shouldFailIfTokenIdentificationFails_5(){
		// given
		userDaoWillReturnUserForAnyPassword();
		userWillMatchPassword();
		tokenServiceWillAlwaysFailVerification();
		
		// when then
		try{
			login.login("username", "password", "112358");
			fail("Exception expected");
		}catch(InvalidCredentialException ice){
			
		}catch(Exception e){
			fail("Wrong exception " + e);
		}
	}
	
	/**
	 * [6]
	 */	
	@Test
	public void shouldFailIfUserIsLocked_6(){
		
		//given
		userDaoWillReturnUserForAnyPassword();
		tokenServiceWillConfirm();
		userWillMatchPassword();
		userWillBeLocked();
		
		// when then
		try{
			login.login("username", "password", "112358");
			fail("Exception expected");
		}catch(UserLockedException ule){
			
		}catch(Exception e){
			fail("Wrong exception " + e);
		}		
		
	}

//	/**
//	 * [7] This is maybe too complex
//	 */		
//	@Test
//	public void shouldLockAKnownUserWhenEnteringThreeWrongTokenCodeInARow() throws InvalidCredentialException, UserLockedException {
//		
//		//given
//		userDaoWillReturnUserForAnyPassword();
//		tokenServiceWillConfirm();
//		userWillMatchPassword();
//		
//		//when
//		login.login("username", "password", "112358");
//		login.login("username", "password", "112358");
//		
//		try{
//			login.login("username", "password", "112358");
//			fail();
//		}catch(UserLockedException ule){
//			
//		}
//		
//		//then
//		verify( user ).setLocked(true);
//		verify( userDao.saveOrUpdate( user ) );
//		
//		
//	}
	
//	/**
//	 * [8]
//	 */		
//	@Test
//	public void shouldIncreaseTheFailureCounter_8() throws Exception {
//		
//		//given
//		userDaoWillReturnUserForAnyPassword();
//		tokenServiceWillAlwaysFailVerification();
//		userWillMatchPassword();
//		
//		//when		
//		try{
//			login.login("username", "password", "112358");
//		}catch(Exception e){
//			
//		}
//		
//		//then
//		// this could be wrong because it is not in order
//      // in the impl...
//		// 			user.increaseFailuers();
//		//			userDao.saveOrUpdate(user);
//		//	or...
//	//			userDao.saveOrUpdate(user);
//	// 			user.increaseFailuers();
//		verify( user ).increaseFailuers();
//		verify( userDao ).saveOrUpdate( user );
//		
//	}
	
	/**
	 * [9]
	 */		
	@Test
	public void shouldIncreaseTheFailureCounter_9() throws Exception {
		
		//given
		userDaoWillReturnUserForAnyPassword();
		tokenServiceWillAlwaysFailVerification();
		userWillMatchPassword();
		
		//when		
		try{
			login.login("username", "password", "112358");
		}catch(Exception e){
			
		}
		
		//then
		InOrder inOrder = inOrder( userDao, user );
		inOrder.verify( user ).increaseFailuers();
		inOrder.verify( userDao ).saveOrUpdate( user );
		
	}
	
	/**
	 * [10]
	 */
	@Test
	public void shouldLockTheUserIfAMaximumAmountOf(){
		
		//given
		userDaoWillReturnUserForAnyPassword();
		tokenServiceWillAlwaysFailVerification();
		userWillMatchPassword();
		when( user.getFailures() ).thenReturn( 3 ); 
		
		//when		
		try{
			login.login("username", "password", "112358");
			fail();
		}catch(UserLockedException e){
			
		}catch(Exception e){
			fail("Unexpected " + e);
		}
		
		//then
		InOrder inOrder = inOrder( userDao, user );
		inOrder.verify( user ).lock();
		inOrder.verify( userDao ).saveOrUpdate( user );		
	}
	
	private void userDaoWontFindAnyUser() {
		when( userDao.findUserByUsername( anyString() ))
		.thenReturn( null );
	}
	
	private void userWillBeLocked() {
		when( user.isLocked() ).thenReturn( true );
	}

	public void userWillReturnTokenId(long tokenId) {
		when( user.getTokenId() ).thenReturn( tokenId );
	}

	public void userWillMatchPassword() {
		when( user.passwordMatches(anyString()) ).thenReturn( true );
	}
	
	public void userDaoWillReturnUserForAnyPassword() {
		when( userDao.findUserByUsername( anyString() ))
		.thenReturn( user );
	}

	public void tokenServiceWillAlwaysFailVerification() {
		when( tokenService.check(anyLong(), anyString()) ).thenReturn( false );
	}
	
	private void tokenServiceWillConfirm() {
		when( tokenService.check(anyLong(), anyString()) ).thenReturn( true );		
	}	

	public void userWillFailPasswordMatch() {
		when(user.passwordMatches(anyString())).thenReturn(false);
	}	

}
