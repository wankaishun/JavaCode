package com.liqun.web;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;

import org.apache.commons.io.IOUtils;

import freemarker.ext.servlet.FreemarkerServlet;
import freemarker.template.TemplateException;


/**
 * 替代{@link freemarker.ext.servlet.FreemarkerServlet}，启动时会读取配置文件。
 * 
 * @author Michelangelo
 *
 */
public class PropertiesSettingsAwaredFreemarkerServlet extends FreemarkerServlet {

	private static final long serialVersionUID = 1L;
	
	String settings_location = "freemarker.properties";

	@Override
	public void init() throws ServletException {
		super.init();
		InputStream in = null;
		try {
//			in = this.getClass().getResourceAsStream("/freemarker.properties");
			in = this.getClass().getClassLoader().getResourceAsStream(settings_location);
			getConfiguration().setSettings(in);
			
			log(this.getClass().getName()+ " - " + settings_location +" loaded successfully.");
		
		} catch (IOException | TemplateException e) {
			throw new ServletException(e);
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

}
