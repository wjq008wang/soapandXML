package com.jyxp.soap;

import javax.xml.namespace.QName;

import org.apache.axiom.om.OMAbstractFactory;  
import org.apache.axiom.om.OMElement;  
import org.apache.axiom.om.OMFactory;  
import org.apache.axiom.om.OMNamespace;  
import org.apache.axis2.AxisFault;  
import org.apache.axis2.Constants;
import org.apache.axis2.addressing.EndpointReference;  
import org.apache.axis2.client.Options;  
import org.apache.axis2.client.ServiceClient;  
import org.apache.axis2.rpc.client.RPCServiceClient; 

import testhttps.TrustSSL;

public class test {
	public static void main(String[] args) {
		// SIG UI组件的信任库文件位于 RM9000安装路径/pms/conf/control/ 下,命名为server.keystore
		try {

			 
//		        System.setProperty("javax.Net.ssl.trustStore","E:\\Java\\jdk1.7.0_80\\bin\\crtTrust.trustStore");
//		           System.setProperty("javax.net.ssl.trustStorePassword", "123456"); 
            
		           TrustSSL.trustEveryone();
			//System.setProperty("javax.net.ssl.trustStore", "E:/Java/jdk1.7.0_80/bin/server.keystore");

//			String endpoint = "https://entservices.totalegame.net/EntServices.asmx?op=IsAuthenticate";
//		
//		
//			String res = (String) call.invoke( new Object[] {"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ent=\"https://entservices.totalegame.net\"><soapenv:Header/><soapenv:Body><ent:IsAuthenticate><ent:loginName>TMHOTS207343</ent:loginName><ent:pinCode>a93241</ent:pinCode></ent:IsAuthenticate></soapenv:Body></soapenv:Envelope>"
//			} );
//			
			 // String url = "http://localhost:8080/axis2ServerDemo/services/StockQuoteService";  
	          String url = "https://entservices.totalegame.net/EntServices.asmx?wsdl";  
	      
	          Options options = new Options();  
	          // 指定调用WebService的URL  
	          EndpointReference targetEPR = new EndpointReference(url);  
	          options.setTo(targetEPR);  
	         // options.setAction("urn:getPrice");  
	         // options.setTransportInProtocol(Constants.TRANSPORT_HTTP);
	          ServiceClient sender = new ServiceClient();  
	          sender.setOptions(options);  
	            
	            
	          OMFactory fac = OMAbstractFactory.getOMFactory();  
	          String tns = "https://entservices.totalegame.net/";  
	          // 命名空间，有时命名空间不增加没事，不过最好加上，因为有时有事，你懂的  
	          OMNamespace omNs = fac.createOMNamespace(tns, "");  
	      
	          OMElement method = fac.createOMElement("IsAuthenticate", omNs);  
	          OMElement symbol = fac.createOMElement("loginName", omNs);  
	    
	          symbol.addChild(fac.createOMText(symbol, "TMHOTS207343"));  
	          method.addChild(symbol);  
	          method.build();  
	          OMElement symbol1 = fac.createOMElement("pinCode", omNs);  
	     
	          symbol1.addChild(fac.createOMText(symbol1, "a93241"));  
	          method.addChild(symbol1);  
	          method.build();  
	          
	          OMElement result = sender.sendReceive(method);  
	      
	          System.out.println(result);  
	      
	        } catch (AxisFault axisFault) {  
	          axisFault.printStackTrace();  
	        }  
	}
}