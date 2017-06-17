package com.xml.explain.jaxb;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "employees")
public class Employees {

	@XmlElement(name = "employee", namespace = NameSpace.ADMIN_URI)
	private List<Employee> employees;

	public Employees() {
		employees = new ArrayList<Employee>();
	}

	public List<Employee> getEmployees() {
		return employees;
	}

	public void addEmployee(Employee employee) {
		employees.add(employee);
	}

}