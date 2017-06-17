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
 * 公众平台通用接口工具类 用户实现https请求
 * 
 * @author golden
 * 
 */
public class HttpsRequestUtil {
	/**
	 * 发起https请求并获取结果 *
	 * 
	 * @param requestUrl
	 *            请求地址
	 * @param requestMethod
	 *            请求方式（GET、POST）
	 * @param param
	 *            提交的数据
	 * @return 通过https请求得到的String result
	 */
	public static String httpRequest(String requestUrl, String requestMethod, JSONObject obj, String method) {

		StringBuffer buffer = new StringBuffer();
		try {

			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());

			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			// 打开连接
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
			// 当有数据需要提交时
			if (null != obj) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write((obj.toString()).getBytes());
				// outputStream.write(param.getBytes("UTF-8"));
				outputStream.close();
			}

			// 将返回的输入流转换成字符串
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
			System.out.println("添加会员接口的XML请求: " + buffer);
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
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
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = { new MyX509TrustManager() };
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
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
			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
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

			// 将返回的输入流转换成字符串
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
			// 释放资源
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