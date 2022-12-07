package library.service;

import java.util.List;

import library.model.Book;
import library.repository.AttractedSQLBookRepository;
import library.repository.BookRepository;

public class BookService {

	BookRepository bookRepository;
	
	AttractedSQLBookRepository attractedSQLBookRepository;

	public BookService(BookRepository bookRepository, AttractedSQLBookRepository attractedSQLBookRepository) {
		this.bookRepository = bookRepository;
		this.attractedSQLBookRepository = attractedSQLBookRepository;
	}

	public List<Book> findAll(String keyword) {

//		return bookRepository.findAll(keyword);
		return attractedSQLBookRepository.findAll(keyword);
	}

	public Book findById(int id) {

		return bookRepository.findById(id);
	}

	public int addBook(String bookName, int authorId, int categoryId) {

//		return bookRepository.add(bookName, authorId, categoryId);
		return attractedSQLBookRepository.add(bookName, authorId, categoryId);
	}

}
