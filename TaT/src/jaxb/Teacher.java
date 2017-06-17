package jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(value = XmlAccessType.FIELD)
@XmlType(propOrder = { "id", "name", "age" })
public class Teacher {
	@XmlElement
	private int id;
	@XmlElement
	private String name;
	@XmlElement
	private int age;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Teacher(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public Teacher() {
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}