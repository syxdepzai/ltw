package ltwst2.services;

import ltwst2.models.UserModel;

public interface IUserService {
	UserModel login(String username, String password);
	
	UserModel FindByUserName(String username);

	void insert(UserModel newUser);
}
