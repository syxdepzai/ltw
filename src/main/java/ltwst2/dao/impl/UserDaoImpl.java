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
		List<UserModel> list= new ArrayList<UserModel>();
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
				rs.getDate("creatDate")));
		return list; }
		} catch (Exception e) {e.printStackTrace();

		}		return null;
		
	}

	@Override
	public UserModel findbyId(int id) {
		String sql = "SELECT * FROM [Users] WHERE id = ? ";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setPassword(rs.getString("password"));
				user.setImages(rs.getString("images"));
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreateDate(rs.getDate("creatDate"));
				return user;
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;

	}

	@Override
	public void insert(UserModel user) {
		String sql = "INSERT INTO users(id, username, email, password, images, fullname, phone, roleid, createDate) VALUES (?,?,?,?, ?, ?, ?, ?, ?)";

		try {
			conn = super.getConnection();

			ps = conn.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getImages());
			ps.setString(6, user.getFullname());
			ps.executeUpdate();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Override
	public UserModel findByUserName(String username) {
		String sql = "SELECT * FROM [Users] WHERE username = ? ";
		try {
			conn = new DBConnectSQL().getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			while (rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setEmail(rs.getString("email"));
				user.setUsername(rs.getString("username"));
				user.setFullname(rs.getString("fullname"));
				user.setPassword(rs.getString("password"));
				user.setImages(rs.getString("images"));
				user.setRoleid(Integer.parseInt(rs.getString("roleid")));
				user.setPhone(rs.getString("phone"));
				user.setCreateDate(rs.getDate("creatDate"));
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
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
}