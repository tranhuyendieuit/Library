package library.repository;

import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import library.model.Role;
import library.model.User;

public class AttackedSQLInjectionUserRepository extends BaseRepository {


	public User findUser(String username, String password) {
		User user  = null;
		try {
			String query_sql = "SELECT * FROM librarydb.user as u"
					+ " left join librarydb.role as r"
					+ " on u.role_id = r.role_id "
					+ " where user_name = '" + username + "' and password ='" + password + "';"; 
			
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query_sql);
			if (rs.next()) {

				// info role
				Role role = new Role();
				role.setId(rs.getInt("role_id"));
				role.setName(rs.getString("role_name"));

				// info book
				user = new User();	
				user.setId(rs.getInt("user_id"));
				user.setUsername(rs.getNString("user_name"));
				user.setPassword(rs.getNString("password"));
				user.setRole(role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
