package library.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import library.model.Author;
import library.model.Book;
import library.model.BookManager;
import library.model.Category;
import library.model.User;

public class BookManagerRepository extends BaseRepository {

	public List<BookManager> findAll() {
		List<BookManager> bookManagers = null;
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM librarydb.book_manager as mb "
					+ " left join status_book as st "
					+ " on mb.status_id = st.status_id "
					+ " left join book as b "
					+ " on mb.book_id =b.book_id "
					+ " left join author as a "
					+ " on a.author_id = b.author_id "
					+ " left join category as c "
					+ " on b.category_id = c.category_id "
					+ " left join user as u "
					+ " on mb.user_id = u.user_id;");
			ResultSet rs = stmt.executeQuery();
			bookManagers = new ArrayList<BookManager>();
			while (rs.next()) {
				
				//info user
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setUsername(rs.getNString("user_name"));
                
                //info author
                Author author = new Author();
                author.setId(rs.getInt("author_id"));
                author.setName(rs.getNString("author_name"));
                
                //info category
                Category category = new Category();
                category.setId(rs.getInt("category_id"));
                category.setName(rs.getNString("category_name"));
                
                //info book
                Book book = new Book();
                book.setId(rs.getInt("book_id"));
                book.setName(rs.getNString("book_name"));
                book.setAuthor(author);
                book.setCategory(category);
                
                //info book manager
                BookManager bookManager = new BookManager();
                bookManager.setId(rs.getInt("manager_id"));
                bookManager.setUser(user);
                bookManager.setBook(book);
                bookManager.setNumberBorrowDay(rs.getInt("borrow_day"));
                bookManager.setStartDate(rs.getTimestamp("start_date"));
                bookManager.setConfirmDate(rs.getTimestamp("confirm_date"));
                bookManager.setEndDate(rs.getTimestamp("end_date"));
                
                //add into list
                bookManagers.add(bookManager);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookManagers;
	}

	public int add(int bookId, int numberBorrowDay, int userId) {
		
		int result = -1;
		try {
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO book_manager (`book_id`, `user_id`, `borrow_day`, `start_date`, `status_id`) VALUES (?, ?, ?, ?, ?);");
			stmt.setInt(1, bookId);
			stmt.setInt(2, userId);
			stmt.setInt(3, numberBorrowDay);
			stmt.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
			stmt.setInt(5, 1);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<BookManager> findByUser(int userId) {

		List<BookManager> bookManagers = null;
		try {
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM librarydb.book_manager as mb "
					+ " left join status_book as st "
					+ " on mb.status_id = st.status_id "
					+ " left join book as b "
					+ " on mb.book_id =b.book_id "
					+ " left join author as a "
					+ " on a.author_id = b.author_id "
					+ " left join category as c "
					+ " on b.category_id = c.category_id "
					+ " left join user as u "
					+ " on mb.user_id = u.user_id "
					+ " where mb.user_id = ?");
			
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			bookManagers = new ArrayList<BookManager>();
			while (rs.next()) {
				
				//info user
                User user = new User();
                user.setId(rs.getInt("user_id"));
                user.setUsername(rs.getNString("user_name"));
                
                //info author
                Author author = new Author();
                author.setId(rs.getInt("author_id"));
                author.setName(rs.getNString("author_name"));
                
                //info category
                Category category = new Category();
                category.setId(rs.getInt("category_id"));
                category.setName(rs.getNString("category_name"));
                
                //info book
                Book book = new Book();
                book.setId(rs.getInt("book_id"));
                book.setName(rs.getNString("book_name"));
                book.setAuthor(author);
                book.setCategory(category);
                
                //info book manager
                BookManager bookManager = new BookManager();
                bookManager.setId(rs.getInt("manager_id"));
                bookManager.setUser(user);
                bookManager.setBook(book);
                bookManager.setNumberBorrowDay(rs.getInt("borrow_day"));
                bookManager.setStartDate(rs.getTimestamp("start_date"));
                bookManager.setConfirmDate(rs.getTimestamp("confirm_date"));
                bookManager.setEndDate(rs.getTimestamp("end_date"));
                
                //add into list
                bookManagers.add(bookManager);
            }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookManagers;
	}

	public int confirmBorrow(int managerId) {
		
		int result = -1;
		try {
			PreparedStatement stmt = conn.prepareStatement("UPDATE book_manager SET confirm_date = ? WHERE manager_id = ?;");
			stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			stmt.setInt(2, managerId);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public int confirmRefund(int managerId) {

		int result = -1;
		try {
			PreparedStatement stmt = conn.prepareStatement("UPDATE book_manager SET end_date = ? WHERE manager_id = ?;");
			stmt.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			stmt.setInt(2, managerId);
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
