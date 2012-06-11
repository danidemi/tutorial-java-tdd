package com.danidemi.tutorial.tdd.mocks;

public class Login {

	private TokenService tokenService;
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {

		this.userDao = userDao;
		
	}

	public void setTokenService(TokenService tokenService) {
		
		this.tokenService = tokenService;
		
	}

	public User login(String username, String password, String token) throws 
		InvalidCredentialException,
		UserLockedException {

		User user = userDao.findUserByUsername(username);
		if(user == null){
			throw new InvalidCredentialException();
		}
		if(!user.passwordMatches(password)){
			throw new InvalidCredentialException();
		}
		if(user.isLocked()){
			throw new UserLockedException();
		}
		if(! tokenService.check(user.getTokenId(), token) ){
			user.increaseFailuers();
			if(user.getFailures()>=3){
				user.setLocked(true);
				userDao.saveOrUpdate(user);
				throw new UserLockedException();				
			}else{
				userDao.saveOrUpdate(user);
				throw new InvalidCredentialException();
			}
		}
		return user;
		
	}

}
