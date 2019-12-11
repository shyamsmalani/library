package com.library.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.library.domain.LibraryDatasource;

public class DatabaseConnection {
	
	Logger logger = Logger.getLogger("DatabaseConnection");
	
	private static LibraryDatasource datasource;

	public static LibraryDatasource getDataSourceInstance(){
		if(datasource == null){
			datasource = new LibraryDatasource("jdbc:mysql://localhost:3306/librarynew",  // url
					"root", // username
					"", //password
					"com.mysql.jdbc.Driver", 
					10, 
					5, 
					1000);
		}
		return datasource;
	}
	
	public Connection getConnection(){
		Connection connection=null;
		try {
			connection = getDataSourceInstance().getConnection();
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Exception while getting Connection from Datasource.", e);
		}
		return connection;
	}
	

}
