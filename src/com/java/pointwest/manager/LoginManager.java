package com.java.pointwest.manager;

import org.apache.log4j.Logger;

import com.java.pointwest.bean.Employee;
import com.java.pointwest.bean.User;
import com.java.pointwest.dao.LoginDao;
import com.java.pointwest.exception.PLSException;

public class LoginManager {
	Logger log = Logger.getLogger(LoginManager.class);
	
	public boolean isUserValid(User userObj) throws PLSException {
		log.info("isUserValid Started");

		boolean isValid = false;
		LoginDao loginDao = new LoginDao();
		Employee user;

		try {
			user = loginDao.retrieveUserDetails(userObj);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new PLSException("An error occured.", e);
		}

		if (user != null) {
			isValid = true;
		}
		log.info("isUserValid Ended");
		return isValid;
	}

	public Employee getUserDetails(User userObj) throws PLSException {
		log.info("getUserDetails Started");
		LoginDao loginDao = new LoginDao();
		Employee user;
		try {
			user = loginDao.retrieveUserDetails(userObj);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new PLSException("An error occured", e);
		}
		log.info("getUserDetails Ended");
		return user;
	}

}
