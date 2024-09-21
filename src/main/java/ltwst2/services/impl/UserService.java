package ltwst2.services.impl;

import ltwst2.dao.IUserDao;
import ltwst2.dao.impl.UserDaoImpl;
import ltwst2.models.UserModel;
import ltwst2.services.IUserService;

public class UserService implements IUserService {
	IUserDao userDao = new UserDaoImpl();

	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.FindByUserName(username);
		if (user != null && password.equals(user.getPassword())) {
			return user;
		}
		return null;
	}

	@Override
	public UserModel FindByUserName(String username) {
		return userDao.findByUserName(username);
	}
	 @Override
	    public void insert(UserModel user) {
	        userDao.insert(user);
	    }
	 @Override
	 public UserModel findByEmail(String email) {
	     return userDao.findByEmail(email);
	 }

	 @Override
	 public void updateResetToken(int userId, String token) {
	     userDao.updateResetToken(userId, token);
	 }

	 @Override
	 public UserModel findByResetToken(String token) {
	     return userDao.findByResetToken(token);
	 }

	 @Override
	 public void updatePassword(int userId, String newPassword) {
	     userDao.updatePassword(userId, newPassword);
	 }
}

