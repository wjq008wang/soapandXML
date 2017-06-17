package com.xml.explain.jaxb;

import java.io.Serializable;

import org.apache.axis.encoding.AnyContentType;
import org.apache.axis.message.MessageElement;

public class ReportResultCurrentPageData implements Serializable, AnyContentType {

	private MessageElement[] _any;

	public ReportResultCurrentPageData(MessageElement[] _any) {
		this._any = _any;
	}

	public MessageElement[] get_any() {
		return this._any;
	}

	public void set_any(MessageElement[] _any) {
		this._any = _any;
	}

}
