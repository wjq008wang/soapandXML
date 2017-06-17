package HttpsURLConnection;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import net.sf.json.JSONObject;

/**
 * ����ƽ̨ͨ�ýӿڹ����� �û�ʵ��https����
 * 
 * @author golden
 * 
 */
public class HttpsRequestUtil {
	/**
	 * ����https���󲢻�ȡ��� *
	 * 
	 * @param requestUrl
	 *            �����ַ
	 * @param requestMethod
	 *            ����ʽ��GET��POST��
	 * @param param
	 *            �ύ������
	 * @return ͨ��https����õ���String result
	 */
	public static String httpRequest(String requestUrl, String requestMethod, JSONObject obj, String method) {

		StringBuffer buffer = new StringBuffer();
		try {

			// ����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());

			// ������SSLContext�����еõ�SSLSocketFactory����
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			// ������
			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setRequestProperty("Host", "entservices.totalegame.net");
			httpUrlConn.setRequestProperty("Content-type", "text/xml; charset=utf-8");
			// byte[] data = (obj.toString()).getBytes();
			// httpUrlConn.setRequestProperty("Content-Length",
			// String.valueOf(data.length));
			// httpUrlConn.setRequestProperty("SOAPAction",
			// "https://entservices.totalegame.net/" + method);
			// httpUrlConn.setRequestProperty("User-agent",
			// "  Mozilla/5.0 (Windows NT 6.1; WOW64; rv:33.0) Gecko/20100101 Firefox/33.0");

			String username = "xiao208225";
			String password = "c629d4";
			String input = username + ":" + password;
			String encoding = new sun.misc.BASE64Encoder().encode(input.getBytes());
			httpUrlConn.setRequestProperty("Authorization", "Basic " + encoding);

			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);

			httpUrlConn.setRequestMethod(requestMethod);
			if ("POST".equalsIgnoreCase(requestMethod)) {
				httpUrlConn.connect();
			}
			// ����������Ҫ�ύʱ
			if (null != obj) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// ע������ʽ����ֹ��������
				outputStream.write((obj.toString()).getBytes());
				// outputStream.write(param.getBytes("UTF-8"));
				outputStream.close();
			}

			// �����ص�������ת�����ַ���
			int resultCode = httpUrlConn.getResponseCode();
			System.out.println("resultCode: " + resultCode);
			InputStream inputStream = null;
			if (resultCode != 200) {
				inputStream = httpUrlConn.getErrorStream();
			} else {
				inputStream = httpUrlConn.getInputStream();
			}
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			System.out.println("��ӻ�Ա�ӿڵ�XML����: " + buffer);
			bufferedReader.close();
			inputStreamReader.close();
			// �ͷ���Դ
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();

		} catch (ConnectException ce) {
			System.out.println("ce: " + ce.toString());
		} catch (Exception e) {
			System.out.println("e: " + e.toString());
		}

		return buffer.toString();
	}

	public static JSONObject httpsRequestJson(String requestUrl, String requestMethod, String outputStr) {
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		try {
			// ����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// ������SSLContext�����еõ�SSLSocketFactory����
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// ��������ʽ��GET/POST��
			httpUrlConn.setRequestMethod(requestMethod);
			httpUrlConn.setRequestProperty("Host", "entservices.totalegame.net");
			httpUrlConn.setRequestProperty("Content-type", "application/json");
			httpUrlConn.setRequestProperty("Content-Length", "length");
			httpUrlConn.setRequestProperty("Accept", "application/json");
			String username = "xiao208225";
			String password = "c629d4";
			String input = username + ":" + password;
			String encoding = new sun.misc.BASE64Encoder().encode(input.getBytes());
			httpUrlConn.setRequestProperty("Authorization", "Basic " + encoding);
			// httpUrlConn.setRequestProperty("SOAPAction",
			// "https://entservices.totalegame.net/" + method);

			if ("POST".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();
			System.out.println("outputStr*********" + outputStr);
			// ����������Ҫ�ύʱ
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// ע������ʽ����ֹ��������
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			int resultCode = httpUrlConn.getResponseCode();
			InputStream inputStream = null;
			if (resultCode != 200) {
				inputStream = httpUrlConn.getErrorStream();
			} else {
				inputStream = httpUrlConn.getInputStream();
			}

			// �����ص�������ת�����ַ���
			// InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			System.out.println("responseCode: " + httpUrlConn.getResponseCode());
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			System.out.println("buffer*********" + buffer);
			bufferedReader.close();
			inputStreamReader.close();
			// �ͷ���Դ
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			System.out.println("Weixin server connection timed out.");
		} catch (Exception e) {
			// System.out.println("https request error:{}", e);
		}
		return jsonObject;
	}
}