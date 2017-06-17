//package com.jyxp.soap.client;
//
//
//import org.apache.http.HttpEntity;  
//import org.apache.http.HttpResponse;  
//import org.apache.http.HttpStatus;  
//import org.apache.http.NameValuePair;  
//import org.apache.http.client.HttpClient;  
//import org.apache.http.client.config.RequestConfig;  
//import org.apache.http.client.entity.UrlEncodedFormEntity;  
//import org.apache.http.client.methods.CloseableHttpResponse;  
//import org.apache.http.client.methods.HttpGet;  
//import org.apache.http.client.methods.HttpPost;  
//import org.apache.http.conn.ssl.SSLConnectionSocketFactory;  
//import org.apache.http.conn.ssl.SSLContextBuilder;  
//import org.apache.http.conn.ssl.TrustStrategy;  
//import org.apache.http.conn.ssl.X509HostnameVerifier;  
//import org.apache.http.entity.StringEntity;  
//import org.apache.http.impl.client.CloseableHttpClient;  
//import org.apache.http.impl.client.DefaultHttpClient;  
//import org.apache.http.impl.client.HttpClients;  
//import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;  
//import org.apache.http.message.BasicNameValuePair;  
//import org.apache.http.util.EntityUtils;  
//  
//import javax.net.ssl.SSLContext;  
//import javax.net.ssl.SSLException;  
//import javax.net.ssl.SSLSession;  
//import javax.net.ssl.SSLSocket;  
//import java.io.IOException;  
//import java.io.InputStream;  
//import java.nio.charset.Charset;  
//import java.security.GeneralSecurityException;  
//import java.security.cert.CertificateException;  
//import java.security.cert.X509Certificate;  
//import java.util.ArrayList;  
//import java.util.HashMap;  
//import java.util.List;  
//import java.util.Map;  
//
//public class testhttpclient {
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//
//		String data = "<root><header><type>fetch</type></header><content><program>test</program></content></root>";  
//		String url = "http://localhost:8080/fetch";  
//		  
//		HttpClient httpclient = new HttpClient();  
//		        PostMethod post  = new PostMethod(url);  
//		        String info = null;  
//		        try {  
//		            RequestEntity entity = new StringRequestEntity(data, "text/xml",  
//		            "iso-8859-1");  
//		            post.setRequestEntity(entity);  
//		            httpclient.executeMethod(post);   
//		            int code = post.getStatusCode();  
//		            if (code == HttpStatus.SC_OK)  
//		                info = new String(post.getResponseBodyAsString());  
//		        } catch (Exception ex) {  
//		            ex.printStackTrace();  
//		        } finally {  
//		            post.releaseConnection();  
//		        }  
//
//	}
//
//}
