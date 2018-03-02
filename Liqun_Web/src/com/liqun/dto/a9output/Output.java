package com.liqun.dto.a9output;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="OUTPUT")
public class Output {
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	@XmlElement(name="ID",required=true)
	private String id;
	@XmlElement(name="DATA",required=true)
	private Data data;
}
