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
import com.library.services.MemberService;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/servlet/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService = new MemberService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String nextPage = "/jsp/memberRegistration.jsp";
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "/jsp/memberRegistration.jsp";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String cpassword = request.getParameter("cpassword");
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String phoneN = request.getParameter("mnum");
		String emailId = request.getParameter("emailid");
		String uniqueId = request.getParameter("uId");
		String idType = request.getParameter("idType");
		String loginType = request.getParameter("userType");

		if (loginType.equalsIgnoreCase("admin") || loginType.equalsIgnoreCase("staff")) {
			HttpSession session = request.getSession();
			if (session != null && !"admin".equals((String) session.getAttribute("loginType"))) {
				request.setAttribute("errorMessage", "Only Administrator can register Admin or Staff.");
			}
		}

		if (!password.equals(cpassword)) {
			request.setAttribute("errorMessage", "Password and Confirm Password not matching.");
		}

		if (request.getAttribute("errorMessage") == null) {
			Boolean status = false;
			try {
				status = memberService.registerNewMember(username, password, fname, lname, phoneN, emailId,
						uniqueId, idType, loginType);
				if (status) {
					request.setAttribute("successMessage",
							"New Member " + fname + " " + lname + " registered Successfully");
				} else {
					request.setAttribute("errorMessage", "New Member " + fname + " " + lname + " registration failed.");
				}
			} catch (LibraryException e) {
				request.setAttribute("errorMessage", e.getMessage());
			}			
		}

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);

	}

}
