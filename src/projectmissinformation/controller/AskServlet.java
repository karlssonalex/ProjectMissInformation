package projectmissinformation.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projectmissinformation.model.DBHandler;

/**
 * @author Alex
 * This servlet receives a question from user, and tells DBHandler to insert to database.
 */
@WebServlet("/AskServlet")
public class AskServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private String question;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		question = (String) request.getParameter("question");
		DBHandler dbHandler = new DBHandler();
		dbHandler.addQuestion(question, (String) request.getParameter("username"));
		
		response.sendRedirect("/ProjectMissInformation/QuestionServlet");
	}

}
