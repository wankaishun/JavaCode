package com.liqun.dto.FpkjRequest;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.eclipse.persistence.oxm.annotations.XmlCDATA;

import com.liqun.aop.CDataAdapter;



/**
 * @author 
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Business implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@XmlAttribute(name = "id")
	private String id;
	
	@XmlAttribute(name="comment")
	private String comment;
	
	@XmlCDATA
	private String s;
	
	
	public String getS() {
		return s;
	} 

	public void setS(String s) {
		this.s = s;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Business [id=" + id + ", comment=" + comment + ", s=" + s + "]";
	}
	
	

}
