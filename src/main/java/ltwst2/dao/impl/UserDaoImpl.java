package ltwst2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import ltwst2.config.DBConnectSQL;
import ltwst2.dao.IUserDao;
import ltwst2.models.UserModel;

public class UserDaoImpl extends DBConnectSQL implements IUserDao {
    public Connection conn = null;
    public PreparedStatement ps = null;
    public ResultSet rs = null;

    @Override
    public List<UserModel> findAll() {
        String sql = "SELECT * FROM users";
        List<UserModel> list = new ArrayList<UserModel>();
        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new UserModel(
                        rs.getInt("id"),    
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("images"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getString("phone"),
                        rs.getInt("roleid"),
                        rs.getDate("createDate")));
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public UserModel findbyId(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                UserModel user = new UserModel();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setFullname(rs.getString("fullname"));
                user.setPassword(rs.getString("password"));
                user.setImages(rs.getString("images"));
                user.setRoleid(rs.getInt("roleid"));
                user.setPhone(rs.getString("phone"));
                user.setCreateDate(rs.getDate("createDate"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void insert(UserModel user) {
        // Câu lệnh SQL bỏ cột id vì id sẽ được tự động tăng
        String sql = "INSERT INTO users(username, email, password, fullname, phone, roleid, createDate) "
                + "VALUES (?, ?, ?, ?, ?, ?, NOW())";

        try (Connection conn = super.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword()); // Có thể thêm mã hóa mật khẩu nếu cần
            ps.setString(4, user.getFullname());
            ps.setString(5, user.getPhone());
            ps.setInt(6, user.getRoleid()); // Giả sử roleid là 1 cho user thường
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserModel findByUserName(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try {
            conn = new DBConnectSQL().getConnection();
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                UserModel user = new UserModel();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setUsername(rs.getString("username"));
                user.setFullname(rs.getString("fullname"));
                user.setPassword(rs.getString("password"));
                user.setImages(rs.getString("images"));
                user.setRoleid(rs.getInt("roleid"));
                user.setPhone(rs.getString("phone"));
                user.setCreateDate(rs.getDate("createDate"));
                return user;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            IUserDao userDao = new UserDaoImpl();
            System.out.println(userDao.findbyId(1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

	@Override
	public void updatePassword(int userId, String newPassword) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public UserModel findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserModel findByResetToken(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateResetToken(int userId, String token) {
		// TODO Auto-generated method stub
		
	}
}
