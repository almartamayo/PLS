package com.java.pointwest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.log4j.Logger;

import com.java.pointwest.bean.Employee;
import com.java.pointwest.bean.User;
import com.java.pointwest.constant.SqlConstants;
import com.java.pointwest.exception.PLSException;


public class LoginDao extends BaseDao {
	Logger log = Logger.getLogger(LoginDao.class);

	public Employee retrieveUserDetails(User userObj) throws PLSException{
		log.info("retrieveUserDetails Started");
		Employee user = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			ps = conn.prepareStatement(SqlConstants.LOGIN_QUERY);
			ps.setString(1, userObj.getUserName());
			ps.setString(2, userObj.getPassword());
			rs = ps.executeQuery();

			while (rs.next()) {
				user = new Employee();
				user.setFirstName(rs.getString(SqlConstants.E_FIRST_NAME_COL));
				user.setLastName(rs.getString(SqlConstants.E_LAST_NAME_COL));
				user.setEmployeeRole(rs.getString(SqlConstants.E_EMP_ROLE_COL));
			}
			
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new PLSException("An error occured.", e);
		} finally {
			// close resources
			closeResources(conn, ps, rs);
		}
		log.info("retrieveUserDetails Ended");

		return user;
	}
}
