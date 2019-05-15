package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.Credentials;
import com.revature.beans.User;
import com.revature.utils.ConnectionUtil;

public class LoginDao {
	/**
	 * 
	 * @param c credentials to test expected username and password to be filled but salt to not be
	 * @return userid if successful
	 */
	public User checkCredByUsername(Credentials c) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT ers_users_id, ers_password, salt, USER_ROLE_ID, User_first_name, user_last_name FROM ers_users WHERE ers_username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, c.getUsername());
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String password = rs.getString("ers_password");
				String salt = rs.getString("salt");
				int id = rs.getInt("ers_users_id");
				User u = new User(id, null, null, rs.getString("User_first_name"),rs.getString("User_last_name"), null, rs.getInt("user_role_id"));
				if(c.verify(password,salt))
				{
					return u;
				}
				else
				{
					return null;
				}
			} else {
				//TODO throw new HttpException(400);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			// TODO throw new HttpException(500);
		}
		return null;
}
}
