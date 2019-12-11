package com.library.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.exception.LibraryException;
import com.library.services.LoginService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/servlet/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	LoginService loginService = new LoginService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String nextPage = "/jsp/login.jsp";
		
		String param = request.getParameter("param");
		if("logout".equals(param)){
			request.getSession().setAttribute("loginUser", null);
			request.getSession().setAttribute("loginType", null);
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nextPage = "/jsp/login.jsp";
		
		String type = request.getParameter("userType");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		Boolean status= false;
		try {
			status = loginService.getLogin(username, password, type);
			if(status){
				nextPage = "/servlet/MembersServlet";
				request.setAttribute("successMessage", "Successfully Login for "+username+" !!.");
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", username);
				session.setAttribute("loginType", type);
			}else{
				request.setAttribute("errorMessage", "User for username "+username+" and role "+type+" does not exist.");
			}
		} catch (LibraryException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
		
	}

}
