package com.liqun.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

@Controller
public class CaptchaController {
	
	private static final Logger logger = LoggerFactory.getLogger(CaptchaController.class);
	/**
	 * 生成验证码
	 * 
	 */
	@Autowired
	private Producer producer = null;
	
	  
	  
    @Autowired  
    public void setCaptchaProducer(Producer kaptchaProducer) {  
        this.producer = kaptchaProducer;  
    }  
	@RequestMapping("/kaptcha")
	public  ModelAndView captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {

		if(logger.isInfoEnabled()) logger.info("CaptchaController.captcha START");
		
        response.setDateHeader("Expires",0);  
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");  
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");  
        response.setHeader("Pragma", "no-cache");  
        response.setContentType("image/jpeg");
        //生成文字验证码
        String text = producer.createText();
        //生成图片验证码
        BufferedImage image = producer.createImage(text);
        //保存到shiro session
        SecurityUtils.getSubject().getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, text);
        
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        try {
        	out.flush();
        } catch (Exception e) {
        	if(logger.isErrorEnabled()) logger.error("传输验证码异常", e);
		} finally {
        	out.close();
        }

		if(logger.isInfoEnabled()) logger.info("CaptchaController.captcha END");
		
        return null;
	}
	
}
