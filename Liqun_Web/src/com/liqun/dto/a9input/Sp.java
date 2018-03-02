package com.liqun.dto.a9input;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@SuppressWarnings("all")
@XmlRootElement(name="sp")
@XmlAccessorType(XmlAccessType.FIELD)

public class Sp implements Serializable{
	
	@XmlAttribute(name = "key")
	private String key;
	
	@XmlAttribute(name="value")
	private String value;
	
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	
}
