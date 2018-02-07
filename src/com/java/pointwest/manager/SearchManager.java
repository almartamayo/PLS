package com.java.pointwest.manager;

import java.util.List;

import org.apache.log4j.Logger;

import com.java.pointwest.bean.Employee;
import com.java.pointwest.dao.SearchDao;
import com.java.pointwest.exception.PLSException;

public class SearchManager {
	SearchDao searchDao = new SearchDao();
	Logger log = Logger.getLogger(SearchManager.class);

	public List<Employee> getSearchResult(List<Employee> employees, String searchBy, String userInput)
			throws PLSException {
		log.info("getSearchResult Started");
		try {
			switch (searchBy) {
			case "Employee ID":
				employees = searchDao.retrieveEmployeeByEmpID(userInput);
				break;
			case "Name":
				employees = searchDao.retrieveEmployeeByName(userInput);
				break;
			case "Project":
				employees = searchDao.retrieveEmployeeByProject(userInput);
				break;
			}

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new PLSException("An error occured.", e);
		}
		log.info("getSearchResult Ended");
		return employees;

	}

}
