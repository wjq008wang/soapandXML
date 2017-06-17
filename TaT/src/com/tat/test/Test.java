package com.tat.test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Test {
	/**
	 * ����ʵ����xml֮���ת��
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		XStream stream = new XStream(new DomDriver());
		// ��ʼ��ʵ����
		Person person = new Person("AAA", "CCC");
		person.setPhone(new PhoneNumber(123, "1234-456"));
		person.setFax(new PhoneNumber(123, "9999-999"));
		/*
		 * ��һ�����Ǳ���ģ�
		 * ���������ע��Ļ���XStreamĬ�ϻ���ת��ʱ������Ӧ��ȫ�޶���Ҳ����XML�ļ��У���com.test.Person��
		 * ����������������ʱ�򣬻�ʹ���ɵ�XML�ļ������١�
		 */
		stream.alias("person", Person.class);
		// ����xml
		String xml = stream.toXML(person);
		System.out.println(xml);
		// ����xml
		Person p = (Person) stream.fromXML(xml);
		System.out.println("code of phone is: " + p.getPhone().getCode());

	}

}
