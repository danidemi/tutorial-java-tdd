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
			throw new InvalidCredentialException("User not specified");
		}
		if(!user.passwordMatches(password)){
			throw new InvalidCredentialException("Password does not match");
		}
		if(user.isLocked()){
			throw new UserLockedException();
		}
		if(! tokenService.check(user.getTokenId(), token) ){
			user.increaseFailuers();
			if(user.getFailures()>=3){
				user.lock();
				userDao.saveOrUpdate(user);
				throw new UserLockedException();				
			}else{
				userDao.saveOrUpdate(user);
				throw new InvalidCredentialException("Token does not match");
			}
		}
		return user;
		
	}

}
