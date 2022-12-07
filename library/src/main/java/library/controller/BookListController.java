package library.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.common.Constant;
import library.model.User;
import library.repository.AttractedSQLBookRepository;
import library.repository.BookRepository;
import library.service.BookService;

@WebServlet(value = "/bookList")
public class BookListController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private BookService bookService;

	public void init() {

		BookRepository bookRepository = new BookRepository();
		AttractedSQLBookRepository attractedSQLBookRepository = new AttractedSQLBookRepository();
		bookService = new BookService(bookRepository, attractedSQLBookRepository);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String keyword = request.getParameter("keyword");
		request.setAttribute("books", bookService.findAll(keyword));
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constant.USER_INFO);
		if (user == null) {
			response.sendRedirect("/library");
			return;
		}
		RequestDispatcher dispatcher = null;
		if (Constant.ROLE_ADMIN.equals(user.getRole().getName())) {
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/booklistforadmin.jsp");
		} else {
			dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/booklist.jsp");
		}
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
