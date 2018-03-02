package com.liqun.web;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 仿照ui-router，让页面可以感知当前是何种状态，从而给某些页面元素添加诸如class="active" 的属性，来标记当前页面是什么。<p>
 * 具体实现步骤：<br>
 * step1: handler方法用{@link com.liqun.web.ViewState}修饰<br>
 * step2: 配置{@link com.liqun.web.ViewStateHandlingInterceptor}拦截器<br>
 * step3: 视图中使用表达式{@code state.includes('xxxx')}感知当前状态<br>
 * @author Michelangelo
 *
 */
public class ViewStateHandlingInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView mv) throws Exception {
		
		if (handler instanceof HandlerMethod) {
			HandlerMethod hm = (HandlerMethod) handler;
			Method method = hm.getMethod();
			ViewState info = method.getAnnotation(ViewState.class);
			if (null!= info) {
				mv.addObject("state", new ViewStateBean(info.value()));
			}
		}
		
//		System.out.println("StateInfoHandlingInterceptor-"+handler.getClass());
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
