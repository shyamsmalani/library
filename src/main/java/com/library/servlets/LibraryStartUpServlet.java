package com.library.servlets;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.library.repository.DatabaseConnection;

/**
 * Servlet implementation class LibraryStartUpServlet
 */
@WebServlet("/LibraryStartUpServlet")
public class LibraryStartUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LibraryStartUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
    public void init() throws ServletException {
    	// TODO Auto-generated method stub
    	super.init();
    	Logger logger = Logger.getLogger("LibraryStartUpServlet");
    	DatabaseConnection.getDataSourceInstance();
    	logger.log(Level.INFO, "Datasource Connection created....");
    }


}
