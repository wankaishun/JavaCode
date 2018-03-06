package com.liqun.util;

import java.io.UnsupportedEncodingException;

import com.htxx.utils.TripleDESUtil;
import com.liqun.dto.a9input.Fp;
import com.liqun.dto.a9output.ComOutput;
import com.liqun.dto.a9output.Fpxt;
import com.liqun.entity.MySocket;

public class TestJaxb {
	 public static void main(String[] args) {
		 try {
			xmlToObjetct();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }    
	    public static  void objectToXml(){
	        MySocket mySocket = new MySocket();
	        mySocket.setName("张三");
	        mySocket.setCode("00012");
	        mySocket.setAge("25");
	        String xml = JaxbUtil.convertToXml(mySocket);
	        System.out.println(xml);
	    } 
	    public static void xmlToObjetct() throws UnsupportedEncodingException{
	        String xml = "<?xml version=\"1.0\" encoding=\"GBK\"?><COM_OUTPUT><ID>0103</ID><CODE>0000</CODE><MESS>成功！</MESS><DATA>PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iR0JLIj8+CjxGUFhUPjxPVVRQVVQ+PElEPjAxMDM8L0lEPjxEQVRBPjxTVUNDRVNTPjxDT0RFPjAwMDA8L0NPREU+PElEPlpQVjAwMDE8L0lEPjxNRVNTPrPJuaajoTwvTUVTUz48REFUQUJVRj5QRDk0Yld3Z2RtVnljMmx2YmowaU1TNHdJaUJsYm1OdlpHbHVaejBpUjBKTElqOCtDanhHVUQ0S0lDQWdJRHhpYkhWbFJuQmtiVDQ4TDJKc2RXVkdjR1J0UGdvZ0lDQWdQR0pzZFdWR2NHaHRQand2WW14MVpVWndhRzArQ2lBZ0lDQThZbk5pZWo0d1BDOWljMko2UGdvZ0lDQWdQR0p6Y1Q0eFBDOWljM0UrQ2lBZ0lDQThZbk42ZEQ0d1BDOWljM3AwUGdvZ0lDQWdQR0o2UGp3dllubytDaUFnSUNBOFkyTmtkejQ4TDJOalpIYytDaUFnSUNBOFkyUStQQzlqWkQ0S0lDQWdJRHhqYkd4NFBqd3ZZMnhzZUQ0S0lDQWdJRHhqYkhOaVpHZytQQzlqYkhOaVpHZytDaUFnSUNBOFkzQjRhRDQ4TDJOd2VHZytDaUFnSUNBOFkzbHliV00rUEM5amVYSnRZejRLSUNBZ0lEeGplWEp1YzNKelltZytQQzlqZVhKdWMzSnpZbWcrQ2lBZ0lDQThZM3BqYUQ0OEwyTjZZMmcrQ2lBZ0lDQThaR3R4ZVcxalBqd3ZaR3R4ZVcxalBnb2dJQ0FnUEdScmNYbHphRDQ4TDJScmNYbHphRDRLSUNBZ0lEeGtkejQ4TDJSM1Bnb2dJQ0FnUEdSNVlubytNRHd2WkhsaWVqNEtJQ0FnSUR4a2VXMWlQand2WkhsdFlqNEtJQ0FnSUR4a2VuTjVhRDR4TXp3dlpIcHplV2crQ2lBZ0lDQThabVJxYUcwK1BDOW1aR3BvYlQ0S0lDQWdJRHhtYUhJK1BDOW1hSEkrQ2lBZ0lDQThabWh5YldNK1BDOW1hSEp0WXo0S0lDQWdJRHhtYUhKdWMzSnpZbWcrUEM5bWFISnVjM0p6WW1nK0NpQWdJQ0E4Wm5Ca2JUNHpOekF5TVRVeE5qSXdQQzltY0dSdFBnb2dJQ0FnUEdad2FHMCtNREF3TVRJMU1EYzhMMlp3YUcwK0NpQWdJQ0E4Wm5Cc2VENWpQQzltY0d4NFBnb2dJQ0FnUEdkbVltZytQQzluWm1Kb1Bnb2dJQ0FnUEdkbVpIcGthRDdSOU1pcXl0Q3o1ODdFeC9pdzBjbTl3cmN5TXptNnhTQXpOVGcyTWpNME56d3ZaMlprZW1Sb1Bnb2dJQ0FnUEdkbWJXTStNakRPdThyOTE5YkxzTHJGeHZQU3RUd3ZaMlp0WXo0S0lDQWdJRHhuWm5Ob1BqRTBNRE13TVRFNU56a3hNak14TURBNE16YzRQQzluWm5Ob1Bnb2dJQ0FnUEdkbWVXaDZhRDY1cE5EUXhNL1Z2ckRzTWpNd05TMHlNVE0xTkRZNFBDOW5abmxvZW1nK0NpQWdJQ0E4YUdkNmFENDhMMmhuZW1nK0NpQWdJQ0E4YUhodFBqd3ZhSGh0UGdvZ0lDQWdQR2g2Wm5jK01Ed3ZhSHBtZHo0S0lDQWdJRHhwYm5aUmNubE9iejR3UEM5cGJuWlJjbmxPYno0S0lDQWdJRHhwYzBKc1lXNXJWMkZ6ZEdVK01Ed3ZhWE5DYkdGdWExZGhjM1JsUGdvZ0lDQWdQR2x6VG1WM1NtUmpabkErTVR3dmFYTk9aWGRLWkdObWNENEtJQ0FnSUR4cGMxSmxaRDR3UEM5cGMxSmxaRDRLSUNBZ0lEeHBjMU54WkQ0d1BDOXBjMU54WkQ0S0lDQWdJRHhxWlQ0Mk1EQXVNRHd2YW1VK0NpQWdJQ0E4YW10NmJYTm9Qand2YW10NmJYTm9QZ29nSUNBZ1BHcHRZbUpvUGpFeFBDOXFiV0ppYUQ0S0lDQWdJRHhxY1dKb1Bqd3ZhbkZpYUQ0S0lDQWdJRHhxYzJ0ZlpuQnRlRjkyWlhKemFXOXVQakk4TDJwemExOW1jRzE0WDNabGNuTnBiMjQrQ2lBZ0lDQThhbmx0UGpnd05qTTNOVFl4TVRVeE5UVXpOREU1TmprelBDOXFlVzArQ2lBZ0lDQThhMlY1Um14aFowNXZQakE4TDJ0bGVVWnNZV2RPYno0S0lDQWdJRHhyY0dwb1BqRThMMnR3YW1nK0NpQWdJQ0E4YTNCeVBybmN3TzNVc1RFOEwydHdjajRLSUNBZ0lEeHJjSEp4UGpJd01UY3RNVEl0TVRJZ01UZzZOVFU2TVRROEwydHdjbkUrQ2lBZ0lDQThhM0J6ZUdnK056d3ZhM0J6ZUdnK0NpQWdJQ0E4YlhjK09IbDNabGx4ZUROeFozRlJOR1JSYjFvemNsTjVkVWRxZW05d1ozSkhNbFV5YTFsVWNFVXpXak5FYnpGcE9GRldPU3RGVnpSTlowaHJNR2RDYTBsU1dGVjBURU5FUldaRVMzbElVa1p4THpWM2RUTk5MMXBPTVhONFEwazVlbXMyZUZGaFIzUm5ZMjFHTlRaVU1tZGhLemxtUjNOWE1GVk5kVmwwUTJrd2MweEhWM0ZQVTNaUWFUZ3hhRlZhZVdKVlN6UklWelJSUFQwOEwyMTNQZ29nSUNBZ1BHMTRhR3BxWlQ0OEwyMTRhR3BxWlQ0S0lDQWdJRHh4ZVdRK1BDOXhlV1ErQ2lBZ0lDQThjbVJ0UW5sMFpUNDhMM0prYlVKNWRHVStDaUFnSUNBOGNtVmtUblZ0UGp3dmNtVmtUblZ0UGdvZ0lDQWdQSEpsZEVOdlpHVStNREF3TUR3dmNtVjBRMjlrWlQ0S0lDQWdJRHh6WTJOcWJXTStQQzl6WTJOcWJXTStDaUFnSUNBOGMyVStNVGd1TUR3dmMyVStDaUFnSUNBOGMyWjZhRzArUEM5elpucG9iVDRLSUNBZ0lEeHphSEp0WXo0OEwzTm9jbTFqUGdvZ0lDQWdQSE5vY201emNuTmlhRDQ4TDNOb2NtNXpjbk5pYUQ0S0lDQWdJRHh6YVdkdVBrMUpTVUpQWjFsS1MyOWFTV2gyWTA1QlVXTkRiMGxKUWt0NlEwTkJVMk5EUVZGRmVFTjZRVXBDWjFWeVJHZE5RMGRuVlVGTlFYTkhRMU54UjFOSllqTkVVVVZJUVZSSFEwRlJXWGRuWjBWRFFXZEZRazFIUVhkV1ZFVk1UVUZyUjBFeFZVVkNhRTFEV1RJMGVFUlVRVXhDWjA1V1FrRnpaVUpCUWtSQlJVVjRSRlJCVEVKblRsWkNRV2RsUWtaTldGUnhkM2hIVkVGWVFtZE9Wa0pCVFdWRlJUUjBWbll4TmtSc1MyaHBObE5NZDFVMGRGZzRUWGhFVkVGTVFtZE9Wa0pCWTJWQ1JrMVlWSEYzUTBKM1NVSkJRVUZCTVRSVmQwTlJXVVpMZHpSRVFXaHZSa0ZFUVU1Q1oydHhhR3RwUnpsM01FSkJVVVZHUVVGVFFtZEtjM1ozUVZKblpYRlFXVUpMY0dsUWIyRXllR0ZNY0VsbVZqTXhibkZvTjJJclRFcHRVMU5HTDNSWE16SjVaelozVkZSaGNrYzBha3BxYUhCUWIyd3lLelZTTkN0cVowcFJhazQzWlVkWk1FTlpRWE5FVUhKR05TODJhMkpIZHpWc2VrY3pWMFIxU1RCTFYwaEVlR1ZqZUROaFJqTjNhVFphVkRkWVYyOW5PRWxXYjB0YWFITkdaV2g2T1V4WVkyVnlVbFZTWWxKVE1EUmFiaXRTWkhOaWVtNDRTbUZHYTBsd1MxVThMM05wWjI0K0NpQWdJQ0E4YzJwa2FENDhMM05xWkdnK0NpQWdJQ0E4YzJ0eVBqd3ZjMnR5UGdvZ0lDQWdQSE5NZGo0d0xqQXpQQzl6VEhZK0NpQWdJQ0E4YzNCbWJXTStQQzl6Y0dadFl6NEtJQ0FnSUR4emNHWnVjM0p6WW1nK1BDOXpjR1p1YzNKelltZytDaUFnSUNBOGMzTjVaajR5TURFM01USThMM056ZVdZK0NpQWdJQ0E4VkVGWVgwTk1RVk5UTHo0S0lDQWdJRHgzYzNCNmFHMCtQQzkzYzNCNmFHMCtDaUFnSUNBOGVHTnljejQ4TDNoamNuTStDaUFnSUNBOGVHWmllajR3UEM5NFptSjZQZ29nSUNBZ1BIaG1aR2crUEM5NFptUm9QZ29nSUNBZ1BIaG1aSG8rUEM5NFptUjZQZ29nSUNBZ1BIaG1aSHBrYUQ0eElERThMM2htWkhwa2FENEtJQ0FnSUR4NFptMWpQcnE5ME1YRjRORzF4dlBTdFR3dmVHWnRZejRLSUNBZ0lEeDRabk5vUGpNM01ESXdNams1T1RrNU9UQTJOand2ZUdaemFENEtJQ0FnSUR4NFpubG9Qand2ZUdaNWFENEtJQ0FnSUR4NFpubG9lbWcrTVR3dmVHWjVhSHBvUGdvZ0lDQWdQSGhtZW1nK1BDOTRabnBvUGdvZ0lDQWdQSGh6WkdwaWFENDhMM2h6WkdwaWFENEtJQ0FnSUR4NWMyaDNlSGcrUEM5NWMyaDNlSGcrQ2lBZ0lDQThlWGx6WW5vK01EQXdNREF3TURBd01Ed3ZlWGx6WW5vK0NpQWdJQ0E4ZW1aaWVqNHdQQzk2Wm1KNlBnb2dJQ0FnUEhwbWMybytQQzk2Wm5OcVBnb2dJQ0FnUEhwbmMzZHFaMlJ0UGp3dmVtZHpkMnBuWkcwK0NpQWdJQ0E4ZW1kemQycG5iV00rUEM5NlozTjNhbWR0WXo0S0lDQWdJRHg2ZVdad1RIZytNRHd2ZW5sbWNFeDRQZ29nSUNBZ1BIcDVjM0J0WXo3RHdMVHZNalJZSUVORUxWSlBUVHd2ZW5semNHMWpQZ29nSUNBZ1BIcDVjM0J6YlQ0d01UQXhQQzk2ZVhOd2MyMCtDaUFnSUNBOFltMWlZbUpvUGpFekxqQThMMkp0WW1KaWFENEtJQ0FnSUR4TmVIaDRQZ29nSUNBZ0lDQWdJRHh6Y0hoNFBnb2dJQ0FnSUNBZ0lDQWdJQ0E4YzNBZ2EyVjVQU0pDU0ZORVNpSWdkbUZzZFdVOUlqWXdNQzR3TURBd01EQXdNQ0l2UGdvZ0lDQWdJQ0FnSUNBZ0lDQThjM0FnYTJWNVBTSkVTaUlnZG1Gc2RXVTlJall3TUM0d01DSXZQZ29nSUNBZ0lDQWdJQ0FnSUNBOGMzQWdhMlY1UFNKR1RFSk5JaUIyWVd4MVpUMGlNVEV3TURFd01UQXhNRFV3TURBd01EQXdNQ0l2UGdvZ0lDQWdJQ0FnSUNBZ0lDQThjM0FnYTJWNVBTSkdVRWhZV2lJZ2RtRnNkV1U5SWpBaUx6NEtJQ0FnSUNBZ0lDQWdJQ0FnUEhOd0lHdGxlVDBpUjBkWVNDSWdkbUZzZFdVOUlqSTBzYmJMMlNJdlBnb2dJQ0FnSUNBZ0lDQWdJQ0E4YzNBZ2EyVjVQU0pJVTBwQ1dpSWdkbUZzZFdVOUlqQWlMejRLSUNBZ0lDQWdJQ0FnSUNBZ1BITndJR3RsZVQwaVNrVWlJSFpoYkhWbFBTSTJNREF1TUNJdlBnb2dJQ0FnSUNBZ0lDQWdJQ0E4YzNBZ2EyVjVQU0pLVEVSWElpQjJZV3gxWlQwaXpLZ2lMejRLSUNBZ0lDQWdJQ0FnSUNBZ1BITndJR3RsZVQwaVMwTkZJaUIyWVd4MVpUMGlNQ0l2UGdvZ0lDQWdJQ0FnSUNBZ0lDQThjM0FnYTJWNVBTSk1VMHhXUWxNaUlIWmhiSFZsUFNJaUx6NEtJQ0FnSUNBZ0lDQWdJQ0FnUEhOd0lHdGxlVDBpVTBVaUlIWmhiSFZsUFNJeE9DNHdJaTgrQ2lBZ0lDQWdJQ0FnSUNBZ0lEeHpjQ0JyWlhrOUlsTk1JaUIyWVd4MVpUMGlNUzR3TURBd01EQXdNQ0l2UGdvZ0lDQWdJQ0FnSUNBZ0lDQThjM0FnYTJWNVBTSlRURllpSUhaaGJIVmxQU0l3TGpBeklpOCtDaUFnSUNBZ0lDQWdJQ0FnSUR4emNDQnJaWGs5SWxOUVFrZ2lJSFpoYkhWbFBTSXdNREV3TVNJdlBnb2dJQ0FnSUNBZ0lDQWdJQ0E4YzNBZ2EyVjVQU0pUVUUxRElpQjJZV3gxWlQwaXc4QzA3ekkwV0NCRFJDMVNUMDBpTHo0S0lDQWdJQ0FnSUNBZ0lDQWdQSE53SUd0bGVUMGlVMUJUVFNJZ2RtRnNkV1U5SWpBeE1ERWlMejRLSUNBZ0lDQWdJQ0FnSUNBZ1BITndJR3RsZVQwaVdGTlpTQ0lnZG1Gc2RXVTlJakVpTHo0S0lDQWdJQ0FnSUNBZ0lDQWdQSE53SUd0bGVUMGlXRlJJUVZOSUlpQjJZV3gxWlQwaUlpOCtDaUFnSUNBZ0lDQWdJQ0FnSUR4emNDQnJaWGs5SWxsSVUwMGlJSFpoYkhWbFBTTFB5TlgzdXZQTnl5SXZQZ29nSUNBZ0lDQWdJRHd2YzNCNGVENEtJQ0FnSUR3dlRYaDRlRDRLSUNBZ0lEeFJaSGg0THo0S1BDOUdVRDRLPC9EQVRBQlVGPjwvU1VDQ0VTUz48L0RBVEE+PC9PVVRQVVQ+PC9GUFhUPg==</DATA></COM_OUTPUT>";
	        ComOutput comOutput= JaxbUtil.converyToJavaBean(xml, ComOutput.class);
	        byte[] data=TripleDESUtil.decode(comOutput.getData().replace(' ','+').getBytes("GBK"));
			String a=new String(data,"GBK");
	        Fpxt fpxt=JaxbUtil.converyToJavaBean(a, Fpxt.class);
	        byte[] data1=TripleDESUtil.decode(fpxt.getOutput().getData().getSuccess().getDATABUF().replace(' ','+').getBytes("GBK"));
			String b=new String(data1,"GBK");
			Fp fp=JaxbUtil.converyToJavaBean(b, Fp.class);
			
			String res = JaxbUtil.convertToXml(fp);
	        System.out.println(res);
	    }
}