package com.liqun.util;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ConcurrentAccessException;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Shiro工具类
 * 
 * @author 
 * 
 */
public class ShiroUtils {

	public static Session getSession() {
		return SecurityUtils.getSubject().getSession();
	}

	public static Subject getSubject() {
		return SecurityUtils.getSubject();
	}
	
	public static void setSessionAttribute(Object key, Object value) {
		getSession().setAttribute(key, value);
	}

	public static Object getSessionAttribute(Object key) {
		return getSession().getAttribute(key);
	}

	public static void logout() {
		SecurityUtils.getSubject().logout();
	}
	
	public static String getKaptcha(String key) throws Exception {
		Object kaptcha = getSessionAttribute(key);
		if(kaptcha == null){
			throw new ConcurrentAccessException("验证码失效");
		}
		getSession().removeAttribute(key);
		return kaptcha.toString();
	}

}
