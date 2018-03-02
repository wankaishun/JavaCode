package com.liqun.web;

import org.apache.shiro.authc.RememberMeAuthenticationToken;

/**
 * 渠道账户的认证token。实质就是channel + authcode + redirect_uri
 * <p>
 * 当渠道用户授权成功，浏览器端重定向至本站authcallback接口时，会携带以上信息。<br>
 * 这些信息将用于向渠道服务器端换取access_token。
 * 
 * @author Michelangelo
 *
 */
public class ChannelAuthcodeToken implements RememberMeAuthenticationToken {

	private static final long serialVersionUID = 1L;

	String channel, code, redirect_uri;

	public ChannelAuthcodeToken(String channel, String authcode, String redirect_uri) {
		super();
		this.channel = channel;
		this.code = authcode;
		this.redirect_uri = redirect_uri;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRedirect_uri() {
		return redirect_uri;
	}

	public void setRedirect_uri(String redirect_uri) {
		this.redirect_uri = redirect_uri;
	}

	@Override
	public Object getPrincipal() {
		return "ch=" + channel + ",code=" + code;
	}

	@Override
	public Object getCredentials() {
		return code;
	}

	@Override
	public boolean isRememberMe() {
		return false;
	}
}
