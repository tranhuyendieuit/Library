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
import library.common.Validation;
import library.model.User;
import library.repository.AttractedSQLBookRepository;
import library.repository.BookManagerRepository;
import library.repository.BookRepository;
import library.service.BookManagerService;
import library.service.BookService;

@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private BookService bookService;

	private BookManagerService bookManagerService;

	public void init() {

		BookRepository bookRepository = new BookRepository();
		AttractedSQLBookRepository attractedSQLBookRepository = new AttractedSQLBookRepository();
		bookService = new BookService(bookRepository, attractedSQLBookRepository);

		BookManagerRepository bookManagerRepository = new BookManagerRepository();
		bookManagerService = new BookManagerService(bookManagerRepository);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if (Constant.DETAIL_ACTION.equals(action)) {
			forwardDetail(request, response);
		} else if (Constant.SUBMIT_ACTION.equals(action)) {
			forwardSubmit(request, response);
		}
	}

	private void forwardSubmit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int bookId = Integer.parseInt(request.getParameter("book-id"));
		int numberBorrowDay = 0;
		if (Validation.isEmpty(request.getParameter("number-day"))) {
			numberBorrowDay = Integer.parseInt(request.getParameter("number-day"));
		}
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constant.USER_INFO);
		if (user == null) {
			response.sendRedirect("/library");
			return;
		}
		bookManagerService.addRegister(bookId, numberBorrowDay, user);
		response.sendRedirect("/library/history");
	}

	private void forwardDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("book", bookService.findById(id));
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/register.jsp");
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