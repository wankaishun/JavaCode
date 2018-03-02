package com.liqun.util;

import javax.xml.namespace.QName;

import org.apache.axis.client.*;


public class AxisWebserviceUtils {
	/**
	 * webservice调用接口
	 * @param iBilldelList
	 * @return
	 */
	public static String getAxis(String con) {
		  try {  
	            // 指出service所在完整的URL  
	            String endpoint = "http://192.168.200.241:8080/dzfp_qz/webservice/eInvWS?wsdl";  
	            //调用接口的targetNamespace  
	            String targetNamespace = "http://ws.einvvat.com";  
	            //所调用接口的方法method  
	            String method = "eInvoiceA9()";  
	            // 创建一个服务(service)调用(call)  
	            Service service = new Service();  
	            Call call = (Call) service.createCall();// 通过service创建call对象  
	            // 设置service所在URL  
	            call.setTargetEndpointAddress(new java.net.URL(endpoint));  
	            call.setOperationName(new QName(targetNamespace, method));  
	            call.setUseSOAPAction(true);  
	            //变量最好只是用String类型，其他类型会报错  
	            call.addParameter(new QName(targetNamespace, "con"), org.apache.axis.encoding.XMLType.XSD_STRING,javax.xml.rpc.ParameterMode.IN);//设置参数名 state  第二个参数表示String类型,第三个参数表示入参    
	            call.setReturnType(org.apache.axis.encoding.XMLType.XSD_STRING);// 设置返回类型  
	       
	             String result = (String) call.invoke(new Object[]{con});
	           // System.out.println(s);
	            return result;
	        } catch (Exception e) {  
	            e.printStackTrace(); 
	        }  
		  return null;
	}
}
