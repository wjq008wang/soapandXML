package HttpsURLConnection;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class httpsRequestUtiltest {

	public static String getSoapHeader() {
		Map<String, String> map = CacheKit.get("PageCache", "signInfo");
		if (map == null) {
			map = WebKit.getSignInfo();
			CacheKit.put("PageCache", "signInfo", map);
		}
		int thirdType = Integer.valueOf(map.get("thirdType"));
		int secret1 = Integer.valueOf(map.get("secret1"));
		String secret2 = map.get("secret2");

		// 上面代码为从缓存中取到我们需求传递到认证头的数据 下面开始添加认证头
		StringBuffer soapHeader = new StringBuffer();
		soapHeader.append("<soap:Header>");
		soapHeader.append("<SecurityHeader xmlns=\"http://www.hzsun.com/\">");
		soapHeader.append("<ThirdType>" + thirdType + "</ThirdType>");
		soapHeader.append("<Secret1>" + secret1 + "</Secret1>");
		soapHeader.append("<Secret2>" + secret2 + "</Secret2>");
		soapHeader.append("</SecurityHeader>");
		soapHeader.append("</soap:Header>");
		return soapHeader.toString();
	}

	public static String getAccInfoXml(String sIDNo, int nIDType) {
		StringBuffer template = new StringBuffer();
		String header = getSoapHeader();
		template.append("<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">");
		template.append(header);
		template.append("<soap:Body>");
		template.append("<GetAccInfo xmlns=\"http://www.hzsun.com/\">");
		template.append("<sIDNo>" + sIDNo + "</sIDNo>");
		template.append("<nIDType>" + nIDType + "</nIDType>");
		template.append("</GetAccInfo>");
		template.append("</soap:Body>");
		template.append("</soap:Envelope>");
		return template.toString();
	}

	public static String getAccInfo() {
		String sIDNo = getPara("sIDNo");
		int nIDType = getParaToInt("nIDType", 4);
		String dataJson = "";
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		String urlStr = "http://xxxx.xxx.xxx.xx/ThirdWebservice.asmx";
		String paraXml = getAccInfoXml(sIDNo, nIDType);
		String soapAction = "http://www.hzsun.com/GetAccInfo";
		OutputStream out = null;
		StringBuffer sb1 = new StringBuffer();
		try {
			URL url = new URL(urlStr);

			HttpURLConnection con;
			con = (HttpURLConnection) url.openConnection();
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setRequestMethod("POST");
			con.setUseCaches(false);
			con.setRequestProperty("Content-type", "text/xml; charset=UTF-8");
			// con.setRequestProperty("WSS-Password Type", "PasswordText");

			con.setRequestProperty("SOAPAction", soapAction);
			// con.setRequestProperty("Encoding", "UTF-8");
			out = con.getOutputStream();
			con.getOutputStream().write(paraXml.getBytes());
			out.flush();
			out.close();
			int code = con.getResponseCode();
			String tempString = null;

			if (code == HttpURLConnection.HTTP_OK) {
				BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
				while ((tempString = reader.readLine()) != null) {
					sb1.append(tempString);
				}
				if (null != reader) {
					reader.close();
				}
			} else {
				BufferedReader reader = new BufferedReader(new InputStreamReader(con.getErrorStream(), "UTF-8"));
				// 一次读入一行，直到读入null为文件结束
				while ((tempString = reader.readLine()) != null) {
					sb1.append(tempString);
				}
				if (null != reader) {
					reader.close();
				}
			}
		} catch (Exception e) {

		}

		return sb1.toString();
	}

	public static void main(String[] args) {

	}

}
