package ltwst2.services;

import ltwst2.models.UserModel;

public interface IUserService {
	UserModel login(String username, String password);

	UserModel FindByUserName(String username);

	void insert(UserModel newUser);

	UserModel findByEmail(String email);

	void updatePassword(int userId, String newPassword);

	

	UserModel findByResetToken(String token);

	void updateResetToken(int id, String token);
}
