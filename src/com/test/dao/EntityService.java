package com.test.dao;

import java.util.*;

import org.json.simple.JSONObject;

import java.sql.*;
import com.test.dao.Entity;
import com.test.dao.EntityImpl;
import com.test.model.Department;
import com.test.model.Employee;

/**
 * Service for accessing data from Employee and Departments
 * Funtionality for getting, deleting, and updating from these tables
 * @author SethThomas
 *
 */
public class EntityService {
	public List<Employee> getEmployees() {
		Entity ent = new EntityImpl();
		List<Employee> employees = new ArrayList<Employee>();
		try {
			Connection conn = ent.getConnection();
			Statement stmt = conn.createStatement();
			//SELECT employee.*, department.NAME, department.DESCRIPTION from employee join department on employee.DEPARTMENT_ID = department.DEPARTMENT_ID
			//ResultSet rs = stmt.executeQuery("SELECT * FROM employee");
			ResultSet rs = stmt.executeQuery("SELECT employee.*, department.NAME as DEP_NAME, department.DESCRIPTION from employee join department on employee.DEPARTMENT_ID = department.DEPARTMENT_ID");
			while (rs.next()) {
				Employee employee = new Employee();
				employee.setEmployeeID(rs.getInt("EMPLOYEE_ID"));
				employee.setName(rs.getString("NAME"));
				employee.setDepName(rs.getString("DEP_NAME"));
				employee.setDepDescription(rs.getString("DESCRIPTION"));
				employees.add(employee);
			}
		} catch (Exception e) { e.printStackTrace(); }
		return employees;
	}
	
	/**
	 * Get employee name and department
	 * This is used to fetch info for the ajax employee update mode when you click the edit button
	 * @param employeeID the ID to be used in the query
	 * @return JSONObject employeeInfo - resembles {"employeeName": "Person's name", "employeeDept", 2}
	 */
	@SuppressWarnings("unchecked") // getting a warning for the way put works with JSONObject
	public JSONObject getEmployeeNameAndDept(int employeeID) {
		JSONObject employeeInfo = new JSONObject();
		try {
			Entity ent = new EntityImpl();
			Connection conn = ent.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT EMPLOYEE_ID, NAME, DEPARTMENT_ID FROM employee WHERE EMPLOYEE_ID = ?");
			stmt.setInt(1, employeeID);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				employeeInfo.put("employeeName", rs.getString("NAME"));
				employeeInfo.put("employeeDept", rs.getInt("DEPARTMENT_ID"));
			}
			
		} catch (Exception e) { e.printStackTrace(); }
		return employeeInfo;
	}
	
	/**
	 * Gets department name and description if given the department id
	 * This is used to fetch info for the ajax department update mode when you click the edit button
	 * @param deptID - the department id to be given
	 * @return JSONObject departmentInfo - resembles {"departmentName": "DEPT5", "departmentDescription": "DEPARTMENT 5"}
	 */
	@SuppressWarnings("unchecked") // getting a warning for the way put works with JSONObject
	public Object getDeptNameAndDescription(int deptID) {
		JSONObject departmentInfo = new JSONObject();
		try {
			Entity ent = new EntityImpl();
			Connection conn = ent.getConnection();
			PreparedStatement stmt = conn.prepareStatement("SELECT NAME, DESCRIPTION FROM department WHERE DEPARTMENT_ID = ?");
			stmt.setInt(1, deptID);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				departmentInfo.put("departmentName", rs.getString("NAME"));
				departmentInfo.put("departmentDescription", rs.getString("DESCRIPTION"));
			}
			
		} catch (Exception e) { e.printStackTrace(); }
		return departmentInfo;
	}
	
	/**
	 * Adds an employee to the database
	 * @param employee - the Employee Object used for the insert statement
	 * @return true or false whether or not the insert worked
	 */
	public boolean addEmployee(Employee employee) {
		Entity ent = new EntityImpl();
		Connection conn;
		PreparedStatement stmt = null;
		boolean writeworked = false;
		int count = 0;
		try {
			conn = ent.getConnection();
			stmt = conn.prepareStatement("INSERT INTO employee(NAME,DEPARTMENT_ID) VALUES (?, ?)");
			stmt.setString(1, employee.getName());
			stmt.setString(2, Integer.toString(employee.getDepartmentID()));
			count = stmt.executeUpdate();
		} catch (Exception e) { e.printStackTrace(); }
		
		writeworked = (count > 0);
		return writeworked;
	}
	
	/**
	 * Deletes an employee
	 * @param employeeID the id of the employee to be deleted
	 * @return true or false depending on whether or not it worked
	 */
	public boolean deleteEmployee(int employeeID) {
		Entity ent = new EntityImpl();
		Connection conn;
		PreparedStatement stmt = null;
		boolean deleteworked = false;
		int count = 0;
		try {
			conn = ent.getConnection();
			stmt = conn.prepareStatement("DELETE FROM employee WHERE EMPLOYEE_ID = ?");
			stmt.setInt(1, employeeID);
			count = stmt.executeUpdate();
		} catch (Exception e) { e.printStackTrace(); }
		
		deleteworked = (count > 0);
		return deleteworked;
	}
	
	/**
	 * Deletes a department
	 * @param departmentID the id of the department to be deleted
	 * @return true or false depending on whether or not it worked
	 */
	public boolean deleteDepartment(int departmentID) {
		Entity ent = new EntityImpl();
		Connection conn;
		PreparedStatement stmt = null;
		boolean deleteworked = false;
		int count = 0;
		try {
			conn = ent.getConnection();
			stmt = conn.prepareStatement("DELETE FROM employee WHERE DEPARTMENT_ID = ?");
			stmt.setInt(1, departmentID);
			stmt.executeUpdate();
			stmt = conn.prepareStatement("DELETE FROM department WHERE DEPARTMENT_ID = ?");
			stmt.setInt(1, departmentID);
			count = stmt.executeUpdate();
		} catch (Exception e) { e.printStackTrace(); }
		
		deleteworked = (count > 0);
		return deleteworked;
	}
	
	/**
	 * Returns a List of Department Objects
	 * @return a List of Department Objects
	 */
	public List<Department> getDepartments() {
		Entity ent = new EntityImpl();
		List<Department> departments = new ArrayList<Department>();
		
		try {
			Connection conn = ent.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM department");
			while (rs.next()) {
				Department department = new Department();
				department.setDepartmentID(rs.getInt("DEPARTMENT_ID"));
				department.setName(rs.getString("NAME"));
				department.setDescription(rs.getString("DESCRIPTION"));
				departments.add(department);
			}
		} catch (Exception e) { e.printStackTrace(); }
		return departments;
	}

	/**
	 * Adds a department to the database
	 * @param department - a Department Object to be used for the insert statement
	 * @return true or false depending on whether or not the insert worked
	 */
	public boolean addDepartment(Department department) {
		Entity ent = new EntityImpl();
		Connection conn;
		PreparedStatement stmt = null;
		boolean writeworked = false;
		int count = 0;
		try {
			conn = ent.getConnection();
			stmt = conn.prepareStatement("INSERT INTO department(NAME,DESCRIPTION) VALUES (?, ?)");
			stmt.setString(1, department.getName());
			stmt.setString(2, department.getDescription());
			count = stmt.executeUpdate();
		} catch (Exception e) { e.printStackTrace(); }
		
		writeworked = (count > 0);
		return writeworked;
	}

	/**
	 * Updates a specific employee
	 * @param empID		id to be used for the update
	 * @param empName	new name of the employee
	 * @param deptID	new department ID to be used
	 * @return			true or false, whether or not the update worked
	 */
	public boolean updateEmployee(Integer empID, String empName, Integer deptID) {
		Entity ent = new EntityImpl();
		Connection conn;
		PreparedStatement stmt = null;
		boolean updateworked = false;
		int count = 0;
		try {
			conn = ent.getConnection();
			stmt = conn.prepareStatement("UPDATE employee SET NAME = ?, DEPARTMENT_ID = ? WHERE EMPLOYEE_ID = ?");
			stmt.setString(1, empName);
			stmt.setInt(2, deptID);
			stmt.setInt(3, empID);
			count = stmt.executeUpdate();
		} catch (Exception e) { e.printStackTrace(); }
		
		updateworked = (count > 0);
		return updateworked;
	}
	
	/**
	 * Updates a specific department
	 * @param deptID		id to be used for the update
	 * @param deptName		new name to be used
	 * @param deptDescr		new description to be used
	 * @return				true or false, whether or not the update worked
	 */
	public boolean updateDepartment(Integer deptID, String deptName, String deptDescr) {
		Entity ent = new EntityImpl();
		Connection conn;
		PreparedStatement stmt = null;
		boolean updateworked = false;
		int count = 0;
		try {
			conn = ent.getConnection();
			stmt = conn.prepareStatement("UPDATE department SET NAME = ?, DESCRIPTION = ? WHERE DEPARTMENT_ID = ?");
			stmt.setString(1, deptName);
			stmt.setString(2, deptDescr);
			stmt.setInt(3, deptID);
			count = stmt.executeUpdate();
		} catch (Exception e) { e.printStackTrace(); }
		
		updateworked = (count > 0);
		return updateworked;
	}

	

	

}
