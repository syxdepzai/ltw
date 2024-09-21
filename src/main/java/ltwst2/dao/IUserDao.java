package ltwst2.dao;

import java.util.List;
import ltwst2.models.UserModel;

public interface IUserDao {

    List<UserModel> findAll();
    
    UserModel findbyId(int id);
    
    void insert(UserModel user);

    UserModel findByUserName(String username);

    // Phương thức cập nhật mật khẩu người dùng
    void updatePassword(int userId, String newPassword);

	UserModel findByEmail(String email);

	UserModel findByResetToken(String token);

	void updateResetToken(int userId, String token);
}
