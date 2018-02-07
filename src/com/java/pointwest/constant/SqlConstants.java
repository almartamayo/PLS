package com.java.pointwest.constant;

public interface SqlConstants {

	public static final String LOGIN_QUERY = "SELECT first_name, last_name, role " + "FROM employee e "
			+ "WHERE e.username = ?  AND e.password = ? ";

	public static final String SEARCH_QUERY_BY_EMP_ID = "SELECT e.emp_id, first_name, last_name, "
			+ "s.bldg_id,floor_number,quadrant,row_number,l.bldg_address,"
			+ "column_number, local_number, shift, group_concat(p.proj_name) AS project " + "FROM employee e "
			+ "LEFT JOIN employee_seat es ON e.emp_id = es.emp_id " + "LEFT JOIN seat s ON es.seat_id = s.seat_id "
			+ "LEFT JOIN location l ON s.bldg_id = l.bldg_id "
			+ "LEFT JOIN employee_project ep ON ep.employee_id = e.emp_id "
			+ "LEFT JOIN project p ON ep.proj_alias = p.proj_alias " + "WHERE e.emp_id LIKE ? "
			+ "GROUP BY e.emp_id, s.bldg_id, s.floor_number, s.quadrant, s.row_number, s.column_number; ";

	public static final String SEARCH_QUERY_BY_NAME = "SELECT e.emp_id, first_name, last_name,"
			+ "s.bldg_id,floor_number,quadrant,row_number, l.bldg_address,"
			+ "column_number, local_number, shift, group_concat(p.proj_name) AS project " + "FROM employee e "
			+ "LEFT JOIN employee_seat es ON e.emp_id = es.emp_id " + "LEFT JOIN seat s ON es.seat_id = s.seat_id "
			+ "LEFT JOIN location l ON s.bldg_id = l.bldg_id "
			+ "LEFT JOIN employee_project ep ON ep.employee_id = e.emp_id "
			+ "LEFT JOIN project p ON ep.proj_alias = p.proj_alias WHERE CONCAT(first_name, last_name) LIKE ? "
			+ "GROUP BY e.emp_id, s.bldg_id, s.floor_number, s.quadrant, s.row_number, s.column_number; ";

	public static final String SEARCH_QUERY_BY_PROJECT = "SELECT e.emp_id, first_name, last_name, "
			+ "s.bldg_id,floor_number,quadrant,row_number, l.bldg_address,"
			+ "column_number, local_number, shift, group_concat(p.proj_name) AS project " + "FROM employee e "
			+ "LEFT JOIN employee_seat es ON e.emp_id = es.emp_id " + "LEFT JOIN seat s ON es.seat_id = s.seat_id "
			+ "LEFT JOIN location l ON s.bldg_id = l.bldg_id "
			+ "LEFT JOIN employee_project ep ON ep.employee_id = e.emp_id "
			+ "LEFT JOIN project p ON ep.proj_alias = p.proj_alias " + "WHERE p.proj_name LIKE ? "
			+ "GROUP BY e.emp_id, s.bldg_id, s.floor_number, s.quadrant, s.row_number, s.column_number;";

	public static final String SEATPLAN_QUERY_BY_BLDG_ID_FLOOR_ID = "SELECT e.first_name, e.last_name, local_number, s.bldg_id, floor_number ,quadrant ,row_number,column_number, l.bldg_address "
			+ "FROM seat s " + "LEFT JOIN location l ON s.bldg_id = l.bldg_id "
			+ "LEFT JOIN employee_seat es ON s.seat_id = es.seat_id " + "LEFT JOIN employee e ON es.emp_id = e.emp_id "
			+ "WHERE l.bldg_id = ? AND floor_number = ? " + "GROUP BY s.seat_id; ";

	public static final String SEATPLAN_QUERY_BY_QUADRANT = "SELECT e.first_name, e.last_name, local_number, s.bldg_id, floor_number ,quadrant ,row_number,column_number, l.bldg_address "
			+ "FROM seat s " + "LEFT JOIN location l ON s.bldg_id = l.bldg_id "
			+ "LEFT JOIN employee_seat es ON s.seat_id = es.seat_id " + "LEFT JOIN employee e ON es.emp_id = e.emp_id "
			+ "WHERE l.bldg_id = ? AND floor_number = ? AND quadrant = ? " + "GROUP BY s.seat_id; ";

	public static final String E_EMP_ID = "emp_id";
	public static final String E_FIRST_NAME_COL = "first_name";
	public static final String E_LAST_NAME_COL = "last_name";
	public static final String E_EMP_ROLE_COL = "role";
	public static final String E_SHIFT_COL = "shift";

	public static final String S_FLOOR_NUM_COL = "floor_number";
	public static final String S_QUADRANT_COL = "quadrant";
	public static final String S_ROW_NUM_COL = "row_number";
	public static final String S_COLUMN_NUM_COL = "column_number";
	public static final String S_LOCAL_NUM_COL = "local_number";

	public static final String L_BLDG_ID_COL = "bldg_id";
	public static final String L_BLDG_ADDRESS_COL = "bldg_address";

	public static final String P_PROJ_ALIAS = "project";

}
