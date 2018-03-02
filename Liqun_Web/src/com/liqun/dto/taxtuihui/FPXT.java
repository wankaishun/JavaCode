package com.liqun.dto.taxtuihui;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="FPXT")
public class FPXT implements Serializable {
	@XmlElement(name="OUTPUT",required=true)
    private OUTPUT output;

	public OUTPUT getOutput() {
		return output;
	}

	public void setOutput(OUTPUT output) {
		this.output = output;
	}
    
}
