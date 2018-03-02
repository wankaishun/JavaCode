package com.liqun.controller.advice;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.multimap.ArrayListValuedHashMap;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.subject.Subject;
import org.bson.Document;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.liqun.web.BadTokenException;
import com.liqun.web.ValidationErrorsException;

@ControllerAdvice(annotations = ApiControllerAdvisable.class)
public class ApiControllerAdvice {
	
	@ExceptionHandler({ShiroException.class})
	public ResponseEntity<?> handleShiroException(Exception ex) {
		
		
		if (ex instanceof AuthorizationException) {

			AuthorizationException e = (AuthorizationException) ex;
			
			Subject subject = SecurityUtils.getSubject();
			
			boolean authenticated = subject.isAuthenticated();
			
			Document body = new Document("errcode", "authorization_error")
					.append("errmsg", "用户授权失败")
					.append("authenticated", authenticated)
					.append("debug_message", e.getMessage());
//			body.append("exception_class", ex.getClass()).append("exception", ex);

			return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
			
		} else if (ex instanceof AuthenticationException) {

			AuthenticationException e = (AuthenticationException) ex;
			Document body = new Document("errcode", "authentication_error")
					.append("errmsg", "用户认证失败")
					.append("debug_message", e.getMessage());
			
			return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
			
		} else {
			Document body = new Document("errcode", "unknown_shiro_error")
					.append("errmsg", ex.getMessage())
					.append("exception_class", ex.getClass())
					.append("exception", ex);
			
			return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
		}
		
		
		
	}
	
	@ExceptionHandler({ValidationErrorsException.class})
	public ResponseEntity<?> handleValidationError(ValidationErrorsException ex) {
		
		Document body = new Document("errcode", "validation_error").append("errmsg", ex.getMessage());
		
		Errors errors = ex.getErrors();
		List<String> action_error_messages = new ArrayList<>();
		
		List<ObjectError> globalErrors = errors.getGlobalErrors();
		for (ObjectError objectError : globalErrors) {
			String defaultMessage = objectError.getDefaultMessage();
			action_error_messages.add(defaultMessage);
		}
		
		MultiValuedMap<String,String> field_error_messages = new ArrayListValuedHashMap<>();
		for (FieldError fieldError : errors.getFieldErrors()) {
			field_error_messages.put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		
		body.put("action_error_messages", action_error_messages);
		body.put("field_error_messages", field_error_messages.asMap());

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
		
	
	
	@ExceptionHandler({BadTokenException.class})
	public ResponseEntity<?> handleBadTokenException(Exception ex) {
		
		Document body = new Document("errcode", "bad_token").append("errmsg", ex.getMessage());
		
		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
	
	
	
	
}
