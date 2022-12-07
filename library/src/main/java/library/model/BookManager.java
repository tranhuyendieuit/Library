package library.model;

import java.sql.Timestamp;

public class BookManager {
	
	private int id;

	private Book book;
	
	private User user;
	
	private int numberBorrowDay;
	
	private Timestamp startDate;

	private Timestamp confirmDate;
	
	private Timestamp endDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getNumberBorrowDay() {
		return numberBorrowDay;
	}

	public void setNumberBorrowDay(int numberBorrowDay) {
		this.numberBorrowDay = numberBorrowDay;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getConfirmDate() {
		return confirmDate;
	}

	public void setConfirmDate(Timestamp confirmDate) {
		this.confirmDate = confirmDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}
	

}
