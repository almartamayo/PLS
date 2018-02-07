package com.java.pointwest.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.java.pointwest.bean.Employee;
import com.java.pointwest.bean.Location;
import com.java.pointwest.dao.SeatPlanDao;
import com.java.pointwest.exception.PLSException;

public class SeatPlanManager {
	SeatPlanDao seatPlanDao = new SeatPlanDao();
	Logger log = Logger.getLogger(SeatPlanManager.class);

	public List<Employee> getSeatPlanResults(List<Employee> employees, Employee emp) throws PLSException {
		log.info("getSeatPlanResults Started");
		try {
			if (emp.getBuilding().getSeat().getFloor() == null) {
				employees = seatPlanDao.retrieveESeatPlanByQuadrant(emp);
			} else {
				employees = seatPlanDao.retrieveESeatPlanByFloor(emp);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new PLSException("An error occured.", e);
		}
		log.info("getSeatPlanResults Ended");
		return employees;
	}

	public Employee putAsteriskToName(String userInput, List<Employee> employees) {
		Employee emp = new Employee();
		emp.setFirstName(employees.get(Integer.parseInt(userInput) - 1).getFirstName());
		emp.setLastName(employees.get(Integer.parseInt(userInput) - 1).getLastName());
		emp.setBuilding(employees.get(Integer.parseInt(userInput) - 1).getBuilding());
		emp.getBuilding()
				.setBuildingAddress(employees.get(Integer.parseInt(userInput) - 1).getBuilding().getBuildingAddress());

		emp.getBuilding().setSeat(employees.get(Integer.parseInt(userInput) - 1).getBuilding().getSeat());
		emp.getBuilding().getSeat()
				.setFloor(employees.get(Integer.parseInt(userInput) - 1).getBuilding().getSeat().getFloor());
		System.out.println(emp.getBuilding().getBuildingAddress() + "ADDRESSSSSSSSSS");
		System.out.println((employees.get(Integer.parseInt(userInput) - 1).getBuilding().getBuildingAddress()
				+ "ADDRESSSSSSSSSS"));
		return emp;
	}

}
