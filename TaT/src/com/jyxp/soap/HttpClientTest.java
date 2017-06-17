package com.jyxp.soap;

import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.HttpEntity;  
 import org.apache.http.HttpResponse;  
 import org.apache.http.HttpStatus;  
 import org.apache.http.NameValuePair;  
 import org.apache.http.client.HttpClient;  
 import org.apache.http.client.config.RequestConfig;  
 import org.apache.http.client.entity.UrlEncodedFormEntity;  
 import org.apache.http.client.methods.CloseableHttpResponse;  
 import org.apache.http.client.methods.HttpGet;  
 import org.apache.http.client.methods.HttpPost;  
 import org.apache.http.conn.ssl.SSLConnectionSocketFactory;  
 import org.apache.http.conn.ssl.SSLContextBuilder;  
 import org.apache.http.conn.ssl.TrustStrategy;  
 import org.apache.http.conn.ssl.X509HostnameVerifier;  
 import org.apache.http.entity.StringEntity;  
 import org.apache.http.impl.client.CloseableHttpClient;  
 import org.apache.http.impl.client.DefaultHttpClient;  
 import org.apache.http.impl.client.HttpClients;  
 import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;  
 import org.apache.http.message.BasicNameValuePair;  
 import org.apache.http.util.EntityUtils;  

import javax.net.ssl.SSLContext;  
import javax.net.ssl.SSLException;  
 import javax.net.ssl.SSLSession;  
 import javax.net.ssl.SSLSocket;  
 import java.io.IOException;  
 import java.io.InputStream;  
 import java.nio.charset.Charset;  
 import java.security.GeneralSecurityException;  
 import java.security.cert.CertificateException;  
 import java.security.cert.X509Certificate;  
 import java.util.ArrayList;  
 import java.util.HashMap;  
 import java.util.List;  
import java.util.Map; 

public class HttpClientTest {
	HttpClient client;
	
	// 使用POST方法发送XML数据  
    public String sendXMLDataByPost(String url, String xmlData) throws Exception {  
        if (client == null){  
            client = HttpClients.createDefault();  
        }  
        HttpPost post = new HttpPost(url);  
        List<BasicNameValuePair> parameters = new ArrayList<>();  
        parameters.add(new BasicNameValuePair("xml", xmlData));  
        post.setEntity(new UrlEncodedFormEntity(parameters,"UTF-8"));  
        HttpResponse response = client.execute(post);  
        System.out.println(response.toString());  
        HttpEntity entity = response.getEntity();  
        String result = EntityUtils.toString(entity, "UTF-8");  
        return result;  
    }  

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		System.setProperty("javax.Net.ssl.trustStore","E:\\Java\\jdk1.7.0_80\\bin\\crtTrust.trustStore");
//        System.setProperty("javax.net.ssl.trustStorePassword", "123456");
//		
		String data = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ent=\"https://entservices.totalegame.net\">   <soapenv:Header/>   <soapenv:Body> <ent:IsAuthenticate><ent:loginName>TMHOTS207343</ent:loginName> <ent:pinCode>a93241</ent:pinCode></ent:IsAuthenticate></soapenv:Body></soapenv:Envelope>";
		String url = "https://entservices.totalegame.net/EntServices.asmx?op=IsAuthenticate";  
		HttpClientTest hct=new HttpClientTest();
		String result =hct.sendXMLDataByPost(url,data);
		  System.out.println(result);  
 		
	}

}
