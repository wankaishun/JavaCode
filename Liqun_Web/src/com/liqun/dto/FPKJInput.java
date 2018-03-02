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
@XmlRootElement(name="FPKJINPUT")
public class FPKJInput {
	@XmlElement(name="INPUT",required=true)
	 private Input input;

	public Input getInput() {
		return input;
	}

	public void setInput(Input input) {
		this.input = input;
	}

	@Override
	public String toString() {
		return "FPKJInput [input=" + input + "]";
	}
}
