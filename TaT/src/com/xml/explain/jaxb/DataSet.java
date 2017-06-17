package com.xml.explain.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "CurrentPageData")
public class DataSet {
	@XmlElement
	private DataTable table;

	public DataTable getTable() {
		return table;
	}

	public void setTable(DataTable table) {
		this.table = table;
	}

}
