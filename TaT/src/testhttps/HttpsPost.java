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
	 * ���KeyStore.
	 * 
	 * @param keyStorePath
	 *            ��Կ��·��
	 * @param password
	 *            ����
	 * @return ��Կ��
	 * @throws Exception
	 */
	public static KeyStore getKeyStore(String password, String keyStorePath)
			throws Exception {
		// ʵ������Կ��
		KeyStore ks = KeyStore.getInstance("JKS");
		// �����Կ���ļ���
		FileInputStream is = new FileInputStream(keyStorePath);
		// ������Կ��
		ks.load(is, password.toCharArray());
		// �ر���Կ���ļ���
		is.close();
		return ks;
	}

	/**
	 * ���SSLSocketFactory.
	 * 
	 * @param password
	 *            ����
	 * @param keyStorePath
	 *            ��Կ��·��
	 * @param trustStorePath
	 *            ���ο�·��
	 * @return SSLSocketFactory
	 * @throws Exception
	 */
	public static SSLContext getSSLContext(String password,
			String keyStorePath, String trustStorePath) throws Exception {
		// ʵ������Կ��
		KeyManagerFactory keyManagerFactory = KeyManagerFactory
				.getInstance(KeyManagerFactory.getDefaultAlgorithm());
		// �����Կ��
		KeyStore keyStore = getKeyStore(password, keyStorePath);
		// ��ʼ����Կ����
		keyManagerFactory.init(keyStore, password.toCharArray());

		// ʵ�������ο�
		TrustManagerFactory trustManagerFactory = TrustManagerFactory
				.getInstance(TrustManagerFactory.getDefaultAlgorithm());
		// ������ο�
		KeyStore trustStore = getKeyStore(password, trustStorePath);
		// ��ʼ�����ο�
		trustManagerFactory.init(trustStore);
		// ʵ����SSL������
		SSLContext ctx = SSLContext.getInstance("TLS");
		// ��ʼ��SSL������
		ctx.init(keyManagerFactory.getKeyManagers(),
				trustManagerFactory.getTrustManagers(), null);
		// ���SSLSocketFactory
		return ctx;
	}

	/**
	 * ��ʼ��HttpsURLConnection.
	 * 
	 * @param password
	 *            ����
	 * @param keyStorePath
	 *            ��Կ��·��
	 * @param trustStorePath
	 *            ���ο�·��
	 * @throws Exception
	 */
	public static void initHttpsURLConnection(String password,
			String keyStorePath, String trustStorePath) throws Exception {
		// ����SSL������
		SSLContext sslContext = null;
		// ʵ������������֤�ӿ�
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
	 * ��������.
	 * 
	 * @param httpsUrl
	 *            ����ĵ�ַ
	 * @param xmlStr
	 *            ���������
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
			// //����Ϊgbk���Խ������������ʱ��ȡ������������������
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
			/** �����ͳɹ������õ���Ӧ **/
			System.out.println(result.getStatusLine().getStatusCode());
			System.out.println("2222222222222" + url);

			if (result.getStatusLine().getStatusCode() == 200) {
				String str = "";
				try {
					/** ��ȡ���������ع�����json�ַ������� **/
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
	 * ���Է���.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// ����
		String password = "123456";
		// ��Կ��
		String keyStorePath = "E:\\Java\\jdk1.7.0_80\\bin\\server.jks";
		// ���ο�
		// System.setProperty("javax.Net.ssl.trustStore","E:\\Java\\jdk1.7.0_80\\bin\\crtTrust.trustStore");
		String trustStorePath = "E:\\Java\\jdk1.7.0_80\\bin\\crtTrust.trustStore";
		// �������https����
		String httpsUrl = "https://entservices.totalegame.net/EntServices.asmx?op=IsAuthenticate";
		// �����ı�
		String header = "POST /EntServices.asmx HTTP/1.1 Host: entservices.totalegame.net Content-Type: text/xml; charset=utf-8 Content-Length: length      SOAPAction: \"https://entservices.totalegame.net/IsAuthenticate\"";
		String xmlStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"  xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\"><soap:Body><IsAuthenticate xmlns=\"https://entservices.totalegame.net\"><loginName>TMHOTS207343</loginName><pinCode>a93241</pinCode></IsAuthenticate></soap:Body></soap:Envelope>";
		// HttpsPost.initHttpsURLConnection(password, keyStorePath,
		// trustStorePath);
		TrustSSL.trustEveryone();
		// ��������
		//HttpsPost.post(httpsUrl, header + xmlStr);
		readContentFromPost(httpsUrl, header + xmlStr);
	}

	
	//httpsurlconnection post ����
	public static void readContentFromPost(String Url, String param)
			throws IOException {
		// Post�����url����get��ͬ���ǲ���Ҫ������
		URL postUrl = new URL(Url);
		// ������
		HttpURLConnection connection = (HttpURLConnection) postUrl
				.openConnection();

		// �����Ƿ���connection�������Ϊ�����post���󣬲���Ҫ����
		// http�����ڣ������Ҫ��Ϊtrue
		connection.setDoOutput(true);
		// Read from the connection. Default is true.
		connection.setDoInput(true);
		// Ĭ���� GET��ʽ
		connection.setRequestMethod("POST");

		// Post ������ʹ�û���
		connection.setUseCaches(false);

		connection.setInstanceFollowRedirects(true);

		// ���ñ������ӵ�Content-type������Ϊapplication/x-www-form-urlencoded��
		// ��˼��������urlencoded�������form�������������ǿ��Կ������Ƕ���������ʹ��URLEncoder.encode
		// ���б���
		connection.setRequestProperty("Content-Type",
				"application/x-www-form-urlencoded");
		// ���ӣ���postUrl.openConnection()���˵����ñ���Ҫ��connect֮ǰ��ɣ�
		// Ҫע�����connection.getOutputStream�������Ľ���connect��
		connection.connect();
		DataOutputStream out = new DataOutputStream(
				connection.getOutputStream());
		// The URL-encoded contend
		// ���ģ�����������ʵ��get��URL�� '? '��Ĳ����ַ���һ��
		String content = URLEncoder.encode(param, "UTF-8");
		// DataOutputStream.writeBytes���ַ����е�16λ��unicode�ַ���8λ���ַ���ʽд��������
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