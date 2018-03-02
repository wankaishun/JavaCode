package com.liqun.dto.a9input;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="spxx")
@XmlAccessorType(XmlAccessType.FIELD)
public class Spxx implements Serializable{
	@XmlElement(name="sp",required=true)
	private List<Sp> sp;
	
	
	public List<Sp> getSp() {
		return sp;
	}

	public void setSp(List<Sp> sp) {
		if(sp == null) {
			sp = new ArrayList<>();
		}
		this.sp = sp;
	}
	
}
