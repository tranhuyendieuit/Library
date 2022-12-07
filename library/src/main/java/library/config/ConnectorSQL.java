package library.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectorSQL {

	private static Connection instance;

	private ConnectorSQL() {
	}

	/**
	 * create statement
	 * 
	 * @author DaiTQ6
	 * 
	 * @return Statement
	 */
	public static Connection getInstance() {

		if (instance == null) {
			instance = getConnection();
		}
		return instance;
	}

	/**
	 * create connection
	 * 
	 * @author DaiTQ6
	 *
	 * @return connection
	 * @throws SQLException
	 */
	public Statement getStatement() throws SQLException {

		// connnect to database
		Connection conn = getInstance();
		// crate statement
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

		return stmt;
	}

	/**
	 * create connection
	 * 
	 * @author DaiTQ6
	 *
	 * @return connection
	 */
	private static Connection getConnection() {

		Connection conn = null;
		try {
			Class.forName(ConstantSQL.FOR_NAME_MYSQL);
			conn = DriverManager.getConnection(ConstantSQL.DB_URL_MYSQL, ConstantSQL.USER_NAME,
					ConstantSQL.PASSWORD);
			System.out.println("connect to DB successfully!");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return conn;
	}
}
