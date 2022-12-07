package library.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import library.common.Constant;
import library.model.User;
import library.repository.BookManagerRepository;
import library.service.BookManagerService;

@WebServlet(value = "/confirmRefund")
public class ConfirmRefundController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private BookManagerService bookManagerService;

	public void init() {

		BookManagerRepository bookManagerRepository = new BookManagerRepository();
		bookManagerService = new BookManagerService(bookManagerRepository);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constant.USER_INFO);
		if (user == null) {
			response.sendRedirect("/library");
			return;
		}
		int managerId = Integer.parseInt(request.getParameter("managerId"));
		bookManagerService.confirmRefund(managerId);
		response.sendRedirect("/library/managerBorrowBook");
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
