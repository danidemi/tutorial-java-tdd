package com.danidemi.tutorial.tdd.mocks;

public interface UserDao {

	User findUserByUsername(String username);

	void saveOrUpdate(User user);

	User newUser();

}
