package com.xml.explain.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "gender", "userId", "password", "name", "age" })
public class Employee {

	@XmlElement(name = "userId", namespace = NameSpace.ADMIN_URI)
	private String userId;

	@XmlElement(name = "password", namespace = NameSpace.ADMIN_URI)
	private String password;

	@XmlElement(name = "name", namespace = NameSpace.ADMIN_URI)
	private String name;

	@XmlElement(name = "age", namespace = NameSpace.ADMIN_URI)
	private int age;

	@XmlElement(name = "gender", namespace = NameSpace.ADMIN_URI)
	private String gender;

	public Employee() {
	}

	public Employee(String userId, String psw, String name, int age, Gender gender) {
		this.userId = userId;
		this.password = psw;
		this.name = name;
		this.age = age;
		this.gender = gender.getValue();
	}

	public String getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

}
