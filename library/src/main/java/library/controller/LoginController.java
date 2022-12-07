package library.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;

import library.common.Constant;
import library.model.User;
import library.repository.AttackedSQLInjectionUserRepository;
import library.repository.UserRepository;
import library.service.UserService;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private UserService userService;

	public void init() {
		UserRepository userRepository = new UserRepository();
		AttackedSQLInjectionUserRepository attackedSQLInjectionUserRepository = new AttackedSQLInjectionUserRepository();
		userService = new UserService(userRepository, attackedSQLInjectionUserRepository);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("inputUsername");
		String password = request.getParameter("inputPassword");
		User user = userService.findUser(username, password);
		if (user != null) {
			// Create cookies for first and last names.
	        Cookie usernameCokkie = new Cookie("username", username);
	        Cookie passwordCokkie = new Cookie("password", password);
	 
	        // Set expiry date after 24 Hrs for both the cookies.
	        usernameCokkie.setMaxAge(60 * 60 * 24);
	        passwordCokkie.setMaxAge(60 * 60 * 24);
	 
	        // Add both the cookies in the response header.
	        response.addCookie(usernameCokkie);
	        response.addCookie(passwordCokkie);
			HttpSession session = request.getSession();
			session.setAttribute(Constant.USER_INFO, user);
			forwardSuccess(user , request, response);
		} else {
			forwardLogin(request, response);
		}
	}

	private void forwardSuccess(User user, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		if (Constant.ROLE_ADMIN.equals(user.getRole().getName())) {
			response.sendRedirect("/library/managerBorrowBook");
		}else {
			response.sendRedirect("/library/bookList");
		}

	}

	private void forwardLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("error", "wrong username or password!");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
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