package com.liqun.dto.taxdownload.find;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="FPXT")
public class FPXT implements Serializable {
	@XmlElement(name="OUTPUT",required=true)
    private OUTPUT1 output;

	public OUTPUT1 getOutput() {
		return output;
	}

	public void setOutput(OUTPUT1 output) {
		this.output = output;
	}
    
}
