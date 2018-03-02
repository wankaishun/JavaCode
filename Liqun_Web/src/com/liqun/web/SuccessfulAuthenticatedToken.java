package com.liqun.web;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.RememberMeAuthenticationToken;

public class SuccessfulAuthenticatedToken implements RememberMeAuthenticationToken {

	private static final long serialVersionUID = 1L;
	
	AuthenticationToken token;
	AuthenticationInfo info;
	boolean rememberMe;

	public SuccessfulAuthenticatedToken(AuthenticationToken authcToken, AuthenticationInfo authcInfo) {
		if (authcToken instanceof RememberMeAuthenticationToken) {
			this.rememberMe = ((RememberMeAuthenticationToken)authcToken).isRememberMe();
		}
		this.token = authcToken;
		this.info = authcInfo;
	}

	@Override
	public Object getPrincipal() {
		return token.getPrincipal();
	}

	@Override
	public Object getCredentials() {
		return token.getCredentials();
	}

	public AuthenticationToken getToken() {
		return token;
	}


	public AuthenticationInfo getInfo() {
		return info;
	}

	@Override
	public boolean isRememberMe() {
		return rememberMe;
	}

}
