package com.test.model;

/**
 * Employee model
 * This acts as a model for the employee table, but also an inner join between employee and departments
 * @author SethThomas
 *
 */
public class Employee {
	private Integer employeeID;
	private String name;
	private Integer departmentID;
	private String depName;
	private String depDescription;
	
	public Employee() {
		super();
	}
	
	public Employee(Integer employeeID) {
		super();
		this.employeeID = employeeID;
	}
	
	public Employee(String name, Integer departmentID) {
		super();
		this.name = name;
		this.departmentID = departmentID;
	}
	
	public Integer getEmployeeID() {
		return employeeID;
	}
	
	public void setEmployeeID(Integer employeeID) {
		this.employeeID = employeeID;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getDepartmentID() {
		return departmentID;
	}
	
	public void setDepartmentID(Integer departmentID) {
		this.departmentID = departmentID;
	}
	
	public String getDepName() {
		return depName;
	}

	public void setDepName(String depName) {
		this.depName = depName;
	}

	public String getDepDescription() {
		return depDescription;
	}

	public void setDepDescription(String depDescription) {
		this.depDescription = depDescription;
	}

	/* tesing */
	public String toString() {
		return this.name;
	}
	
}
