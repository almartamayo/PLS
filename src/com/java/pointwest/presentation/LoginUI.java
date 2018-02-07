package com.java.pointwest.presentation;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.java.pointwest.bean.Employee;
import com.java.pointwest.bean.User;
import com.java.pointwest.constant.ErrorMsgConstants;
import com.java.pointwest.constant.LogConstants;
import com.java.pointwest.exception.PLSException;
import com.java.pointwest.manager.LoginManager;

public class LoginUI {
	LoginManager loginManager = new LoginManager();
	Scanner scanner = new Scanner(System.in);
	Logger log = Logger.getLogger(LoginUI.class);

	public Employee loginUser() {
		log.info("loginUser Started");
		User userObj = new User();
		Employee emp = new Employee();
		try {
			System.out.println(LogConstants.HEADER_BOARDER_BANNER);
			System.out.println(LogConstants.LOGIN_HEADER_BANNER);
			System.out.println(LogConstants.HEADER_BOARDER_BANNER);
			System.out.println();
			do {
				do {
					System.out.println(LogConstants.LOGIN_ENTER_USERNAME);
					userObj.setUserName(scanner.nextLine());
					System.out.println(LogConstants.LOGIN_ENTER_PASSWORD);
					userObj.setPassword(scanner.nextLine());

					if (!userObj.getUserName().matches("([aA-zZ])+\\.([aA-zZ])+")) {
						System.out.println(ErrorMsgConstants.LOGIN_CHECK_CREDENTIAL_FORMAT);
						System.out.println(ErrorMsgConstants.LOGIN_CREDENTIAL_IS_CASE_SENSITIVE);
					}
				} while (!userObj.getUserName().matches("([aA-zZ])+\\.([aA-zZ])+")
						&& !userObj.getPassword().matches("[a-zA-Z0-9]*"));
				if (!loginManager.isUserValid(userObj)) {
					System.out.println(ErrorMsgConstants.LOGIN_INVALID_CREDENTIALS);
				}
			} while (!loginManager.isUserValid(userObj));

			emp = loginManager.getUserDetails(userObj);
			
			log.info("loginUser Ended");
			
		} catch (PLSException e) {
			log.error(e.getMessage());
			System.out.println(e.getMessage());
		}
		return emp;
	}
	

	public Boolean logoutUser(Boolean isTerminate) {
		log.info("logoutUser Started");
		String userInput;
		do {
		System.out.println(LogConstants.HEADER_BOARDER_BANNER);
		System.out.println(LogConstants.LOGOUT_CONFIRMATION);
		System.out.println(LogConstants.LOGOUT_OPTION_ONE);
		System.out.println(LogConstants.LOGOUT_OPTION_TWO);
		System.out.println(LogConstants.LOGOUT_GET_INPUT);
		
			userInput = scanner.nextLine();
			if (userInput.matches("[1]")) {
				isTerminate = false;
			}else if(userInput.matches("[2]")) {
				isTerminate = true;
			} else {
				System.out.println(ErrorMsgConstants.DISPLAY_INVALID_INPUT);
			}
		}while(!userInput.matches("[1-2]"));
		log.info("logoutUser Endded");
		return isTerminate;
	}
	
	public void displayLogoutMessage() {
		log.info("displayLogoutMessage Started");
		System.out.println(LogConstants.LOGOUT_MESSAGE);
		log.info("displayLogoutMessage Ended");
	}
}
