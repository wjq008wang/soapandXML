package com.xml.explain.jaxb;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class ReportResult implements Serializable {

	@XmlElement
	private String sourceStatus;
	@XmlElement
	private String errorValue;
	@XmlElement
	private String reportName;
	@XmlElement
	private Paging paging;
	@XmlElement
	private ReportResultCurrentPageData[] currentPageData;
	@XmlElement
	private String status;
	@XmlElement
	private String id;
	@XmlElement
	private String description;

	public String getSourceStatus() {
		return sourceStatus;
	}

	public void setSourceStatus(String sourceStatus) {
		this.sourceStatus = sourceStatus;
	}

	public String getErrorValue() {
		return errorValue;
	}

	public void setErrorValue(String errorValue) {
		this.errorValue = errorValue;
	}

	public String getReportName() {
		return reportName;
	}

	public void setReportName(String reportName) {
		this.reportName = reportName;
	}

	public Paging getPaging() {
		return paging;
	}

	public void setPaging(Paging paging) {
		this.paging = paging;
	}

	public ReportResultCurrentPageData[] getCurrentPageData() {
		return currentPageData;
	}

	public void setCurrentPageData(ReportResultCurrentPageData[] currentPageData) {
		this.currentPageData = currentPageData;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
