package invoicetest;

import com.htxx.utils.HttpRequest;
import com.htxx.utils.TripleDESUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class TestA9KP {
    private static Logger logger = Logger.getLogger(TestA9KP.class.getName());
    public static void main(String [] args) throws IOException {

        String con = FileUtils.readFileToString(new File("d:\\专票.txt"),"GBK");

        String content = "";
        content = content+"<?xml version=\"1.0\" encoding=\"GBK\"?>";
        content = content +"<FPXT>";
        content = content +"<INPUT>";
        content = content +"<ZDH>"+"1"+"</ZDH>";
        content = content +"<DATA>";
        content = content + "<ID>ZPV0001</ID>";
        content = content + "<DATABUF>"+new String(TripleDESUtil.encode(con.getBytes("GBK")),"GBK")+"</DATABUF>";
        content = content + "</DATA>";
        content = content +"</INPUT>";
        content = content +"</FPXT>";

        //String content = FileUtils.readFileToString(new File("C:\\Users\\Administrator\\Desktop\\a9kp(1).txt"),"GBK");

        String xml = "";
        xml = xml + "<?xml version=\"1.0\" encoding=\"GBK\"?>";
        xml = xml + "<COM_INPUT>";
        xml = xml + "<ID>"+"0103"+"</ID>";
        xml = xml + "<DATA>"+new String(TripleDESUtil.encode(content.getBytes("GBK")),"GBK")+"</DATA>";
        xml = xml + "</COM_INPUT>";

        try {
            String sendPost = HttpRequest.sendPost("http://192.168.200.195:8080/370202999999066_0/InvoiceService", xml);
            logger.info(sendPost);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
