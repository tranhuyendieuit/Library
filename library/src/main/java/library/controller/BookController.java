package library.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import library.common.Constant;
import library.repository.AttractedSQLBookRepository;
import library.repository.AuthorRepository;
import library.repository.BookRepository;
import library.repository.CategoryRepository;
import library.service.AuthorService;
import library.service.BookService;
import library.service.CategoryService;

@WebServlet(urlPatterns = "/addBook")
public class BookController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private BookService bookService;

	private AuthorService authorService;

	private CategoryService categoryService;

	public void init() {

		BookRepository bookRepository = new BookRepository();
		AttractedSQLBookRepository attractedSQLBookRepository = new AttractedSQLBookRepository();
		bookService = new BookService(bookRepository, attractedSQLBookRepository);

		AuthorRepository authorRepository = new AuthorRepository();
		authorService = new AuthorService(authorRepository);

		CategoryRepository categoryRepository = new CategoryRepository();
		categoryService = new CategoryService(categoryRepository);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if (Constant.ADD_ACTION.equals(action)) {
			forwardAdd(request, response);
		} else if (Constant.SUBMIT_ACTION.equals(action)) {
			forwardSubmit(request, response);
		}
	}

	private void forwardSubmit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String bookName = request.getParameter("name-book");
		byte[] bytes = bookName.getBytes(StandardCharsets.ISO_8859_1);
		bookName = new String(bytes, StandardCharsets.UTF_8);

		int authorId = Integer.parseInt(request.getParameter("author"));
		int categoryId = Integer.parseInt(request.getParameter("category"));
		bookService.addBook(bookName, authorId, categoryId);
		response.sendRedirect("/library/bookList");
	}

	private void forwardAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		request.setAttribute("authors", authorService.findAll());
		request.setAttribute("categories", categoryService.findAll());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/addbook.jsp");
		dispatcher.forward(request, response);

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		processRequest(request, response);
	}

}