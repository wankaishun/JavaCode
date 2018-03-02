package com.liqun.aop;

import java.io.IOException;
import java.io.Writer;

import com.sun.xml.bind.marshaller.CharacterEscapeHandler;

public class CustomCharacterEscapeHandler implements CharacterEscapeHandler {
	 private int cdataMiniLength = "<![CDATA[]]>".length();  
	    private static String[] escapeList = new String[63];  
	    static {  
	        escapeList[(int) '&'] = "&amp;";  
	        escapeList[(int) '<'] = "&lt;";  
	        escapeList[(int) '>'] = "&gt;";  
	        escapeList[(int) '"'] = "&quot;";  
	        escapeList[(int) '\t'] = "&#x9;";  
	        escapeList[(int) '\r'] = "&#xD;";  
	        escapeList[(int) '\n'] = "&#xA;";  
	    }  
	  
	  
	    public void escape(char[] ch, int start, int length,  
	            boolean isAttVal, Writer out) throws IOException {  
	        if (isAttVal) {  
	            for (char c : ch) {  
	                if (escapeList.length > ((int) c)  
	                        && escapeList[(int) c] != null) {  
	                    out.write(escapeList[(int) c]);  
	                } else {  
	                    out.write(c);  
	                }  
	            }  
	        } else {  
	            if (length >= cdataMiniLength) {  
	                String s = new String(ch).trim();  
	                if (s.startsWith("<![CDATA[")  
	                        && s.endsWith("]]>")) {  
	                    out.write(ch);  
	                    return;  
	                }  
	            }  
	            for (char c : ch) {  
	                if (c == '"' || c == '\t' || c == '\n') {  
	                    out.write(c);  
	                } else if (escapeList.length > ((int) c)  
	                        && escapeList[(int) c] != null) {  
	                    out.write(escapeList[(int) c]);  
	                } else {  
	                    out.write(c);  
	                }  
	            }  
	        }  
	    }  
}
