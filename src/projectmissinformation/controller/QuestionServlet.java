package projectmissinformation.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projectmissinformation.model.DBHandler;
import projectmissinformation.model.Question;
import projectmissinformation.model.User;

/**
 * @author Axel
 * Servlet implementation class QuestionServlet
 */
@WebServlet("/QuestionServlet")
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String name;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		DBHandler dbHandler = new DBHandler();
		
		Cookie[] Cookies = request.getCookies();
		if (Cookies != null) {
			for (Cookie c : Cookies) {
				if (c.getName().equals("user")) {
					User u = dbHandler.getUser(c.getName());
					List<Question> questionList = dbHandler.listQuestions(u);
					request.setAttribute("questionList", questionList);
					request.setAttribute("adminStatus", u.getAdmin());
					RequestDispatcher rd = request.getRequestDispatcher("/ProjectMissInformation/qoa.jsp");
					rd.forward(request, response);
				}
			}
		}
	}

}
