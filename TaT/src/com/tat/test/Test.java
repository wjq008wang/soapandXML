package com.tat.test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Test {
	/**
	 * 测试实体与xml之间的转换
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		XStream stream = new XStream(new DomDriver());
		// 初始化实体类
		Person person = new Person("AAA", "CCC");
		person.setPhone(new PhoneNumber(123, "1234-456"));
		person.setFax(new PhoneNumber(123, "9999-999"));
		/*
		 * 这一步不是必须的，
		 * 如果不进行注册的话，XStream默认会在转换时，将对应类全限定名也加入XML文件中，如com.test.Person，
		 * 这如果在数据量大的时候，会使生成的XML文件增大不少。
		 */
		stream.alias("person", Person.class);
		// 生成xml
		String xml = stream.toXML(person);
		System.out.println(xml);
		// 解析xml
		Person p = (Person) stream.fromXML(xml);
		System.out.println("code of phone is: " + p.getPhone().getCode());

	}

}
