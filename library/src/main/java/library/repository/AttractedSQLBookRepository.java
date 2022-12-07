package library.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import library.model.Author;
import library.model.Book;
import library.model.Category;

public class AttractedSQLBookRepository extends BaseRepository {

	public List<Book> findAll(String keyword) {
		List<Book> books = null;
		try {
			String conditionSql = "";
			if (keyword != null && !"".equals(keyword.trim())) {
				conditionSql = " Where b.book_name like '%" + keyword + "%'";
			}
			String query = "SELECT b.book_id, "
					+ " b.book_name, "
					+ " b.author_id, "
					+ " a.author_name, "
					+ " b.category_id, "
					+ " c.category_name "
					+ " FROM librarydb.book as b " 
					+ " LEFT JOIN author as a "
					+ " on a.author_id = b.author_id " 
					+ " LEFT JOIN category as c "
					+ " on b.category_id = c.category_id " + conditionSql;
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			books = new ArrayList<Book>();
			while (rs.next()) {

				// info author
				Author author = new Author();
				author.setId(rs.getInt(3));
				author.setName(rs.getNString(4));

				// info category
				Category category = new Category();
				category.setId(rs.getInt(5));
				category.setName(rs.getNString(6));

				// info book
				Book book = new Book();
				book.setId(rs.getInt(1));
				book.setName(rs.getNString(2));
				book.setAuthor(author);
				book.setCategory(category);

				// add into list
				books.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return books;
	}

	public Book findById(int id) {
		Book book  = null;
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM librarydb.book as b "
					+ " left join author as a "
					+ " on a.author_id = b.author_id "
					+ " left join category as c "
					+ " on b.category_id = c.category_id where book_id = ?");
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {

				// info author
				Author author = new Author();
				author.setId(rs.getInt("author_id"));
				author.setName(rs.getNString("author_name"));

				// info category
				Category category = new Category();
				category.setId(rs.getInt("category_id"));
				category.setName(rs.getNString("category_name"));

				// info book
				book = new Book();
				book.setId(rs.getInt("book_id"));
				book.setName(rs.getNString("book_name"));
				book.setAuthor(author);
				book.setCategory(category);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return book;
	}

	public int add(String bookName, int authorId, int categoryId) {
		int result = -1;
		try {
			String query = "INSERT INTO `librarydb`.`book` "
					+ "(`book_name`, `author_id`, `category_id`)" 
					+ " VALUES ('" + bookName + "' , " + authorId + ", " + categoryId + ");";

			Statement stmt = conn.createStatement();
			result = stmt.execute(query) ? 1 : -1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
