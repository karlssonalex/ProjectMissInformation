package projectmissinformation.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import projectmissinformation.model.DBHandler;

	/**
	 * Servlet implementation class RegisterServlet
	 * @author Charlotte
	 */
	@WebServlet("/RegisterServlet")
	public class RegisterServlet extends HttpServlet {
		private static final long serialVersionUID = 1L;
	
		/**
		 * @see HttpServlet#HttpServlet()
		 */
		public RegisterServlet() {
			super();
		}
	
		/**
		 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
		}
	
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			DBHandler dbH = new DBHandler();	
			HttpSession session = request.getSession();
			
			session.setMaxInactiveInterval(10080 * 80);
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			request.setAttribute("user", username);
			request.setAttribute("password", password);
			
			//dbH.validateInput(username);
			//dbH.validateInput(password);
			
			if(!dbH.userExists(username)){
				try {
					dbH.createUser(username, password);
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
				getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			}
			else{
				request.setAttribute("duplicatename", "Username is already taken!");
				getServletContext().getRequestDispatcher("/registration.jsp").forward(request, response);
			}
	
		}
	}