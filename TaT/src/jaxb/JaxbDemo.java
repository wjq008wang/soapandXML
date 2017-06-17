package jaxb;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JaxbDemo {

	public static void main(String[] args) {
       Student stu = new Student();
       stu.setId("1");
       stu.setName("罗志茂");
       stu.setAge(25);
       stu.setBirthDay(new Date());
       //list集合，set集合一样
       ArrayList<Teacher> list = new ArrayList<Teacher>();
       list.add(new Teacher(2,"曾利梅",22));
       list.add(new Teacher(3,"黄伙林",22));
       list.add(new Teacher(4,"潘志华",22));
       stu.setList(list);
       //在javaBean中添加javaBean
       Teacher teacher = new Teacher(1,"宋瑶",22);
       stu.setTeacher(teacher);
       //添加map集合
       Map<Object, Object> map = new HashMap<>();
       map.put("1", "林乐贤");
       map.put("2", "德甲");
       stu.setMap(map);
       try {
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "GBK");
        //marshaller.marshal(stu, System.out);
        StringWriter writer = new StringWriter();
        marshaller.marshal(stu, writer);
        System.out.println(writer.toString());
        //反序列化
        Unmarshaller unmarshaller = context.createUnmarshaller();
        StringReader reader = new StringReader(writer.toString());
        Student stu2 = (Student) unmarshaller.unmarshal(reader);
        System.out.println("stu2"+stu2);
    } catch (JAXBException e) {
        e.getMessage();
        e.printStackTrace();
    }
   }
}