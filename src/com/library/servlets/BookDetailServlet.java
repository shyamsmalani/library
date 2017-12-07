package com.library.servlets;

import static com.library.common.ConstantPool.PARAM_BOOK_ISBN;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.library.common.ConstantPool;
import com.library.common.LibraryUtills;
import com.library.domain.BookDetails;
import com.library.exception.LibraryException;
import com.library.services.BookService;
import com.library.services.LibraryStoreService;

/**
 * Servlet implementation class BookDetailServlet
 */
@WebServlet("/servlet/BookDetailServlet")
public class BookDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BookService bookService = new BookService();
	private LibraryStoreService libraryStoreService = new LibraryStoreService();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookDetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String isbn = request.getParameter(PARAM_BOOK_ISBN);

		if (isbn != null) {
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			try {
				if (!bookService.searchBooks(isbn, null, "ISBN", false, false).isEmpty() ) {
					response.getWriter().write("ISBN Already Exist!!");
				}
			} catch (LibraryException e) {
				e.printStackTrace();
			}
		} else {
			String nextJSP = "/jsp/bookdetails.jsp";
			request.setAttribute("booktypes", LibraryUtills.getBookSubjectType().entrySet());
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
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String submitType = request.getParameter(ConstantPool.PARAM_SUBMIT_TYPE);
		if ("Search".equals(submitType)) {
			String bookType = request.getParameter(ConstantPool.PARAM_BOOK_TYPE);
			String searchBy = request.getParameter(ConstantPool.PARAM_SEARCH_TYPE);
			String searchString = request.getParameter(ConstantPool.PARAM_BOOK_SEARCH);
			Boolean onlyAvail = request.getParameter(ConstantPool.PARAM_ONLY_AVAIL) == null ? Boolean.FALSE
					: Boolean.TRUE;
			Boolean sameTopic = request.getParameter(ConstantPool.PARAM_SAME_TOPIC) == null ? Boolean.FALSE
					: Boolean.TRUE;

			try {
				List<BookDetails> bookDetails = bookService.searchBooks(searchString, bookType, searchBy, onlyAvail,
						sameTopic);
				request.setAttribute("searchResult", bookDetails);
				request.setAttribute(ConstantPool.PARAM_SUCCESS_MSG, "Search Results!!");
			} catch (LibraryException e) {
				request.setAttribute(ConstantPool.PARAM_ERROR_MSG, e.getMessage());
			}
		}
		if ("Submit".equals(submitType)) {
			String[] deleteId = request.getParameterValues(ConstantPool.PARAM_DELETE_ID);
			String[] activeMemberId = request.getParameterValues(ConstantPool.PARAM_BOOK_LOCKID);

			try {
				if (activeMemberId != null && activeMemberId.length != 0)
					libraryStoreService.lockBook(activeMemberId, (String) session.getAttribute("loginUser"));
				if (deleteId != null && deleteId.length != 0)
					bookService.deletBook(deleteId);

				request.setAttribute(ConstantPool.PARAM_SUCCESS_MSG, "Record updated successfully!!");
			} catch (LibraryException e) {
				request.setAttribute(ConstantPool.PARAM_ERROR_MSG, e.getMessage());
			}

		}

		doGet(request, response);

	}

}
