package com.liqun.web;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;


/**
 * 在session创建时放入一个mutex对象，主要用于处理登录时的线程同步问题，防止用户重复登录。
 * @author Michelangelo
 *
 */
public class SessionAccessMutexListener implements HttpSessionListener {
	
	List<String> mutexs;
	
	public SessionAccessMutexListener() {
		mutexs = Arrays.asList(
				WebConsts.SESSION_LOGIN_MUTEX,
				WebConsts.SESSION_ATTRIBUTE_ACCESS_MUTEX
		);
	}

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		
		for (String attrName : mutexs) {
			se.getSession().setAttribute(attrName, new SessionAccessMutex());
		}
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {

		for (String attrName : mutexs) {
			se.getSession().removeAttribute(attrName);
		}
	}

	private static class SessionAccessMutex implements Serializable {
		private static final long serialVersionUID = 1L;
	}

}
	