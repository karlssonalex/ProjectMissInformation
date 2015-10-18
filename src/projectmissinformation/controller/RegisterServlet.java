package projectmissinformation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projectmissinformation.model.DBHandler;

/***
 * 
 * @author Charlotte
 *
 */
	/**
	 * Servlet implementation class RegisterServlet
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
	
			response.getWriter().append("Served at: ").append(request.getContextPath());
		}
	
		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
		 *      response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			
			DBHandler dbH = new DBHandler();			
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			dbH.createUser(username, password);
	
			getServletContext().getRequestDispatcher("/Login.html").forward(request, response);
	
		}
	}