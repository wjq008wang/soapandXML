package com.test.simplesoap;

import javax.xml.namespace.QName;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class TestClient {

	public static void main(String[] args) throws Exception {
		String endpoint = "https://entservices.totalegame.net/EntServices.asmx";// 注意区别在这里！https！

		System.setProperty("javax.net.ssl.keyStore", "E:\\Java\\jdk1.7.0_80\\bin\\client.jks");
		System.setProperty("javax.net.ssl.keyStorePassword", "123456");
		System.setProperty("javax.net.ssl.trustStore", "E:\\Java\\jdk1.7.0_80\\bin\\client.truststore");
		System.setProperty("javax.net.ssl.trustStorePassword", "123456");

		Service service = new Service();
		Call call = (Call) service.createCall();
		call.setTargetEndpointAddress(new java.net.URL(endpoint));
		call.setOperationName(new QName(endpoint, "IsAuthenticate"));
		//call.setOperationName("IsAuthenticate");
		String res = (String) call.invoke(new Object[] {"<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"  xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><IsAuthenticate xmlns=\"https://entservices.totalegame.net\"><loginName>TMHOTS207343</loginName><pinCode>a93241</pinCode></IsAuthenticate></soap:Body></soap:Envelope>"});

		System.out.println(res);
	}
}