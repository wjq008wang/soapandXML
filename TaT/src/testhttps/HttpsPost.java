package testhttps;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.KeyStore;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import org.apache.http.util.EntityUtils;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.StatusLine;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import java.net.URLDecoder;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URLDecoder;

public class HttpsPost {
	/**
	 * 获得KeyStore.
	 * 
	 * @param keyStorePath
	 *            密钥库路径
	 * @param password
	 *            密码
	 * @return 密钥库
	 * @throws Exception
	 */
	public static KeyStore getKeyStore(String password, String keyStorePath)
			throws Exception {
		// 实例化密钥库
		KeyStore ks = KeyStore.getInstance("JKS");
		// 获得密钥库文件流
		FileInputStream is = new FileInputStream(keyStorePath);
		// 加载密钥库
		ks.load(is, password.toCharArray());
		// 关闭密钥库文件流
		is.close();
		return ks;
	}

	/**
	 * 获得SSLSocketFactory.
	 * 
	 * @param password
	 *            密码
	 * @param keyStorePath
	 *            密钥库路径
	 * @param trustStorePath
	 *            信任库路径
	 * @return SSLSocketFactory
	 * @throws Exception
	 */
	public static SSLContext getSSLContext(String password,
			String keyStorePath, String trustStorePath) throws Exception {
		// 实例化密钥库
		KeyManagerFactory keyManagerFactory = KeyManagerFactory
				.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		// 获得密钥库
		KeyStore keyStore = getKeyStore(password, keyStorePath);
		// 初始化密钥工厂
		keyManagerFactory.init(keyStore, password.toCharArray());

		// 实例化信任库
		TrustManagerFactory trustManagerFactory = TrustManagerFactory
				.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		// 获得信任库
		KeyStore trustStore = getKeyStore(password, trustStorePath);
		// 初始化信任库
		trustManagerFactory.init(trustStore);
		// 实例化SSL上下文
		SSLContext ctx = SSLContext.getInstance("TLS");
		// 初始化SSL上下文
		ctx.init(keyManagerFactory.getKeyManagers(),
				trustManagerFactory.getTrustManagers(), null);
		// 获得SSLSocketFactory
		return ctx;
	}

	/**
	 * 初始化HttpsURLConnection.
	 * 
	 * @param password
	 *            密码
	 * @param keyStorePath
	 *            密钥库路径
	 * @param trustStorePath
	 *            信任库路径
	 * @throws Exception
	 */
	public static void initHttpsURLConnection(String password,
			String keyStorePath, String trustStorePath) throws Exception {
		// 声明SSL上下文
		SSLContext sslContext = null;
		// 实例化主机名验证接口
		HostnameVerifier hnv = new MyHostnameVerifier();
		try {
			sslContext = getSSLContext(password, keyStorePath, trustStorePath);
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		if (sslContext != null) {
			HttpsURLConnection.setDefaultSSLSocketFactory(sslContext
					.getSocketFactory());
		}
		HttpsURLConnection.setDefaultHostnameVerifier(hnv);
	}

	/**
	 * 发送请求.
	 * 
	 * @param httpsUrl
	 *            请求的地址
	 * @param xmlStr
	 *            请求的数据
	 */
	public static void post(String httpsUrl, String xmlStr) {
		HttpsURLConnection urlCon = null;
		try {
			urlCon = (HttpsURLConnection) (new URL(httpsUrl)).openConnection();

			urlCon.setDoOutput(true);
			urlCon.setUseCaches(false);
			urlCon.setDoInput(true);
			urlCon.setConnectTimeout(20000);
			urlCon.setReadTimeout(300000);
			urlCon.setRequestMethod("POST");
			// urlCon.setRequestProperty("Content-Length",
			// String.valueOf(xmlStr.getBytes().length));
			// urlCon.setUseCaches(false);
			// urlCon.setRequestProperty("Charset", "UTF-8");
			// //设置为gbk可以解决服务器接收时读取的数据中文乱码问题
			// urlCon.getOutputStream().write(xmlStr.getBytes("utf-8"));

			byte[] requestBytes = xmlStr.getBytes();
			HttpPost postMethod = new HttpPost(httpsUrl);

			postMethod
					.addHeader(
							"User-Agent",
							"Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63");

			InputStream inputStream = new ByteArrayInputStream(requestBytes, 0,
					requestBytes.length);
			RequestEntity requestEntity = new InputStreamRequestEntity(
					inputStream, requestBytes.length,
					"application/soap+xml; charset=utf-8");
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			nvps.add(new BasicNameValuePair("XML", xmlStr));
			postMethod.setEntity(new UrlEncodedFormEntity(nvps));
			HttpClient httpClient = new DefaultHttpClient();
			System.out.println("1111111111111" + xmlStr);
			HttpResponse result = httpClient.execute(postMethod);
			String url = URLDecoder.decode(httpsUrl, "UTF-8");
			/** 请求发送成功，并得到响应 **/
			System.out.println(result.getStatusLine().getStatusCode());
			System.out.println("2222222222222" + url);

			if (result.getStatusLine().getStatusCode() == 200) {
				String str = "";
				try {
					/** 读取服务器返回过来的json字符串数据 **/
					str = EntityUtils.toString(result.getEntity());
					System.out.println(str);
				} catch (Exception e) {

				}
			}
			//
			// InputStream soapResponseStream =
			// postMethod.getResponseBodyAsStream();
			// InputStreamReader inputStreamReader = new
			// InputStreamReader(soapResponseStream);
			// BufferedReader bufferedReader = new
			// BufferedReader(inputStreamReader);
			//
			// String responseLine = "";
			// String soapResponseInfo = "";
			// while((responseLine = bufferedReader.readLine()) != null) {
			// soapResponseInfo = soapResponseInfo + responseLine;
			// }
			urlCon.getOutputStream().flush();
			urlCon.getOutputStream().close();

			// BufferedReader in = new BufferedReader(new InputStreamReader(
			// urlCon.getInputStream()));
			// String line;
			// while ((line = in.readLine()) != null) {
			// System.out.println(line);
			// }
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 测试方法.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// 密码
		String password = "123456";
		// 密钥库
		String keyStorePath = "E:\\Java\\jdk1.7.0_80\\bin\\server.jks";
		// 信任库
		// System.setProperty("javax.Net.ssl.trustStore","E:\\Java\\jdk1.7.0_80\\bin\\crtTrust.trustStore");
		String trustStorePath = "E:\\Java\\jdk1.7.0_80\\bin\\crtTrust.trustStore";
		// 本地起的https服务
		String httpsUrl = "https://entservices.totalegame.net/EntServices.asmx?op=IsAuthenticate";
		// 传输文本
		String header = "POST /EntServices.asmx HTTP/1.1 Host: entservices.totalegame.net Content-Type: text/xml; charset=utf-8 Content-Length: length      SOAPAction: \"https://entservices.totalegame.net/IsAuthenticate\"";
		String xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"  xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><IsAuthenticate xmlns=\"https://entservices.totalegame.net\"><loginName>TMHOTS207343</loginName><pinCode>a93241</pinCode></IsAuthenticate></soap:Body></soap:Envelope>";
		// HttpsPost.initHttpsURLConnection(password, keyStorePath,
		// trustStorePath);
		TrustSSL.trustEveryone();
		// 发起请求
		//HttpsPost.post(httpsUrl, header + xmlStr);
		readContentFromPost(httpsUrl, header + xmlStr);
	}

	
	//httpsurlconnection post 参数
	public static void readContentFromPost(String Url, String param)
			throws IOException {
		// Post请求的url，与get不同的是不需要带参数
		URL postUrl = new URL(Url);
		// 打开连接
		HttpURLConnection connection = (HttpURLConnection) postUrl
				.openConnection();

		// 设置是否向connection输出，因为这个是post请求，参数要放在
		// http正文内，因此需要设为true
		connection.setDoOutput(true);
		// Read from the connection. Default is true.
		connection.setDoInput(true);
		// 默认是 GET方式
		connection.setRequestMethod("POST");

		// Post 请求不能使用缓存
		connection.setUseCaches(false);

		connection.setInstanceFollowRedirects(true);

		// 配置本次连接的Content-type，配置为application/x-www-form-urlencoded的
		// 意思是正文是urlencoded编码过的form参数，下面我们可以看到我们对正文内容使用URLEncoder.encode
		// 进行编码
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		// 连接，从postUrl.openConnection()至此的配置必须要在connect之前完成，
		// 要注意的是connection.getOutputStream会隐含的进行connect。
		connection.connect();
		DataOutputStream out = new DataOutputStream(
				connection.getOutputStream());
		// The URL-encoded contend
		// 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
		String content = URLEncoder.encode(param, "UTF-8");
		// DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
		out.writeBytes(content);

		out.flush();
		out.close();

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String line;

		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}

		reader.close();
		connection.disconnect();
	}

}