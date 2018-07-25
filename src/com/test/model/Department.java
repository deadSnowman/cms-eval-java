package com.test.model;

public class Department {

	private Integer departmentID;
	private String name;
	private String description;
	
	public Department() {
		super();
	}
	
	public Department(Integer departmentID) {
		super();
		this.departmentID = departmentID;
	}
	
	public Department(String name, String description) {
		super();
		this.name = name;
		this.description = description;
	}

	public Integer getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(Integer departmentID) {
		this.departmentID = departmentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	/* testing */
	public String toString() {
		return this.description;
	}
	
}
