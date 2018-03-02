package com.liqun.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
/**
 * 利群传过来
 * @author wks
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="COMMON_NODE")
public class CommonNode {
	@XmlElement(name="NAME",required=true)
	private String name;
	@XmlElement(name="VALUE",required=true)
	private String value;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
