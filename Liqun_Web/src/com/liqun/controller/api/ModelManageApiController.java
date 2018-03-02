package com.liqun.controller.api;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.liqun.entity.SysModel;
import com.liqun.service.ModelManageService;
@RequestMapping("/api")
@Controller  
public class ModelManageApiController {
	@Autowired
	private ModelManageService modelManageService;
	 /**  
     * 文件上传功能  
     * @param file  
     * @return  
     * @throws IOException   
     */  
    @RequestMapping(value="/upload",method=RequestMethod.POST)  
    @ResponseBody  
    public String upload(MultipartFile file1,HttpServletRequest request) throws IOException{  
        String path = request.getSession().getServletContext().getRealPath("upload");  
        String name=request.getParameter("name");
        int modelid=Integer.parseInt(request.getParameter("modelid"));
        String fileName = file1.getOriginalFilename(); 
        SysModel record=new SysModel();
        record.setModelId(modelid);
        record.setModelName(fileName);
        record.setName(name);
        record.setPath(path);
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        modelManageService.saveModel(record);
        File dir = new File(path,fileName);
        if(!dir.exists()){  
            dir.mkdirs();  
        }  
        //MultipartFile自带的解析方法  
        file1.transferTo(dir);  
        return "ok!";  
    } 
    @RequestMapping(value="/getModelList",method=RequestMethod.GET)  
    @ResponseBody 
    public Object getModelList(HttpServletRequest request) {
    	return modelManageService.getSysModelList();
    }
      
    /**  
     * 文件下载功能  
     * @param request  
     * @param response  
     * @throws Exception  
     */  
    @RequestMapping("/down")  
    public void down(HttpServletRequest request,HttpServletResponse response) throws Exception{  
        //模拟文件，myfile.txt为需要下载的文件  
    	int id=Integer.parseInt(request.getParameter("id"));
    	SysModel sysModel=modelManageService.getSysModelById(id);
        //String fileName = request.getSession().getServletContext().getRealPath("upload")+"/myfile.txt";  
        String fileName=sysModel.getPath()+"\\"+sysModel.getModelName();
    	//获取输入流  
        InputStream bis = new BufferedInputStream(new FileInputStream(new File(fileName)));  
        //假如以中文名下载的话  
        String filename = sysModel.getModelName();  
        //转码，免得文件名中文乱码  
        filename = URLEncoder.encode(filename,"UTF-8");  
        //设置文件下载头  
        response.addHeader("Content-Disposition", "attachment;filename=" + filename);    
        //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型    
        response.setContentType("multipart/form-data");   
        BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());  
        int len = 0;  
        while((len = bis.read()) != -1){  
            out.write(len);  
            out.flush();  
        }  
        out.close();  
    }  
}
