package com.liqun.dto.a9output;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="FPXT")
public class Fpxt {
	public Output getOutput() {
		return output;
	}

	public void setOutput(Output output) {
		this.output = output;
	}

	@XmlElement(name="OUTPUT",required=true)
	private Output output;

	@Override
	public String toString() {
		return "Fpxt [output=" + output + "]";
	}
}
