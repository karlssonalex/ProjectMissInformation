package projectmissinformation.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import projectmissinformation.model.DBHandler;
import projectmissinformation.model.User;

/**
 * Servlet implementation class Login.
 * @author Alex
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The persistence unit name. */
	private final String PERSISTENCE_UNIT_NAME = "ProjectMissInformation";

	/** The factory. */
	private EntityManagerFactory factory;

	/**
	 * Instantiates a new login.
	 *
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		if(session != null) {
			session.invalidate();
		}
		
		Cookie[] loginCookies = request.getCookies();
		if (loginCookies != null) {
			for (Cookie c : loginCookies) {
				if (c.getName().equals("username")) {
					c.setMaxAge(0);
					response.addCookie(c);
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					response.sendRedirect("/ProjectMissInformation/login.jsp");
				}
			}
		}
	}

	/**
	 * Do post.
	 *
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @throws ServletException
	 *             the servlet exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		DBHandler dbH = new DBHandler();
		
		session.setMaxInactiveInterval(10080 * 60);
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		
		try {
			if (dbH.checkLogin(username, password)) {	
				Cookie loginCookie = new Cookie("username", username);
				loginCookie.setMaxAge(30 * 60);
				response.addCookie(loginCookie);
				session.setAttribute("Authenticated", new Boolean(true));
				response.sendRedirect("/ProjectMissInformation/home.jsp");
			} else {
				response.sendRedirect("/ProjectMissInformation/login.jsp");
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
}
