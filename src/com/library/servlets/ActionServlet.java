package com.library.servlets;

import static com.library.common.ConstantPool.PARAM_ACTIVE_MEMBER_ID;
import static com.library.common.ConstantPool.PARAM_BOOK_AUTHOR;
import static com.library.common.ConstantPool.PARAM_BOOK_DESC;
import static com.library.common.ConstantPool.PARAM_BOOK_ISBN;
import static com.library.common.ConstantPool.PARAM_BOOK_TITLE;
import static com.library.common.ConstantPool.PARAM_BOOK_TYPE;
import static com.library.common.ConstantPool.PARAM_DELETE_ID;
import static com.library.common.ConstantPool.PARAM_DELETE_MEMBER_ID;
import static com.library.common.ConstantPool.PARAM_ERROR_MSG;
import static com.library.common.ConstantPool.PARAM_ISSUE_ID;
import static com.library.common.ConstantPool.PARAM_LOGIN_TYPE;
import static com.library.common.ConstantPool.PARAM_LOGIN_USER;
import static com.library.common.ConstantPool.PARAM_MANAGE_BOOKS;
import static com.library.common.ConstantPool.PARAM_MANAGE_LIBARY;
import static com.library.common.ConstantPool.PARAM_MANAGE_USERS;
import static com.library.common.ConstantPool.PARAM_MYISSUED_BOOKS;
import static com.library.common.ConstantPool.PARAM_PARAMETER;
import static com.library.common.ConstantPool.PARAM_RETURN_BOOK;
import static com.library.common.ConstantPool.PARAM_SUCCESS_MSG;
import static com.library.common.ConstantPool.ROLE_ADMIN;
import static com.library.common.ConstantPool.ROLE_MEMBER;
import static com.library.common.ConstantPool.ROLE_STAFF;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.common.LibraryUtills;
import com.library.exception.LibraryException;
import com.library.services.BookService;
import com.library.services.LibraryStoreService;
import com.library.services.MemberService;

/**
 * Servlet implementation class ActionServlet
 */
@WebServlet("/servlet/ActionServlet")
public class ActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private MemberService memberService = new MemberService();
	private BookService bookService = new BookService();
	private LibraryStoreService libraryStoreService = new LibraryStoreService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ActionServlet() {
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
		HttpSession session = request.getSession();
		String parameter = request.getParameter(PARAM_PARAMETER);
		String loginRole = (String) session.getAttribute(PARAM_LOGIN_TYPE);
		if (parameter != null && loginRole != null) {
			if (session != null && (ROLE_ADMIN.equals(loginRole) || ROLE_STAFF.equals(loginRole))) {
				try {
					request.setAttribute("inactiveUser", memberService.getInactivateMemberList());
					request.setAttribute("booktypes", LibraryUtills.getBookSubjectType().entrySet());
					request.setAttribute("lockBooks", libraryStoreService.getLockBooks());

				} catch (LibraryException e) {
					request.setAttribute("errorMessage", e.getMessage());
				}

				if (ROLE_STAFF.equals(loginRole)) {

				}

			}
			if (session != null && ROLE_MEMBER.equals(loginRole)) {
				try {
					request.setAttribute(PARAM_MYISSUED_BOOKS,
							libraryStoreService.getUserBooks((String) session.getAttribute(PARAM_LOGIN_USER)));
				} catch (LibraryException e) {
					request.setAttribute("errorMessage", e.getMessage());
				}
			}

			String nextJSP = "/jsp/actions.jsp";
			request.setAttribute("parameter", parameter);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String parameter = request.getParameter(PARAM_PARAMETER);
		String logineType = (String) session.getAttribute(PARAM_LOGIN_TYPE);
		if (session != null && (ROLE_ADMIN.equals(logineType) || ROLE_STAFF.equals(logineType))) {
			if (parameter != null && parameter.equals(PARAM_MANAGE_USERS)) {
				String[] activeMemberId = request.getParameterValues(PARAM_ACTIVE_MEMBER_ID);
				String[] deleteMemberId = request.getParameterValues(PARAM_DELETE_MEMBER_ID);

				try {
					memberService.activateMember(activeMemberId);
					memberService.deleteMember(deleteMemberId);
					request.setAttribute(PARAM_SUCCESS_MSG, " Record Activated/Deleted Successfully!!");
				} catch (LibraryException e) {
					request.setAttribute(PARAM_ERROR_MSG, e.getMessage());
				}
			}
			if (parameter != null && parameter.equals(PARAM_MANAGE_BOOKS)) {
				String booktype = request.getParameter(PARAM_BOOK_TYPE);
				String booktitle = request.getParameter(PARAM_BOOK_TITLE);
				String author = request.getParameter(PARAM_BOOK_AUTHOR);
				String description = request.getParameter(PARAM_BOOK_DESC);
				String isbn = request.getParameter(PARAM_BOOK_ISBN);

				try {
					bookService.registerNewBook(booktitle, booktype, isbn, author, description);
					request.setAttribute(PARAM_SUCCESS_MSG, " Book Added Successfully!!");
				} catch (LibraryException e) {
					request.setAttribute(PARAM_ERROR_MSG, e.getMessage());
				}
			}
			if (parameter != null && parameter.equals(PARAM_MANAGE_LIBARY)) {
				String[] issueIds = request.getParameterValues(PARAM_ISSUE_ID);
				String[] deleteIds = request.getParameterValues(PARAM_DELETE_ID);

				try {
					libraryStoreService.manageLibrary(issueIds, deleteIds);
					request.setAttribute(PARAM_SUCCESS_MSG, " Approved/Deleted Book Request Successfully!!");
				} catch (LibraryException e) {
					request.setAttribute(PARAM_ERROR_MSG, e.getMessage());
				}

			}
			if (parameter != null && parameter.equals(PARAM_RETURN_BOOK)) {
				String username = request.getParameter("searchUsername");
				String[] returnIds = request.getParameterValues("returnId");
				try {
					if (request.getParameter("returnBookSubmit") != null) {
						libraryStoreService.returnBooks(returnIds);
					}
					request.setAttribute("userBooks", libraryStoreService.getUserBooks(username));
					request.setAttribute("searchUsername", username);
					request.setAttribute(PARAM_SUCCESS_MSG, " Action completed  Syccessfully!!");

				} catch (LibraryException e) {
					request.setAttribute(PARAM_ERROR_MSG, e.getMessage());
				}

			}

			if (ROLE_STAFF.equals(logineType)) {

			}

		}
		if (session != null && ROLE_MEMBER.equals(logineType)) {

		}
		request.setAttribute("parameter", parameter);
		doGet(request, response);
	}

}
