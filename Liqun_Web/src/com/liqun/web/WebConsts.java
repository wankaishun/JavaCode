package com.liqun.web;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;

public class WebConsts {

	public final static String SESSION_LOGIN_MUTEX = WebConsts.class.getName() + ".SESSION_LOGIN_MUTEX";
	public static final String SESSION_ATTRIBUTE_ACCESS_MUTEX = WebConsts.class.getName() + ".SESSION_ATTRIBUTE_ACCESS_MUTEX";
	
	public final static String LOGGED_IN_TIME = WebConsts.class.getName() + ".LOGGED_IN_TIME";
	public final static String LOGGED_IN_FROM = WebConsts.class.getName() + ".LOGGED_IN_FROM";
	public final static String ORIGINAL_AUTHENTICATION_TOKEN_TYPE = WebConsts.class.getName() + ".ORIGINAL_AUTHENTICATION_TOKEN_TYPE";
	
	/**
	 * 用户登录登出操作，避免出现多线程同时操作造成数据不一致。
	 * @param subject
	 * @return
	 */
	public final static Object getSessionLoginMutex(Subject subject) {
		return subject.getSession().getAttribute(WebConsts.SESSION_LOGIN_MUTEX);
	}

	/**
	 * 线程安全地读写session中的token之类的东西
	 * @param subject
	 * @return
	 */
	public final static Object getSessionAttributeAccessMutex(Subject subject) {
		return subject.getSession().getAttribute(WebConsts.SESSION_ATTRIBUTE_ACCESS_MUTEX);
	}

	/**
	 * 去session里查询是否已经认证。这与{@link Subject#isAuthenticated}总是调取缓存的认证状态是不同的.
	 * @param subject
	 * @return
	 */
	public final static boolean isAuthenticatedInRealTime(Subject subject) {
		return Boolean.TRUE.equals(subject.getSession().getAttribute(DefaultSubjectContext.AUTHENTICATED_SESSION_KEY));
	}
	
	public final static ResponseEntity<?> new400ResponseEntity(String errcode, String message) {
		
		return new400ResponseEntity(errcode, message, null);
	}
	
	public final static ResponseEntity<?> new400ResponseEntity(String errcode, String message, Document extraData) {
		
		Document body = new Document("errcode", errcode)
				.append("message", message);
		
		if (null != extraData)
			body.putAll(extraData);
		
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}

	/**
	 * example: publisher,genre,-publish_time
	 * @param sort
	 * @return
	 */
	public static Document parseSort(String sort) {
		
		Document sortDoc = new Document();
		
		String[] sortkeys = sort.split(",");
		for (String sortkey : sortkeys) {
			
			String keyName = sortkey.startsWith("-")? sortkey.substring(1): sortkey;
			Integer direct = sortkey.startsWith("-")? -1 : 1;
			
			sortDoc.append(keyName, direct);
		}
		
		return sortDoc;
	}

	public static String getSHA256InBase64(String str) {
		MessageDigest messageDigest;
		String encdeStr = "";
		try {
			messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] hash = messageDigest.digest(str.getBytes("UTF-8"));
			encdeStr = Base64.encodeBase64URLSafeString(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return encdeStr;
	}

	/**
	 * @throws ValidationErrorsException
	 * @param errors
	 */
	public static void ensureNoError(Errors errors) {
		if (errors.hasErrors()) {
			throw new ValidationErrorsException(errors);
		}
	}
}
