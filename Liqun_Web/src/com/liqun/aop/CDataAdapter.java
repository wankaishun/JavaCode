package com.liqun.aop;
import javax.xml.bind.annotation.adapters.XmlAdapter;
public class CDataAdapter extends XmlAdapter<String, String>{
	//从javabean到xml的适配方法
    @Override
    public String marshal(String str) throws Exception {
    	 if(str != null){  
    	        return "<![CDATA[" + str+ "]]>";
         }  
         return null;  
      
    }
    //从xml到javabean的适配方法
    @Override
    public String unmarshal(String str) throws Exception {
        return str;
    }
}
