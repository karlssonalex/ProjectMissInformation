package projectmissinformation.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
 * @author Axel
 * This servlet receives a question from user, and tells DBHandler to insert to database.
 */
@WebServlet("/AskServlet")
public class AskServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String question, user;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user1 = new User();						//ch
		HttpSession session = request.getSession();		//ch
		
		question = request.getParameter("question");
		user1 = (User)session.getAttribute("user");		//ch
		System.out.println(user1);
		DBHandler dbHandler = new DBHandler();
		dbHandler.addQuestion(question, user1.getName());	//ch - ändrat lite
		
		response.sendRedirect("/ProjectMissInformation/mainbox.html");
	}

}
