package com.liqun.util;

import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Util {
	// 加密  
	 public static String getBase64(String str,String charset) {  
	        byte[] b = null;  
	        String s = null;  
	        try {  
	        	if(charset==null){
	        		b=str.getBytes();
	        	}else{
	        		 b = str.getBytes(charset);  
	        	}
	        } catch (UnsupportedEncodingException e) {  
	            e.printStackTrace();  
	        }  
	        if (b != null) {  
	            s = new BASE64Encoder().encode(b);  
	        }  
	        return s;  
	    }  
	  
	    // 解密  
	    public static String getFromBase64(String s,String charset) {  
	        byte[] b = null;  
	        String result = null;  
	        if (s != null) {  
	            BASE64Decoder decoder = new BASE64Decoder();  
	            try {  
	                b = decoder.decodeBuffer(s);  
	                if(charset==null){
	                	result=new String(b);
	                }else{
	                	 result = new String(b, charset);  
	                }
	            } catch (Exception e) {  
	                e.printStackTrace();  
	            }  
	        }  
	        return result;  
	    }  
	    public static void main(String[] args) {
			String str="aHR0cDovL2VpLjUxZmFwaWFvLmNuOjkwODAvNTFwdFByb3h5X2RqL2ZweHo/cD1mYjg5NGVhYjFmYTE0NWM3OGYwMzlmMDU3MjFiM2RlZQ==";
			//System.out.println(Base64Util.getBase64(str, null));
			System.out.println(Base64Util.getFromBase64(str,"gbk"));
		}
}
