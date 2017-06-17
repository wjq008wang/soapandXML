package com.xml.explain.jaxb;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Test {
	// XML ���з��� �ͽ�������һ����Ҫ��lib��axis-bin-1_4

	public static void main(String[] args) throws FileNotFoundException {

		testUnmarshal2();
		// testUnmarshal();
		// testMarshal();
	}

	public static String testExample03(String path) {
		String content = "";
		// 1�����ڴ��д�Ҫ��ȡ�ļ����ַ�������
		try {
			Reader reader = new FileReader(path);

			int ch = reader.read();
			StringBuffer buffer = new StringBuffer();
			while (ch != -1) { // ��ȡ�ɹ�
				buffer.append((char) ch);
				ch = reader.read();
			}
			content = buffer.toString();
			// 3���ر���
			reader.close();
		} catch (FileNotFoundException e) {
			System.out.println("Ҫ��ȡ���ļ������ڣ�" + e.getMessage());
		} catch (IOException e) {
			System.out.println("�ļ���ȡ����" + e.getMessage());
		}
		return content;
	}

	public static void testUnmarshal2() throws FileNotFoundException {
		String path = "C:\\Users\\Administrator\\Desktop\\employees.xml";
		String content = testExample03(path);

		GetReportResultResponse getReportResultResponse = XMLUtil.xmlToBean(content, "GetReportResultResponse", GetReportResultResponse.class);
		// XMLParser.unmarshal(new FileInputStream(new File()),
		// Employees.class);

		System.out.println(getReportResultResponse.toString());
	}

	public static void testUnmarshal() throws FileNotFoundException {
		Employees employees = (Employees) XMLParser.unmarshal(new FileInputStream(new File("C:\\Users\\Administrator\\Desktop\\employees.xml")), Employees.class);

		List<Employee> employeeList = employees.getEmployees();

		if (employeeList != null && employeeList.size() > 0) {
			for (Employee employee : employeeList) {
				StringBuilder builder = new StringBuilder();
				builder.append("[UserID: ").append(employee.getUserId()).append(", ").append("Password: ").append(employee.getPassword()).append(", ").append("Name: ").append(employee.getName()).append(", ").append("Age: ").append(employee.getAge()).append(", ").append("Gender").append(employee.getGender()).append("]");

				System.out.println(builder.toString());
			}
		}
	}

	public static void testMarshal() {
		Employees employees = new Employees();
		employees.addEmployee(new Employee("johnsmith@company.com", "abc123_", "John Smith", 24, Gender.MALE));
		employees.addEmployee(new Employee("christinechen@company.com", "123456", "Christine Chen", 27, Gender.FEMALE));

		String result = XMLParser.marshal(employees, Employees.class);
		System.out.println(result);
	}
}
