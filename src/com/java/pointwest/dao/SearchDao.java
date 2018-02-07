package com.java.pointwest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.java.pointwest.bean.Employee;
import com.java.pointwest.bean.Location;
import com.java.pointwest.bean.Seat;
import com.java.pointwest.constant.SqlConstants;
import com.java.pointwest.exception.PLSException;
import com.mysql.jdbc.StringUtils;

public class SearchDao extends BaseDao {
	Logger log = Logger.getLogger(SearchDao.class);

	public List<Employee> retrieveEmployeeByEmpID(String inEmployeeID) throws PLSException {
		log.info("retrieveEmployeeByEmpID Started");
		List<Employee> employeeList = new ArrayList<Employee>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		// get connection
		try {
			conn = getConnection();

			ps = conn.prepareStatement(SqlConstants.SEARCH_QUERY_BY_EMP_ID);
			ps.setString(1, "%" + inEmployeeID + "%");

			rs = ps.executeQuery();

			while (rs.next()) {
				putResultSetToList(employeeList, rs);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new PLSException("An error occured.", e);
		} finally {
			// close resources
			closeResources(conn, ps, rs);
		}
		log.info("retrieveEmployeeByEmpID Ended");
		return employeeList;
	}

	public List<Employee> retrieveEmployeeByName(String inName) throws PLSException {
		log.info("retrieveEmployeeByName Started");
		List<Employee> employeeList = new ArrayList<Employee>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		// get connection
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SqlConstants.SEARCH_QUERY_BY_NAME);
			ps.setString(1, "%" + inName + "%");
			rs = ps.executeQuery();
			while (rs.next()) {
				putResultSetToList(employeeList, rs);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new PLSException("An error occured.", e);
		} finally {
			// close resources
			closeResources(conn, ps, rs);
		}
		log.info("retrieveEmployeeByName Ended");
		return employeeList;
	}

	public List<Employee> retrieveEmployeeByProject(String inProject) throws PLSException {
		log.info("retrieveEmployeeByProject Started");
		List<Employee> employeeList = new ArrayList<Employee>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		// get connection
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SqlConstants.SEARCH_QUERY_BY_PROJECT);
			ps.setString(1, "%" + inProject + "%");

			rs = ps.executeQuery();

			while (rs.next()) {
				putResultSetToList(employeeList, rs);
			}
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new PLSException("An error occured.", e);
		} finally {
			// close resources
			closeResources(conn, ps, rs);
		}
		log.info("retrieveEmployeeByProject Ended");
		return employeeList;
	}

	private void putResultSetToList(List<Employee> employeeList, ResultSet rs) throws PLSException {
		log.info("putResultSetToList Started");
		try {

			Seat seat = new Seat();
			seat.setFloor(rs.getString(SqlConstants.S_FLOOR_NUM_COL));
			seat.setQuadrant(rs.getString(SqlConstants.S_QUADRANT_COL));
			seat.setRowNum(rs.getInt(SqlConstants.S_ROW_NUM_COL));
			seat.setColumnNum(rs.getInt(SqlConstants.S_COLUMN_NUM_COL));
			seat.setLocalNum(rs.getString(SqlConstants.S_LOCAL_NUM_COL));

			Location building = new Location();
			building.setBuildingId(rs.getString(SqlConstants.L_BLDG_ID_COL));
			building.setBuildingAddress(rs.getString(SqlConstants.L_BLDG_ADDRESS_COL));
			building.setSeat(seat);

			Employee employee = new Employee();
			employee.setEmployeeID(rs.getInt(SqlConstants.E_EMP_ID));
			employee.setFirstName(rs.getString(SqlConstants.E_FIRST_NAME_COL));
			employee.setLastName(rs.getString(SqlConstants.E_LAST_NAME_COL));
			employee.setShift(rs.getString(SqlConstants.E_SHIFT_COL));

			if (!StringUtils.isNullOrEmpty(rs.getString(SqlConstants.P_PROJ_ALIAS))) {
				if (rs.getString(SqlConstants.P_PROJ_ALIAS).contains(",")) {
					if (!rs.getString(SqlConstants.P_PROJ_ALIAS).split(",")[0].contains("Project Never Exist")) {
						employee.setProject(rs.getString(SqlConstants.P_PROJ_ALIAS).split(",")[0]);
					} else if (!rs.getString(SqlConstants.P_PROJ_ALIAS).split(",")[1].contains("Project Never Exist")) {
						employee.setProject(rs.getString(SqlConstants.P_PROJ_ALIAS).split(",")[1]);
					} else {
						employee.setProject(rs.getString(SqlConstants.P_PROJ_ALIAS));
					}
				} else if (!rs.getString(SqlConstants.P_PROJ_ALIAS).contains("Project Never Exist")) {
					employee.setProject(rs.getString(SqlConstants.P_PROJ_ALIAS));
				} else {
					employee.setProject("N/A");
				}
			} else {
				employee.setProject("N/A");
			}

			employee.setBuilding(building);
			employeeList.add(employee);

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new PLSException("An error occured.", e);
		}
		log.info("putResultSetToList Ended");
	}

}