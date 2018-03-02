package com.liqun.controller.api;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.liqun.controller.advice.ApiControllerAdvisable;
import com.liqun.entity.Customer;
import com.liqun.entity.CustomerDto;
import com.liqun.entity.IBillmain;
import com.liqun.service.CustomerService;
import com.liqun.util.DataGridPage;
import com.liqun.util.ExcelUtil;

@Controller
@RequestMapping("/api/datamaintain")
@ApiControllerAdvisable
public class CustomerApiController {
	@Autowired
	private CustomerService customerService;

	@GetMapping("/customerList")
	@ResponseBody
	public Object customerList(@RequestParam int page, @RequestParam int rows, String khmc, String khsh) {
		// 数据库分页 当前页需要减一
		Page<CustomerDto> customerlist = customerService.findAll(PageRequest.of(page - 1, rows), khmc, khsh);

		return DataGridPage.pageToGrid(customerlist);
	}

	@GetMapping("/delete")
	@ResponseBody
	public Object delete(Integer khbh) {

		return customerService.delete(khbh);
	}

//	@GetMapping("/addcolumn")
//	@ResponseBody
//	public Object addcolumn(String addcolumn) {
//		return customerService.addcolumn(addcolumn);
//	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public int addCustomer(Customer customer) {
		customer.setKhzt(1);// 追加客户状态

		return customerService.addCustomer(customer);
	}

	// 修改商品信息
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public int updateCustomer(Customer customer) {

		System.out.println("--------------------------------------------");
		return customerService.updateCustomer(customer);
	}

	@RequestMapping("/getCustomerInfo")
	@ResponseBody
	public Object getCustomerInfo(Integer khbh) {
		return customerService.getCustomerInfo(khbh);
	}

	@GetMapping("/exportCustomer")
	public Object exportBill(HttpServletResponse response, HttpServletRequest request) {

		String khmc = request.getParameter("khmc");
		String khsh = request.getParameter("khsh");

		response.setContentType("application/binary;charset=UTF-8");
		try {
			ServletOutputStream out = response.getOutputStream();
			String fileName = new String(
					(URLEncoder.encode("客户信息", "UTF-8") + new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
							.getBytes(),
					"UTF-8");
			response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xls");
			List<CustomerDto> list = customerService.getIBillmainForExport(khmc, khsh);
			ExcelUtil<CustomerDto> util = new ExcelUtil<CustomerDto>(CustomerDto.class);// 创建工具类.
			util.exportExcel(list, "客户信息", 65536, out);// 导出
			System.out.println(out);
			System.out.println("----执行完毕----------");
		} catch (Exception e) {
			e.printStackTrace();
			return "导出信息失败";
		}
		return null;
	}
	@GetMapping("/importCustomer")
	@ResponseBody
	 public Object importExcel() throws IOException{
		/*FileSystemView fsv = FileSystemView.getFileSystemView();
        File line = fsv.getHomeDirectory();//获取桌面路径
        JFileChooser jFileChooser = new JFileChooser(line);
        ExcelFilter excelFilter = new ExcelFilter();//excel过滤,只显示文件夹和excel格式文件
        jFileChooser.addChoosableFileFilter(excelFilter);
        jFileChooser.setFileFilter(excelFilter);

        jFileChooser.setDialogTitle("请选择Excel表格");//修改弹出框标题

        int i = jFileChooser.showOpenDialog(null);
        if(i== jFileChooser.APPROVE_OPTION){//打开文件
            filePath  = jFileChooser.getSelectedFile().getAbsolutePath();
            name = jFileChooser.getSelectedFile().getName();
//            System.out.println("当前文件路径："+path+"\n当前文件名："+name);
*/	        FileInputStream fis = null;  
	        try {  
	            fis = new FileInputStream(" ");  
	            ExcelUtil<Customer> util = new ExcelUtil<Customer>(  
	            		Customer.class);// 创建excel工具类  
	            List<Customer> list = util.importExcel("客户信息", fis);// 导入  
	            System.out.println(list);
	            customerService.importExcel(list);
	            
	        } catch (FileNotFoundException e) {  
	            e.printStackTrace();  
	        }
	        return 1;
	    }
}
