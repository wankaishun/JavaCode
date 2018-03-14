package io.github.wankaishun.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.github.wankaishun.annotation.Controller;
import io.github.wankaishun.annotation.Quatifier;
import io.github.wankaishun.annotation.RequestMapping;
import io.github.wankaishun.service.MyService;
import io.github.wankaishun.service.SpringmvcServiceImpl;

@Controller("chaoyue")  
public class SpringmvcController {  
    @Quatifier("MyServiceImpl")  
    MyService myService;  
    @Quatifier("SpringmvcServiceImpl")  
    SpringmvcServiceImpl smService;  
  
    @RequestMapping("insert")  
    public String insert(HttpServletRequest request, HttpServletResponse response, String param) {  
        myService.insert(null);  
        smService.insert(null);  
        return null;  
    }  
  
    @RequestMapping("delete")  
    public String delete(HttpServletRequest request, HttpServletResponse response, String param) {  
        myService.delete(null);  
        smService.delete(null);  
        return null;  
    }  
  
    @RequestMapping("update")  
    public String update(HttpServletRequest request, HttpServletResponse response, String param) {  
        myService.update(null);  
        smService.update(null);  
        return null;  
    }  
  
    @RequestMapping("select")  
    public String select(HttpServletRequest request, HttpServletResponse response, String param) {  
        myService.select(null);  
        smService.select(null);  
        return null;  
    }  
}  
