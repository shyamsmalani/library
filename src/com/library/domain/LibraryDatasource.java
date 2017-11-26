package com.library.domain;

import org.apache.tomcat.jdbc.pool.DataSource;

public class LibraryDatasource extends DataSource {
	
 private String url;
 private String username;
 private String password;
 private String driverClass;
 
 private Integer maxActive;
 private Integer initialSize;
 private Integer maxWait;
public LibraryDatasource(String url, String username, String password, String driverClass, Integer maxActive,
		Integer initialSize, Integer maxWait) {
	super();
	this.setUrl(url);
	this.setUsername(username);
	this.setPassword(password);
	this.setDriverClassName(driverClass);
	this.setMaxActive(maxActive);
	this.setInitialSize(initialSize);
	this.setMaxWait(maxWait);
}
 
 
 
}
