package jaxb;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlType(propOrder = { "id", "name", "age", "birthDay", "list", "teacher", "map" })
// 指定序列成的xml节点顺序
@XmlAccessorType(value = XmlAccessType.FIELD)
// 访问类型改为字段
@XmlRootElement
public class Student {
	@XmlElement
	private String id;
	@XmlElement
	private String name;
	@XmlElement
	private int age;
	@XmlJavaTypeAdapter(value = DateAdapter.class)
	@XmlElement
	private Date birthDay;
	@XmlElement
	private List<Teacher> list;
	@XmlElement
	private Teacher teacher;

	@XmlJavaTypeAdapter(value = MapAdapter.class)
	// @XmlElementWrapper(name="role")
	@XmlElement
	private Map map;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", age=" + age + ", birthDay=" + birthDay + ", list=" + list + ", teacher=" + teacher + ", map=" + map + "]";
	}

}