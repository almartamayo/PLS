package com.java.pointwest.presentation;

import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.java.pointwest.constant.ErrorMsgConstants;
import com.java.pointwest.constant.SeachPageConstants;
import com.java.pointwest.exception.PLSException;
import com.java.pointwest.manager.SearchManager;
import com.java.pointwest.bean.Employee;

public class SearchPageUI {
	Scanner scanner = new Scanner(System.in);
	SearchManager searchManager = new SearchManager();
	Logger log = Logger.getLogger(SearchPageUI.class);

	public String displaySearchPage(String banner) {
		log.info("displaySearchPage Starded");
		String userInput = null;
		System.out.println(banner);
		System.out.println(SeachPageConstants.SEARCHPAGE_MENU);
		do {
			System.out.println(SeachPageConstants.SEARCHPAGE_OPTION_ONE);
			System.out.println(SeachPageConstants.SEARCHPAGE_OPTION_TWO);
			System.out.println(SeachPageConstants.SEARCHPAGE_OPTION_THREE);
			System.out.println(SeachPageConstants.SEARCHPAGE_GET_INPUT);
			userInput = scanner.nextLine();

			if (!userInput.matches("[1-3]")) {
				System.out.println(ErrorMsgConstants.DISPLAY_INVALID_INPUT);
				System.out.println(ErrorMsgConstants.INPUT_NUMERIC_CHAR);
			}
		} while (!userInput.matches("[1-3]"));
		log.info("displaySearchPage Ended");
		return userInput;
	}

	public List<Employee> getEmployeesByChoice(List<Employee> employees, String searchBy) {
		log.info("getEmployeesByChoice Started");
		String userInput;

		System.out.println("##SEARCH - By " + searchBy + "##");
		do {
			System.out.print("Enter " + searchBy + ": ");
			userInput = scanner.nextLine();

			if (!userInput.matches("[a-zA-Z0-9]+")) {
				System.out.println(ErrorMsgConstants.DISPLAY_INVALID_INPUT);
				if (SeachPageConstants.SEARCH_BY_EMPLOYEE.equalsIgnoreCase(searchBy)) {
					System.out.println(ErrorMsgConstants.INPUT_NUMERIC_CHAR);
				} else if (SeachPageConstants.SEARCH_BY_NAME.equalsIgnoreCase(searchBy)) {
					System.out.println((ErrorMsgConstants.INPUT_STRING_CHAR));
				} else {
					System.out.println(ErrorMsgConstants.SELECT_PROJECT_AVAIL);
				}
			}
		} while (!userInput.matches("[a-zA-Z0-9]+"));

		try {
			employees = searchManager.getSearchResult(employees, searchBy, userInput);
		} catch (PLSException e) {
			System.out.println(e.getMessage());
		}
		log.info("getEmployeesByChoice Ended");
		return employees;
	}

	public void displaySearchResult(List<Employee> employees) {
		log.info("displaySearchResult Started");
		System.out.println(SeachPageConstants.SEARCH_RESULT_BANNER + employees.size() + ")##");
		if (employees.size() == 0) {
			System.out.println("##NO RESULTS FOUND##");
		} else {
			print(employees);
		}
	}

	public String displayActions() {
		String userInput;
		do {
			System.out.println(SeachPageConstants.SEARCHPAGE_ACTIONS);
			userInput = scanner.nextLine();
			if (!userInput.matches("[1-2]")) {
				System.out.println(ErrorMsgConstants.DISPLAY_INVALID_INPUT);
				System.out.println(ErrorMsgConstants.INPUT_NUMERIC_CHAR);
			}
		} while (!userInput.matches("[1-2]"));
		log.info("displaySearchResult Ended");
		return userInput;
	}

	private void print(List<Employee> employees) {
		log.info("printSearchResults Started");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------");
		System.out.printf("%-15s %-20s %-20s %-15s %-15s %-15s %-20s|\n", "| EMPLOYEE ID", "| FIRST NAME",
				"| LAST NAME", "| SEAT", "| LOCAL", "| SHIFT", "| PROJECT(S)");
		System.out.println(
				"-------------------------------------------------------------------------------------------------------------------------------");
		for (Employee employee : employees) {
			System.out.printf("%-15s %-20s %-20s %-15s %-15s %-15s %-20s|\n", "| " + employee.getEmployeeID(),
					"| " + employee.getFirstName(), "| " + employee.getLastName(),
					"| " + employee.getBuilding().getBuildingId() + employee.getBuilding().getSeat().getFloor() + "F"
							+ employee.getBuilding().getSeat().getQuadrant()
							+ employee.getBuilding().getSeat().getRowNum() + "-"
							+ employee.getBuilding().getSeat().getColumnNum(),
					"| " + employee.getBuilding().getSeat().getLocalNum(), "| " + employee.getShift(),
					"| " + employee.getProject());

		}
		System.out.println(
				"----------------------------End of Result---------------------------------------------------------------------------------------");

		log.info("printSearchResults Eded");
	}

}
