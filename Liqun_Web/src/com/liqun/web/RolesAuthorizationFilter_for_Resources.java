package com.liqun.web;

import java.io.IOException;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 * 对于未授权的请求直接返回401响应。
 * @author Michelangelo
 *
 */
public class RolesAuthorizationFilter_for_Resources extends RolesAuthorizationFilter {

	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {

		WebUtils.toHttp(response).sendError(HttpServletResponse.SC_UNAUTHORIZED);

		return false;
	}
}
