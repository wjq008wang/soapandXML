package HttpsURLConnection;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPMessage;

import net.sf.json.JSONObject;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.onetwo.common.utils.CollectionUtils;
import org.xml.sax.SAXException;

public class testHttpsRequestUtil {

	/**
	 * @param args
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 * @throws DocumentException
	 */
	public static void main(String[] args) throws DocumentException {
		Long LastRowId = 0L;

		JSONObject obj2 = JSONObject.fromObject(LastRowId);
		JSONObject obj = new JSONObject();
		obj.element("LastRowId", 0L);
		// https://tegapi.totalegame.net/
		JSONObject object = HttpsRequestUtil.httpsRequestJson("https://tegapi04.totalegame.net/GetSpinBySpinData", "POST", obj.toString());
		// String resultXML =
		// HttpsRequestUtil.httpRequest("https://tegapi04.totalegame.net/GetSpinBySpinData",
		// "POST", obj, "GetSpinBySpinData");
		System.out.println("��ӻ�Ա�ӿڵ�XML����: " + object);
		// str="<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><soap:Body><GetCurrenciesForAddAccountResponse xmlns=\"https://entservices.totalegame.net\"><GetCurrenciesForAddAccountResult>   <Currency>           <IsDefaultProduct>true</IsDefaultProduct>		               <ProductType>Casino</ProductType>		               <CurrencyId>8</CurrencyId>		               <IsoName>Chinese Yuan</IsoName>		               <IsoCode>CNY</IsoCode>		            </Currency>		         </GetCurrenciesForAddAccountResult>		      </GetCurrenciesForAddAccountResponse>		   </soap:Body>	</soap:Envelope>";
		// //String
		// str="<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\"><soap:Body><soap:Fault><soap:Code><soap:Value>soap:Sender</soap:Value></soap:Code><soap:Reason><soap:Text xml:lang=\"en\">Server was unable to read request. ---> There is an error in XML document (5, 79). ---> Guid should contain 32 digits with 4 dashes (xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx).</soap:Text></soap:Reason><soap:Detail/></soap:Fault></soap:Body></soap:Envelope>";
		// String result=getXMLValue(str, "CurrencyId");
		// System.out.println("��ӻ�Ա�ӿڵ�XML����: " + result);

	}

	// HttpsRequestUtil httpsRequestUtil = new HttpsRequestUtil();
	//
	// String soapIsAuthenticateXMl =
	// "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\"> <soap12:Body> <IsAuthenticate xmlns=\"https://entservices.totalegame.net\"> <loginName>param0</loginName> <pinCode>param1</pinCode></IsAuthenticate></soap12:Body></soap12:Envelope>";
	// // ��������������Ҫ�����滻 ����param0,param1,�Ժ�ֱ�� ������hashMap�����滻
	// String soapAddAccountXMl =
	// "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\">  <soap:Header>     <AgentSession xmlns=\"https://entservices.totalegame.net\">    <SessionGUID>param0</SessionGUID>        <ErrorCode>param1</ErrorCode>        <IPAddress>param2</IPAddress>      <IsExtendSession>true</IsExtendSession>     </AgentSession>   </soap:Header>    <soap:Body><AddAccount xmlns=\"https://entservices.totalegame.net\"><accountNumber>param3</accountNumber><password>param4</password><firstName>null</firstName><lastName>null</lastName><currency>0</currency> <mobileNumber>0123456789</mobileNumber>  <isSendGame>true</isSendGame><email>test@test.com</email>  <BettingProfileId>0</BettingProfileId><rngBettingProfileId>0</rngBettingProfileId><moblieGameLanguageId>0</moblieGameLanguageId>  <isProgressive>true</isProgressive>  </AddAccount>   </soap:Body></soap:Envelope> ";
	// String soapAddAccountXMl2 =
	// "<?xml version=\"1.0\" encoding=\"utf-8\"?><soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ent=\"https://entservices.totalegame.net\"> <soap:Header><ent:AgentSession><ent:SessionGUID>param0</ent:SessionGUID><ent:ErrorCode>param1</ent:ErrorCode><ent:IPAddress>param2</ent:IPAddress><ent:IsExtendSession>0</ent:IsExtendSession></ent:AgentSession></soap:Header><soap:Body><ent:AddAccount><ent:accountNumber>param3</ent:accountNumber><ent:password>param4</ent:password><ent:firstName>null</ent:firstName><ent:lastName>null</ent:lastName><ent:currency>1</ent:currency><ent:mobileNumber>0123456789</ent:mobileNumber><ent:isSendGame>true</ent:isSendGame><ent:email>test@test.com</ent:email><ent:BettingProfileId>0</ent:BettingProfileId><ent:rngBettingProfileId>0</ent:rngBettingProfileId><ent:moblieGameLanguageId>0</ent:moblieGameLanguageId><ent:isProgressive>0</ent:isProgressive></ent:AddAccount></soap:Body></soap:Envelope>";
	//
	// // �����ı�
	// // String header =
	// //
	// "POST /EntServices.asmx HTTP/1.1 Host: entservices.totalegame.net Content-Type: text/xml; charset=utf-8 Content-Length: length      SOAPAction: \"https://entservices.totalegame.net/IsAuthenticate\"";
	// // String xmlStr =
	// //
	// "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:ent=\"https://entservices.totalegame.net\"><soap:Header><ent:AgentSession><ent:SessionGUID>de2bc5f0-4b00-4e2c-a094-76aebb4c3f29</ent:SessionGUID><ent:ErrorCode>0</ent:ErrorCode><ent:IPAddress>127.0.0.1</ent:IPAddress><ent:IsExtendSession>0</ent:IsExtendSession></ent:AgentSession> </soap:Header><soap:Body><ent:AddAccount><ent:accountNumber>aktest2222</ent:accountNumber><ent:password>aktest89989</ent:password><ent:firstName>null</ent:firstName><ent:lastName>null</ent:lastName><ent:currency>1</ent:currency> <ent:mobileNumber>0123456789</ent:mobileNumber><ent:isSendGame>true</ent:isSendGame><ent:email>test@test.com</ent:email><ent:BettingProfileId>0</ent:BettingProfileId><ent:rngBettingProfileId>0</ent:rngBettingProfileId><ent:moblieGameLanguageId>0</ent:moblieGameLanguageId><ent:isProgressive>0</ent:isProgressive></ent:AddAccount></soap:Body></soap:Envelope>";
	// // //TrustSSL.trustEveryone();
	// // String result=httpsRequestUtil.httpRequest(httpsUrl,"POST",xmlStr);
	// String SOAP_URL = "https://entservices.totalegame.net/EntServices.asmx";
	// // String requestXML="";
	// // String resultXML="";
	// // String result="";
	// String resultaddAccount = "";
	// //while (!resultaddAccount.equalsIgnoreCase("0")) {
	// Map<String, Object> map = new HashMap();
	// map.put("param0", "xiao208225");
	// map.put("param1", "c629d4");
	// String requestXML = soapXMLReplace(soapIsAuthenticateXMl, map);
	// String resultXML = HttpsRequestUtil.httpRequest(SOAP_URL, "POST",
	// requestXML,"IsAuthenticate");
	// System.out.println("�����¼��XML����: " + requestXML + " , ����ĵ�ַΪ: "
	// + SOAP_URL + ", ����Ľ��:" + resultXML);
	// String result = getXMLValue(resultXML, "ErrorCode");
	// // result=0,�����¼�ɹ���������SessionGUID
	// if (result.equalsIgnoreCase("0")) {
	//
	// map.clear();
	// map.put("param0", getXMLValue(resultXML, "SessionGUID"));
	// map.put("param1", result);
	// map.put("param2", getXMLValue(resultXML, "IPAddress"));
	// map.put("param3", "aktest001");
	// map.put("param4", "aktest9999");
	// requestXML = soapXMLReplace(soapAddAccountXMl2, map);
	// // ���ô����¼��sessionGUID��������Ա
	//
	// resultXML = HttpsRequestUtil.httpRequest(SOAP_URL+"?op=AddAccount",
	// "POST",
	// requestXML,"AddAccount");
	// System.out.println("��ӻ�Ա�ӿڵ�XML����: " + requestXML
	// + " , ����ĵ�ַΪ: " + SOAP_URL+"?op=AddAccount" + ", ����Ľ��:" + resultXML);
	// resultaddAccount = getXMLValue(resultXML, "ErrorCode");
	// System.out.println("��ӻ�Ա�ӿڵ�XML����: " + resultaddAccount);
	// // try {
	// // Thread.sleep(30000);
	// // } catch (InterruptedException e) {
	// // // TODO Auto-generated catch block
	// // e.printStackTrace();
	// // }
	// }

	private static String soapXMLReplace(String soapIsAuthenticateXMl, Map<String, Object> map) {
		String tempXml = soapIsAuthenticateXMl;
		if (!CollectionUtils.isEmpty(map)) {
			for (int i = 0; i < map.size(); i++) {
				tempXml = tempXml.replace("param" + i, "" + map.get("param" + i));
			}

		}
		return tempXml;
	}

	// ���µĳ�������SOAP ���ص�XML��������Ӧ��ֵ
	private static String getXMLValue(String XMLvalue, String node) throws DocumentException {
		Document document = DocumentHelper.parseText(XMLvalue);
		Element root = document.getRootElement();// ��ȡ���ڵ�
		// getNodes(root);// �Ӹ��ڵ㿪ʼ�������нڵ�
		List<Element> list = root.elements();
		String result = getNode(root, node, null);
		return result;

	}

	// ���µĳ�������XML������͵Ľ�㣬�������ֵ����Ҫ�Ľӵ���ͬʱ��ֱ�ӷ��ؽӵ��ֵ
	public static String getNode(Element node, String nodeString, Element nodeFound) {
		String result = "";
		if (nodeFound != null) {
			return result;
		} else {
			// ��ǰ�ڵ������ӽڵ������
			Iterator<Element> it = node.elementIterator();
			while ((it.hasNext()) && (nodeFound == null)) {

				Element e = it.next();
				if (e.getName().equals(nodeString)) {
					nodeFound = e;
					result = e.getTextTrim();
				} else {
					result = getNode(e, nodeString, nodeFound);
				}
			}
		}
		return result;
	}

	/**
	 * String ret = parseSoapMessage(result); try { SOAPMessage msg =
	 * formatSoapString(result); SOAPBody body = msg.getSOAPBody();
	 * Iterator<SOAPElement> iterator = body.getChildElements(); String
	 * str=getValue(iterator,"SessionGUID");
	 * 
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * @param soapString
	 * @return
	 * @see [�ࡢ��#��������#��Ա]
	 */
	private static SOAPMessage formatSoapString(String soapString) {
		MessageFactory msgFactory;
		try {
			msgFactory = MessageFactory.newInstance();
			SOAPMessage reqMsg = msgFactory.createMessage(new MimeHeaders(), new ByteArrayInputStream(soapString.getBytes("UTF-8")));
			reqMsg.saveChanges();
			return reqMsg;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String parseSoapMessage(String soapXML) {
		String resultBean = "";
		try {
			SOAPMessage msg = formatSoapString(soapXML);
			SOAPBody body = msg.getSOAPBody();
			Iterator<SOAPElement> iterator = body.getChildElements();
			resultBean = getValue(iterator, "SessionGUID");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultBean;
	}

	private static String getValue(Iterator<SOAPElement> iterator, String side) {
		String result = "";
		while (iterator.hasNext()) {
			SOAPElement element = (SOAPElement) iterator.next();
			System.out.println("Local Name:" + element.getLocalName());
			System.out.println("Node Name:" + element.getNodeName());
			System.out.println("Tag Name:" + element.getTagName());
			System.out.println("Value:" + element.getValue());

			if (null == element.getValue() && element.getChildElements().hasNext()) {

			}
			result = element.getValue();
		}
		return result;
	}

}
