package com.xml.explain.jaxb;

import java.io.ByteArrayInputStream;

import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.impl.builder.StAXOMBuilder;
import org.apache.axis2.databinding.utils.BeanUtil;
import org.apache.axis2.engine.DefaultObjectSupplier;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

public class XMLUtil {
	/**
	 * 
	 * @author : zKF27092
	 * @version: 1.0 ʱ�� : 2010-12-15
	 * 
	 *           ���� : ��XML�ַ���ת���ɶ���
	 * 
	 *           ʵ�ַ�������XML�ַ���ת���ɶ���
	 * 
	 *           Copyright 1988-2005, Huawei Tech. Co., Ltd.
	 * 
	 * @param <T>
	 * @param xml
	 *            XML�ַ���
	 * @param elementName
	 *            ����XML��Ԫ�ص�����
	 * @param cls
	 *            ��������
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xmlToBean(String xml, String elementName, Class<T> cls) {
		T object = null;
		try {
			Document document = DocumentHelper.parseText(xml);
			// �������SOAP���صı��ģ���XML�ַ�������Ҫ���д���
			String beanXml = document.getRootElement().element("Body").element(elementName).asXML();

			OMElement omElement = new StAXOMBuilder(new ByteArrayInputStream(beanXml.getBytes("UTF-8"))).getDocumentElement();

			object = (T) BeanUtil.processObject(omElement, cls, null, true, new DefaultObjectSupplier(), cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
}