package com.java.pointwest.main;

import java.util.List;

import org.apache.log4j.Logger;

import com.java.pointwest.bean.Employee;
import com.java.pointwest.constant.SeachPageConstants;
import com.java.pointwest.presentation.HomePageUI;
import com.java.pointwest.presentation.LoginUI;
import com.java.pointwest.presentation.SearchPageUI;
import com.java.pointwest.presentation.ViewSeatPlanPageUI;

public class PLSMain {

	public static void main(String[] args) {
		Logger log = Logger.getLogger(PLSMain.class);
		log.info("MAIN Started");
		LoginUI loginUI = new LoginUI();
		HomePageUI homePageUI = new HomePageUI();
		SearchPageUI searchPageUI = new SearchPageUI();
		ViewSeatPlanPageUI seatPlanPageUI = new ViewSeatPlanPageUI();

		List<Employee> employees = null;
		String userAction;
		String searchBy;
		String userChoice;
		String userInput;
		Boolean isTerminate = true;
		Employee employee;
		Employee user = new Employee();

		user = loginUI.loginUser();
		do {
			userAction = homePageUI.displayHomePage(user);
			switch (userAction) {
			case "1":
				do {
					userChoice = searchPageUI.displaySearchPage(SeachPageConstants.SEARCHPAGE_BANNER);
					switch (userChoice) {
					case "1":
						searchBy = SeachPageConstants.SEARCH_BY_EMPLOYEE;
						break;
					case "2":
						searchBy = SeachPageConstants.SEARCH_BY_NAME;
						break;
					default:
						searchBy = SeachPageConstants.SEARCH_BY_PROJECT;
						break;
					}
					employees = searchPageUI.getEmployeesByChoice(employees, searchBy);
					System.out.println(employees.size());
					searchPageUI.displaySearchResult(employees);
					userChoice = searchPageUI.displayActions();
				} while ("1".equals(userChoice));
				break;
			case "2":
				employee = new Employee();
				do {
					userChoice = seatPlanPageUI.displayViewSeatPlanPage();
					switch (userChoice) {
					case "1":
						employee = seatPlanPageUI.displaySeatPlanSearchBarByFloor();
						break;
					case "2":
						employee = seatPlanPageUI.displaySeatPlanSearchBarByQuadrant();
						break;
					case "3":
						do {
							userInput = searchPageUI.displaySearchPage(SeachPageConstants.SEARCHPAGE_SEATPLAN_BANNER);
							switch (userInput) {
							case "1":
								searchBy = SeachPageConstants.SEARCH_BY_EMPLOYEE;
								break;
							case "2":
								searchBy = SeachPageConstants.SEARCH_BY_NAME;
								break;
							default:
								searchBy = SeachPageConstants.SEARCH_BY_PROJECT;
								break;
							}
							employees = searchPageUI.getEmployeesByChoice(employees, searchBy);

							searchPageUI.displaySearchResult(employees);
						} while (employees.size() == 0);
						userInput = seatPlanPageUI.displaySelectEmployeeQuestion();
						employee = seatPlanPageUI.putAsteriskToSelectedName(userInput, employees);
						break;
					}
					employees = seatPlanPageUI.getSeatPlanResults(employees, employee);
					seatPlanPageUI.displaySeatPlanBanner(employee);
					seatPlanPageUI.displaySeatPlan(employees, userChoice);
					userChoice = seatPlanPageUI.displayActions();
				} while ("1".equals(userChoice));
				break;

			case "3":
				isTerminate = loginUI.logoutUser(isTerminate);
				if (!isTerminate) {
					loginUI.displayLogoutMessage();
				}
				break;
			}
		} while (isTerminate);
		log.info("Main ENDED");
	}
}
