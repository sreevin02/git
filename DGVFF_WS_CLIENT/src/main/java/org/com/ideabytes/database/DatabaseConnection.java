/**
 * @author Praveen Kumar Reddy Rachala
 * 
 * <DatabaseConnection.java>, V.0.0.1
 *
 * Created on 04-March-2016 by Praveen R (Java Programmer)
 *
 * Copyright 2016 Ideabytes Ltd
 */
package org.com.ideabytes.database;

import java.sql.Connection;
import java.sql.DriverManager;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.com.ideabytes.exceptions.DGVFFNestableException;

public class DatabaseConnection {
	/**
	 * Whic is establish connection from database this method take Database
	 * Information from Dbproperties file.
	 * 
	 * @return connection
	 */
	public static Connection getConnection() {
		Connection connection = null;
		try {

			Configuration config = new PropertiesConfiguration("db.properties");
			Class.forName("com.mysql.jdbc.Driver");
			String ip = (String) config.getProperty("db.ip");
			String port = (String) config.getProperty("db.port");
			String dbName = (String) config.getProperty("db.databasename");
			String userName = (String) config.getProperty("db.username");
			String passWord = (String) config.getProperty("db.password");
			connection = DriverManager.getConnection("jdbc:mysql://" + ip + ":"
					+ port + "/" + dbName, userName, passWord);
			if (connection == null) {
				System.out.println("Connection cannot be established");
			}
			return connection;
		} catch (Exception e) {
			throw new DGVFFNestableException(
					DGVFFNestableException.CODE_AUTHENTICATION_ERROR,
					"Exception caught while connecting to Database");
		}
	}
}