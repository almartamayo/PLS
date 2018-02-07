package com.java.pointwest.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.java.pointwest.exception.PLSException;

public class BaseDao {
	Logger log = Logger.getLogger(BaseDao.class);

	// set the connection to the database
	protected Connection getConnection() throws PLSException {
		log.info("getConnection Started");
		String dbURL = "jdbc:mysql://localhost:3306/plsdb";
		String dbUsername = "root";
		String dbPassword = "root";
		Connection conn = null;

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
		} catch (ClassNotFoundException cfne) {
			log.error(cfne.getMessage());
			throw new PLSException("Cannot find driver.", cfne);
		} catch (SQLException sqle) {
			log.error(sqle.getMessage());
			throw new PLSException("Cannot connect to database.", sqle);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new PLSException("Cannot connect to database.", e);
		}
		log.info("getConnection Ended");
		return conn;
	}

	// close the resources after using it for the database
	protected void closeResources(Connection conn, PreparedStatement statement,
			ResultSet rs) throws PLSException {
		log.info("closeResources Started");
		try {
			if (conn != null) {
				conn.close();
			}
			if (statement != null) {
				statement.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new PLSException("Cannot close connection", e);
		}
		log.info("closeResources Ended");
	}
}
