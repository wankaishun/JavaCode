package com.liqun.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="ROOT")
@XmlType(propOrder={"code","name","age"})//xml格式数据的显示的顺序名字要和定义变量的一样，而不是@XmlElement中的name
public class MySocket {
	    @XmlElement(name="Code",required=true)//定义xml中显示的数据
	    private  String code;
	    @XmlElement(name="Name",required=true)
	    private String name;
	    @XmlElement(name="Age",required=true)
	    private String age;
	     
	    public String getCode() {
			return code;
		}

		public void setCode(String code) {
			this.code = code;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getAge() {
			return age;
		}

		public void setAge(String age) {
			this.age = age;
		}

		public String toString(){
	        return this.name+","+this.age+","+this.code;
	    }
}
