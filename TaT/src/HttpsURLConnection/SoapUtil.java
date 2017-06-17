package HttpsURLConnection;

import java.io.ByteArrayInputStream;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.dom4j.io.SAXReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class SoapUtil {
    
	/**
	  * @param args
	  */
	 public static void main(String[] args) {
		 try{
			 resule="";
			ByteArrayInputStream inputStream = new ByteArrayInputStream(result.getBytes());
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = factory.newDocumentBuilder();
			org.w3c.dom.Document doc = db.parse(inputStream);
	     
	     //得到根节点
	     Element root = doc.getDocumentElement();
	     NodeList nl = root.getElementsByTagName("id");
	     Element e = (Element) nl.item(0);
	     String id=e.getTextContent();
	     System.out.println("id的值为："+id);
	        }catch(Exception  e){
	         e.printStackTrace();
	        }
	 }
	}