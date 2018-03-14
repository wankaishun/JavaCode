package io.github.wankaishun.servlet;

import java.io.File;  
import java.io.IOException;  
import java.lang.reflect.Field;  
import java.lang.reflect.InvocationTargetException;  
import java.lang.reflect.Method;  
import java.net.URL;  
import java.util.ArrayList;  
import java.util.HashMap;  
import java.util.List;  
import java.util.Map;  
  
import javax.servlet.ServletConfig;  
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import io.github.wankaishun.annotation.Controller;
import io.github.wankaishun.annotation.Quatifier;
import io.github.wankaishun.annotation.RequestMapping;
import io.github.wankaishun.annotation.Service;
import io.github.wankaishun.controller.SpringmvcController;

@WebServlet("/")  
public class DispatcherServlet extends HttpServlet {  
    private static final long serialVersionUID = 1L;  
    List<String> packageNames = new ArrayList<String>();  
    // �������ʵ����key��ע���value,value���������ʵ��  
    Map<String, Object> instanceMap = new HashMap<String, Object>();  
    Map<String, Object> handerMap = new HashMap<String, Object>();  
    public DispatcherServlet() {  
        super();  
    }  
  
    public void init(ServletConfig config) throws ServletException {  
        // ��ɨ��,��ȡ���е��ļ�  
        scanPackage("io.github.wankaishun");  
        try {  
            filterAndInstance();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        // ����ӳ���ϵ  
        handerMap();  
        // ʵ��ע��  
        ioc();  
    }  
  
    private void filterAndInstance() throws Exception {  
        if (packageNames.size() <= 0) {  
            return;  
        }  
        for (String className : packageNames) {  
            Class<?> cName = Class.forName(className.replace(".class", "").trim());  
            if (cName.isAnnotationPresent(Controller.class)) {  
                Object instance = cName.newInstance();  
                Controller controller = (Controller) cName.getAnnotation(Controller.class);  
                String key = controller.value();  
                instanceMap.put(key, instance);  
            } else if (cName.isAnnotationPresent(Service.class)) {  
                Object instance = cName.newInstance();  
                Service service = (Service) cName.getAnnotation(Service.class);  
                String key = service.value();  
                instanceMap.put(key, instance);  
            } else {  
                continue;  
            }  
        }  
    }  
  
    private void ioc() {  
        if (instanceMap.isEmpty())  
            return;  
        for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {  
            // �õ��������������  
            Field fields[] = entry.getValue().getClass().getDeclaredFields();  
            for (Field field : fields) {  
                field.setAccessible(true);// �ɷ���˽������  
                if (field.isAnnotationPresent(Quatifier.class));  
                Quatifier quatifier = field.getAnnotation(Quatifier.class);  
                String value = quatifier.value();  
                field.setAccessible(true);  
                try {  
                    field.set(entry.getValue(), instanceMap.get(value));  
                } catch (IllegalArgumentException e) {  
                    e.printStackTrace();  
                } catch (IllegalAccessException e) {  
                    e.printStackTrace();  
                }  
            }  
        }  
    }  
  
    /** 
     * ɨ����µ������ļ� 
     *  
     * @param Package 
     */  
    private void scanPackage(String Package) {  
        URL url = this.getClass().getClassLoader().getResource("/" + replaceTo(Package));// �����е�.ת���ȡ��Ӧ��·��  
        String pathFile = url.getFile();  
        File file = new File(pathFile);  
        String fileList[] = file.list();  
        for (String path : fileList) {  
            File eachFile = new File(pathFile + path);  
            if (eachFile.isDirectory()) {  
                scanPackage(Package + "." + eachFile.getName());  
            } else {  
                packageNames.add(Package + "." + eachFile.getName());  
            }  
        }  
    }  
  
    /** 
     * ����ӳ���ϵ 
     */  
    private void handerMap() {  
        if (instanceMap.size() <= 0)  
            return;  
        for (Map.Entry<String, Object> entry : instanceMap.entrySet()) {  
            if (entry.getValue().getClass().isAnnotationPresent(Controller.class)) {  
                Controller controller = (Controller) entry.getValue().getClass().getAnnotation(Controller.class);  
                String ctvalue = controller.value();  
                Method[] methods = entry.getValue().getClass().getMethods();  
                for (Method method : methods) {  
                    if (method.isAnnotationPresent(RequestMapping.class)) {  
                        RequestMapping rm = (RequestMapping) method.getAnnotation(RequestMapping.class);  
                        String rmvalue = rm.value();  
                        handerMap.put("/" + ctvalue + "/" + rmvalue, method);  
                    } else {  
                        continue;  
                    }  
                }  
            } else {  
                continue;  
            }  
  
        }  
    }  
  
    private String replaceTo(String path) {  
        return path.replaceAll("\\.", "/");  
    }  
  
    @Override  
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
        this.doPost(req, resp);  
    }  
  
    @Override  
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {  
        String url = req.getRequestURI();  
        String context = req.getContextPath();  
        String path = url.replace(context, "");  
        Method method = (Method) handerMap.get(path);  
        SpringmvcController controller = (SpringmvcController) instanceMap.get(path.split("/")[1]);  
        try {  
            method.invoke(controller, new Object[] { req, resp, null });  
        } catch (IllegalAccessException e) {  
            e.printStackTrace();  
        } catch (IllegalArgumentException e) {  
            e.printStackTrace();  
        } catch (InvocationTargetException e) {  
            e.printStackTrace();  
        }  
    }  
  
}  