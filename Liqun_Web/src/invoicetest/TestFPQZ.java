package invoicetest;
import com.google.gson.Gson;
import com.htxx.pojo.FPQZReturnObj;
import com.htxx.pojo.i_billdel;
import com.htxx.pojo.i_billmain;
import com.htxx.pojo.i_inv;
import com.htxx.services.FPQZ;
import com.htxx.utils.TripleDESUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TestFPQZ {
	public static void main(String[] args) throws Exception {
		Gson gson = new Gson();
		String billmain = FileUtils.readFileToString(new File("d:/billmain.txt"),"UTF-8");
        i_billmain billmain1 = gson.fromJson(billmain, i_billmain.class);
        System.out.println(billmain1.getFPLSH());
        
        String inv = FileUtils.readFileToString(new File("d:/inv.txt"),"UTF-8");
        i_inv inv1 = gson.fromJson(inv, i_inv.class);
        System.out.println(inv1.getFPDM());
        
        String billdel = FileUtils.readFileToString(new File("d:/billdel.txt"),"UTF-8");
        i_billdel billdel1 = gson.fromJson(inv, i_billdel.class);
        System.out.println(billdel1.getFPLSH());
        List<i_billdel> list = new ArrayList();
        list.add(billdel1);
        FPQZ fpqz = new FPQZ();
        FPQZReturnObj fpqz2 = fpqz.FPQZ(inv1, "SJ", billmain1,list);
        String message = new String(TripleDESUtil.decode(fpqz2.getReturnMessage().getBytes("UTF-8")),"UTF-8");
        System.out.println(message);
	}
	
	public static String getPropertyAddress(String key) {
		return Thread.currentThread().getContextClassLoader().getResource("").getPath() + key;
	}
}
