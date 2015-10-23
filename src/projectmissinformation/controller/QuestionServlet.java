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
import javax.servlet.http.HttpSession;

import projectmissinformation.model.DBHandler;
import projectmissinformation.model.Question;
import projectmissinformation.model.User;

/**
 * @author Axel Servlet implementation class QuestionServlet
 */
@WebServlet("/QuestionServlet")
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		DBHandler dbHandler = new DBHandler();

		User u = dbHandler.getUser((String) session.getAttribute("username"));
		System.out.println(session.getAttribute("username"));
		List<Question> questionList = dbHandler.listQuestions(u);
		request.setAttribute("questionList", questionList);
		request.setAttribute("adminStatus", u.getAdmin());
		RequestDispatcher rd = request.getRequestDispatcher("qoa.jsp");
		rd.forward(request, response);
	}
}
