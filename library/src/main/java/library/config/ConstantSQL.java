package library.config;

public class ConstantSQL {

	//database name
	public static final String DB_NAME = "LIBRARYDB";
	
	//Constant for connect to sql server
	public static final String DB_URL_SQL_SERVER = "jdbc:sqlserver://localhost:1433;" + "databaseName=" + DB_NAME + ";"
			+ "integratedSecurity=true";
	public static final String FOR_NAME_SQL_SERVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	
	//Constant for connect to my sql
	public static final String DB_URL_MYSQL = "jdbc:mysql://localhost:3306/" + DB_NAME ;//+ "?useUnicode=true&characterEncoding=utf-8";
	public static final String USER_NAME = "root";
	public static final String PASSWORD = "12345678";
	public static final String FOR_NAME_MYSQL = "com.mysql.cj.jdbc.Driver";
}
