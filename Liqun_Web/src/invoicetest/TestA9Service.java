package invoicetest;

import com.htxx.pojo.A9Param;
import com.htxx.services.A9Servcie;
import com.htxx.utils.TripleDESUtil;

public class TestA9Service {
    public static void main(String[] args) throws Exception{
        String content = "";
        content = content+"<?xml version=\"1.0\" encoding=\"GBK\"?>";
        content = content +"<FPXT>";
        content = content +"<INPUT>";
        content = content +"<ZDH>"+"1"+"</ZDH>";
        content = content +"</INPUT>";
        content = content +"</FPXT>";

        String xml = "";
        xml = xml + "<?xml version=\"1.0\" encoding=\"GBK\"?>";
        xml = xml + "<COM_INPUT>";
        xml = xml + "<ID>"+"0201"+"</ID>";
        xml = xml + "<DATA>"+new String(TripleDESUtil.encode(content.getBytes("GBK")),"GBK")+"</DATA>";
        xml = xml + "</COM_INPUT>";

        A9Param a9Param = new A9Param();
        a9Param.setBH("0");
        a9Param.setReqXml(xml);
        a9Param.setSH("370202999999066");
        String s = A9Servcie.requestA9(a9Param);
        System.out.println(s);
    }
}
