package com.liqun.dto.a9FPKJOutput;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="FPKJOUTPUT")
public class FpkjOutput implements Serializable{
	@XmlElement(name="OUTPUT",required=true)
	private OUTPUT output;

	public OUTPUT getOutput() {
		return output;
	}

	public void setOutput(OUTPUT output) {
		this.output = output;
	}
	
	
	
	
}
