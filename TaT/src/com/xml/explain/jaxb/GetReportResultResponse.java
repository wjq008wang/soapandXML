package com.xml.explain.jaxb;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "GetReportResultResult")
public class GetReportResultResponse implements Serializable {
	@XmlElement
	private ReportResult getReportResultResult;

	public ReportResult getGetReportResultResult() {
		return getReportResultResult;
	}

	public void setGetReportResultResult(ReportResult getReportResultResult) {
		this.getReportResultResult = getReportResultResult;
	}

}
