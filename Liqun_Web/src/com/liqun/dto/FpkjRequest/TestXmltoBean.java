package com.liqun.dto.FpkjRequest;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;

import com.liqun.util.JaxbUtil;
import com.sun.org.apache.regexp.internal.RE;
import com.sun.xml.bind.marshaller.CharacterEscapeHandler;

public class TestXmltoBean {
	/*@Test
	public void test() throws Exception {
		Business business = new Business();
		business.setComment("111");
		business.setId("1111");
		business.setS("");
		String str = convertToXml(business);
		System.out.println(str);
	}
	
	 public static void main(String args[])
	    {
	      
	            String name = "<h1>kshitij</h1>";
	            String surname = "<h1>solanki</h1>";
	            String id = "<h1>1</h1>";

	            Root cdata = new Root();
	            cdata.setId("111");
	            cdata.setName("2222");
	            cdata.setSurname("3");
	          String con = convertToXml(cdata);
	          System.out.println(con);
	          
	    }*/
	 public static String convertToXml(Object obj) {
		
		 String result="";
         try {
        	JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
             Marshaller marshaller = jaxbContext.createMarshaller();
            // marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
             marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
             marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);
			marshaller.setProperty(CharacterEscapeHandler.class.getName(), new CharacterEscapeHandler() { 
			     public void escape(char[] ac, int i, int j, boolean flag,
			     Writer writer) throws IOException {
			     writer.write( ac, i, j ); }
			     });
			StringWriter stringWriter = new StringWriter(); 
	         marshaller.marshal(obj, stringWriter);
	         System.out.println(stringWriter.toString());
	         return result;
		} catch (PropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         return result;
	 }
	 
	 @Test
	 public void test1() {
		/* REQUEST_COMMON_FPKJ  request_COMMON_FPKJ = new REQUEST_COMMON_FPKJ();
		 request_COMMON_FPKJ.setClasss("11111");
		 COMMON_FPKJ_FPT commonFPKJFPT = request_COMMON_FPKJ.getCommonFPKJFPT();
		commonFPKJFPT.setBZ("11111111111");
		commonFPKJFPT.setClasses("222222");
		
		String convertToXml = JaxbUtil.convertToXml(request_COMMON_FPKJ);
		System.out.println(convertToXml);*/
		 COMMON_FPKJ_FPT common_FPKJ_FPT = new COMMON_FPKJ_FPT();
		 common_FPKJ_FPT.setBZ("11111");
		 common_FPKJ_FPT.setClasses("2222");
		 
		 COMMON_FPKJ_XMXX common_FPKJ_XMXX  = new COMMON_FPKJ_XMXX();
		 common_FPKJ_XMXX.setDW("111111");
		 common_FPKJ_XMXX.setFPHXZ("11fgdigds绝对是地方季度石佛111");
		 COMMON_FPKJ_XMXXS common_FPKJ_XMXXS = new COMMON_FPKJ_XMXXS();
		 common_FPKJ_XMXXS.setClasses("1111");
		 common_FPKJ_XMXXS.setCommonFPJKXMXX(common_FPKJ_XMXX);
		 common_FPKJ_XMXXS.setSize("1");
		 
		 REQUEST_COMMON_FPKJ request_COMMON_FPKJ = new REQUEST_COMMON_FPKJ();
		 request_COMMON_FPKJ.setClasss("111111");
		 request_COMMON_FPKJ.setCommonFPKJXMXXS(common_FPKJ_XMXXS);
		 request_COMMON_FPKJ.setCommonFPKJFPT(common_FPKJ_FPT);
		 Business business = new Business();
		 String convertToXml = JaxbUtil.convertToXml(request_COMMON_FPKJ);
		 business.setComment("1111");
		 business.setId("11111");
		 business.setS(convertToXml);
		 String s = convertToXml(business);
		 System.out.println(s);
	 }
 @Test
	 public void test() {
	 //解析xml to bean
		 String xml="<business id=\"11111\" comment=\"1111\"><bussiness><![CDATA[<?xml version='1.0' encoding='GBK'?><REQUEST_COMMON_FPKJ class=\"111111\"><COMMON_FPKJ_FPT class=\"2222\"><BZ>11111</BZ></COMMON_FPKJ_FPT><COMMON_FPKJ_XMXXS class=\"1111\" size=\"1\"><commonFPJKXMXX><FPHXZ>11fgdigds&#x7edd;&#x5bf9;&#x662f;&#x5730;&#x65b9;&#x5b63;&#x5ea6;&#x77f3;&#x4f5b;111</FPHXZ><DW>111111</DW></commonFPJKXMXX></COMMON_FPKJ_XMXXS></REQUEST_COMMON_FPKJ>]]></bussiness></business>\r\n" + 
		 		"";
		 Business business = new Business();
		 business = xml2Bean(Business.class, xml);
		 String s = business.getS();
		System.out.println(business.toString());
	 }
 
 public static <T> T xml2Bean(Class<T> bean, String xmlStr) {
     T s = null;
     try {
         JAXBContext context = JAXBContext.newInstance(bean);
         Unmarshaller unmarshaller = context.createUnmarshaller();
         s = (T)unmarshaller.unmarshal(new StringReader(xmlStr));
     } catch (JAXBException e) {
         e.printStackTrace();
     }
     return s;
 }

}
