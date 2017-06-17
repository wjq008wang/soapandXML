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
	 * @version: 1.0 时间 : 2010-12-15
	 * 
	 *           描述 : 将XML字符串转换成对象
	 * 
	 *           实现方法：将XML字符串转换成对象
	 * 
	 *           Copyright 1988-2005, Huawei Tech. Co., Ltd.
	 * 
	 * @param <T>
	 * @param xml
	 *            XML字符串
	 * @param elementName
	 *            对象XML根元素的名称
	 * @param cls
	 *            返回类型
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T xmlToBean(String xml, String elementName, Class<T> cls) {
		T object = null;
		try {
			Document document = DocumentHelper.parseText(xml);
			// 如果不是SOAP返回的报文，是XML字符串则不需要这行代码
			String beanXml = document.getRootElement().element("Body").element(elementName).asXML();

			OMElement omElement = new StAXOMBuilder(new ByteArrayInputStream(beanXml.getBytes("UTF-8"))).getDocumentElement();

			object = (T) BeanUtil.processObject(omElement, cls, null, true, new DefaultObjectSupplier(), cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}
}