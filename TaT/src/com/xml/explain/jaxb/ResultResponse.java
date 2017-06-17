package com.xml.explain.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "GetReportResultResponse")
public class ResultResponse {
	@XmlElement
	private GetReportResultResponse retReportResultResponse;

	public GetReportResultResponse getRetReportResultResponse() {
		return retReportResultResponse;
	}

	public void setRetReportResultResponse(GetReportResultResponse retReportResultResponse) {
		this.retReportResultResponse = retReportResultResponse;
	}

}
