package library.repository;

import java.sql.Connection;

import library.config.ConnectorSQL;

public class BaseRepository {

	protected Connection conn;

	public BaseRepository() {

		this.conn = ConnectorSQL.getInstance();
	}

}
