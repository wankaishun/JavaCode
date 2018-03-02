package com.liqun.dto.lqinput.searchbill;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="INPUT")
public class Input {
	@XmlElement(name="CSDM",required=true)
	private String csdm;//超市代码
	@XmlElement(name="TQM",required=true)
	private String tqm;//提取码
	@XmlElement(name="LSH",required=true)
	private String lsh;//流水号
	public String getCsdm() {
		return csdm;
	}
	public void setCsdm(String csdm) {
		this.csdm = csdm;
	}
	public String getTqm() {
		return tqm;
	}
	public void setTqm(String tqm) {
		this.tqm = tqm;
	}
	public String getLsh() {
		return lsh;
	}
	public void setLsh(String lsh) {
		this.lsh = lsh;
	}
}
