package projectmissinformation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projectmissinformation.model.DBHandler;

/**
 * @author Alex This servlet receives a request from admin and handles it.
 */
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
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
		String username = request.getParameter("username");
		int admin = Integer.parseInt(request.getParameter("admin"));
		DBHandler dbH = new DBHandler();

		if (dbH.validateInput(username)) {
			dbH.updateUser(username, admin);
		} else {
			request.setAttribute("error", "The characters you entered are not allowed!");
		}
		getServletContext().getRequestDispatcher("/adminpage.jsp").forward(request, response);
	}

}
