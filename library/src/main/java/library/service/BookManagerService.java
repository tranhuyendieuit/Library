package library.service;

import java.util.List;

import library.model.BookManager;
import library.model.User;
import library.repository.BookManagerRepository;

public class BookManagerService {

	private BookManagerRepository bookManagerRepository;
	
	public BookManagerService(BookManagerRepository bookManagerRepository) {
		this.bookManagerRepository = bookManagerRepository;
	}

	public List<BookManager> findAll() {
		return bookManagerRepository.findAll();
	}

	public int addRegister(int bookId, int numberBorrowDay, User user) {
		
		return bookManagerRepository.add(bookId,numberBorrowDay,user.getId());
	}

	public Object findByUser(int userId) {
		return bookManagerRepository.findByUser(userId);
	}

	public int confirmBorrow(int managerId) {
		
		return bookManagerRepository.confirmBorrow(managerId);
	}

	public int confirmRefund(int managerId) {

		return bookManagerRepository.confirmRefund(managerId);
		
	}

}
