package projectmissinformation.controller;

import java.io.IOException;
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
		Cookie[] loginCookies = request.getCookies();
		if (loginCookies != null) {
			for (Cookie c : loginCookies) {
				if (c.getName().equals("user")) {
					c.setMaxAge(0);
					response.addCookie(c);
					response.sendRedirect("/ProjectMissInformation/Login.html");
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
		HttpSession session = request.getSession();					//ch
		DBHandler dbh = new DBHandler();
		
		String username = (String) request.getParameter("user");
		String password = (String) request.getParameter("password");

		if (checkLogin(username, password)) {
			
			System.out.println("user" + dbh.getUser(username));		//ch
			session.setAttribute("user", dbh.getUser(username));	//ch
			
			Cookie loginCookie = new Cookie("user", username);
			loginCookie.setMaxAge(30 * 60);
			response.addCookie(loginCookie);
			response.sendRedirect("/ProjectMissInformation/home.jsp");
		} else {
			response.sendRedirect("/ProjectMissInformation/Login.html");
		}
	}

	/**
	 * Check login.
	 *
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @return true, if successful
	 */
	@SuppressWarnings("unchecked")
	private boolean checkLogin(String username, String password) {
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
		EntityManager em = factory.createEntityManager();
		Query q = em.createQuery("SELECT u FROM User u");
		List<User> userList = q.getResultList();
		
		for(User u : userList){
			if (u.getName().equals(username)) {
				if (u.getPassword().equals(password)) {
					em.close();
					return true;
				}
			}
		}

		em.close();
		return false;
	}
}
