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

public class SeatPlanDao extends BaseDao {
	Logger log = Logger.getLogger(SeatPlanDao.class);

	public List<Employee> retrieveESeatPlanByFloor(Employee inEmployee) throws PLSException {
		log.info("retrieveESeatPlanByFloor Started");
		List<Employee> employeeList = new ArrayList<Employee>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		// get connection
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SqlConstants.SEATPLAN_QUERY_BY_BLDG_ID_FLOOR_ID);
			ps.setString(1, inEmployee.getBuilding().getBuildingId());
			ps.setString(2, inEmployee.getBuilding().getSeat().getFloor());

			rs = ps.executeQuery();

			while (rs.next()) {
				putResultSetToList(employeeList, rs);
			}
			// close resources
			closeResources(conn, ps, rs);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new PLSException("An error occured.", e);
		} finally {
			// close resources
			closeResources(conn, ps, rs);
		}
		log.info("retrieveESeatPlanByFloor Ended");

		return employeeList;
	}

	public List<Employee> retrieveESeatPlanByQuadrant(Employee inEmployee) throws PLSException {
		log.info("retrieveESeatPlanByFloor started");
		List<Employee> employeeList = new ArrayList<Employee>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		// get connection
		try {
			conn = getConnection();
			ps = conn.prepareStatement(SqlConstants.SEATPLAN_QUERY_BY_QUADRANT);
			ps.setString(1, inEmployee.getBuilding().getBuildingId());
			ps.setString(2, inEmployee.getBuilding().getSeat().getFloor());
			ps.setString(3, inEmployee.getBuilding().getSeat().getQuadrant());

			rs = ps.executeQuery();

			while (rs.next()) {
				putResultSetToList(employeeList, rs);

			}
			// close resources
			closeResources(conn, ps, rs);
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new PLSException("An error occured.", e);
		} finally {
			// close resources
			closeResources(conn, ps, rs);
		}
		log.info("retrieveESeatPlanByFloor Ended");
		return employeeList;
	}

	private void putResultSetToList(List<Employee> employeeList, ResultSet rs) throws PLSException {
		log.info("retrieveESeatPlanByFloor Started");
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
			employee.setFirstName(rs.getString(SqlConstants.E_FIRST_NAME_COL));
			employee.setLastName(rs.getString(SqlConstants.E_LAST_NAME_COL));
			employee.setBuilding(building);
			employeeList.add(employee);

		} catch (Exception e) {
			log.error(e.getMessage());
			throw new PLSException("An error occured.", e);
		}
		log.info("retrieveESeatPlanByFloor Ended");
	}
}
