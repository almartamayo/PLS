package com.java.pointwest.presentation;

import java.awt.datatransfer.StringSelection;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.java.pointwest.bean.Employee;
import com.java.pointwest.bean.Location;
import com.java.pointwest.bean.Seat;
import com.java.pointwest.constant.ErrorMsgConstants;
import com.java.pointwest.constant.SeachPageConstants;
import com.java.pointwest.constant.ViewSeatPlanConstants;
import com.java.pointwest.exception.PLSException;
import com.java.pointwest.manager.SeatPlanManager;
import com.mysql.jdbc.StringUtils;

public class ViewSeatPlanPageUI {
	Scanner scanner = new Scanner(System.in);
	SeatPlanManager seatPlanManager = new SeatPlanManager();
	Logger log = Logger.getLogger(ViewSeatPlanPageUI.class);
	Employee emp;
	Boolean isToPlaceAsterisk = false;

	public String displayViewSeatPlanPage() {
		log.info("displayViewSeatPlanPage Started");
		String userInput = null;
		System.out.println(ViewSeatPlanConstants.SEATPLAN_BANNER);
		System.out.println(ViewSeatPlanConstants.SEATPLAN_HEADER);
		do {
			System.out.println(ViewSeatPlanConstants.SEATPLAN_OPTION_ONE);
			System.out.println(ViewSeatPlanConstants.SEATPLAN_OPTION_TWO);
			System.out.println(ViewSeatPlanConstants.SEATPLAN_OPTION_THREE);
			System.out.println(ViewSeatPlanConstants.VIEW_SEATPLAN_GET_INPUT);
			userInput = scanner.nextLine();
			if (!userInput.matches("[1-3]")) {
				System.out.println(ErrorMsgConstants.DISPLAY_INVALID_INPUT);
			}
		} while (!userInput.matches("[1-3]"));
		log.info("displayViewSeatPlanPage Ended");
		return userInput;
	}

	public Employee displaySeatPlanSearchBarByFloor() {
		log.info("displaySeatPlanSearchBarByFloor Started");
		Employee emp = new Employee();
		Location loc = new Location();
		Seat seat = new Seat();
		String location;
		String floor;

		System.out.println(ViewSeatPlanConstants.VIEW_SEATPLAN_BY_FLOOR);
		do {
			System.out.print(ViewSeatPlanConstants.ENTER_LOCATION);
			location = scanner.nextLine();

			System.out.print(ViewSeatPlanConstants.ENTER_FLOOR_NUM);
			floor = scanner.nextLine();

			if (!location.matches("[a-zA-Z]+")) {
				System.out.println(ErrorMsgConstants.DISPLAY_INVALID_INPUT);
				System.out.println(ErrorMsgConstants.INPUT_STRING_CHAR);
			} else if (!floor.matches("[0-9]+")) {
				System.out.println(ErrorMsgConstants.DISPLAY_INVALID_INPUT);
				System.out.println(ErrorMsgConstants.INPUT_NUMERIC_CHAR);
			}
		} while (!location.matches("[a-zA-Z]+") && !floor.matches("[0-9]+"));

		seat.setFloor(floor);
		loc.setBuildingId(location);
		loc.setSeat(seat);
		emp.setBuilding(loc);
		log.info("displaySeatPlanSearchBarByFloor Ended");
		return emp;
	}

	public Employee displaySeatPlanSearchBarByQuadrant() {
		log.info("displaySeatPlanSearchBarByQuadrant Started");
		Employee emp = new Employee();
		Location loc = new Location();
		Seat seat = new Seat();
		String location;
		String floor;
		String quadrant;

		System.out.println(ViewSeatPlanConstants.VIEW_BY_QUADRANT);
		do {
			System.out.print(ViewSeatPlanConstants.ENTER_LOCATION);
			location = scanner.nextLine();

			System.out.print(ViewSeatPlanConstants.ENTER_FLOOR_NUM);
			floor = scanner.nextLine();

			System.out.println(ViewSeatPlanConstants.ENTER_QUADRANT);
			quadrant = scanner.nextLine();

			if (!location.matches("[a-zA-Z]+")) {
				System.out.println(ErrorMsgConstants.DISPLAY_INVALID_INPUT);
				System.out.println(ErrorMsgConstants.INPUT_STRING_CHAR);
			} else if (!floor.matches("[0-9]+")) {
				System.out.println(ErrorMsgConstants.DISPLAY_INVALID_INPUT);
				System.out.println(ErrorMsgConstants.INPUT_NUMERIC_CHAR);
			} else if (!quadrant.matches("[A-D]")) {
				System.out.println(ErrorMsgConstants.DISPLAY_INVALID_INPUT);
				System.out.println(ErrorMsgConstants.SELECT_QUADRANT_AVAIL);
			}
		} while (!location.matches("[a-zA-Z]+") || !floor.matches("[0-9]+") || !quadrant.matches("[A-D]"));

		seat.setFloor(floor);
		seat.setQuadrant(quadrant);
		loc.setBuildingId(location);
		loc.setSeat(seat);
		emp.setBuilding(loc);
		log.info("displaySeatPlanSearchBarByQuadrant Ended");
		return emp;
	}

	public List<Employee> getSeatPlanResults(List<Employee> employees, Employee emp) {
		log.info("getSeatPlanResults Started");
		try {
			employees = seatPlanManager.getSeatPlanResults(employees, emp);

		} catch (PLSException e) {
			System.out.println(e.getMessage());
		}
		log.info("getSeatPlanResults Ended");
		return employees;
	}

	public void displaySeatPlan(List<Employee> employees, String inputChoice) {
		log.info("displaySeatPlan Started");
		if ("2".equals(inputChoice)) {
			printSeatPlanQuadrant(employees);
		} else {
			printSeatPlanByFloor(employees);
		}
		log.info("displaySeatPlan Ended");
	}

	public String displayActions() {
		log.info("displayActions Started");
		String userInput;
		do {
			System.out.println(ViewSeatPlanConstants.VIEW_SEATPLAN_ACTION);
			userInput = scanner.nextLine();
			if (!userInput.matches("[1-2]")) {
				System.out.println(ErrorMsgConstants.DISPLAY_INVALID_INPUT);
			}
		} while (!userInput.matches("[1-2]"));
		log.info("displayActions Ended");
		return userInput;
	}

	public String displaySelectEmployeeQuestion() {
		log.info("displaySelectEmployeeQuestion Started");
		String userInput;
		do {
			System.out.println(ViewSeatPlanConstants.ENTER_RESULT_NUMBER);
			userInput = scanner.nextLine();
			if (!userInput.matches("[0-9]")) {
				System.out.println(ErrorMsgConstants.DISPLAY_INVALID_INPUT);
				System.out.println(ErrorMsgConstants.INPUT_NUMERIC_CHAR);
			}
		} while (!userInput.matches("[0-9]"));
		log.info("displaySelectEmployeeQuestion Ended");
		return userInput;
	}

	public Employee putAsteriskToSelectedName(String userInput, List<Employee> employees) {
		emp = seatPlanManager.putAsteriskToName(userInput, employees);
		isToPlaceAsterisk = true;
		return emp;
	}

	public void displaySeatPlanBanner(Employee emp) {
		System.out.println(ViewSeatPlanConstants.SEATPLAN_BANNER);
		System.out.println("LOCATION: " + emp.getBuilding().getBuildingAddress() + ", FLOOR: "
				+ emp.getBuilding().getSeat().getFloor());
	}

	private void printSeatPlanByFloor(List<Employee> employees) {
		log.info("printSeatPlanByFloor Started");
		int row = 0;
		int col = 0;

		if (isToPlaceAsterisk) {
			for (Employee employee : employees) {
				if (!StringUtils.isNullOrEmpty(employee.getFirstName())) {
					if (employee.getFirstName().equalsIgnoreCase(emp.getFirstName())
							&& employee.getLastName().equalsIgnoreCase(emp.getLastName())) {
						System.out.println(employee.getFirstName());
						employee.setFirstName("*" + employee.getFirstName());
						employee.setLastName(employee.getLastName() + "*");
					}
				}
			}
		}
		for (Employee index : employees) {
			if (index.getBuilding().getSeat().getRowNum() > row) {
				row = (index.getBuilding().getSeat().getRowNum());
			}
			if (index.getBuilding().getSeat().getColumnNum() > col) {
				col = index.getBuilding().getSeat().getColumnNum();
			}
		}
		for (Employee employeeObj : employees) {
			if (employeeObj.getFirstName() == null || employeeObj.getLastName() == null) {
				employeeObj.setFirstName("  ");
				employeeObj.setLastName("  ");
			}
			if (employeeObj.getBuilding().getSeat().getLocalNum() == null) {
				employeeObj.getBuilding().getSeat().setLocalNum(" ");
			}

		}

		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		for (int rowNum = 0; rowNum < row; rowNum++) {

			for (int colNum = 0; colNum < col; colNum++) {
				System.out.printf("| %-25s",
						employees.get((rowNum * col) + (colNum)).getBuilding().getBuildingId()
								+ employees.get((rowNum * col) + (colNum)).getBuilding().getSeat().getFloor() + "F"
								+ employees.get((rowNum * col) + (colNum)).getBuilding().getSeat().getQuadrant()
								+ employees.get((rowNum * col) + (colNum)).getBuilding().getSeat().getRowNum() + "-"
								+ employees.get((rowNum * col) + (colNum)).getBuilding().getSeat().getColumnNum());

			}
			System.out.print("|");
			for (int colNum = 9; colNum < col + 9; colNum++) {
				int colNumAdjusted = colNum;
				System.out.printf("| %-25s", employees.get((rowNum * col) + (colNumAdjusted)).getBuilding()
						.getBuildingId()
						+ employees.get((rowNum * col) + (colNumAdjusted)).getBuilding().getSeat().getFloor() + "F"
						+ employees.get((rowNum * col) + (colNumAdjusted)).getBuilding().getSeat().getQuadrant()
						+ employees.get((rowNum * col) + (colNumAdjusted)).getBuilding().getSeat().getRowNum() + "-"
						+ employees.get((rowNum * col) + (colNumAdjusted)).getBuilding().getSeat().getColumnNum());
			}
			System.out.print("|");
			System.out.println();

			for (int colNum = 0; colNum < col; colNum++) {
				System.out.printf("| %-25s", employees.get((rowNum * col) + colNum).getFirstName() + " "
						+ employees.get((rowNum * col) + colNum).getLastName());
			}
			System.out.print("|");
			for (int colNum = 9; colNum < col + 9; colNum++) {
				System.out.printf("| %-25s", employees.get((rowNum * col) + colNum).getFirstName() + " "
						+ employees.get((rowNum * col) + colNum).getLastName());
			}
			System.out.print("|");
			System.out.println();

			for (int colNum = 0; colNum < col; colNum++) {
				System.out.printf("| %-25s",
						employees.get((rowNum * col) + colNum).getBuilding().getSeat().getLocalNum());
			}
			System.out.print("|");
			for (int colNum = 9; colNum < col + 9; colNum++) {
				System.out.printf("| %-25s",
						employees.get((rowNum * col) + colNum).getBuilding().getSeat().getLocalNum());
			}
			System.out.print("|");
			System.out.println();
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------------------------------------------------------------");

		}
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		for (int rowNum = 6; rowNum < row + 6; rowNum++) {

			for (int colNum = 0; colNum < col; colNum++) {
				System.out.printf("| %-25s",
						employees.get((rowNum * col) + (colNum)).getBuilding().getBuildingId()
								+ employees.get((rowNum * col) + (colNum)).getBuilding().getSeat().getFloor() + "F"
								+ employees.get((rowNum * col) + (colNum)).getBuilding().getSeat().getQuadrant()
								+ employees.get((rowNum * col) + (colNum)).getBuilding().getSeat().getRowNum() + "-"
								+ employees.get((rowNum * col) + (colNum)).getBuilding().getSeat().getColumnNum());

			}
			System.out.print("|");
			for (int colNum = 9; colNum < col + 9; colNum++) {
				int colNumAdjusted = colNum;
				System.out.printf("| %-25s", employees.get((rowNum * col) + (colNumAdjusted)).getBuilding()
						.getBuildingId()
						+ employees.get((rowNum * col) + (colNumAdjusted)).getBuilding().getSeat().getFloor() + "F"
						+ employees.get((rowNum * col) + (colNumAdjusted)).getBuilding().getSeat().getQuadrant()
						+ employees.get((rowNum * col) + (colNumAdjusted)).getBuilding().getSeat().getRowNum() + "-"
						+ employees.get((rowNum * col) + (colNumAdjusted)).getBuilding().getSeat().getColumnNum());
			}
			System.out.print("|");
			System.out.println();

			for (int colNum = 0; colNum < col; colNum++) {
				System.out.printf("| %-25s", employees.get((rowNum * col) + colNum).getFirstName() + " "
						+ employees.get((rowNum * col) + colNum).getLastName());
			}
			System.out.print("|");
			for (int colNum = 9; colNum < col + 9; colNum++) {
				System.out.printf("| %-25s", employees.get((rowNum * col) + colNum).getFirstName() + " "
						+ employees.get((rowNum * col) + colNum).getLastName());
			}
			System.out.print("|");
			System.out.println();

			for (int colNum = 0; colNum < col; colNum++) {
				System.out.printf("| %-25s",
						employees.get((rowNum * col) + colNum).getBuilding().getSeat().getLocalNum());
			}
			System.out.print("|");
			for (int colNum = 9; colNum < col + 9; colNum++) {
				System.out.printf("| %-25s",
						employees.get((rowNum * col) + colNum).getBuilding().getSeat().getLocalNum());
			}
			System.out.print("|");
			System.out.println();
			System.out.println(
					"---------------------------------------------------------------------------------------------------------------------------------------------------------------------");
			log.info("printSeatPlanByFloor Ended");
		}
	}

	public void printSeatPlanQuadrant(List<Employee> employee) {
		log.info("printSeatPlanQuadrant Started");
		int row = 0;
		int col = 0;
		for (Employee index : employee) {
			if (index.getBuilding().getSeat().getRowNum() >= row) {
				row = (index.getBuilding().getSeat().getRowNum());
			}
			if (index.getBuilding().getSeat().getColumnNum() > col) {
				col = index.getBuilding().getSeat().getColumnNum();
			}
		}
		for (Employee employeeObj : employee) {
			if (employeeObj.getFirstName() == null || employeeObj.getLastName() == null) {
				employeeObj.setFirstName("  ");
				employeeObj.setLastName("  ");
			}
			if (employeeObj.getBuilding().getSeat().getLocalNum() == null) {
				employeeObj.getBuilding().getSeat().setLocalNum(" ");
			}
		}

		System.out.println("----------------------------------------------------------------------------------");
		for (int rowNum = 0; rowNum < row; rowNum++) {
			for (int colNum = 0; colNum < col; colNum++) {
				System.out.printf("| %-25s",
						employee.get((rowNum * col) + colNum).getBuilding().getBuildingId()
								+ employee.get((rowNum * col) + colNum).getBuilding().getSeat().getFloor() + "F"
								+ employee.get((rowNum * col) + colNum).getBuilding().getSeat().getQuadrant()
								+ employee.get((rowNum * col) + colNum).getBuilding().getSeat().getColumnNum() + "-"
								+ employee.get((rowNum * col) + colNum).getBuilding().getSeat().getRowNum());
			}
			System.out.print("|");
			System.out.println();

			for (int colNum = 0; colNum < col; colNum++) {
				System.out.printf("| %-25s", employee.get((rowNum * col) + colNum).getFirstName() + " "
						+ employee.get((rowNum * col) + colNum).getLastName());
			}
			System.out.print("|");
			System.out.println();
			for (int colNum = 0; colNum < col; colNum++) {
				System.out.printf("| %-25s",
						employee.get((rowNum * col) + colNum).getBuilding().getSeat().getLocalNum());
			}
			System.out.print("|");
			System.out.println();
			System.out.println("----------------------------------------------------------------------------------");
			log.info("printSeatPlanQuadrant Ended");
		}
	}
}
