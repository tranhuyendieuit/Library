package library.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.model.Author;

public class AuthorRepository extends BaseRepository {

	public List<Author> findAll() {

		List<Author> authors = null;
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM librarydb.author");
			ResultSet rs = stmt.executeQuery();
			authors = new ArrayList<Author>();
			while (rs.next()) {

				// info author
				Author author = new Author();
				author.setId(rs.getInt("author_id"));
				author.setName(rs.getNString("author_name"));

				// add into list
				authors.add(author);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return authors;
	}

}
