package com.java.pointwest.presentation;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.java.pointwest.bean.Employee;
import com.java.pointwest.constant.ErrorMsgConstants;
import com.java.pointwest.constant.HomePageConstants;

public class HomePageUI {
	Scanner scanner = new Scanner(System.in);
	Logger log = Logger.getLogger(HomePageUI.class);

	public String displayHomePage(Employee empObj) {
		log.info("displayHomePage Started");
		String userInput = null;

		System.out.println(HomePageConstants.HOMEPAGE_BANNER);
		System.out.println(HomePageConstants.HOMEPAGE_BOARDER);
		System.out.println("Welcome: " + empObj.getFirstName() + " " + empObj.getLastName() + " " + "["
				+ empObj.getEmployeeRole() + "]");
		System.out.println(HomePageConstants.HOMEPAGE_BOARDER);
		do {
			System.out.println(HomePageConstants.HOMEPAGE_MENU);
			System.out.println(HomePageConstants.HOMEPAGE_OPTION_ONE);
			System.out.println(HomePageConstants.HOMEPAGE_OPTION_TWO);
			System.out.println(HomePageConstants.HOMEPAGE_OPTION_THREE);
			System.out.println(HomePageConstants.HOMEPAGE_GET_INPUT);
			userInput = scanner.nextLine();

			if (!userInput.matches("[1-3]")) {
				System.out.println(ErrorMsgConstants.DISPLAY_INVALID_INPUT);
				System.out.println(ErrorMsgConstants.INPUT_NUMERIC_CHAR);
			}
		} while (!userInput.matches("[1-3]"));
		
		log.info("displayHomePage Ended");
		return userInput;

	}

}
