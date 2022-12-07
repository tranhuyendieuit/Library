package library.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.model.Author;
import library.model.Book;
import library.model.Category;

public class BookRepository extends BaseRepository{

	public List<Book> findAll(String keyword) {
		List<Book> books = null;
		try {
			String conditionSql = "";
			if (keyword != null && !"".equals(keyword.trim())) {
				conditionSql = " Where b.book_name like ?";
			}
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM librarydb.book as b "
					+ " left join author as a "
					+ " on a.author_id = b.author_id "
					+ " left join category as c "
					+ " on b.category_id = c.category_id " + conditionSql);
			if (keyword != null && !"".equals(keyword.trim())) {
				stmt.setNString(1, "%" + keyword.trim() + "%");
			}
			ResultSet rs = stmt.executeQuery();
			books = new ArrayList<Book>();
			while (rs.next()) {

				// info author
				Author author = new Author();
				author.setId(rs.getInt("author_id"));
				author.setName(rs.getNString("author_name"));

				// info category
				Category category = new Category();
				category.setId(rs.getInt("category_id"));
				category.setName(rs.getNString("category_name"));

				// info book
				Book book = new Book();
				book.setId(rs.getInt("book_id"));
				book.setName(rs.getNString("book_name"));
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
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO `librarydb`.`book` (`book_name`, `author_id`, `category_id`) VALUES (?, ?, ?);");
			stmt.setNString(1, bookName);
			stmt.setInt(2, authorId);
			stmt.setInt(3, categoryId);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
