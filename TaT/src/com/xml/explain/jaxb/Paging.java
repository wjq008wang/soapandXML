package com.xml.explain.jaxb;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Paging {
	@XmlElement
	private int rowsPerPage;
	@XmlElement
	private int pageNumber;
	@XmlElement
	private int totalPage;
	@XmlElement
	private int totalRow;
	@XmlElement
	private int rowsInCurrentPage;

	public int getRowsPerPage() {
		return rowsPerPage;
	}

	public void setRowsPerPage(int rowsPerPage) {
		this.rowsPerPage = rowsPerPage;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(int totalRow) {
		this.totalRow = totalRow;
	}

	public int getRowsInCurrentPage() {
		return rowsInCurrentPage;
	}

	public void setRowsInCurrentPage(int rowsInCurrentPage) {
		this.rowsInCurrentPage = rowsInCurrentPage;
	}

}
